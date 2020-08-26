package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.GoodsMapper;
import kxg.album.system.provider.pojo.Goods;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class GoodsDao {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     *
     * @param goodsName 商品名称
     * @param shopIds 不能展示的店铺id
     * @return
     */
    public List<Goods> findAllGoods(String goodsName,List<String> shopIds){
        Example example=new Example(Goods.class);
        if (!StringUtils.isEmpty(goodsName)){
            example.and().andEqualTo("goodsName","%"+goodsName+"%");
        }
        if (!CollectionUtils.isEmpty(shopIds)){
            example.and().andNotIn("shopId",shopIds);
        }
        return goodsMapper.selectByExample(example);
    }
}
