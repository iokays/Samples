-- 删除从表（需先删除外键依赖）
DROP TABLE IF EXISTS my_sub_entity;

-- 创建带自增主键的表
CREATE TABLE my_sub_entity (
    id BIGSERIAL PRIMARY KEY,
    name_index INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    my_entity_id BIGINT NOT NULL,
    CONSTRAINT fk_my_entity
        FOREIGN KEY (my_entity_id) REFERENCES my_entity(id)
        ON DELETE CASCADE
);

-- 删除旧表（如果存在）
DROP TABLE IF EXISTS my_entity;

-- 创建带自增主键的表
CREATE TABLE my_entity (
    id BIGSERIAL PRIMARY KEY,  -- 自增主键
    address VARCHAR(255),
    version INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
