package kxg.album.system.provider.mapper;

import kxg.album.system.provider.common.CommonMapper;
import kxg.album.system.provider.pojo.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsMapper extends CommonMapper<Goods> {
    @Select("SELECT id,goods_name,create_time,update_time,content,type_id,goods_index,small_pic,big_pic FROM t_goods ORDER BY RAND() LIMIT #{numbers}")
    @Results(id="userMap", value={
            @Result(column="id", property="id", id=true),
            @Result(column="goods_name", property="goodsName"),
            @Result(column="create_time ", property="createTime"),
            @Result(column="update_time", property="updateTime"),
            @Result(column="content", property="content"),
            @Result(column="type_id", property="typeId"),
            @Result(column="goods_index", property="goodsIndex"),
            @Result(column="small_pic", property="smallPic"),
            @Result(column="big_pic", property="bigPic")
    })
    List<Goods> findRandGoods(@Param("numbers") Integer numbers);
}