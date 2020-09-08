package kxg.album.system.provider.service;

import kxg.album.system.provider.pojo.AppUser;

import kxg.album.system.request.*;

import kxg.album.system.response.*;
import org.apache.ibatis.annotations.Update;

/**
 * 要写注释呀
 */
public interface AppUserService {
    AppUserLoginResponse login(AppUserLoginRequest request);
    FindAppOrderInfoResponse findAppOrderInfo(FindAppOrderInfoRequest request);
    FindSecondaryAgentResponse findSecondaryAgent(FindSecondaryAgentRequest request);
    FindSecondaryByPhoneNumberResponse findSecondaryByPhoneNumber(FindSecondaryByPhoneNumberRequest request);
    AddSecondaryResponse addSecondary(AddSecondaryRequest request);
    UpdateAryResponse updateAry(UpdateAryRequest request);
    UpdateAppUserInfoResponse updateAppUser(UpdateAppUserInfoRequest request);
}
