package com.gs.lshly.facade.bbb.controller.h5.foundation;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPicturesVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.middleware.oss.ConstantPropertiesUtil;
import com.gs.lshly.middleware.oss.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-21
*/
@RestController
@RequestMapping("/bbb/h5/userCenter/pictures")
@Api(tags = "图片上传管理 : Starry")
public class BbbH5PicturesSettingController {

    @Autowired
    private IFileService fileService;

    @ApiOperation(value = "本地文件上传")
    @PostMapping("upload")
    public ResponseData<BbcPicturesVO.DetailVO > upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file,

            @ApiParam(name = "host", value = "文件上传路径", required = false)
            @RequestParam(value = "host", required = false) String host) {

        if(!StringUtils.isEmpty(host)){
            ConstantPropertiesUtil.FILE_HOST = host;
            ConstantPropertiesUtil.LOCAL_MODULE = host;
        }
        PicturesVO.DetailVO detailVO = fileService.upload(file);
        BbcPicturesVO.DetailVO bbcDetailVO = new BbcPicturesVO.DetailVO();
        BeanUtils.copyProperties(detailVO,bbcDetailVO);
        //返回对象
        return ResponseData.data(bbcDetailVO);
    }

    @ApiOperation(value = "网络图片上传")
    @PostMapping("uploadNet")
    public ResponseData<BbcPicturesVO.DetailVO > uploadNet(@RequestParam String url) {
        PicturesVO.DetailVO detailVO =  fileService.uploadNet(url);
        BbcPicturesVO.DetailVO bbcDetailVO = new BbcPicturesVO.DetailVO();
        BeanUtils.copyProperties(detailVO,bbcDetailVO);
        return ResponseData.data(bbcDetailVO);
    }
}
