create table user
(
    id          bigint auto_increment comment '主键ID'
        primary key,
    name        varchar(30)   null comment '姓名',
    age         int           null comment '年龄',
    email       varchar(50)   null comment '邮箱',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '更新时间',
    version     int default 1 null comment '乐观锁version',
    deleted      int default 0 null comment '逻辑删除'
);
