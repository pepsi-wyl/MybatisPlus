# 简介
[Mybatis-Plus](https://baomidou.com/pages/24112f/#%E7%89%B9%E6%80%A7)（简称 MP）是一个 MyBatis的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

- 我们的愿景是成为 MyBatis 最好的搭档，就像 [魂斗罗](https://baomidou.com/img/contra.jpg) 中的 1P、2P，基友搭配，效率翻倍。
- 建议安装 **MybatisX **插件 

![relationship-with-mybatis.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646442081344-a6760773-0e33-43cf-b176-61135a3ecc61.png#clientId=u2fca9262-f34d-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=ud89a265b&margin=%5Bobject%20Object%5D&name=relationship-with-mybatis.png&originHeight=361&originWidth=1024&originalType=binary&ratio=1&rotation=0&showTitle=false&size=30807&status=done&style=none&taskId=u77b37325-9717-46c7-bd42-f5ac21017a5&title=)
## 特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作
## 支持数据库
任何能使用 MyBatis 进行 CRUD, 并且支持标准 SQL 的数据库，具体支持情况如下，如果不在下列表查看分页部分教程 PR 您的支持。

- MySQL，Oracle，DB2，H2，HSQL，SQLite，PostgreSQL，SQLServer，Phoenix，Gauss ，ClickHouse，Sybase，OceanBase，Firebird，Cubrid，Goldilocks，csiidb
- 达梦数据库，虚谷数据库，人大金仓数据库，南大通用(华库)数据库，南大通用数据库，神通数据库，瀚高数据库
## 框架结构
![mybatis-plus-framework.jpg](https://cdn.nlark.com/yuque/0/2022/jpeg/23219042/1646442730738-55a034ba-acc0-4927-bcdc-ddf5934962e7.jpeg#clientId=u2fca9262-f34d-4&crop=0&crop=0&crop=1&crop=1&from=drop&id=u238405dc&margin=%5Bobject%20Object%5D&name=mybatis-plus-framework.jpg&originHeight=852&originWidth=1330&originalType=binary&ratio=1&rotation=0&showTitle=false&size=71663&status=done&style=none&taskId=u7c1b11e4-aca0-4ab2-ba9d-e15a6214b03&title=)
# 入门
## 创建数据库和表插入数据
```sql
create database mybatisplus;

use mybatisplus;

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

INSERT INTO user (id, name, age, email) VALUES
                                            (1, 'Jone', 18, 'test1@baomidou.com'),
                                            (2, 'Jack', 20, 'test2@baomidou.com'),
                                            (3, 'Tom', 28, 'test3@baomidou.com'),
                                            (4, 'Sandy', 21, 'test4@baomidou.com'),
                                            (5, 'Billie', 24, 'test5@baomidou.com');
```
## 初始化SpringBoot项目
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646443376454-8842450d-3b6d-40fb-ab17-18492dcd797b.png#clientId=u2fca9262-f34d-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=624&id=u934f186b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=624&originWidth=713&originalType=binary&ratio=1&rotation=0&showTitle=false&size=41883&status=done&style=none&taskId=ua52ca70f-2f2e-43d3-8d99-aa71f1d8f28&title=&width=713)
## 添加依赖
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.pepsi-wyl</groupId>
    <artifactId>Mybatis-plus_T</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>Mybatis-plus_T</name>
    <description>Mybatis-plus_T</description>

    <properties>
        <java.version>11</java.version>
    </properties>

    <dependencies>

        <!--
        springBoot 启动器
        -->
        <!--springBoot场景启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <!--web场景启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
      
        <!--
        辅助开发工具
        -->
        <!--yaml配置提示 configuration-processor -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <!--lombok插件简化Bean开发-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
  
        <!--
        数据库操作
        -->
        <!--JDBC-mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!--druid-dataSource 场景启动器-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.8</version>
        </dependency>
        <!--mybatis - plus场景启动器 内置了 jdbc启动场景-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3.4</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <!--configuration-processor yaml配置提示-->
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-configuration-processor</artifactId>
                        </exclude>
                        <!--lombok插件简化Bean开发-->
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
        <resources>
            <resource>
                <directory>
                    src/main/java
                </directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                    <include>**/*.yml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>

</project>

```
## 配置
```properties
# 配置数据库
spring.datasource.druid.url=jdbc:mysql://localhost:3306/mybatisplus?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true
spring.datasource.druid.username=root
spring.datasource.druid.password=bsy8023.00
spring.datasource.druid.driver-class-name=com.mysql.cj.jdbc.Driver
#配置日志
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```
```yaml
spring:
  datasource:
    username: root
    password: bsy8023.00
    url: jdbc:mysql://localhost:3306/mybatis_plus?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true
    driver-class-name: com.mysql.cj.jdbc.Driver

mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  # 别名扫描包  
  type-aliases-package: com.pepsiwyl.pojo
```
## 编码
### 编写 实体类 User.java
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Alias("user")

@Component(value = "user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```
### 编写 Mapper类  
extends BaseMapper<T>
```java
// 继承BaseMapper
@Mapper // 注入mapper类  // @MapperScan("") 批量扫描
@Repository(value = "userMapper")
public interface UserMapper extends BaseMapper<User> {

}
```
### 编写 MapperXML类   可以不写
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.pepsiwyl.mybatisplus_t.mapper.UserMapper">
  <!--自定义sql语句-->
</mapper>
```
### 编写 UserService类 可以不写
extends IService<T>
```java
public interface UserService extends IService<User> {

}
```
### 编写UserServiceImpl类 可以不写
```java
@Service(value = "userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
```
## 测试
```java
@SpringBootApplication
public class MybatisPlusTApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MybatisPlusTApplication.class, args);
        UserMapper userMapper = run.getBean("userMapper", UserMapper.class);
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
```
### ![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646446264681-2ff37aba-ba12-4544-8518-9b8700cd4a03.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=354&id=u5682ee44&margin=%5Bobject%20Object%5D&name=image.png&originHeight=354&originWidth=857&originalType=binary&ratio=1&rotation=0&showTitle=false&size=446098&status=done&style=none&taskId=uc47c53f3-f5ad-46a2-b7d6-54741b1bdce&title=&width=857)
# 自动配置
## 原理

- MybatisPlusAutoConfiguration 配置类，MybatisPlusProperties 配置项绑定。**mybatis-plus：xxx 就是对mybatis-plus的定制**
- **SqlSessionFactory 自动配置好。底层是容器中默认的数据源**
- **mapperLocations 自动配置好的。有默认值。classpath*:/mapper/**/*.xml；任意包的类路径下的所有mapper文件夹下任意路径下的所有xml都是sql映射文件。  建议以后sql映射文件，放在 mapper下**
- **容器中也自动配置好了 SqlSessionTemplate**
- **@Mapper 标注的接口也会被自动扫描；建议直接 **@MapperScan(**"com.atguigu.admin.mapper"**) 批量扫描就行

**优点：**

-  只需要我们的Mapper继承 **BaseMapper **就可以拥有crud能力
# 注解
## @TableName
### 源码
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface TableName {
    String value() default "";     //  表名
    String schema() default "";    //  schema数据库名称
    boolean keepGlobalPrefix() default false; // 是否保持使用全局的 tablePrefix 的值（当全局 tablePrefix 生效时）
    String resultMap() default "";            // xml 中 resultMap 的 id
    boolean autoResultMap() default false;    // 是否自动构建 resultMap 并使用
    String[] excludeProperty() default {};    // 需要排除的属性名
}
```
### 事例
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Component(value = "user")

// 描述：表名注解，标识实体类对应的表
// 使用位置：实体类
@TableName(schema = "mybatis_plus", value = "user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```
## @TavleId
### 源码
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface TableId {
    String value() default "";          // 主键字段名
    IdType type() default IdType.NONE;  // 指定主键类型  默认值IdType.NONE
}
```
### IdType
```java
public enum IdType {
    AUTO(0),          // 数据库 ID 自增
    NONE(1),          // 无状态，该类型为未设置主键类型（注解里等于跟随全局，全局里约等于 INPUT）
    INPUT(2),         // insert 前自行 set 主键值
    ASSIGN_ID(3),     // 雪花算法 主键类型为 Number(Long 和 Integer) 或 String
    ASSIGN_UUID(4);   // UUID 主键类型为 String
}
```
### 事例
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Component(value = "user")

@TableName(schema = "mybatis_plus", value = "user")
public class User {

    // @TableId 主键注解 z主键策略 雪花算法
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
    private Integer age;
    private String email;
}
```
### 
## @TableField
### 源码
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface TableField {
    String value() default "";      // 数据库字段名
    boolean exist() default true;   // 是否为数据库表字段  默认true
    String condition() default "";  // 字段 where 实体查询比较条件 有值设置则按设置的值为准，没有则为默认
    String update() default "";     // 字段 update set 部分注入
    FieldStrategy insertStrategy() default FieldStrategy.DEFAULT; //NOT_NULL
    FieldStrategy updateStrategy() default FieldStrategy.DEFAULT; //IGNORED
    FieldStrategy whereStrategy() default FieldStrategy.DEFAULT;  //NOT_EMPTY

    FieldFill fill() default FieldFill.DEFAULT;  // 字段自动填充策略

    boolean select() default true;               // 是否进行 select 查询

    boolean keepGlobalFormat() default false;

    String property() default "";

    JdbcType jdbcType() default JdbcType.UNDEFINED;

    Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;

    boolean javaType() default false;

    String numericScale() default "";     // 指定小数点后保留的位数
}
```
### FieldStrategy
```java
public enum FieldStrategy {
    IGNORED,    // 忽略判断
    NOT_NULL,   // 非 NULL 判断
    NOT_EMPTY,  // 非空判断(只对字符串类型字段,其他类型字段依然为非 NULL 判断)
    DEFAULT,    // 追随全局配置
    NEVER;      // 不判断
}
```
### FieldFill
```java
public enum FieldFill {
    DEFAULT,       // 默认不处理
    INSERT,        // 插入时填充字段
    UPDATE,        // 更新时填充字段
    INSERT_UPDATE; // 插入和更新时填充字段
}
```
### 事例
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Component(value = "user")

@TableName(schema = "mybatis_plus", value = "user")
public class User {

    // @TableId 主键注解 主键策略 雪花算法
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    // TableField 自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

```
## @Version
### 源码
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Version { 
    //描述：乐观锁注解、标记 @Verison 在字段上
}
```
### 事例
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Component(value = "user")

//  表名注解，标识实体类对应的表
@TableName(schema = "mybatis_plus", value = "user")
public class User {

    // @TableId 主键注解 主键策略 雪花算法
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    // TableField 自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // @Version 乐观锁插件
    @Version
    private Integer version;
}
```
## @TableLogic
### 源码
表字段逻辑处理注解（逻辑删除）
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface TableLogic {
    String value() default "";  // 逻辑未删除值
    String delval() default ""; // 逻辑删除值
}
```
## @OrderBy
### 源码
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface OrderBy {
    boolean asc() default false;
    /** @deprecated */
    @Deprecated
    boolean isDesc() default true;  // 是否倒序查询
    short sort() default 32767;     // 数字越小越靠前
}
```
### 实例
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Component(value = "user")

// 表名注解，标识实体类对应的表
@TableName(schema = "mybatis_plus", value = "user")
public class User {

    // @TableId 主键注解 主键策略 雪花算法
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    @OrderBy(isDesc = true) // 年龄倒序
    private Integer age;
    
    private String email;

    // TableField 自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // @Version 乐观锁插件
    @Version
    private Integer version;

    // 逻辑删除
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

}
```
## @EnumValue

- 描述：普通枚举类注解(注解在枚举字段上)
# CRUD
## Service CRUD 接口
### save
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646472095846-e53e6bcb-a83a-4ce7-8003-519054ff2725.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=86&id=ud6393788&margin=%5Bobject%20Object%5D&name=image.png&originHeight=86&originWidth=591&originalType=binary&ratio=1&rotation=0&showTitle=false&size=36263&status=done&style=none&taskId=u57bffddb-ee54-46d9-8cfe-ce9b5b05bc2&title=&width=591)
```java
// 插入一条记录（选择字段，策略插入）
boolean save(T entity);  
// 插入（批量）
boolean saveBatch(Collection<T> entityList); 
// 插入（批量）
boolean saveBatch(Collection<T> entityList, int batchSize); 
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| T | entity | 实体对象 |
| Collection<T> | entityList | 实体对象集合 |
| int | batchSize | 插入批次数量 |

### SaveOrUpdate
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646472112075-a1c04e68-470b-43a5-ac86-5d5ed244591f.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=107&id=u3990d329&margin=%5Bobject%20Object%5D&name=image.png&originHeight=107&originWidth=601&originalType=binary&ratio=1&rotation=0&showTitle=false&size=45175&status=done&style=none&taskId=u09f48966-1dd9-461e-aec7-d92fda8ae51&title=&width=601)
```java
// TableId 注解存在更新记录，否插入一条记录
boolean saveOrUpdate(T entity);
// 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper);
// 批量修改插入
boolean saveOrUpdateBatch(Collection<T> entityList);
// 批量修改插入
boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| T | entity | 实体对象 |
| Wrapper<T> | updateWrapper | 实体对象封装操作类 UpdateWrapper |
| Collection<T> | entityList | 实体对象集合 |
| int | batchSize | 插入批次数量 |

### Remove
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646472143710-391809b7-d622-43a8-975b-af7981465303.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=269&id=ud169ef07&margin=%5Bobject%20Object%5D&name=image.png&originHeight=269&originWidth=600&originalType=binary&ratio=1&rotation=0&showTitle=false&size=106063&status=done&style=none&taskId=u5624e33e-afb6-46b4-8fec-c535580fe65&title=&width=600)
```java
// 根据 entity 条件，删除记录
boolean remove(Wrapper<T> queryWrapper);
// 根据 ID 删除
boolean removeById(Serializable id);
// 根据 columnMap 条件，删除记录
boolean removeByMap(Map<String, Object> columnMap);
// 删除（根据ID 批量删除）
boolean removeByIds(Collection<? extends Serializable> idList);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| Wrapper<T> | queryWrapper | 实体包装类 QueryWrapper |
| Serializable | id | 主键 ID |
| Map<String, Object> | columnMap | 表字段 map 对象 |
| Collection<? extends Serializable> | idList | 主键 ID 列表 |

### Update
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646472173360-736abfae-3201-4b6c-a006-bd39e93a9c0d.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=167&id=u7ed3a20a&margin=%5Bobject%20Object%5D&name=image.png&originHeight=167&originWidth=585&originalType=binary&ratio=1&rotation=0&showTitle=false&size=61993&status=done&style=none&taskId=u95713222-2e42-4c9f-936d-bf85d59151f&title=&width=585)
```java
// 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
boolean update(Wrapper<T> updateWrapper);
// 根据 whereWrapper 条件，更新记录
boolean update(T updateEntity, Wrapper<T> whereWrapper);
// 根据 ID 选择修改
boolean updateById(T entity);
// 根据ID 批量更新
boolean updateBatchById(Collection<T> entityList);
// 根据ID 批量更新
boolean updateBatchById(Collection<T> entityList, int batchSize);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| Wrapper<T> | updateWrapper | 实体对象封装操作类 UpdateWrapper |
| T | entity | 实体对象 |
| Collection<T> | entityList | 实体对象集合 |
| int | batchSize | 更新批次数量 |

### Get
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646472191262-a7553502-297c-40b8-8fab-170554059496.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=164&id=u69c2a0c8&margin=%5Bobject%20Object%5D&name=image.png&originHeight=164&originWidth=587&originalType=binary&ratio=1&rotation=0&showTitle=false&size=61295&status=done&style=none&taskId=ub9e90ee6-212b-433a-bea3-cfea633341e&title=&width=587)
```java
// 根据 ID 查询
T getById(Serializable id);
// 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
T getOne(Wrapper<T> queryWrapper);
// 根据 Wrapper，查询一条记录
T getOne(Wrapper<T> queryWrapper, boolean throwEx);
// 根据 Wrapper，查询一条记录
Map<String, Object> getMap(Wrapper<T> queryWrapper);
// 根据 Wrapper，查询一条记录
<V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| Serializable | id | 主键 ID |
| Wrapper<T> | queryWrapper | 实体对象封装操作类 QueryWrapper |
| boolean | throwEx | 有多个 result 是否抛出异常 |
| T | entity | 实体对象 |
| Function<? super Object, V> | mapper | 转换函数 |

### LIst
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646472207822-ccc8ca72-3c73-4a50-86ad-8d4a0ed5af23.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=243&id=u071847b8&margin=%5Bobject%20Object%5D&name=image.png&originHeight=243&originWidth=581&originalType=binary&ratio=1&rotation=0&showTitle=false&size=84890&status=done&style=none&taskId=u6c75a3de-dcb8-489a-a2cf-c9a092ca90c&title=&width=581)
```java
// 查询所有
List<T> list();
// 查询列表
List<T> list(Wrapper<T> queryWrapper);
// 查询（根据ID 批量查询）
Collection<T> listByIds(Collection<? extends Serializable> idList);
// 查询（根据 columnMap 条件）
Collection<T> listByMap(Map<String, Object> columnMap);
// 查询所有列表
List<Map<String, Object>> listMaps();
// 查询列表
List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);
// 查询全部记录
List<Object> listObjs();
// 查询全部记录
<V> List<V> listObjs(Function<? super Object, V> mapper);
// 根据 Wrapper 条件，查询全部记录
List<Object> listObjs(Wrapper<T> queryWrapper);
// 根据 Wrapper 条件，查询全部记录
<V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| Wrapper<T> | queryWrapper | 实体对象封装操作类 QueryWrapper |
| Collection<? extends Serializable> | idList | 主键 ID 列表 |
| Map<String, Object> | columnMap | 表字段 map 对象 |
| Function<? super Object, V> | mapper | 转换函数 |

### Page
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646472221544-aeb72137-8808-4e00-a55a-94794f9a7486.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=128&id=u18c02039&margin=%5Bobject%20Object%5D&name=image.png&originHeight=128&originWidth=632&originalType=binary&ratio=1&rotation=0&showTitle=false&size=54400&status=done&style=none&taskId=u7d667be3-90f2-4483-81d0-626d458048a&title=&width=632)
```java
// 无条件分页查询
IPage<T> page(IPage<T> page);
// 条件分页查询
IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);
// 无条件分页查询
IPage<Map<String, Object>> pageMaps(IPage<T> page);
// 条件分页查询
IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| IPage<T> | page | 翻页对象 |
| Wrapper<T> | queryWrapper | 实体对象封装操作类 QueryWrapper |

