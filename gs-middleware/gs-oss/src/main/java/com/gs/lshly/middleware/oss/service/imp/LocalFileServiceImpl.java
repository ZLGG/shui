package com.gs.lshly.middleware.oss.service.imp;

import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectResult;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.middleware.oss.ConstantPropertiesUtil;
import com.gs.lshly.middleware.oss.service.IFileService;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Random;
import java.util.UUID;


/**
 * @author lxus
 */
public class LocalFileServiceImpl implements IFileService {
    @Override
    public PicturesVO.DetailVO upload(MultipartFile file) {

        String fileDir = ConstantPropertiesUtil.LOCAL_FILE_DIR;
        String bucketName = ConstantPropertiesUtil.LOCAL_BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.LOCAL_FILE_HOST;
        String module = ConstantPropertiesUtil.LOCAL_MODULE;

        String uploadUrl = null;
        PicturesVO.DetailVO detailVO = new PicturesVO.DetailVO();

        try {

            //获取上传文件流
            InputStream inputStream = file.getInputStream();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(file.getBytes());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
            //获取原始图片的尺寸
            BufferedImage sourceImg = ImageIO.read(byteArrayInputStream);
            // 源图宽度
            int imageWidth = sourceImg.getWidth();
            // 源图高度
            int imageHeight = sourceImg.getHeight();

            //构建日期路径：avatar/2019/11/19/文件名
            String fileSavePath = module + "/" + new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf(".")).toLowerCase();
            String newName = fileName + fileType;
            String fileUrl = fileSavePath + "/" + newName;

            if (!fileType.equals(".jpg") && !fileType.equals(".png") && !fileType.equals(".bmp") &&
            !fileType.equals(".gif") && !fileType.equals(".tiff") && !fileType.equals(".jpeg") ){
                throw new BusinessException("只能上传以下图片格式：JPG、PNG、BMP、GIF、TIFF 、JPEG");
            }
            if (file.getSize() >= (1024*20*1024)){
                throw new BusinessException("文件大小最大不能超过1M");
            }
            FileUtil.mkdir(fileDir);
            FileUtil.writeFromStream(inputStream, new File(fileDir +"/" + fileUrl));

            //获取url地址
            uploadUrl = "http://" + bucketName + "." + fileHost + "/" + fileUrl;

            //获取图片的大小
            BigDecimal size = BigDecimal.valueOf(file.getSize()).divide(BigDecimal.valueOf(1024), 2, 4);
            detailVO.setHidden(0);
            detailVO.setImageName(newName);
            detailVO.setImgHeight(imageHeight);
            detailVO.setImgWeight(imageWidth);
            detailVO.setImgType(fileType);
            detailVO.setSize(size + "KB");
            detailVO.setStorageEngine("local");
            detailVO.setImageUrl(uploadUrl);
            detailVO.setOriginalImageName(original);
            System.out.println(detailVO + "图片信息");
        } catch (IOException e) {
            System.out.println(e);
            throw new BusinessException("上传异常");
        }

