package kxg.album.system.provider.service;

import kxg.album.system.request.AddSAUserRequest;
import kxg.album.system.request.SAOpenIdRequest;
import kxg.album.system.response.AddUserResponse;
import kxg.album.system.response.IntegerResultResponse;
import kxg.album.system.response.SAOpenIdResponse;

/**
 * 小程序业务逻辑
 */
public interface SmallApplicationService {
    SAOpenIdResponse getOpenId(SAOpenIdRequest request);
    AddUserResponse addOrUpdateUser(AddSAUserRequest addSAUserRequest);
}
