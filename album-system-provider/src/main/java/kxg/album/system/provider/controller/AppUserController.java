package kxg.album.system.provider.controller;

import io.swagger.annotations.Api;
import kxg.album.system.provider.common.KxgResponse;
import kxg.album.system.provider.service.AppUserService;
import kxg.album.system.request.AppUserLoginRequest;
import kxg.album.system.request.FindAppOrderInfoRequest;
import kxg.album.system.response.AppUserLoginResponse;
import kxg.album.system.response.FindAppOrderInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商户
 */
@Api
@RestController
@RequestMapping("app/user")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    /**
     * 代理登录
     * @param request
     * @return
     */
    @PostMapping("login")
    public KxgResponse<AppUserLoginResponse> login(@RequestBody AppUserLoginRequest request){
        return KxgResponse.ok(appUserService.login(request));
    }

    /**
     * 获取订单信息
     * @param request
     * @return
     */
    @PostMapping("find")
    public KxgResponse<FindAppOrderInfoResponse> findAppOrderInfo(@RequestBody FindAppOrderInfoRequest request){
        return KxgResponse.ok(appUserService.findAppOrderInfo(request));
    }
}
