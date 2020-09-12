package kxg.album.system.provider.service;

import kxg.album.system.request.AddGoodsOrderRequest;
import kxg.album.system.request.FindAllGoodsRequest;
import kxg.album.system.request.GetSupportGoodsRequest;
import kxg.album.system.response.AddGoodsOrderResponse;
import kxg.album.system.response.FindAllGoodsResponse;
import kxg.album.system.response.GetSupportGoodsResponse;

/**
 * 要写注释呀
 */
public interface GoodsService {
    FindAllGoodsResponse findAllGoods(FindAllGoodsRequest request);
    AddGoodsOrderResponse addNewOrder(AddGoodsOrderRequest request);
    GetSupportGoodsResponse getSupport(GetSupportGoodsRequest request);
}
