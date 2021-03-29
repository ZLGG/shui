package com.gs.lshly.middleware.oss.service;

import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    /**
     * 本地文件上传至阿里云
     * @param file
     * @return
     */
    PicturesVO.DetailVO upload(MultipartFile file);

    /**
     * 上传网络图片
     * @param url
     * @return
     */
    PicturesVO.DetailVO uploadNet(String url);

    /**
     * 视频上传至阿里云
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

}