### Count
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646472236383-4bab4ae6-b009-4480-a9e6-dcfe6f18cd06.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=87&id=u6e6d7ac4&margin=%5Bobject%20Object%5D&name=image.png&originHeight=87&originWidth=574&originalType=binary&ratio=1&rotation=0&showTitle=false&size=31176&status=done&style=none&taskId=u822e0d89-c324-4021-bc3e-3a738f1d7eb&title=&width=574)
```java
// 查询总记录数
int count();
// 根据 Wrapper 条件，查询总记录数
int count(Wrapper<T> queryWrapper);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| Wrapper<T> | queryWrapper | 实体对象封装操作类 QueryWrapper |

### Chain

#### query
```java
// 链式查询 普通
QueryChainWrapper<T> query();
// 链式查询 lambda 式。注意：不支持 Kotlin
LambdaQueryChainWrapper<T> lambdaQuery();

// 示例：
query().eq("column", value).one();
lambdaQuery().eq(Entity::getId, value).list();
```
#### update
```java
// 链式更改 普通
UpdateChainWrapper<T> update();
// 链式更改 lambda 式。注意：不支持 Kotlin
LambdaUpdateChainWrapper<T> lambdaUpdate();

// 示例：
update().eq("column", value).remove();
lambdaUpdate().eq(Entity::getId, value).update(entity);
```
## Mapper CRUD 接口
### Insert
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646471645403-6ad314dd-db6a-4818-b0c2-62da4456211a.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=59&id=ue913041c&margin=%5Bobject%20Object%5D&name=image.png&originHeight=59&originWidth=599&originalType=binary&ratio=1&rotation=0&showTitle=false&size=26650&status=done&style=none&taskId=u720c5077-7ebe-42e3-8e31-fdacb57c7e1&title=&width=599)
```java
// 插入一条记录
int insert(T entity);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| T | entity | 实体对象 |

