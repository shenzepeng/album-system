package kxg.album.system.provider.controller;

import kxg.album.system.provider.common.KxgResponse;
import kxg.album.system.provider.service.GoodsService;
import kxg.album.system.request.AddGoodsOrderRequest;
import kxg.album.system.request.FindAllGoodsRequest;
import kxg.album.system.response.AddGoodsOrderResponse;
import kxg.album.system.response.FindAllGoodsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @PostMapping("find/all/goods")
    public KxgResponse<FindAllGoodsResponse> findAllGoods(@RequestBody FindAllGoodsRequest request){
        return KxgResponse.ok(goodsService.findAllGoods(request));
    }
    @PostMapping("new/order")
    public KxgResponse<AddGoodsOrderResponse> addNewOrder(@RequestBody AddGoodsOrderRequest request){
        return KxgResponse.ok(goodsService.addNewOrder(request));
    }

}
