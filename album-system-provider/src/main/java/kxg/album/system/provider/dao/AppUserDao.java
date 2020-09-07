package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.AppUserMapper;
import kxg.album.system.provider.pojo.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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

    public List<AppUser> findUserByIds(List<Long> ids){
        if (CollectionUtils.isEmpty(ids)){
            return new ArrayList();
        }
        Example example=new Example(AppUser.class);
        example.createCriteria()
                .andIn("id",ids);
        return appUserMapper.selectByExample(example);
    }


    public List<AppUser> findUserByPhoneNumber(String phoneNumber){
        Example example=new Example(AppUser.class);
        if (!StringUtils.isEmpty(phoneNumber)){
            example.and().andLike("phoneNumber","%"+phoneNumber+"%");
        }
        return appUserMapper.selectByExample(example);
    }
}