### Delete
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646471691629-8b3d8270-3712-4f8d-bc99-d2bdcbba3e12.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=137&id=u36073794&margin=%5Bobject%20Object%5D&name=image.png&originHeight=137&originWidth=585&originalType=binary&ratio=1&rotation=0&showTitle=false&size=52943&status=done&style=none&taskId=ue0394d35-2d6f-4391-b76f-304c6dd01f6&title=&width=585)
```java
// 根据 entity 条件，删除记录
int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
// 删除（根据ID 批量删除）
int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
// 根据 ID 删除
int deleteById(Serializable id);
// 根据 ID 删除
int deleteById(T entity);
// 根据 columnMap 条件，删除记录
int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| Wrapper<T> | wrapper | 实体对象封装操作类（可以为 null） |
| Collection<? extends Serializable> | idList | 主键 ID 列表(不能为 null 以及 empty) |
| Serializable | id | 主键 ID |
| Map<String, Object> | columnMap | 表字段 map 对象 |

### Update
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646471775823-26bac766-24d4-4345-b60b-7ac7aeb968ce.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=110&id=ua0cb754d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=110&originWidth=576&originalType=binary&ratio=1&rotation=0&showTitle=false&size=53271&status=done&style=none&taskId=u4c25a4d7-cc70-4685-b9d2-8ac6e4d5a60&title=&width=576)
```java
// 根据 whereWrapper 条件，更新记录
int update(@Param(Constants.ENTITY) T updateEntity, @Param(Constants.WRAPPER) Wrapper<T> whereWrapper);
// 根据 ID 修改
int updateById(@Param(Constants.ENTITY) T entity);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| T | entity | 实体对象 (set 条件值,可为 null) |
| Wrapper<T> | updateWrapper | 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句） |

