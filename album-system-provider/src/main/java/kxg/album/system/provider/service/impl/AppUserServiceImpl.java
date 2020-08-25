package kxg.album.system.provider.service.impl;

import kxg.album.system.provider.constant.ReturnCode;
import kxg.album.system.provider.dao.AppUserDao;
import kxg.album.system.provider.exception.KxgException;
import kxg.album.system.provider.pojo.AppUser;
import kxg.album.system.provider.service.AppUserService;

import kxg.album.system.request.AppUserLoginRequest;
import kxg.album.system.request.AppUserUnionLoginRequest;
import kxg.album.system.response.AppUserLoginResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 要写注释呀
 */
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserDao appUserDao;

    @Override
    public AppUserLoginResponse login(AppUserLoginRequest request) {
        AppUserLoginResponse response=new AppUserLoginResponse();
        AppUser appUser=new AppUser();
        if (!StringUtils.isEmpty(request.getUnionId())){
            List<AppUser> userByUnionId = appUserDao.findUserByUnionId(request.getUnionId());
            if (CollectionUtils.isEmpty(userByUnionId)){
                BeanUtils.copyProperties(request,appUser);
                BeanUtils.copyProperties(appUser,response);
                addAppUser(appUser);
                return response;
            }
            BeanUtils.copyProperties(userByUnionId.get(0),response);
            return response;
        }
        if (!StringUtils.isEmpty(request.getPhoneNumber())) {
            List<AppUser> login = appUserDao.login(request.getPhoneNumber());
            if (CollectionUtils.isEmpty(login)){
                BeanUtils.copyProperties(request,appUser);
                BeanUtils.copyProperties(appUser,response);
                addAppUser(appUser);
                return response;
            }
            if (StringUtils.isEmpty(request.getPassword())||!request.getPassword().equals(login.get(0).getPassword())){
                throw new KxgException(ReturnCode.PLEASE_CHECK_PHONE_NUMBER_AND_PASSWORD);
            }
            BeanUtils.copyProperties(login.get(0),response);
            return response;

        }
        throw new KxgException(ReturnCode.PLEASE_CHECK_PHONE_NUMBER_AND_PASSWORD);
    }

    private void addAppUser(AppUser appUser){
        appUserDao.addUser(appUser);
    }

}
