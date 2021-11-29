
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author by wyl
 * @date 2021/11/28.19点01分
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

/**
 * @TableName 表名注解
 */
@TableName(schema = "mybatis_plus", value = "user")
public class User {

    /**
     * @TableId 主键注解  
     */
    //DefaultIdentifierGenerator雪花算法  主键生成策略
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    /**
     * @TableField  字段注解(非主键)
     * 字段填充
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * @Version   乐观锁插件
     */
    @Version
    private Integer version;

    /**
     * @TableLogic
     * 逻辑删除
     * value     逻辑未删除值
     * delval	   逻辑删除值
     */
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

}
