package kxg.album.system.provider.controller;

import io.swagger.annotations.Api;
import kxg.album.system.provider.common.KxgResponse;
import kxg.album.system.provider.service.AppUserService;
import kxg.album.system.request.*;
import kxg.album.system.response.*;
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

    /**
     * 查看自己已添加的
     * 二级代理
     * @param request
     * @return
     */
    @PostMapping("findSecond")
    public KxgResponse<FindSecondaryAgentResponse> findSecondaryAgent(@RequestBody FindSecondaryAgentRequest request){
        return KxgResponse.ok(appUserService.findSecondaryAgent(request));
    }

    /**
     * 通过手机号找到
     * @param request
     * @return
     */
    @PostMapping("findByPhoneNumber")
    public KxgResponse<FindSecondaryByPhoneNumberResponse> findSecondaryByPhoneNumber(@RequestBody FindSecondaryByPhoneNumberRequest request){
        return KxgResponse.ok(appUserService.findSecondaryByPhoneNumber(request));
    }
    /**
     * 添加代理
     */
    @PostMapping("add/second/ary")
    public KxgResponse<AddSecondaryResponse> addSecondaryResponse(@RequestBody AddSecondaryRequest request){
        return KxgResponse.ok(appUserService.addSecondary(request));
    }
    /**
     * 更新appUser
     */
    @PostMapping("put/user/info")
    public KxgResponse<UpdateAppUserInfoResponse> update(@RequestBody UpdateAppUserInfoRequest request){
        return KxgResponse.ok(appUserService.updateAppUser(request));
    }
}
