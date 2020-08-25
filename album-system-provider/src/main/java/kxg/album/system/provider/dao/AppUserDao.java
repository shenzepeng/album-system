package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.AppUserMapper;
import kxg.album.system.provider.pojo.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class AppUserDao {
    @Autowired
    private AppUserMapper appUserMapper;

    public Integer addUser(AppUser appUser){
        return appUserMapper.insertSelective(appUser);
    }


    public List<AppUser> findUserByUnionId(String unionId){
        Example example=new Example(AppUser.class);
        example.createCriteria()
                .andEqualTo("unionId",unionId);
        return appUserMapper.selectByExample(example);
    }

    public Integer updateUser(AppUser appUser){
        return appUserMapper.updateByPrimaryKeySelective(appUser);
    }

    public List<AppUser> login(String phoneNumber){
        Example example=new Example(AppUser.class);
        example.createCriteria()
                .andEqualTo("phoneNumber",phoneNumber);
        return appUserMapper.selectByExample(example);
    }

}
