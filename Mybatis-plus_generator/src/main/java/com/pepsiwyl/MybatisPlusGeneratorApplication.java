package com.pepsiwyl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pepsiwyl.mapper.UserMapper;
import com.pepsiwyl.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MybatisPlusGeneratorApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MybatisPlusGeneratorApplication.class, args);
        UserMapper userMapper = run.getBean("userMapper", UserMapper.class);
        Page<User> userPage = new Page<>(1, 2);
        userMapper.selectPage(userPage,null);
        userPage.getRecords().forEach(System.out::println);
    }

}