        return detailVO;
    }

    @Override
    public PicturesVO.DetailVO uploadNet(String url) {
        if (url == null){
            throw new BusinessException("参数不能为空！");
        }
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;

        String uploadUrl = null;
        String fileUrl = null;
        PicturesVO.DetailVO detailVO = new PicturesVO.DetailVO();

        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            InputStream inputStream = getUrlStream(url);
            InputStream inputStreams = getUrlStream(url);
            if (inputStream != null && inputStreams != null){
                //获取图片的大小
                BigDecimal size = BigDecimal.valueOf(inputStream.available())
                        .divide(BigDecimal.valueOf(1024), 2, 4);
                detailVO.setSize(size + "KB");
                //获取图片尺寸
                BufferedImage img = ImageIO.read(inputStreams);
                int imageWidth = img.getWidth();
                int imageHeight = img.getHeight();
                detailVO.setImgHeight(imageHeight);
                detailVO.setImgWeight(imageWidth);

                String imgType = url.substring(url.lastIndexOf(".")).toLowerCase();

                if (!imgType.equals(".jpg") && !imgType.equals(".png") && !imgType.equals(".bmp") &&
                        !imgType.equals(".gif") && !imgType.equals(".tiff") && !imgType.equals(".jpeg")){
                    throw new BusinessException("只能上传以下图片格式：JPG、PNG、BMP、GIF、TIFF 、JPEG");
                }
                if (inputStream.available() >= (1024*1024*1)){
                    throw new BusinessException("文件大小最大不能超过1M");
                }

                String newName = UUID.randomUUID().toString()+imgType;
                 fileUrl = fileHost + "/" +  new DateTime().toString("yyyy/MM/dd") + "/" + newName;
                detailVO.setImageName(newName);
                detailVO.setImgType(imgType);
                //文件上传至阿里云
                ossClient.putObject(bucketName, fileUrl, inputStream);

                // 关闭OSSClient
                ossClient.shutdown();
            }
            //获取url地址
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;
            detailVO.setHidden(0);
            detailVO.setStorageEngine("aliyuncs");
            detailVO.setImageUrl(uploadUrl);

            System.out.println(detailVO + "图片信息");
        } catch (Exception e) {
            System.out.println(e);
            throw new BusinessException("上传异常");
        }
        return detailVO;
    }

    @Override
    public String uploadVideo(MultipartFile file) {
    	
    	String fileDir = ConstantPropertiesUtil.LOCAL_FILE_DIR;
        String bucketName = ConstantPropertiesUtil.LOCAL_BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.LOCAL_FILE_HOST;
        String module = ConstantPropertiesUtil.LOCAL_MODULE;

        String uploadUrl = null;
        PicturesVO.DetailVO detailVO = new PicturesVO.DetailVO();

        try {

            //获取上传文件流
            InputStream inputStream = file.getInputStream();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(file.getBytes());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
            //获取原始图片的尺寸
            BufferedImage sourceImg = ImageIO.read(byteArrayInputStream);
            // 源图宽度
//            int imageWidth = sourceImg.getWidth();
//            // 源图高度
//            int imageHeight = sourceImg.getHeight();

            //构建日期路径：avatar/2019/11/19/文件名
            String fileSavePath = module + "/" + new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf(".")).toLowerCase();
            String newName = fileName + fileType;
            String fileUrl = fileSavePath + "/" + newName;

            /**
            if (!fileType.equals(".jpg") && !fileType.equals(".png") && !fileType.equals(".bmp") &&
            !fileType.equals(".gif") && !fileType.equals(".tiff") && !fileType.equals(".jpeg") ){
                throw new BusinessException("只能上传以下图片格式：JPG、PNG、BMP、GIF、TIFF 、JPEG");
            }
            if (file.getSize() >= (1024*20*1024)){
                throw new BusinessException("文件大小最大不能超过1M");
            }**/
            FileUtil.mkdir(fileDir);
            FileUtil.writeFromStream(inputStream, new File(fileDir +"/" + fileUrl));

            //获取url地址
            uploadUrl = "http://" + bucketName + "." + fileHost + "/" + fileUrl;

            //获取图片的大小
            BigDecimal size = BigDecimal.valueOf(file.getSize()).divide(BigDecimal.valueOf(1024), 2, 4);
            detailVO.setHidden(0);
            detailVO.setImageName(newName);
//            detailVO.setImgHeight(imageHeight);
//            detailVO.setImgWeight(imageWidth);
            detailVO.setImgType(fileType);
            detailVO.setSize(size + "KB");
            detailVO.setStorageEngine("local");
            detailVO.setImageUrl(uploadUrl);
            detailVO.setOriginalImageName(original);
            System.out.println(detailVO + "图片信息");
        } catch (IOException e) {
            System.out.println(e);
            throw new BusinessException("上传异常");
        }

        return uploadUrl;
    	
    	/**
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.LOCAL_BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.LOCAL_FILE_HOST;
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = "Video/"+random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream stream = file.getInputStream();
            String filename = System.currentTimeMillis() + file.getOriginalFilename();
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSSClient client = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            if (!client.doesBucketExist(bucketName)) {
                //创建bucket
                client.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            String fileUrl = fileHost + "/" + name;
            PutObjectResult result = client.putObject(bucketName, fileUrl, stream);
            client.shutdown();
            String uploadUrl = "http://" + bucketName + "." + ConstantPropertiesUtil.END_POINT + "/" + fileUrl;
            return uploadUrl;
        } catch (Exception e) {
            System.out.println(e);
            throw new BusinessException("视频上传失败");
        }
        **/
    }

    private static InputStream getUrlStream(String url) {
        try {
            return new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
