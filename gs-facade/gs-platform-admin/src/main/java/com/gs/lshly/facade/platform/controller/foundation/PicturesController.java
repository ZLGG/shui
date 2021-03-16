package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PicturesDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PictureGroupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PicturesQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.middleware.oss.ConstantPropertiesUtil;
import com.gs.lshly.middleware.oss.service.IFileService;
import com.gs.lshly.rpc.api.platadmin.commodity.IPicturesRpc;
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
* @since 2020-10-06
*/
@RestController
@RequestMapping("/platadmin/pictures")
@Api(tags = "文件管理")
public class PicturesController {

    @DubboReference
    private IPicturesRpc picturesRpc;

    @Autowired
    private IFileService fileService;

    @ApiOperation("文件管理列表")
    @GetMapping("")
    public ResponseData<PageData<PicturesVO.ListVO>> list(PicturesQTO.QTO qto) {
        return ResponseData.data(picturesRpc.pageData(qto));
    }

    @ApiOperation("新增文件管理")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PicturesDTO.ETO dto) {
        picturesRpc.addPictures(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("将文件移入回收站管理")
    @PutMapping(value = "/move-recycle")
    public ResponseData<Void> moveRecycle(@RequestBody PicturesDTO.IdListDTO dto) {
        picturesRpc.moveInRecyclePictures(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("将文件从回收站移回原处")
    @PutMapping(value = "/move-old-place")
    public ResponseData<Void> moveToOldPlace(@RequestBody PicturesDTO.IdListDTO dto) {
        picturesRpc.moveToOldPlacePictures(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("将文件移入其他分组中")
    @PutMapping(value = "/moveGroup")
    public ResponseData<Void> moveGroup(@RequestBody List<PicturesDTO.MoveGroupDTO> dto) {
        picturesRpc.moveGroup(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("删除文件")
    @DeleteMapping(value = "deleteBatches")
    public ResponseData<Void> delete(PicturesDTO.IdListDTO dto) {
        picturesRpc.deletePictures(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("查询平台下不同分组显示的图片")
    @GetMapping("/getByGroup")
    public ResponseData<PageData<PicturesVO.ListVO>> getBy(PictureGroupQTO.GroupPictureQTO qto) {
        return ResponseData.data(picturesRpc.pagePicturesBy(qto));
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
