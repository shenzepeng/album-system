package kxg.album.system.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import kxg.album.system.dto.GoodsDto;
import kxg.album.system.dto.PictureDto;
import kxg.album.system.provider.constant.ReturnCode;
import kxg.album.system.provider.dao.*;
import kxg.album.system.provider.exception.KxgException;
import kxg.album.system.provider.pojo.*;
import kxg.album.system.provider.service.GoodsService;
import kxg.album.system.provider.utils.JsonUtils;
import kxg.album.system.provider.utils.Md5Util;
import kxg.album.system.request.AddGoodsOrderRequest;
import kxg.album.system.request.FindAllGoodsRequest;
import kxg.album.system.request.GetSupportGoodsRequest;
import kxg.album.system.response.AddGoodsOrderResponse;
import kxg.album.system.response.FindAllGoodsResponse;
import kxg.album.system.response.GetSupportGoodsResponse;
import kxg.fuck.weishangxiangce.service.dto.ImgDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsOrderDao goodsOrderDao;
    @Override
    public FindAllGoodsResponse findAllGoods(FindAllGoodsRequest request) {
        /**
         *获取不可以展示的
         */
        int offset=(request.getPageNumber()-1)*request.getPageSize();
        List<Goods> allGoods = goodsDao.findAllGoodsByContent(offset,request.getPageSize(),request.getGoodsName());
        //商品总数
        int goodsNumber = goodsDao.findAllGoodsNumber(request.getGoodsName());
        //获取内容id
        //全部小图
        List<GoodsDto> goodsDtos = allGoods.stream().map(new Function<Goods, GoodsDto>() {
            @Override
            public GoodsDto apply(Goods goods) {
                GoodsDto goodsDto=new GoodsDto();
                goodsDto.setId(goods.getId());
                goodsDto.setCreateTime(goods.getCreateTime());
                goodsDto.setUpdateTime(goods.getUpdateTime());
                goodsDto.setGoodsName(goods.getGoodsName());
                goodsDto.setContent(goods.getContent());
                ImgDto bigDto = JsonUtils.jsonToPojo(goods.getBigPic(), ImgDto.class);
                PictureDto bigPicture=new PictureDto();
                bigPicture.setImgUrls(bigDto.getImgs());
                goodsDto.setBigPicture(bigPicture);
                ImgDto smallDto = JsonUtils.jsonToPojo(goods.getSmallPic(), ImgDto.class);
                PictureDto smallPicture=new PictureDto();
                smallPicture.setImgUrls(smallDto.getImgs());
                goodsDto.setSmallPic(smallPicture);
                return goodsDto;
            }
        }).collect(Collectors.toList());
        FindAllGoodsResponse findAllGoodsResponse=new FindAllGoodsResponse();
        findAllGoodsResponse.setGoodsDtos(goodsDtos);
        findAllGoodsResponse.setTotal(goodsNumber);
        return findAllGoodsResponse;
    }

    @Override
    public AddGoodsOrderResponse addNewOrder(AddGoodsOrderRequest request) {
        String orderKey = getOrderKey();
        if (StringUtils.isEmpty(orderKey)){
            throw new KxgException(ReturnCode.QUEST_FAIL);
        }
        GoodsOrder goodsOrder=new GoodsOrder();
        BeanUtils.copyProperties(request,goodsOrder);
        goodsOrder.setOrderKey(orderKey);
        goodsOrderDao.addGoodsOrder(goodsOrder);
        AddGoodsOrderResponse response=new AddGoodsOrderResponse();
        response.setOrderKey(orderKey);
        return response;
    }

    @Override
    public GetSupportGoodsResponse getSupport(GetSupportGoodsRequest request) {
        if (request.getGoodsNums()>10){
            request.setGoodsNums(10);
        }else if (request.getGoodsNums()<1){
            request.setGoodsNums(1);
        }
        List<Goods> randGoods = goodsDao.findRandGoods(request.getGoodsNums());
        List<GoodsDto> goodsDto = randGoods.stream()
                .peek(t->log.info("GetSupportGoodsResponse {}",t))
                .map(new Function<Goods, GoodsDto>() {
            @Override
            public GoodsDto apply(Goods goods) {
                GoodsDto goodsDto=new GoodsDto();
                goodsDto.setId(goods.getId());
                goodsDto.setCreateTime(goods.getCreateTime());
                goodsDto.setUpdateTime(goods.getUpdateTime());
                goodsDto.setGoodsName(goods.getGoodsName());
                goodsDto.setContent(goods.getContent());
                ImgDto bigDto = JsonUtils.jsonToPojo(goods.getBigPic(), ImgDto.class);
                PictureDto bigPicture=new PictureDto();
                bigPicture.setImgUrls(bigDto.getImgs());
                goodsDto.setBigPicture(bigPicture);
                ImgDto smallDto = JsonUtils.jsonToPojo(goods.getSmallPic(), ImgDto.class);
                PictureDto smallPicture=new PictureDto();
                smallPicture.setImgUrls(smallDto.getImgs());
                goodsDto.setSmallPic(smallPicture);
                return goodsDto;
            }
        }).collect(Collectors.toList());
        GetSupportGoodsResponse response=new GetSupportGoodsResponse();
        response.setGoodsDtos(goodsDto);
        return response;
    }

    private String getOrderKey(){
        int i=0;
        String key = null;
        while (true){
            String md5String = Md5Util.getMd5String(UUID.randomUUID().toString());
            List<GoodsOrder> orderKey = goodsOrderDao.getOrderKey(md5String);
            if (CollectionUtils.isEmpty(orderKey)){
                key=md5String;
                break;
            }
            if (i>10){
                break;
            }
        }
        return key;
    }
}
