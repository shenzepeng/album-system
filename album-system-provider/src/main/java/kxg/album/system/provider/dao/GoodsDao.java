package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.GoodsMapper;
import kxg.album.system.provider.pojo.Goods;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
     * @param searchContent \
     * @param
     * @return
     */
    public List<Goods> findAllGoodsByContent(Integer offset, Integer pageNumber, String searchContent){
        Example example=new Example(Goods.class);
        if (!StringUtils.isEmpty(searchContent)){
            example.and().andLike("goodsName","%"+searchContent+"%");
            example.or().andLike("content","%"+searchContent+"%");
        }
        RowBounds rowBounds=new RowBounds(offset,pageNumber);
        return goodsMapper.selectByExampleAndRowBounds(example,rowBounds);
    }
    public int findAllGoodsNumber(String searchContent){
        Example example=new Example(Goods.class);
        if (!StringUtils.isEmpty(searchContent)){
            example.and().andLike("goodsName","%"+searchContent+"%");
            example.or().andLike("content","%"+searchContent+"%");
        }
        return goodsMapper.selectCountByExample(example);
    }

    public List<Goods> findGoodsByIds(List<Long> ids){
        if (CollectionUtils.isEmpty(ids)){
            return new ArrayList();
        }
        Example example=new Example(Goods.class);
        example.createCriteria()
                .andIn("id",ids);
        return goodsMapper.selectByExample(example);
    }

    public List<Goods> findGoodsByGoodsIndex(List<String> list){
        if (CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        Example example=new Example(Goods.class);
        example.createCriteria()
                .andIn("goodsIndex",list);
        return goodsMapper.selectByExample(example);
    }

    public Integer addGoodsList(List<Goods> goods){
        return goodsMapper.insertList(goods);
    }

    public List<Goods> findRandGoods(Integer numbers){
        return goodsMapper.findRandGoods(numbers);
    }
}
