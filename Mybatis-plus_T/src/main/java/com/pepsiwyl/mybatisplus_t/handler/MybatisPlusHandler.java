package com.pepsiwyl.mybatisplus_t.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author by wyl
 * @date 2021/11/28.20点57分
 */

@Slf4j
@Component   //处理器加入IOC容器中
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
