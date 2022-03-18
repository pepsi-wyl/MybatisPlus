package com.pepsiwyl.mybatisplus_t.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author by pepsi-wyl
 * @date 2022-03-05 9:45
 */

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

    // 年龄倒序
    @OrderBy(isDesc = true)
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

    // 逻辑删除  value     逻辑未删除值         delval	 逻辑删除值
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

}
