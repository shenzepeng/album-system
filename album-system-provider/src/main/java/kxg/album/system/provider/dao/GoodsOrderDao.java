package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.GoodsOrderMapper;
import kxg.album.system.provider.pojo.GoodsOrder;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
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


    public List<GoodsOrder> findOrderList(Short status,String oderKey,Long appUserId,Integer offset,Integer pageSize){
        Example example=new Example(GoodsOrder.class);
        RowBounds rowBounds=new RowBounds(offset,pageSize);
        if (null!=status){
            example.and().andEqualTo("status",status);
        }
        if (!StringUtils.isEmpty(oderKey)){
            example.and().andEqualTo("orderKey",oderKey);
        }
        if (null!=appUserId){
            example.and().andEqualTo("appUserId",appUserId);
        }
        return goodsOrderMapper.selectByExampleAndRowBounds(example,rowBounds);
    }

    public int findOrderListNumbers(Short status,String oderKey,Long appUserId){
        Example example=new Example(GoodsOrder.class);
        if (null!=status){
            example.and().andEqualTo("status",status);
        }
        if (!StringUtils.isEmpty(oderKey)){
            example.and().andEqualTo("orderKey",oderKey);
        }
        if (null!=appUserId){
            example.and().andEqualTo("appUserId",appUserId);
        }
        return goodsOrderMapper.selectCountByExample(example);
    }
}
