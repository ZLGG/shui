package com.gs.lshly.facade.platform.controller.foundation.rbac;


import com.gs.lshly.common.enums.OAuth2UserTypeEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.OAuth2DTO;
import com.gs.lshly.common.struct.common.OAuth2VO;
import com.gs.lshly.rpc.api.platadmin.foundation.IOAuth2Rpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 * <p>
 * 1,建表oauth2_client表:client,接口权限.
 * 2,建表oauth2_code表:分配为user分配code,code使用过后立即删除.
 * 3,建表oauth2_token表:为user生成访问token,refresh_token.
 *
 * @author lxus
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/oauth/platadmin")
@Api(tags = "RBAC账号OAuth2认证")
public class SysUserOAuth2Controller {

    @DubboReference
    IOAuth2Rpc oAuth2SysUserRpc;

    @ApiOperation("根据已登录的用户为client分配code")
    @GetMapping("/code/{clientId}")
    public ResponseData<String> allocateCode(@PathVariable String clientId) {
        OAuth2DTO.AllocateCodeDTO dto = new OAuth2DTO.AllocateCodeDTO();
        dto.setClientId(clientId);
        String code = oAuth2SysUserRpc.allocateCode(OAuth2UserTypeEnum.平台账号, dto);
        return ResponseData.data(code);
    }

    @ApiOperation("校验code并返回token")
    @GetMapping("/token/{code}")
    public ResponseData<OAuth2VO.TokenVO> generationToken(@PathVariable String code) {
        OAuth2DTO.GenerationTokenDTO dto = new OAuth2DTO.GenerationTokenDTO();
        dto.setCode(code);
        OAuth2VO.TokenVO token = oAuth2SysUserRpc.generationToken(dto);
        return ResponseData.data(token);
    }

    @ApiOperation("通过token获取用户信息")
    @PostMapping("/userInfo")
    public ResponseData<OAuth2VO.SysUserVO> userInfo(String token) {
        OAuth2DTO.UserInfoDTO dto = new OAuth2DTO.UserInfoDTO();
        dto.setToken(token);
        OAuth2VO.SysUserVO userVO = oAuth2SysUserRpc.sysUserInfo(dto);
        return ResponseData.data(userVO);
    }

    @ApiOperation("通过refresh_token刷新访问token.")
    @GetMapping("/refresh_token/{refreshToken}")
    public ResponseData<OAuth2VO.TokenVO> refreshToken(@PathVariable String refreshToken) {
        OAuth2DTO.RefreshTokenDTO dto = new OAuth2DTO.RefreshTokenDTO();
        dto.setRefreshToken(refreshToken);
        OAuth2VO.TokenVO token = oAuth2SysUserRpc.refreshToken(dto);
        return ResponseData.data(token);
    }

}