### Select
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646471797684-ef55f3b9-1b40-4769-90e8-5d8a29515fda.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=254&id=uebacac7f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=254&originWidth=579&originalType=binary&ratio=1&rotation=0&showTitle=false&size=99781&status=done&style=none&taskId=u88a84a65-0725-4950-b4e9-cc620b81669&title=&width=579)
```java
// 根据 ID 查询
T selectById(Serializable id);
// 根据 entity 条件，查询一条记录
T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

// 查询（根据ID 批量查询）
List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
// 根据 entity 条件，查询全部记录
List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
// 查询（根据 columnMap 条件）
List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
// 根据 Wrapper 条件，查询全部记录
List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
// 根据 Wrapper 条件，查询全部记录。注意： 只返回第一个字段的值
List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

// 根据 entity 条件，查询全部记录（并翻页）
IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
// 根据 Wrapper 条件，查询全部记录（并翻页）
IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
// 根据 Wrapper 条件，查询总记录数
Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
```
#### 参数说明
| 类型 | 参数名 | 描述 |
| --- | --- | --- |
| Serializable | id | 主键 ID |
| Wrapper<T> | queryWrapper | 实体对象封装操作类（可以为 null） |
| Collection<? extends Serializable> | idList | 主键 ID 列表(不能为 null 以及 empty) |
| Map<String, Object> | columnMap | 表字段 map 对象 |
| IPage<T> | page | 分页查询条件（可以为 RowBounds.DEFAULT） |

