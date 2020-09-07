package kxg.album.system.provider.service.impl;

import kxg.album.system.dto.WXSessionModelDto;
import kxg.album.system.provider.constant.ReturnCode;
import kxg.album.system.provider.dao.SmallApplicationDao;
import kxg.album.system.provider.dao.UserShopRelationDao;
import kxg.album.system.provider.exception.KxgException;
import kxg.album.system.provider.pojo.SmallApplicationUser;
import kxg.album.system.provider.pojo.UserShopRelation;
import kxg.album.system.provider.service.SmallApplicationService;
import kxg.album.system.provider.utils.HttpClientUtil;
import kxg.album.system.provider.utils.JsonUtils;
import kxg.album.system.request.AddSAUserRequest;
import kxg.album.system.request.SAOpenIdRequest;
import kxg.album.system.response.AddUserResponse;
import kxg.album.system.response.IntegerResultResponse;
import kxg.album.system.response.SAOpenIdResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 要写注释呀
 */

@Service
public class SmallApplicationServiceImpl implements SmallApplicationService {
    @Value("${small.application.appId:wxe5e476c440a56df3}")
    private String appId;
    @Value("${small.application.secret:c21f05d89c84af53d1fc8675467b1bf7}")
    private String secret;
    @Value("${small.application.grantType:authorization_code}")
    private String grantType;

    @Autowired
    private SmallApplicationDao smallApplicationDao;
    @Autowired
    private UserShopRelationDao userShopRelationDao;
    /**
     * 获取openId
     * @param request
     * @return
     */
    @Override
    public SAOpenIdResponse getOpenId(SAOpenIdRequest request) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap();
        //小程序id
        param.put("appid", appId);
        //微信秘钥
        param.put("secret", secret);
        param.put("js_code", request.getCode());
        //写死
        param.put("grant_type", grantType);
        String wxResult = HttpClientUtil.doGet(url, param);
        WXSessionModelDto model = JsonUtils.jsonToPojo(wxResult, WXSessionModelDto.class);
        SAOpenIdResponse saOpenIdResponse=new SAOpenIdResponse();
        BeanUtils.copyProperties(model,saOpenIdResponse);
        return saOpenIdResponse;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AddUserResponse addOrUpdateUser(AddSAUserRequest addSAUserRequest) {
        if (StringUtils.isEmpty(addSAUserRequest.getOpenId())){
            throw new KxgException(ReturnCode.OPEN_ID_CAN_NOT_BE_NULL);
        }
        if (null==addSAUserRequest.getAppUserId()){
            throw new KxgException(ReturnCode.APP_USER_ID_CAN_NOT_BE_NULL);
        }
        AddUserResponse addUserResponse=new AddUserResponse();
        List<SmallApplicationUser> userByOpenId = smallApplicationDao.findUserByOpenId(addSAUserRequest.getOpenId());
        if (!CollectionUtils.isEmpty(userByOpenId)){
            if (StringUtils.isEmpty(addSAUserRequest.getPhoneNumber())||
                    addSAUserRequest.getPhoneNumber().equals(userByOpenId.get(0).getPhoneNumber())){
                BeanUtils.copyProperties(userByOpenId.get(0),addUserResponse);
                return addUserResponse;
            }
            SmallApplicationUser smallApplicationUser=new SmallApplicationUser();
            BeanUtils.copyProperties(userByOpenId.get(0),smallApplicationUser);
            smallApplicationDao.updateUser(smallApplicationUser);
            BeanUtils.copyProperties(smallApplicationUser,addUserResponse);
            return addUserResponse;
        }
        SmallApplicationUser smallApplicationUser=new SmallApplicationUser();
        BeanUtils.copyProperties(userByOpenId.get(0),smallApplicationUser);
        smallApplicationDao.addUser(smallApplicationUser);
        UserShopRelation userShopRelation=new UserShopRelation();
        userShopRelation.setAppUserId(addSAUserRequest.getAppUserId());
        userShopRelation.setUserId(smallApplicationUser.getId());
        userShopRelationDao.addRelation(userShopRelation);
        BeanUtils.copyProperties(smallApplicationUser,addUserResponse);
        return addUserResponse;
    }
}
