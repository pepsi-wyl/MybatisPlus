package com.pepsiwyl.mybatisplus_t.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pepsiwyl.mybatisplus_t.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author by pepsi-wyl
 * @date 2022-03-05 9:46
 */

// 继承BaseMapper
@Mapper
@Repository(value = "userMapper")
public interface UserMapper extends BaseMapper<User> {

}
