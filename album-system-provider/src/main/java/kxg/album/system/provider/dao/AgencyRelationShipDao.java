package kxg.album.system.provider.dao;

import kxg.album.system.provider.mapper.AgencyRelationShipMapper;
import kxg.album.system.provider.pojo.AgencyRelationShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class AgencyRelationShipDao {
    @Autowired
    private AgencyRelationShipMapper agencyRelationShipMapper;

    public List<AgencyRelationShip> findAllByUserId(Long userId){
        Example example=new Example(AgencyRelationShip.class);
        example.createCriteria()
                .andEqualTo("userId",userId);
        return agencyRelationShipMapper.selectByExample(example);
    }

    public Integer add(AgencyRelationShip agencyRelationShip){
        return agencyRelationShipMapper.insertSelective(agencyRelationShip);
    }

    public Integer update(AgencyRelationShip agencyRelationShip){
        return agencyRelationShipMapper.updateByPrimaryKeySelective(agencyRelationShip);
    }
}
