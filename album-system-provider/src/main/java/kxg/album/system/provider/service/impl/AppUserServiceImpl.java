package kxg.album.system.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import kxg.album.system.dto.AppOrderDto;
import kxg.album.system.dto.AppUserDto;
import kxg.album.system.dto.GoodsDto;
import kxg.album.system.dto.SmallApplicationUserDto;
import kxg.album.system.provider.common.KxgResponse;
import kxg.album.system.provider.constant.ReturnCode;
import kxg.album.system.provider.dao.*;
import kxg.album.system.provider.exception.KxgException;
import kxg.album.system.provider.pojo.*;
import kxg.album.system.provider.service.AppUserService;

import kxg.album.system.request.*;
import kxg.album.system.response.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 要写注释呀
 */
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserDao appUserDao;
    @Autowired
    private AgencyRelationShipDao agencyRelationShipDao;
    @Autowired
    private SmallGoodsPictrueDao smallGoodsPictrueDao;
    @Autowired
    private BigGoodsPictureDao bigGoodsPictureDao;
    @Autowired
    private GoodsOrderDao goodsOrderDao;
    @Autowired
    private ContentDao contentDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SmallApplicationDao smallApplicationDao;
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

    @Override
    public FindAppOrderInfoResponse findAppOrderInfo(FindAppOrderInfoRequest request) {
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        List<GoodsOrder> orderList = goodsOrderDao.findOrderList(request.getStatus(), request.getOrderKey(), request.getAppUserId());
        PageInfo<GoodsOrder> pageInfo=new PageInfo<>(orderList);
        FindAppOrderInfoResponse response=new FindAppOrderInfoResponse();
        response.setTotal(pageInfo.getTotal());
        List<Long> goodsId = pageInfo.getList().stream().map(t -> t.getGoodsId()).collect(Collectors.toList());
        List<Goods> goodsByIds = goodsDao.findGoodsByIds(goodsId);
        List<Long> goodIds = goodsByIds.stream().map(t -> t.getId()).collect(Collectors.toList());
        //全部商品信息
        Map<Long, Goods> idAndGoods = goodsByIds
                .stream()
                .collect(Collectors.toMap(Goods::getId, goods -> goods));
        Map<Long, String> goodsContent = contentDao.goodsContents(goodIds)
                .stream()
                .collect(Collectors.toMap(GoodsContent::getId, GoodsContent::getContent));
        //全部小图
        List<SmallGoodsPicture> smallGoodsPictrueDaoAll = smallGoodsPictrueDao.findAll(goodsId);
        List<BigGoodsPicture> bigGoodsPictures = bigGoodsPictureDao.goodsPictures(goodsId);
        //小程序信息
        List<Long> addUserId = pageInfo.getList().stream().map(t -> t.getAddUserId()).collect(Collectors.toList());
        Map<Long, SmallApplicationUser> smallApplicationUserMap = smallApplicationDao.findUserByIds(addUserId)
                .stream()
                .collect(Collectors.toMap(SmallApplicationUser::getId, smallApplicationUser -> smallApplicationUser));
        //构建订单信息
        List<AppOrderDto> appOrderDtos = pageInfo.getList().stream().map(new Function<GoodsOrder, AppOrderDto>() {
            @Override
            public AppOrderDto apply(GoodsOrder goodsOrder) {
                AppOrderDto appOrderDto=new AppOrderDto();
                appOrderDto.setGoodsId(goodsOrder.getGoodsId());
                appOrderDto.setOrderKey(goodsOrder.getOrderKey());
                appOrderDto.setStatus(goodsOrder.getStatus());
                List<String> smallPicture=new ArrayList<>();
                for (SmallGoodsPicture smallGoodsPicture : smallGoodsPictrueDaoAll) {
                    smallPicture.add( smallGoodsPicture.getImgUrl());
                }
                appOrderDto.setSmallPicture(smallPicture);
                List<String> bigPicture=new ArrayList<>();
                for (BigGoodsPicture bigGoodsPicture : bigGoodsPictures) {
                    bigPicture.add(bigGoodsPicture.getImgUrl());
                }
                appOrderDto.setBigPicture(bigPicture);
                appOrderDto.setGoodsName(idAndGoods.get(goodsOrder.getGoodsId()).getGoodsName());
                appOrderDto.setContent(goodsContent.get(goodsOrder.getId()));
                SmallApplicationUser smallApplicationUser = smallApplicationUserMap.get(goodsOrder.getAddUserId());
                SmallApplicationUserDto smallApplicationUserDto=new SmallApplicationUserDto();
                BeanUtils.copyProperties(smallApplicationUserDto,smallApplicationUser);
                appOrderDto.setSmallApplicationUserDto(smallApplicationUserDto);
                return appOrderDto;
            }
        }).collect(Collectors.toList());
        response.setDtos(appOrderDtos);
        return response;
    }

    @Override
    public FindSecondaryAgentResponse findSecondaryAgent(FindSecondaryAgentRequest request) {
        List<AppUser> userByUnionId = appUserDao.findUserByUnionId(request.getUnionId());
        if (CollectionUtils.isEmpty(userByUnionId)){
            throw new KxgException(ReturnCode.UNION_ID_IS_NOT_RIGHT);
        }
        AppUser appUser = userByUnionId.get(0);
        List<AgencyRelationShip> allByUserId = agencyRelationShipDao.findAllByUserId(appUser.getId());
        FindSecondaryAgentResponse response=new FindSecondaryAgentResponse();
        List<Long> userIds = allByUserId.stream().map(t -> t.getId()).collect(Collectors.toList());
        List<AppUser> userByIds = appUserDao.findUserByIds(userIds);
        List<AppUserDto> appUserDtoList = userByIds.stream().map(new Function<AppUser, AppUserDto>() {
            @Override
            public AppUserDto apply(AppUser appUser) {
                AppUserDto appUserDto = new AppUserDto();
                BeanUtils.copyProperties(appUser, appUserDto);
                return appUserDto;
            }
        }).collect(Collectors.toList());
        response.setUserDtoList(appUserDtoList);
        return response;
    }

    @Override
    public FindSecondaryByPhoneNumberResponse findSecondaryByPhoneNumber(FindSecondaryByPhoneNumberRequest request) {
        List<AppUserDto> appUserDtoList = appUserDao.findUserByPhoneNumber(request.getPhoneNumber()).stream().map(new Function<AppUser, AppUserDto>() {
            @Override
            public AppUserDto apply(AppUser appUser) {
                AppUserDto appUserDto = new AppUserDto();
                BeanUtils.copyProperties(appUser, appUserDto);
                return appUserDto;
            }
        }).collect(Collectors.toList());
        FindSecondaryByPhoneNumberResponse  findSecondaryByPhoneNumber=new FindSecondaryByPhoneNumberResponse();
        findSecondaryByPhoneNumber.setUserDtoList(appUserDtoList);
        return findSecondaryByPhoneNumber;
    }

    @Override
    public AddSecondaryResponse addSecondary(AddSecondaryRequest request) {
        AgencyRelationShip agencyRelationShip=new AgencyRelationShip();
        BeanUtils.copyProperties(request,agencyRelationShip);
        agencyRelationShip.setType((short)2);
        AddSecondaryResponse response=new AddSecondaryResponse();
        response.setResult(agencyRelationShipDao.add(agencyRelationShip));
        return response;
    }

    @Override
    public UpdateAryResponse updateAry(UpdateAryRequest request) {
        AgencyRelationShip agencyRelationShip=new AgencyRelationShip();
        BeanUtils.copyProperties(request,agencyRelationShip);
        Integer result = agencyRelationShipDao.update(agencyRelationShip);
        UpdateAryResponse response=new UpdateAryResponse();
        response.setResult(result);
        return response;
    }

    private void addAppUser(AppUser appUser){
        appUserDao.addUser(appUser);
    }


}
