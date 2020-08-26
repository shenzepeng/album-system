package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.ShopSwitchMapper;
import kxg.album.system.provider.pojo.ShopSwitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class ShopSwitchDao {
    @Autowired
    private ShopSwitchMapper shopSwitchMapper;

    public Integer addGoodsShopShip(ShopSwitch goodsShopShip){
        return shopSwitchMapper.insertSelective(goodsShopShip);
    }

    public Integer updateGoodsShip(ShopSwitch goodsShopShip){
        return shopSwitchMapper.updateByPrimaryKeySelective(goodsShopShip);
    }

    public List<ShopSwitch> findAllByStatus(Short status){
        Example example=new Example(ShopSwitch.class);
        example.createCriteria()
                .andEqualTo("status",status);
        return shopSwitchMapper.selectByExample(example);
    }
}