## 条件构造器
### 比较
```java
QueryWrapper<User> wrapper = new QueryWrapper<>();
wrapper
         .eq("name", "wyl")  //eq 等于
         .ne("name", "wy")   //ne 不等于
         .gt("age", 10)      //gt 大于
         .ge("age", 11)      //ge 大于等于
         .lt("age", 100)     //lt 小于
         .le("age", 99)      //le 小于等于
         .between("version", 0, 1) //BETWEEN 值1 AND 值2
         .notBetween("version", 3, 4);//NOT BETWEEN 值1 AND 值2
```
### like拼接
```java
QueryWrapper<User> wrapper = new QueryWrapper<>();
wrapper
         .like("email", "2322535585")//like      LIKE   '%值%'
         .notLike("email", "163.com")//notLike   NOT LIKE '%值%'
         .likeLeft("name", "w")      //likeLeft  LIKE '%值'
         .likeRight("name", "w");    //likeRight LIKE '值%'
```
### isNull  in 
```java
QueryWrapper<Object> wrapper = new QueryWrapper<>();
wrapper
         .isNull("name")     // 字段 IS NULL
         .isNotNull("name")  // 字段 IS NOT NULL
         .in("age",1,2,3)    //字段 IN (v0, v1, ...)
         .notIn("age",1,2,3); //字段 NOT IN (v0, v1, ...)
```
### inSql notInSql
```java
QueryWrapper<Object> wrapper = new QueryWrapper<>();
wrapper
          .inSql("id","select id from table where id < 3")       // 字段 IN ( sql语句 )
          .notInSql("id","select id from table where id < 3");   // 字段 NOT IN ( sql语句 )
```
### groupBy
```java
QueryWrapper<Object> wrapper = new QueryWrapper<>();
wrapper
          .groupBy("id","name"); // ORDER BY 字段, ... ASC
```
### orderBy
```java
QueryWrapper<User> wrapper = new QueryWrapper<>();
wrapper
         .orderByAsc("age", "name")       // 排序：ORDER BY 字段, ... ASC
         .orderByDesc("age", "name")      // 排序：ORDER BY 字段, ... DESC
         .orderBy(true, true, "age", "name"); // ORDER BY 字段, ... 
```
### having
```java
QueryWrapper<User> wrapper = new QueryWrapper<>();
wrapper
         .having("sum(age)>10")      // having sum(age) > 10
         .having("sum(age)>{0},11"); // having sum(age) > 11
```
### or and
```java
QueryWrapper<User> wrapper = new QueryWrapper<>();
wrapper
        .eq("id", 1).or().eq("name", "老王")  // id = 1  or name = '老王'
        .or(i -> i.eq("name", "李白").ne("status", "活着")) // or (name = '李白' and status <> '活着')
        .and(i -> i.eq("name", "李白").ne("status", "活着"));// and (name = '李白' and status <> '活着'
```
### exists  notExists
```java
QueryWrapper<User> wrapper = new QueryWrapper<>();
wrapper
        .exists("select id from table where age = 1")     // exists (select id from table where age = 1)
        .notExists("select id from table where age = 1"); // not exists (select id from table where age = 1)
```
# 核心功能
## 主键生成策略
### 常见的主键生成策略
#### 自动增长

- 好处：

代码方便，简单易用，性能可接受
数字天然排列，对分页和需要排序的结果有帮助

- 缺点：

不同数据库或者不同版本数据库的语法和实现不同，数据迁移的时候可能需要处理
在性能达不到要求的情况下，难于扩展
如果需要多个系统合并或者涉及到数据库迁移会比较痛苦
分表分库的时候会有麻烦
在读写分离或者一主多从的情况下，只有一个主库生成，有单点故障的风险
优化方案： 如果有多个master库，可以给每个master设置步长，如135，248
#### UUID
常见的方式：利用数据库或者程序生成

- 好处：

简单，代码方便
生成ID性能好
全球唯一，在遇见数据库迁移，系统数据合并，或者数据库变更情况下，可以从容应对

