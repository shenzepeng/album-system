package kxg.album.system.provider.dao;

import io.swagger.models.auth.In;
import kxg.album.system.provider.mapper.SmallApplicationUserMapper;
import kxg.album.system.provider.pojo.SmallApplicationUser;
import kxg.album.system.request.AddSAUserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class SmallApplicationDao {
    @Autowired
    private SmallApplicationUserMapper smallApplicationUserMapper;

    public List<SmallApplicationUser> findUserByOpenId(String openId){
        Example example=new Example(SmallApplicationUser.class);
        example.createCriteria()
                .andEqualTo("openId",openId);
        return smallApplicationUserMapper.selectByExample(example);
    }

    public Integer updateUser(SmallApplicationUser smallApplicationUser){
        return smallApplicationUserMapper.updateByPrimaryKeySelective(smallApplicationUser);
    }

    public Integer addUser(SmallApplicationUser smallApplicationUser){
        return smallApplicationUserMapper.insertSelective(smallApplicationUser);
    }
}
