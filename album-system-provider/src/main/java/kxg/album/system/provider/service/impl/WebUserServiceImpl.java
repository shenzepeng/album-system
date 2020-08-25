package kxg.album.system.provider.service.impl;

import kxg.album.system.provider.constant.ReturnCode;
import kxg.album.system.provider.dao.WebUserDao;
import kxg.album.system.provider.exception.KxgException;
import kxg.album.system.provider.pojo.WebUser;
import kxg.album.system.provider.service.WebUserService;
import kxg.album.system.request.WebUserLoginRequest;
import kxg.album.system.response.WebUserLoginResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * 要写注释呀
 */
@Service
public class WebUserServiceImpl implements WebUserService {
    @Autowired
    private WebUserDao webUserDao;

    /**
     * 管理员登录逻辑
     * @param webUserLoginRequest
     * @return
     */
    @Override
    public WebUserLoginResponse login(WebUserLoginRequest webUserLoginRequest) {
        List<WebUser> login = webUserDao.login(webUserLoginRequest.getPhoneNumber(), webUserLoginRequest.getPassword());
        if (!CollectionUtils.isEmpty(login)
                &&login.get(0).getPassword().equals(webUserLoginRequest.getPassword())) {
            WebUserLoginResponse webUserLoginResponse = new WebUserLoginResponse();
            BeanUtils.copyProperties(webUserLoginResponse, login.get(0));
            webUserLoginResponse.setToken(UUID.randomUUID().toString());
            return webUserLoginResponse;
        }
        throw new KxgException(ReturnCode.WEB_USER_PHONE_NUMBER_OR_PASSWORD_IS_NOT_RIGHT);
    }
}