- 缺点：

       没有排序，无法保证趋势递增
       UUID往往使用字符串，查询效率较低
存储空间较大，如果是海量数据库，就需要考虑储量问题
传输数据量大
不可读
#### Redis生成ID

- 好处**：**
不依赖于数据库，且性能优于数据库
数字ID天然排序，对分页或者需要排序的结果有帮助
- 缺点**：**
如果系统中没有Redis，还要引入组件、增加了系统复杂度
需要编码和配置的工作量加大
#### Twitter的snowflake算法

- 产生的ID根据时间有序生产
- 算法生成的ID的结果是一个64bit大小的整数，转换成十进制则为Long型(最多19为数字)
- 分布是系统内不会产生ID碰撞(由datacenter和workerId作区分)并且生成效率很高
### 基本使用
#### 自动增长
设置主键自动增长  数据库需要自动增长
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Component(value = "user")
public class User {

    // 设置主键为ID 主键策略为自动增长
    @TableId(value = "id", type = IdType.AUTO)  
    private Long id;

    private String name;
    private Integer age;
    private String email;
}

```
```sql
# 主键必须为自动增长
create table mybatis_plus.user
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(30)   null comment '姓名',
    age         int           null comment '年龄',
    email       varchar(50)   null comment '邮箱',
);
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646456190848-28d4982e-8548-4130-ad57-8544b9c94eed.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=48&id=uf20f5833&margin=%5Bobject%20Object%5D&name=image.png&originHeight=48&originWidth=613&originalType=binary&ratio=1&rotation=0&showTitle=false&size=47660&status=done&style=none&taskId=uc07b7578-c6d4-4fa2-89ff-19811f66343&title=&width=613)
#### 雪花算法
```java
@Component(value = "user")
public class User {

    // 设置主键为ID 主键策略为雪花算法
    @TableId(value = "id", type = IdType.ASSIGN_ID) 
    private Long id;

    private String name;
    private Integer age;
    private String email;
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646449350876-e4dfc771-a291-4c79-9666-21b54fdc47e5.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=90&id=uf8a7feb5&margin=%5Bobject%20Object%5D&name=image.png&originHeight=90&originWidth=809&originalType=binary&ratio=1&rotation=0&showTitle=false&size=105015&status=done&style=none&taskId=uc58cc375-592e-45d5-8300-28857bc73c3&title=&width=809)
## 自动填充功能
### 数据库修改
增添创建时间和修改时间的字段
```java
create table mybatis_plus.user
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(30)   null comment '姓名',
    age         int           null comment '年龄',
    email       varchar(50)   null comment '邮箱',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '更新时间',
);
```
### 实体类修改
增添创建时间和修改时间的字段
添加注解 标识填充策略
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Component(value = "user")
public class User {

    // @TableId 主键注解 主键策略 雪花算法
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    // TableField 自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
```
```java
// 常见的填充策略
public enum FieldFill {
    DEFAULT,         // 默认不处理
    INSERT,          // 插入填充字段
    UPDATE,          // 更新填充字段
    INSERT_UPDATE;   // 插入和更新填充字段
}
```
### Handler配置
MybatisPlusHandler 实现元对象处理器接口
```java
@Slf4j // 日志 
@Component   //处理器加入IOC容器中   一定不能忘记！！！
public class MybatisPlusHandler implements MetaObjectHandler {
    // 插入时候的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("createTime", new Date(), metaObject);
    }
    // 更新时候的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
```
### 注意事项

- 填充原理是直接给entity的属性设置值!!!
- 注解则是指定该属性在对应情况下必有值,如果无值则入库会是null
- MetaObjectHandler提供的默认方法的策略均为:如果属性有值则不覆盖,如果填充值为null则不填充
- 字段必须声明TableField注解,属性fill选择对应策略,该声明告知Mybatis-Plus需要预留注入SQL字段
- 填充处理器MyMetaObjectHandler在 Spring Boot 中需要声明@Component或@Bean注入
- 要想根据注解FieldFill.xxx和字段名以及字段类型来区分必须使用父类的strictInsertFill或者strictUpdateFill方法
- 不需要根据任何来区分可以使用父类的fillStrategy方法
- update(T t,Wrapper updateWrapper)时t不能为空,否则自动填充失效
## 乐观锁插件
### 乐观锁悲观锁

- 乐观锁 十分乐观,总认为不会出现问题,无论干什么都不去上锁 ,出现问题,再次跟新值测试!!!
- 悲观锁 十分悲观,总认为总是出现问题,无论干什么都去上锁,再去操作!!!
### 乐观锁实现方式

- 取出记录时，获取当前 version
- 更新时，带上这个 version
- 执行更新时， set version = newVersion where version = oldVersion
- 如果 version 不对，就更新失败
### 数据库修改
添加Verson字段   默认值为1
```sql
create table mybatis_plus.user
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(30)   null comment '姓名',
    age         int           null comment '年龄',
    email       varchar(50)   null comment '邮箱',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '更新时间',
  
    version     int default 1 null comment '乐观锁version',
);
```
### 实体类修改
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Component(value = "user")

// 表名注解，标识实体类对应的表
@TableName(schema = "mybatis_plus", value = "user")
public class User {
    
    // @TableId 主键注解 主键策略 雪花算法
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    private String name;
    private Integer age;
    private String email;
    
    // TableField 自动填充
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
    // @Version 乐观锁插件
    @Version
    private Integer version;
}

