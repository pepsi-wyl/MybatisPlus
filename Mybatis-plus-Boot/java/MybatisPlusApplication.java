
import com.wyl.mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class MybatisPlusApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MybatisPlusApplication.class, args);

        UserMapper userMapper = run.getBean("userMapper", UserMapper.class);

        /**
         * 插入User  主键生成策略
         */
//        User userAdd = new User();
//        userAdd.setName("zhazha");
//        userAdd.setAge(20);
//        userAdd.setEmail("2322535585@qq.com");
//        userMapper.insert(userAdd);
//        //遍历所有user
//        userMapper.selectList(null).forEach((v) -> System.out.println(v));

        /**
         * 修改User  动态Sql拼接
         */
//        User userUpdate = new User();
//        userUpdate.setId(1465150354504151041L);
//        userUpdate.setName("wyl");
//        userUpdate.setEmail("2322535585@qq.com");
//        userMapper.updateById(userUpdate);
//        //遍历所有user
//        userMapper.selectList(null).forEach((v) -> System.out.println(v));

        /**
         * 乐观锁
         */
//        //乐观锁测试 单线程
//        User user = userMapper.selectById(1465150354504151041L);
//        user.setEmail("wyl");
//        userMapper.updateById(user);
//        //乐观锁测试 多线程
//        User user1 = userMapper.selectById(1465150354504151041L);
//        user1.setEmail("2322535585@qq.com");
//        user1.setName("wxl");
//        User user2 = userMapper.selectById(1465150354504151041L);
//        user2.setAge(30);
//        userMapper.updateById(user2);
//        userMapper.updateById(user1);


        /**
         * 查询
         */
//        //根据 ID 查询
//        System.out.println(userMapper.selectById(1465150354504151041L));
//        //根据ID 批量查询
//        userMapper.selectBatchIds(Arrays.asList(1465150354504151041L, 1465150663112556546L)).forEach(System.out::println);
//        //根据 columnMap 条件
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "zhazha");
//        map.put("age", 30);
//        userMapper.selectByMap(map).forEach(System.out::println);
//
//        //分页插件  当前页 页面大小
//        Page<User> page = new Page<>(1, 2);
//        userMapper.selectPage(page, null);
//        page.getRecords().forEach(System.out::println);


        /**
         * 删除
         * 逻辑删除  deleted 属性改为1    0为默认
         */
        //根据 ID 删除
//        userMapper.deleteById(1465150354504151041L);
//        //根据ID 批量查询
//        userMapper.deleteBatchIds(Arrays.asList(1465150354504151041L, 1465150663112556546L));
//        //根据 columnMap 条件
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "zhazha");
//        map.put("age", 30);
//        userMapper.deleteByMap(map);


        /**
         *条件构造器
         */
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper
//                .eq("name", "wyl")  //eq 等于
//                .ne("name", "wy")   //ne 不等于
//                .gt("age", 10)      //gt 大于
//                .ge("age", 11)      //ge 大于等于
//                .lt("age", 100)     //lt 小于
//                .le("age", 99)      //le 小于等于
//                .between("version", 0, 1) //BETWEEN 值1 AND 值2
//                .notBetween("version", 3, 4)//NOT BETWEEN 值1 AND 值2
//        userMapper.selectList(wrapper).forEach(System.out::println);


        //模糊查询
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper
//                .like("email", "2322535585")//LIKE '%值%'
//                .notLike("email", "163.com");//NOT LIKE '%值%'
//        userMapper.selectList(wrapper).forEach(System.out::println);

        //排序
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.orderByAsc("age");
//        userMapper.selectList(wrapper).forEach(System.out::println);

        //子查询
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.inSql("id","select id from mybatis_plus.user where name = 'wyl'");
//        userMapper.selectList(wrapper).forEach(System.out::println);




    }
}
