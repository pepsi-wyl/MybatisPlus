package com.pepsiwyl.service.impl;

import com.pepsiwyl.pojo.User;
import com.pepsiwyl.mapper.UserMapper;
import com.pepsiwyl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pepsi-wyl
 * @since 2022-03-05
 */
@Service(value = "userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
