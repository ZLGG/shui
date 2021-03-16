package com.gs.lshly.facade.platform.controller.foundation;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PicturesSettingDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesSettingVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.middleware.oss.ConstantPropertiesUtil;
import com.gs.lshly.middleware.oss.service.IFileService;
import com.gs.lshly.rpc.api.platadmin.foundation.IPicturesSettingRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-21
*/
@RestController
@RequestMapping("/platadmin/picturesSetting")
@Api(tags = "平台图片设置管理 : Starry")
public class PicturesSettingController {

    @DubboReference
    private IPicturesSettingRpc picturesSettingRpc;

    @Autowired
    private IFileService fileService;


    @ApiOperation("平台图片设置管理列表")
    @GetMapping("")
    public ResponseData<List<PicturesSettingVO.ListVO>> list() {
        return ResponseData.data(picturesSettingRpc.listPicturesSetting());
    }

    @ApiOperation("新增平台图片设置管理单")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody List<PicturesSettingDTO.ETO> dto) {
        picturesSettingRpc.addPicturesSetting(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation(value = "本地文件上传")
    @PostMapping("upload")
    public ResponseData<PicturesVO.DetailVO > upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file,

            @ApiParam(name = "host", value = "文件上传路径", required = false)
            @RequestParam(value = "host", required = false) String host) {

        if(!StringUtils.isEmpty(host)){
            ConstantPropertiesUtil.FILE_HOST = host;
            ConstantPropertiesUtil.LOCAL_MODULE = host;
        }
        PicturesVO.DetailVO detailVO = fileService.upload(file);
        //返回对象
        return ResponseData.data(detailVO);
    }

    @ApiOperation(value = "网络图片上传")
    @PostMapping("uploadNet")
    public ResponseData<PicturesVO.DetailVO > uploadNet(@RequestParam String url) {
        return ResponseData.data(fileService.uploadNet(url));
    }
}
