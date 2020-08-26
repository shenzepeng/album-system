package kxg.album.system.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import kxg.album.system.dto.GoodsDto;
import kxg.album.system.provider.constant.ReturnCode;
import kxg.album.system.provider.dao.*;
import kxg.album.system.provider.exception.KxgException;
import kxg.album.system.provider.pojo.*;
import kxg.album.system.provider.service.GoodsService;
import kxg.album.system.provider.utils.Md5Util;
import kxg.album.system.request.AddGoodsOrderRequest;
import kxg.album.system.request.FindAllGoodsRequest;
import kxg.album.system.response.AddGoodsOrderResponse;
import kxg.album.system.response.FindAllGoodsResponse;
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

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private ContentDao contentDao;
    @Autowired
    private SmallGoodsPictrueDao smallGoodsPictrueDao;
    @Autowired
    private BigGoodsPictureDao bigGoodsPictureDao;
    @Autowired
    private ShopSwitchDao shopSwitchDao;
    @Autowired
    private GoodsOrderDao goodsOrderDao;
    @Override
    public FindAllGoodsResponse findAllGoods(FindAllGoodsRequest request) {
        /**
         *获取不可以展示的
         */
        List<ShopSwitch> allByStatus = shopSwitchDao.findAllByStatus((short) 1);
        List<String> shopIdsList = allByStatus.stream().map(t -> t.getShopId()).collect(Collectors.toList());
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        List<Goods> allGoods = goodsDao.findAllGoods(request.getGoodsName(),shopIdsList);
        PageInfo<Goods> goodsPageInfo=new PageInfo<>(allGoods);
        List<Goods> list = goodsPageInfo.getList();
        //获取内容id
        List<Long> contentList = list.stream().map(t -> t.getContentId()).collect(Collectors.toList());
        List<GoodsContent> goodsContents = contentDao.goodsContents(contentList);
        Map<Long, String> content = goodsContents.stream().collect(Collectors.toMap(GoodsContent::getId, GoodsContent -> GoodsContent.getContent()));
        //商品id
        List<Long> goodsId=list.stream().map(t->t.getId()).collect(Collectors.toList());
        //全部小图
        List<SmallGoodsPicture> smallGoodsPictrueDaoAll = smallGoodsPictrueDao.findAll(goodsId);
        List<BigGoodsPicture> bigGoodsPictures = bigGoodsPictureDao.goodsPictures(goodsId);
        List<GoodsDto> goodsDtos = list.stream().map(new Function<Goods, GoodsDto>() {
            @Override
            public GoodsDto apply(Goods goods) {
                GoodsDto goodsDto=new GoodsDto();
                goodsDto.setCreateTime(goods.getCreateTime());
                goodsDto.setUpdateTime(goods.getUpdateTime());
                goodsDto.setGoodsName(goods.getGoodsName());
                goodsDto.setContent(content.get(goods.getContentId()));
                List<String> smallPicture=new ArrayList<>();
                for (SmallGoodsPicture smallGoodsPicture : smallGoodsPictrueDaoAll) {
                    smallPicture.add( smallGoodsPicture.getImgUrl());
                }
                goodsDto.setSmallPicture(smallPicture);
                List<String> bigPicture=new ArrayList<>();
                for (BigGoodsPicture bigGoodsPicture : bigGoodsPictures) {
                    bigPicture.add(bigGoodsPicture.getImgUrl());
                }
                goodsDto.setBigPicture(bigPicture);
                return goodsDto;
            }
        }).collect(Collectors.toList());
        FindAllGoodsResponse findAllGoodsResponse=new FindAllGoodsResponse();
        findAllGoodsResponse.setGoodsDtos(goodsDtos);
        findAllGoodsResponse.setTotal(goodsPageInfo.getTotal());
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
