package kxg.album.system.provider.controller;

import io.swagger.annotations.Api;
import kxg.album.system.provider.common.KxgResponse;
import kxg.album.system.provider.service.WebUserService;
import kxg.album.system.request.WebUserLoginRequest;
import kxg.album.system.response.WebUserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 要写注释呀
 */
@Api
@RestController
@RequestMapping("web/user")
public class WebUserController {
    @Autowired
    private WebUserService webUserService;
    @PostMapping("login")
    public KxgResponse<WebUserLoginResponse> login( @RequestBody WebUserLoginRequest webUserLoginRequest){
        return KxgResponse.ok(webUserService.login(webUserLoginRequest));
    }
}
