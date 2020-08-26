package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.GoodsOrderMapper;
import kxg.album.system.provider.pojo.GoodsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class GoodsOrderDao {
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    public Integer addGoodsOrder(GoodsOrder goodsOrder){
        return goodsOrderMapper.insertSelective(goodsOrder);
    }

    public List<GoodsOrder> getOrderKey(String orderKey){
        Example example=new Example(GoodsOrder.class);
        example.createCriteria()
                .andEqualTo("orderKey",orderKey);
        return goodsOrderMapper.selectByExample(example);
    }
}
