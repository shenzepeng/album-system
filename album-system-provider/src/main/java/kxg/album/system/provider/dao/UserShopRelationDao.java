package kxg.album.system.provider.dao;


import kxg.album.system.provider.mapper.UserShopRelationMapper;
import kxg.album.system.provider.pojo.UserShopRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 要写注释呀
 */
@Repository
public class UserShopRelationDao {
    @Autowired
    private UserShopRelationMapper userShopRelationMapper;

    public Integer addRelation(UserShopRelation userShopRelation){
        return userShopRelationMapper.insertSelective(userShopRelation);
    }
}
