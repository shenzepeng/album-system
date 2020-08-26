package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.GoodsContentMapper;
import kxg.album.system.provider.pojo.GoodsContent;
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
public class ContentDao {
    @Autowired
    private GoodsContentMapper goodsContentMapper;

    public List<GoodsContent> goodsContents(List<Long> ids){
        if (CollectionUtils.isEmpty(ids)){
            return new ArrayList<>();
        }
        Example example=new Example(GoodsContent.class);
        example.createCriteria()
                .andIn("id",ids);
        return goodsContentMapper.selectByExample(example);
    }
    public Integer addContentList(List<GoodsContent> goodsContents){
        return goodsContentMapper.insertList(goodsContents);
    }
}
