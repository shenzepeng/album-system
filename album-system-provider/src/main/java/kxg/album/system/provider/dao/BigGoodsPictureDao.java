package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.BigGoodsPictureMapper;
import kxg.album.system.provider.pojo.BigGoodsPicture;
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
public class BigGoodsPictureDao {
    @Autowired
    private BigGoodsPictureMapper bigGoodsPictureMapper;

    public List<BigGoodsPicture> goodsPictures(List<Long> goodsId){
        if (CollectionUtils.isEmpty(goodsId)){
            return new ArrayList<>();
        }
        Example example=new Example(BigGoodsPicture.class);
        example.createCriteria()
                .andIn("goodsId",goodsId);
        return bigGoodsPictureMapper.selectByExample(example);
    }

    public Integer addBigPicture(List<BigGoodsPicture> pictureImgUrl){
        return bigGoodsPictureMapper.insertList(pictureImgUrl);
    }
}
