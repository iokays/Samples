# Spring Integration 用例

[ebay的完整方案](https://queue.acm.org/detail.cfm?id=1394128)

## 1. 本地消息表分布式事务实现

利用SpringEvent发出的事件的存储过程与业务逻辑的存储过程在同一个事务中, 保证了数据的一致性.

1. 复用SpringEvent发事件的能力.
2. 复用SpringIntegration的消息存储和分发能力. (也可以用事件监听和任务循环来处理)

### 2. 运行步骤
1. 下载该模块
2. 启动数据库, 并建立表结构
3. 启动该项目即可

   
``` docker
docker pull mysql
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql  
```

```
create database db_spring_integration;
```

```
create table tbl_stored_event (
    event_id bigint not null auto_increment,
    event_body longtext not null,
    occurred_on datetime not null,
    type_name varchar(1024) not null,
    create_time datetime not null,
    primary key (event_id)
) engine=InnoDB default charset=utf8mb4;
```

```
create table tbl_dispatcher_last_event (
    event_id bigint not null,
    primary key (event_id)
) engine=InnoDB default charset=utf8mb4;
```

```
insert into tbl_dispatcher_last_event (event_id) values (0);
```