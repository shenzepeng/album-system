package kxg.album.system.provider.dao;

import io.swagger.models.auth.In;
import kxg.album.system.provider.mapper.SmallGoodsPictureMapper;
import kxg.album.system.provider.pojo.SmallGoodsPicture;
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
public class SmallGoodsPictrueDao {
    @Autowired
    private SmallGoodsPictureMapper smallGoodsPictureMapper;

    public List<SmallGoodsPicture> findAll(List<Long> goodsId){
        if (CollectionUtils.isEmpty(goodsId)){
            return new ArrayList<>();
        }
        Example example=new Example(SmallGoodsPicture.class);
        example.createCriteria()
                .andIn("goodsId",goodsId);
        return smallGoodsPictureMapper.selectByExample(example);
    }

    public Integer addSmallImgs(List<SmallGoodsPicture> goodsPictures){
        return smallGoodsPictureMapper.insertList(goodsPictures);
    }
}
