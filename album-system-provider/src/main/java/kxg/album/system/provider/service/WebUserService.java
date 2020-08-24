package kxg.album.system.provider.service;

import kxg.album.system.request.WebUserLoginRequest;
import kxg.album.system.response.WebUserLoginResponse;

/**
 * 要写注释呀
 */
public interface WebUserService {
    WebUserLoginResponse login(WebUserLoginRequest webUserLoginRequest);
}
