package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.GoodsShopShipMapper;
import kxg.album.system.provider.pojo.GoodsShopShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class GoodsShopShipDao {
    @Autowired
    private GoodsShopShipMapper goodsShopShipMapper;

    public Integer addGoodsShopShip(GoodsShopShip goodsShopShip){
        return goodsShopShipMapper.insertSelective(goodsShopShip);
    }

    public Integer updateGoodsShip(GoodsShopShip goodsShopShip){
        return goodsShopShipMapper.updateByPrimaryKeySelective(goodsShopShip);
    }

    public List<GoodsShopShip> findAllByStatus(Short status){
        Example example=new Example(GoodsShopShip.class);
        example.createCriteria()
                .andEqualTo("status",status);
        return goodsShopShipMapper.selectByExample(example);
    }
}
