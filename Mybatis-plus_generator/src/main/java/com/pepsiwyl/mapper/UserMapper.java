package com.pepsiwyl.mapper;

import com.pepsiwyl.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pepsi-wyl
 * @since 2022-03-05
 */
@Mapper
@Repository(value = "userMapper")
public interface UserMapper extends BaseMapper<User> {

}
