package kxg.album.system.provider.controller;

import io.swagger.annotations.Api;
import kxg.album.system.provider.common.KxgResponse;
import kxg.album.system.provider.service.SmallApplicationService;
import kxg.album.system.request.AddSAUserRequest;
import kxg.album.system.request.SAOpenIdRequest;
import kxg.album.system.response.AddUserResponse;
import kxg.album.system.response.SAOpenIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
@RequestMapping("small/app")
public class SmallAppController {
    @Autowired
    private SmallApplicationService smallApplicationService;
    @PostMapping("get/openid")
    public KxgResponse<SAOpenIdResponse> getOpenId(@RequestBody SAOpenIdRequest request){
        return KxgResponse.ok(smallApplicationService.getOpenId(request));
    }
    @PostMapping("add")
    public KxgResponse<AddUserResponse> addOrUpdateUser(@RequestBody AddSAUserRequest addSAUserRequest){
        return KxgResponse.ok(smallApplicationService.addOrUpdateUser(addSAUserRequest));
    }
}