```
### 插件配置
```java
@MapperScan("com.pepsiwyl.mybatisplus_t.mapper")
@Configuration               // 配置类
public class MybatisPlusConfig {
    // 插件注册
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //注册乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
```
### 说明

- **支持的数据类型有:int,Integer,long,Long,Date,Timestamp,LocalDateTime**
- 整数类型下 newVersion = oldVersion + 1 newVersion 会回写到 entity 中
- 仅支持 updateById(id) 与 update(entity, wrapper) 方法
- **在 update(entity, wrapper) 方法下, wrapper 不能复用!!!**
## 逻辑删除
### 逻辑删除与物理删除

- 物理删除: 从数据库直接移除
- 逻辑删除: 数据库种没有删除,而是通过一个变量让他失效

管理员可以查看被删除的记录,防止数据丢失,类似于回收站
### 说明
只对自动注入的 sql 起效:

- 插入: 不作限制
- 查找: 追加 where 条件过滤掉已删除数据,且使用 wrapper.entity 生成的 where 条件会忽略该字段
- 更新: 追加 where 条件防止更新到已删除数据,且使用 wrapper.entity 生成的 where 条件会忽略该字段
- 删除: 转变为 更新
### 数据库修改
```sql
create table mybatis_plus.user
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(30)   null comment '姓名',
    age         int           null comment '年龄',
    email       varchar(50)   null comment '邮箱',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '更新时间',
    version     int default 1 null comment '乐观锁version',
    deleted     int default 0 null comment '逻辑删除'
);
```
### 实体类修改
```sql
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Component(value = "user")

// 表名注解，标识实体类对应的表
@TableName(schema = "mybatis_plus", value = "user")
public class User {

    // @TableId 主键注解 主键策略 雪花算法
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    // @TableId 主键注解 z主键策略 雪花算法
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // @Version 乐观锁插件
    @Version
    private Integer version;

    // 逻辑删除  value 逻辑未删除值    delval	逻辑删除值
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

}

```
### 配置
#### application.properties
```properties
mybatis-plus.global-config.db-config.logic-delete-field=flag   # 全局逻辑删除的实体字段名
mybatis-plus.global-config.db-config.logic-delete-value=1      # 逻辑已删除值(默认为 1)
mybatis-plus.global-config.db-config.logic-not-delete-value=0  # 逻辑未删除值(默认为 0)
```
#### application.yml
```yaml
mybatis-plus:
  global-config:
    db-config:
      #逻辑删除
      logic-delete-field: flag    # 全局逻辑删除的实体字段名
      logic-delete-value: 1       # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0   # 逻辑未删除值(默认为 0)
```
## 分页插件
### 插件配置
```java
@MapperScan("com.pepsiwyl.mybatisplus_t.mapper")
@Configuration               // 配置类
public class MybatisPlusConfig {
    // 插件注册
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //page分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
```
### 实例
```java
ConfigurableApplicationContext run = SpringApplication.run(MybatisPlusTApplication.class, args);
UserMapper userMapper = run.getBean("userMapper", UserMapper.class);
// 分页插件  当前页 页面大小
Page<User> userPage = new Page<>(1,2);
userMapper.selectPage(userPage, null);
userPage.getRecords().forEach(System.out::println);
```
## Druid监控
### yml配置
```yaml
server:
  port: 80
  servlet:
    context-path: /

spring:
  datasource:
    # 基本数据参数配置
    url: jdbc:mysql://101.43.169.194:3306/springboot?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true
    username: root
    password: bsy8023.00
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 数据源为 com.alibaba.druid   #默认数据源  HikariDataSource
    type: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper
    # druid配置
    druid:
      # 初始化时建立物理连接的个数
      initial-size: 5
      # 最小连接池数量
      min-idle: 5
      # 最大连接池数量
      max-active: 20
      # 获取连接等待超时的时间  毫秒
      max-wait: 60000
      # 闭空闲连接的检测时间间隔  毫秒
      time-between-eviction-runs-millis: 60000
      # 连接的最小生存时间.连接保持空闲而不被驱逐的最小时间
      min-evictable-idle-time-millis: 300000
      # 验证数据库服务可用性的sql.用来检测连接是否有效的sql
      validation-query: SELECT 1 FROM DUAL
      # 申请连接时检测空闲时间，根据空闲时间再检测连接是否有效
      test-while-idle: true
      # 申请连接时直接检测连接是否有效
      test-on-return: false
      # 归还连接时检测连接是否有效
      test-on-borrow: false
      # 开启PSCache
      pool-prepared-statements: true
      # 设置PSCache值
      max-pool-prepared-statement-per-connection-size: 20
      # 数据库服务宕机自动重连机制
      break-after-acquire-failure: true
      # 连接出错后重试时间间隔
      time-between-connect-error-millis: 300000
      # 异步初始化策略
      async-init: true
      # 是否自动回收超时连接
      remove-abandoned: true
      # 超时时间(以秒数为单位)
      remove-abandoned-timeout: 1800
      # 事务超时时间
      transaction-query-timeout: 6000
      # 打开mergeSql功能,慢SQL记录
      connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
      # 合并多个DruidDataSource的监控数据
      use-global-data-source-stat: true

      # Spring监控AOP切入点，如x.y.z.service.*,配置多个英文逗号分隔
      aop-patterns: com.wyl.service.*

      # 配置监控统计拦截的filters，  stat:监控统计、log4j：日志记录、wall：防御sql注入
      filters: stat,wall
      filter:
        # stat:监控统计
        stat:
          enabled: true
          slow-sql-millis: 1000
          log-slow-sql: true
        # wall：防御sql注入
        wall:
          enabled: true
          config:
            drop-table-allow: false

