package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.WebUserMapper;
import kxg.album.system.provider.pojo.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class WebUserDao {
    @Autowired
    private WebUserMapper webUserMapper;

    public List<WebUser> login(String phoneNumber,String password){
        Example example=new Example(WebUser.class);
        example.createCriteria()
                .andEqualTo("phoneNumber",phoneNumber);
        return webUserMapper.selectByExample(example);
    }
}
