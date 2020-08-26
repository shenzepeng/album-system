package kxg.album.system.provider.service;

import kxg.album.system.provider.pojo.AppUser;

import kxg.album.system.request.AppUserLoginRequest;

import kxg.album.system.request.AppUserUnionLoginRequest;
import kxg.album.system.request.FindAppOrderInfoRequest;
import kxg.album.system.response.AddUserResponse;
import kxg.album.system.response.AppUserLoginResponse;
import kxg.album.system.response.FindAppOrderInfoResponse;

/**
 * 要写注释呀
 */
public interface AppUserService {
    AppUserLoginResponse login(AppUserLoginRequest request);
    FindAppOrderInfoResponse findAppOrderInfo(FindAppOrderInfoRequest request);
}
