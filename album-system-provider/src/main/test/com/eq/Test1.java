package com.eq;
import kxg.album.system.provider.DubboProviderBootstrap;
import kxg.fuck.weishangxiangce.service.request.FindGoodsRequest;
import kxg.fuck.weishangxiangce.service.respopnse.FindGoodsResponse;
import kxg.fuck.weishangxiangce.service.service.WSXCGoodsService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 要写注释呀
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboProviderBootstrap.class)
public class Test1 {
    @Reference
    private WSXCGoodsService goodsService;
    @Test
    public void test1(){
        FindGoodsRequest request=new FindGoodsRequest();
        FindGoodsResponse goods = goodsService.findGoods(request);
        System.out.println(goods);
    }
}