      # 配置监控页功能
      stat-view-servlet:
        # 开启
        enabled: true
        # 配置DruidStatViewServlet
        url-pattern: /druid/*
        # 禁用HTML页面上的“Reset All”功能
        reset-enable: false
        # 监控页面登录的用户名
        login-username: 'zhazha'
        # 监控页面登录的密码
        login-password: '000000'
        # IP白名单(没有配置或者为空，则允许所有访问)
        allow: 127.0.0.1,192.168.0.148
        # IP黑名单 (存在共同时，deny优先于allow)
        deny:

      # 监控web
      web-stat-filter:
        # 开启
        enabled: true
        # 监控路径
        url-pattern: /*
        # 排除的路径
        exclusions: /druid/*,*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico
        session-stat-enable: true
        session-stat-max-count: 10
        principal-session-name: session_name
        principal-cookie-name: cookie_name

mybatis-plus:
  configuration:
    #日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImp
```
### 效果
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646466869457-d62d4637-4485-4567-baaa-f05de6b1a380.png#clientId=ufc51a128-45df-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=321&id=u89079088&margin=%5Bobject%20Object%5D&name=image.png&originHeight=321&originWidth=779&originalType=binary&ratio=1&rotation=0&showTitle=false&size=16413&status=done&style=none&taskId=u5b1dfc4c-c104-49bb-b277-96608e7abeb&title=&width=779)
## 自动生产代码
### 添加依赖
springBoot 启动器
```java
        <!--
        springBoot 启动器
        -->
        <!--springBoot场景启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <!--springBoot单元测试-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--web场景启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--JDBC场景启动器 可以使用JDBCTemplate-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
```
辅助开发工具
```java
        <!--
        辅助开发工具
        -->
        <!--yaml配置提示 configuration-processor -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <!--lombok插件简化Bean开发-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
```
数据库操作
```java
        <!--
        数据库操作
        -->
        <!--JDBC-mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!--druid-dataSource 场景启动器-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.8</version>
        </dependency>
        <!--mybatis - plus场景启动器-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.1</version>
        </dependency>
```
代码生成依赖
```java
<!--generator 代码生产器-->
<dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>mybatis-plus-generator</artifactId>
       <version>3.4.0</version>
</dependency>
<!--模板引擎  由于默认使用velocity模板引擎  故需要添加此依赖-->
<dependency>
       <groupId>org.apache.velocity</groupId>
       <artifactId>velocity-engine-core</artifactId>
       <version>2.2</version>
</dependency>
```
### AutoCode
```java
package com.pepsiwyl;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;

/**
 * @author by pepsi-wyl
 * @date 2022-03-05 20:01
 */
class AutoCode {
    public static void main(String[] args) {

        String modelName = "Mybatis-plus_generator";  //项目名称
        String author = "pepsi-wyl";                  //作者名称

        String username = "root";
        String password = "bsy8023.00";
        String url = "jdbc:mysql://localhost:3306/mybatis_plus?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true";
        String DriverName = "com.mysql.cj.jdbc.Driver";

        String tableName = "user";  // 表名称

        String parentPackage = "com.pepsiwyl";

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig(); //构建全局配置对象
        globalConfig
                .setOutputDir(System.getProperty("user.dir") + "/" + modelName + "/src/main/java")  // 输出文件路径
                .setAuthor(author)    // 设置作者名字
                .setOpen(false)       // 是否打开资源管理器
                .setFileOverride(true)        // 是否覆盖原来生成的
                .setIdType(IdType.ASSIGN_ID)  // 主键策略
                .setDateType(DateType.ONLY_DATE) //日期配置
                .setBaseResultMap(true)  // 生成resultMap
                .setBaseColumnList(true) // XML中生成基础列
//                .setSwagger2(true)       // 开启Swagger2
                .setServiceName("%sService"); // 生成的service接口名字首字母是否为I，这样设置就没有I

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();// 创建数据源配置
        dataSourceConfig
                .setUrl(url)
                .setDriverName(DriverName)
                .setUsername(username)
                .setPassword(password)
                .setDbType(DbType.MYSQL);  //MYSQL数据库

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig
                .setParent(parentPackage)  // 父包
                .setEntity("pojo")
                .setController("controller")
                .setService("service")
                .setMapper("mapper");

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)  // 开启全局大写命名
                .setInclude(tableName) // 设置要映射的表
                .setNaming(NamingStrategy.underline_to_camel)// 下划线到驼峰的命名方式
                .setColumnNaming(NamingStrategy.underline_to_camel)// 下划线到驼峰的命名方式
                .setEntityLombokModel(true)    // 自动生成lombok
                .setLogicDeleteFieldName("deleted")  // 逻辑删除
                .setTableFillList(                   // 设置填充策略
                        Arrays.asList(
                                new TableFill("create_time", FieldFill.INSERT),
                                new TableFill("update_time", FieldFill.UPDATE)
                        ))
                .setVersionFieldName("version") // 乐观锁
                .setRestControllerStyle(true)   // 是否开启rest风格
                .setControllerMappingHyphenStyle(true); //127.0.0.1:8080/1_2_3

        // 整合配置
        AutoGenerator autoGenerator = new AutoGenerator();// 构建代码生自动成器对象
        autoGenerator
                .setGlobalConfig(globalConfig)    // 将全局配置放到代码生成器对象中
                .setDataSource(dataSourceConfig)  // 将数据源配置放到代码生成器对象中
                .setPackageInfo(packageConfig)    // 将包配置放到代码生成器对象中
                .setStrategy(strategyConfig)      // 将策略配置放到代码生成器对象中
                .execute();                       // 执行！
    }
}
```
# 
