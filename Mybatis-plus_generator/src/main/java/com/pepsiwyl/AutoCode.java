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
        String parentPackage = "com.pepsiwyl";        //父包名称
        String author = "pepsi-wyl";                  //作者名称

        String username = "root";
        String password = "bsy8023.00";
        String url = "jdbc:mysql://localhost:3306/mybatis_plus?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true";
        String DriverName = "com.mysql.cj.jdbc.Driver";

        String tableName = "user";  // 表名称

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig(); //构建全局配置对象
        globalConfig
                .setOutputDir(System.getProperty("user.dir") + "/" + modelName + "/src/main/java")  // 输出文件路径
                .setAuthor(author)    // 设置作者名字
                .setOpen(false)       // 是否打开资源管理器
                .setFileOverride(true)            // 是否覆盖原来生成的
                .setIdType(IdType.ASSIGN_ID)      // 主键策略
                .setDateType(DateType.ONLY_DATE)  //日期配置
                .setBaseResultMap(true)        // 生成resultMap
                .setBaseColumnList(true)       // XML中生成基础列
//                .setSwagger2(true)           // 开启Swagger2
                .setServiceName("%sService");  // 生成的service接口名字首字母是否为I，这样设置就没有I

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig(); // 创建数据源配置
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


