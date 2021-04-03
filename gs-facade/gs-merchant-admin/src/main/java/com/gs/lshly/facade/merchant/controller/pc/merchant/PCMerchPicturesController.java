package com.gs.lshly.facade.merchant.controller.pc.merchant;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPicturesDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPicturesQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPicturesVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.middleware.oss.ConstantPropertiesUtil;
import com.gs.lshly.middleware.oss.service.IFileService;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchPicturesRpc;
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
* @since 2020-10-22
*/
@RestController
@RequestMapping("/merchadmin/pictures")
@Api(tags = "商家店铺图片管理管理-v1.1.0")
public class PCMerchPicturesController {

    @DubboReference
    private IPCMerchPicturesRpc pcMerchPicturesRpc;

    @Autowired
    private IFileService fileService;

    @ApiOperation("商家店铺图片管理列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchPicturesVO.ListVO>> list(PCMerchPicturesQTO.QTO qto) {
        return ResponseData.data(pcMerchPicturesRpc.pageData(qto));
    }

    @ApiOperation("商家店铺图片管理详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchPicturesVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchPicturesRpc.detailPictures(new PCMerchPicturesDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家店铺图片管理")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchPicturesDTO.ETO dto) {
            pcMerchPicturesRpc.addPictures(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("批量删除商家店铺图片管理")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> delete(PCMerchPicturesDTO.IdListDTO dto) {
        pcMerchPicturesRpc.deletePictures(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("批量将商家店铺图片移入其他分组")
    @PutMapping(value = "/move")
    public ResponseData<Void> move(@RequestBody List<PCMerchPicturesDTO.MoveGroupETO> etos) {
        pcMerchPicturesRpc.moveInGroup(etos);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
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

    @ApiOperation(value = "视频上传-v1.1.0")
    @PostMapping("uploadVideo")
    public ResponseData<String> uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file,

            @ApiParam(name = "host", value = "文件上传路径", required = false)
            @RequestParam(value = "host", required = false) String host) {

        if(!StringUtils.isEmpty(host)){
            ConstantPropertiesUtil.FILE_HOST = host;
            ConstantPropertiesUtil.LOCAL_MODULE = host;
        }
        String url = fileService.uploadVideo(file);
        //返回对象
        return ResponseData.data(url);
    }

}
