package kxg.album.system.provider.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author fuck
 * @date 2018/12/13 20:36
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
