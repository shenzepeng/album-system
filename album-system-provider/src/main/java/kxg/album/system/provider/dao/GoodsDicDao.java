package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.GoodsDicMapper;
import kxg.album.system.provider.mapper.GoodsMapper;
import kxg.album.system.provider.pojo.GoodsDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class GoodsDicDao {
    @Autowired
    private GoodsDicMapper goodsDicMapper;

    public List<GoodsDic> findAll(){
        return goodsDicMapper.selectAll();
    }

}
