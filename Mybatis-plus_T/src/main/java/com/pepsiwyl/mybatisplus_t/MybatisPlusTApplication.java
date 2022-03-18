package com.pepsiwyl.mybatisplus_t;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pepsiwyl.mybatisplus_t.mapper.UserMapper;
import com.pepsiwyl.mybatisplus_t.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MybatisPlusTApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MybatisPlusTApplication.class, args);
        UserMapper userMapper = run.getBean("userMapper", UserMapper.class);

//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper
//                .eq("name", "wyl")  //eq 等于
//                .ne("name", "wy")   //ne 不等于
//                .gt("age", 10)      //gt 大于
//                .ge("age", 11)      //ge 大于等于
//                .lt("age", 100)     //lt 小于
//                .le("age", 99)      //le 小于等于
//                .between("version", 0, 1) //BETWEEN 值1 AND 值2
//                .notBetween("version", 3, 4);//NOT BETWEEN 值1 AND 值2
//        userMapper.selectList(wrapper).forEach(System.out::println);

        Long aLong = userMapper.selectCount(null);
        System.out.println(aLong);

        //子查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id","select id from mybatis_plus.user where name = 'wyl'");
        userMapper.selectList(wrapper).forEach(System.out::println);


    }
}
