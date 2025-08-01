-- 创建客户表
CREATE TABLE q_customer (
    id SERIAL PRIMARY KEY,
    customer_code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

-- 创建客户地址表
CREATE TABLE q_customer_address (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL,
    province VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    district VARCHAR(50),
    detailed_address TEXT NOT NULL,
    CONSTRAINT fk_customer
        FOREIGN KEY (customer_id)
        REFERENCES q_customer(id)
        ON DELETE CASCADE
);

-- 为地址表创建索引以提高查询性能
CREATE INDEX idx_customer_address_customer_id ON q_customer_address(customer_id);


INSERT INTO q_customer (id, customer_code, name) VALUES
(1, 'GU001', '苏婉容'),
(2, 'GU002', '李墨白'),
(3, 'GU003', '慕容雪'),
(4, 'GU004', '赵明远'),
(5, 'GU005', '柳如是');

INSERT INTO q_customer_address (customer_id, province, city, district, detailed_address) VALUES
(1, '江南道', '苏州府', '吴县', '拙政园西厢海棠苑'),
(1, '江南道', '杭州府', '钱塘县', '西子湖畔苏堤春晓别院'),
(1, '江南道', '扬州府', '江都县', '二十四桥明月夜画舫');

INSERT INTO q_customer_address (customer_id, province, city, district, detailed_address) VALUES
(2, '京畿道', '顺天府', '大兴县', '琉璃厂墨香斋'),
(2, '江南道', '绍兴府', '会稽县', '兰亭书院竹里馆'),
(2, '剑南道', '成都府', '华阳县', '浣花溪畔草堂');

INSERT INTO q_customer_address (customer_id, province, city, district, detailed_address) VALUES
(3, '河北道', '幽州', '蓟县', '燕子坞参合庄'),
(3, '陇右道', '凉州', '姑臧县', '天山缥缈峰灵鹫宫'),
(3, '江南道', '金陵', '上元县', '乌衣巷慕容旧邸');

INSERT INTO q_customer_address (customer_id, province, city, district, detailed_address) VALUES
(4, '淮南道', '扬州府', '江阳县', '东关街赵氏盐号'),
(4, '岭南道', '广州府', '南海县', '十三行明远商行'),
(4, '河东道', '太原府', '晋阳县', '晋商票号总柜');

INSERT INTO q_customer_address (customer_id, province, city, district, detailed_address) VALUES
(5, '江南道', '应天府', '上元县', '秦淮河畔媚香楼'),
(5, '江南道', '杭州府', '仁和县', '西泠桥畔水云阁'),
(5, '江南道', '苏州府', '长洲县', '山塘街绛云轩');
