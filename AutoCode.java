import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;


/**
 * @author by wyl
 * @date 2021/11/29.14点06分
 */

//代码自动生成器
public class AutoCode {
    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        /**
         * 配置策略
         */
        //GlobalConfig
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("G:\\idea\\Mybatis\\Mybatis-plus-02" + "/src/main/java");//代码输出路径
        globalConfig.setAuthor("pepsi-wyl");//作者信息
        globalConfig.setOpen(false);//是否打开资源管理器
        globalConfig.setFileOverride(false);//是否覆盖
        globalConfig.setServiceName("%sService");//去除Service的I前缀
        globalConfig.setIdType(IdType.ASSIGN_ID);//主键生成策略  雪花算法
        globalConfig.setDateType(DateType.ONLY_DATE);//日期配置

        //DataSourceConfig
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("bsy8023.00");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setDbType(DbType.MYSQL); //MYSQL数据库

        //PackageConfig
        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName("module");//设置模块
        packageConfig.setParent("com.wyl");//设置父包
        packageConfig.setEntity("pojo");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");

        //StrategyConfig
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//包 _=>驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//列 _=>驼峰
        strategy.setInclude("user"); //要映射的表明
        strategy.setEntityLombokModel(true);//自动生成lombok
        strategy.setLogicDeleteFieldName("deleted");//逻辑删除
        strategy.setTableFillList(//设置填充策略
                Arrays.asList(
                        new TableFill("create_time", FieldFill.INSERT),
                        new TableFill("update_time", FieldFill.UPDATE)
                ));
        strategy.setVersionFieldName("version");//乐观锁
        strategy.setRestControllerStyle(true);//REST_Controller
        strategy.setControllerMappingHyphenStyle(true);//127.0.0.1:8080/1_2_3

        generator.setTemplateEngine(new FreemarkerTemplateEngine());//设置Freemarker模板引擎
        generator.setGlobalConfig(globalConfig);//GlobalConfig
        generator.setDataSource(dataSourceConfig);//DataSourceConfig
        generator.setPackageInfo(packageConfig);//PackageConfig
        generator.setStrategy(strategy);//StrategyConfig
        generator.execute();
    }
}
