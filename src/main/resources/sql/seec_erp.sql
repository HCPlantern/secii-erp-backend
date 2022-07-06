/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : seec_erp

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 2022-07-06 16:22:09
*/
create database `seec_erp`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) NOT NULL COMMENT '名字',
    `parent_id`  int(11)      NOT NULL COMMENT '父节点id',
    `is_leaf`    tinyint(4)   NOT NULL COMMENT '是否为叶节点',
    `item_count` int(11)      NOT NULL COMMENT '商品个数',
    `item_index` int(11)      NOT NULL COMMENT '插入的下一个index',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `category` (`id`, `name`, `parent_id`, `is_leaf`, `item_count`, `item_index`)
VALUES (1, '商品', 0, 0, 0, 0),
       (2, '电子产品', 1, 0, 0, 0),
       (4, '电脑', 2, 1, 2, 3),
       (5, '手机', 2, 1, 20, 21);

DROP TABLE IF EXISTS `collection_sheet`;
CREATE TABLE `collection_sheet`
(
    `id`           varchar(31) NOT NULL,
    `customer`     int(11)     DEFAULT NULL,
    `operator`     varchar(35) DEFAULT NULL,
    `total_amount` bigint(20)  DEFAULT NULL,
    `state`        varchar(31) DEFAULT NULL,
    `create_time`  datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='收款单';

INSERT INTO `collection_sheet` (`id`, `customer`, `operator`, `total_amount`, `state`, `create_time`)
VALUES ('SKD-20220612-00000', 2, 'hcx', 100000, '审批完成', '2022-06-12 00:00:00'),
       ('SKD-20220612-00001', 2, 'hcx', 1200, '审批完成', '2022-06-12 17:04:55'),
       ('SKD-20220612-00002', 6, 'hcx', 100, '审批失败', '2022-06-12 17:12:20'),
       ('SKD-20220612-00003', 7, 'hcx', 90, '审批失败', '2022-06-12 17:16:25'),
       ('SKD-20220612-00004', 8, 'hcx', 30, '审批完成', '2022-06-12 17:18:29'),
       ('SKD-20220612-00005', 10, 'hcx', 90, '审批失败', '2022-06-12 17:25:39'),
       ('SKD-20220627-00000', 2, 'bobby', 100, '审批失败', '2022-06-27 14:56:05'),
       ('SKD-20220627-00001', 3, 'bobby', 10, '审批失败', '2022-06-27 14:56:59'),
       ('SKD-20220706-00000', 2, 'sky', 1, '审批失败', '2022-07-06 13:22:24'),
       ('SKD-20220706-00001', 2, 'sky', 100000, '审批失败', '2022-07-06 20:25:49'),
       ('SKD-20220706-00002', 2, 'sky', -100000, '审批失败', '2022-07-06 20:30:12'),
       ('SKD-20220706-00003', 2, 'sky', -100000, '审批失败', '2022-07-06 20:30:53'),
       ('SKD-20220706-00004', 5, 'sky', 0, '待审批', '2022-07-06 20:39:21'),
       ('SKD-20220706-00005', 2, 'sky', 0, '待审批', '2022-07-06 20:40:09'),
       ('SKD-20220706-00006', 2, 'sky', -100000, '待审批', '2022-07-06 21:38:00');

DROP TABLE IF EXISTS `company_account`;
CREATE TABLE `company_account`
(
    `id`     int(11)      NOT NULL AUTO_INCREMENT,
    `name`   varchar(255) NOT NULL,
    `amount` bigint(20) DEFAULT '1000000',
    PRIMARY KEY (`id`),
    UNIQUE KEY `company_account_id_uindex` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = latin1 COMMENT ='公司银行账户';

INSERT INTO `company_account` (`id`, `name`, `amount`)
VALUES (1, 'dkjgfjs', 1091230),
       (3, 'wash', 100);

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `type`           varchar(31)    DEFAULT NULL COMMENT '分类(供应商\\销售商)',
    `level`          int(11)        DEFAULT NULL COMMENT '级别（五级，一级普通用户，五级VIP客户）',
    `name`           varchar(31)    DEFAULT NULL COMMENT '姓名',
    `phone`          varchar(15)    DEFAULT NULL COMMENT '电话号码',
    `address`        varchar(255)   DEFAULT NULL COMMENT '地址',
    `zipcode`        char(6)        DEFAULT NULL COMMENT '邮编',
    `email`          varchar(255)   DEFAULT NULL COMMENT '电子邮箱',
    `line_of_credit` decimal(10, 2) DEFAULT NULL COMMENT '应收额度本公司给客户的信用额度，客户欠本公司的钱的总额不能超过应收额度）',
    `receivable`     decimal(10, 2) DEFAULT '0.00' COMMENT '应收（客户还应付给本公司但还未付的钱， 即本公司应收的钱）',
    `payable`        decimal(10, 2) DEFAULT '0.00' COMMENT '应付（本公司欠客户的钱）',
    `operator`       varchar(255)   DEFAULT NULL COMMENT '默认业务员',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

INSERT INTO `customer` (`id`, `type`, `level`, `name`, `phone`, `address`, `zipcode`, `email`, `line_of_credit`,
                        `receivable`, `payable`, `operator`)
VALUES (1, '销售商', 1, '田所浩二', '114514', '下北泽', '114514', '1919810@qq.com', 114.00, 514.00, 8680000.00, '67'),
       (2, '销售商', 1, 'hello', '12580', '蝻睛大学软件学院', '123457', '12121@cba.com', 20000000.00, 4949100.00, 3080616.00,
        'uncl'),
       (3, '供应商', 1, '胡铁波', '120', '蝻睛大学领袖', '123456', '23333@nju.com', 0.00, 100.00, 1.00, 'uncln'),
       (5, '供应商', 1, '潘拙学', '110', '蝻睛大学软件学院', '210046', '22@qq.com', 100.00, 0.00, 0.00, '67'),
       (6, '销售商', 3, '杭宸歆', '15951320294', '蝻睛大学软件学院', '210046', '201250037@qq.com', 1000.00, 0.00, 0.00, 'duffvs'),
       (7, '供应商', 4, '陈阵雨', '31434234', '蝻睛大学软件学院', '210046', '1667827813@qq.com', 1222.00, 0.00, 0.00, '67'),
       (8, '供应商', 5, '房夏蓉', '4385628745', '蝻睛大学软件学院', '210046', '1667827813@qq.com', 1342.00, 0.00, -30.00, '435'),
       (10, '销售商', 2, '万辔霈', '110', '蝻睛大学软件学院', '210046', '201250038@smail.nju.edu.cn', 1000.00, 0.00, 0.00, 'hcx'),
       (11, '供应商', 2, '邓优靓', '110', '蝻睛大学软件学院', '210046', '201250035@smail.nju.edu.cn', 1000.00, 0.00, 0.00, 'hcx'),
       (12, '销售商', 2, '华炚菘', '110', '蝻睛大学软件学院', '210046', '110@qq.com', 1000.00, 0.00, 0.00, 'hcx');

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`
(
    `id`             int(11)      NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name`           varchar(255) NOT NULL COMMENT '姓名',
    `sex`            varchar(255) DEFAULT NULL COMMENT '性别',
    `phone`          varchar(31)  DEFAULT NULL COMMENT '手机号码',
    `grade`          int(11)      DEFAULT NULL,
    `salary_account` varchar(31)  DEFAULT NULL COMMENT '工资卡账户',
    `job`            varchar(255) DEFAULT NULL COMMENT '岗位名称',
    `birthday`       date         DEFAULT NULL COMMENT '出生日期',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = latin1;

INSERT INTO `employee` (`id`, `name`, `sex`, `phone`, `grade`, `salary_account`, `job`, `birthday`)
VALUES (1, 'seecoder', NULL, NULL, 1, NULL, 'INVENTORY_MANAGER', NULL),
       (2, 'uncln', NULL, NULL, 1, NULL, 'INVENTORY_MANAGER', NULL),
       (3, 'kucun', NULL, NULL, 1, NULL, 'INVENTORY_MANAGER', NULL),
       (5, 'zxr', NULL, NULL, 1, NULL, 'SALE_MANAGER', NULL),
       (6, '67', NULL, NULL, 1, NULL, 'GM', NULL),
       (7, 'xiaoshou', NULL, NULL, 3, NULL, 'SALE_STAFF', NULL),
       (8, 'Leone', NULL, NULL, 3, NULL, 'GM', NULL),
       (9, 'xiaoshoujingli', NULL, NULL, 1, NULL, 'SALE_MANAGER', NULL),
       (10, 'warehouse', NULL, NULL, 2, NULL, 'INVENTORY_MANAGER', NULL),
       (11, 'hcx', NULL, NULL, 1, NULL, 'FINANCIAL_STAFF', NULL),
       (19, 'ppw4', 'male', '123', 2, '234', 'SALE_STAFF', '2011-11-11'),
       (20, 'hr', 'male', '123', 1, '123', 'HR', '2011-11-11');

DELIMITER ;;

CREATE TRIGGER `update_user_role_with_employee`
    BEFORE UPDATE
    ON `employee`
    FOR EACH ROW
begin
    update user set role=new.job where employee_id = new.id;
end;;

CREATE TRIGGER `delete_user_when_delete_employee`
    BEFORE DELETE
    ON `employee`
    FOR EACH ROW
begin
    delete from user where employee_id = old.id;
end;;

DELIMITER ;

DROP TABLE IF EXISTS `init_company_account`;
CREATE TABLE `init_company_account`
(
    `id`     int(11) NOT NULL AUTO_INCREMENT,
    `name`   varchar(255) DEFAULT NULL,
    `amount` bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `init_company_account_id_uindex` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

INSERT INTO `init_company_account` (`id`, `name`, `amount`)
VALUES (1, 'sdfhgj', 1000);

DROP TABLE IF EXISTS `init_customer`;
CREATE TABLE `init_customer`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `type`           varchar(33)    DEFAULT NULL,
    `level`          int(11)        DEFAULT NULL,
    `name`           varchar(255)   DEFAULT NULL,
    `phone`          varchar(255)   DEFAULT NULL,
    `address`        varchar(255)   DEFAULT NULL,
    `zipcode`        char(6)        DEFAULT NULL,
    `email`          varchar(255)   DEFAULT NULL,
    `line_of_credit` decimal(10, 2) DEFAULT NULL,
    `receivable`     decimal(10, 2) DEFAULT NULL,
    `payable`        decimal(10, 2) DEFAULT NULL,
    `operator`       varchar(255)   DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `init_customer_id_uindex` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='期初建账时的客户表';


DROP TABLE IF EXISTS `init_product`;
CREATE TABLE `init_product`
(
    `id`             char(16) NOT NULL,
    `name`           varchar(255)   DEFAULT NULL,
    `category_id`    int(11)        DEFAULT NULL,
    `type`           varchar(255)   DEFAULT NULL,
    `quantity`       bigint(20)     DEFAULT NULL,
    `purchase_price` decimal(10, 2) DEFAULT NULL,
    `retail_price`   decimal(10, 2) DEFAULT NULL,
    `recent_pp`      decimal(10, 2) DEFAULT NULL,
    `recent_rp`      decimal(10, 2) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `init_product_id_uindex` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用于保存期初建账时候的商品';


DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`
(
    `id`                        int(11)        NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name`                      varchar(255)   NOT NULL,
    `base_wage`                 decimal(10, 2) NOT NULL DEFAULT '0.00' COMMENT '基本工资',
    `salary_calculation_method` varchar(255)   NOT NULL COMMENT '薪资计算方式',
    `salary_payment_method`     varchar(255)   NOT NULL COMMENT '薪资发放方式',
    `post_wage`                 decimal(10, 2)          DEFAULT '0.00' COMMENT '岗位工资',
    `annual_bonus`              decimal(10, 2)          DEFAULT '0.00' COMMENT '年终奖',
    `deduct_rate`               decimal(10, 2)          DEFAULT '0.00' COMMENT '提成率',
    `grade_rate`                decimal(10, 2)          DEFAULT '0.00' COMMENT '岗位级别加薪率',
    `insurance`                 decimal(10, 2)          DEFAULT '0.00' COMMENT '失业保险金',
    `housing_fund`              decimal(10, 2)          DEFAULT '0.00' COMMENT '住房公积金',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = latin1;

INSERT INTO `job` (`id`, `name`, `base_wage`, `salary_calculation_method`, `salary_payment_method`, `post_wage`,
                   `annual_bonus`, `deduct_rate`, `grade_rate`, `insurance`, `housing_fund`)
VALUES (1, 'INVENTORY_MANAGER', 4500.00, 'POST', 'MONTHLY', 1000.00, 0.00, 0.00, 0.20, 100.00, 2000.00),
       (2, 'SALE_MANAGER', 5500.00, 'DEDUCT', 'MONTHLY', 0.00, 0.00, 0.08, 0.00, 100.00, 1500.00),
       (3, 'GM', 8000.00, 'POST', 'ANNUALLY', 5000.00, 114514.00, 0.00, 0.10, 100.00, 2000.00),
       (4, 'FINANCIAL_STAFF', 6000.00, 'POST', 'ANNUALLY', 1000.00, 0.00, 0.00, 0.15, 100.00, 1000.00),
       (5, 'SALE_STAFF', 5000.00, 'DEDUCT', 'MONTHLY', 0.00, 0.00, 0.05, 0.00, 200.00, 1000.00),
       (6, 'HR', 6000.00, 'POST', 'ANNUALLY', 5000.00, 0.00, 0.00, 0.10, 100.00, 2000.00);

DROP TABLE IF EXISTS `payment_sheet`;
CREATE TABLE `payment_sheet`
(
    `id`           varchar(31) NOT NULL COMMENT '付款单id',
    `customer`     int(11)     DEFAULT NULL COMMENT '客户',
    `operator`     varchar(30) DEFAULT NULL COMMENT '操作员',
    `total_amount` bigint(20)  DEFAULT NULL COMMENT '付款总额',
    `state`        varchar(32) DEFAULT NULL,
    `create_time`  datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='付款单';

INSERT INTO `payment_sheet` (`id`, `customer`, `operator`, `total_amount`, `state`, `create_time`)
VALUES ('XJFKD-20220612-00000', 2, 'hcx', 10000, '审批完成', '2022-06-12 20:16:24'),
       ('XJFKD-20220612-00001', 13, 'hcx', 1000, '审批失败', NULL),
       ('XJFKD-20220706-00000', 1, 'sky', 1, '审批失败', '2022-07-06 21:24:22'),
       ('XJFKD-20220706-00001', 1, 'sky', 1, '审批失败', '2022-07-06 21:26:09'),
       ('XJFYD-20220612-00001', 3, 'hcx', 100, '审批失败', NULL);

DROP TABLE IF EXISTS `payment_sheet_content`;
CREATE TABLE `payment_sheet_content`
(
    `id`                 int(11) NOT NULL AUTO_INCREMENT,
    `company_account_id` int(11)      DEFAULT NULL COMMENT '对应的银行账户id',
    `transfer_amount`    bigint(20)   DEFAULT NULL COMMENT '付款金额',
    `remark`             varchar(255) DEFAULT NULL,
    `payment_sheet_id`   varchar(31)  DEFAULT NULL COMMENT '对应的付款单id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `payment_sheet_content_id_uindex` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8 COMMENT ='付款单内容,与收款单内容一致';

INSERT INTO `payment_sheet_content` (`id`, `company_account_id`, `transfer_amount`, `remark`, `payment_sheet_id`)
VALUES (1, 1, 10000, '软工二寄了', 'XJFKD-20220612-00000'),
       (2, 1, 100, '谢谢你', 'XJFYD-20220612-00001'),
       (3, 1, 34, '你寄了', NULL),
       (4, 1, 34, '你寄了', NULL),
       (5, 1, 1000, '超市', 'XJFKD-20220612-00001'),
       (30, 3, 1, '1', 'XJFKD-20220706-00000'),
       (31, 3, 1, 'ww', 'XJFKD-20220706-00000'),
       (32, 1, 1, 'ww', 'XJFKD-20220706-00000'),
       (33, 3, 1, 'ww', 'XJFKD-20220706-00000'),
       (34, 1, 1, 'ww', 'XJFKD-20220706-00000'),
       (35, 3, 1, 'ww', 'XJFKD-20220706-00000'),
       (36, 1, 1, 'ww', 'XJFKD-20220706-00000'),
       (37, 3, 1, 'ww', 'XJFKD-20220706-00001'),
       (38, 1, 1, 'ww', 'XJFKD-20220706-00001'),
       (39, 1, 1, '111', 'XJFKD-20220706-00001');

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `id`             char(16)       NOT NULL COMMENT '分类id(11位) + 位置(5位) = 编号',
    `name`           varchar(255)   NOT NULL COMMENT '名字',
    `category_id`    int(11)        NOT NULL COMMENT '商品分类id',
    `type`           varchar(255)   NOT NULL COMMENT '商品型号',
    `quantity`       int(11)        NOT NULL COMMENT '商品数量',
    `purchase_price` decimal(10, 2) NOT NULL COMMENT '进价',
    `retail_price`   decimal(10, 2) NOT NULL COMMENT '零售价',
    `recent_pp`      decimal(10, 2) DEFAULT NULL COMMENT '最近进价',
    `recent_rp`      decimal(10, 2) DEFAULT NULL COMMENT '最近零售价',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `product` (`id`, `name`, `category_id`, `type`, `quantity`, `purchase_price`, `retail_price`, `recent_pp`,
                       `recent_rp`)
VALUES ('0000000000400000', '黑苹果电脑', 4, '戴尔(DELL)Vostro笔记本电脑5410 123色 戴尔成就3500Vostro1625D', 358, 4000.00, 4056.00, 1.00,
        2800.00),
       ('0000000000400001', '小米手机', 4, 'lalalalala', 1000, 2000.00, 3500.00, 2900.00, 3800.00),
       ('0000000000400002', 'Y9000P2022', 4, 'intel 12代i7+RTX3060', 0, 8999.00, 11999.00, NULL, NULL),
       ('0000000000500000', 'intel电脑', 5, 'iphone14maxpro', 0, 6000.00, 10000.00, NULL, NULL),
       ('0000000000500001', 'iphone', 5, 'iphone14普通版', 0, 4000.00, 8000.00, NULL, NULL),
       ('0000000000500002', '坚果', 5, 'pro3', 0, 2499.00, 3199.00, NULL, NULL),
       ('0000000000500003', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500004', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500006', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500007', 'test', 5, '试卷', 0, 3.00, 7.00, NULL, NULL),
       ('0000000000500008', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500009', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500010', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500011', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500012', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500013', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500014', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500015', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500016', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500017', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500018', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500019', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL),
       ('0000000000500020', 'test', 5, 'unknown', 0, 1.00, 2.00, NULL, NULL);

DROP TABLE IF EXISTS `promotion_sheet`;
CREATE TABLE `promotion_sheet`
(
    `customer_level` int(11) NOT NULL,
    `gift`           varchar(15) DEFAULT NULL,
    `discount`       float       DEFAULT NULL,
    `coupon`         int(11)     DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS `purchase_returns_sheet`;
CREATE TABLE `purchase_returns_sheet`
(
    `id`                varchar(31)    DEFAULT NULL COMMENT '进货退货单id',
    `purchase_sheet_id` varchar(31)    DEFAULT NULL COMMENT '关联的进货单id',
    `operator`          varchar(31)    DEFAULT NULL COMMENT '操作员',
    `state`             varchar(31)    DEFAULT NULL COMMENT '单据状态',
    `create_time`       datetime       DEFAULT NULL COMMENT '创建时间',
    `total_amount`      decimal(10, 2) DEFAULT NULL COMMENT '退货的总金额',
    `remark`            varchar(255)   DEFAULT NULL COMMENT '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `purchase_returns_sheet` (`id`, `purchase_sheet_id`, `operator`, `state`, `create_time`, `total_amount`,
                                      `remark`)
VALUES ('JHTHD-20220523-00000', 'JHD-20220523-00001', 'xiaoshoujingli', '审批完成', '2022-05-23 23:22:41', 800000.00,
        '退钱！'),
       ('JHTHD-20220523-00001', 'JHD-20220523-00000', 'xiaoshoujingli', '审批完成', '2022-05-23 23:22:54', 500000.00,
        '退钱！！！'),
       ('JHTHD-20220523-00002', 'JHD-20220523-00000', 'xiaoshoujingli', '审批完成', '2022-05-23 23:34:34', 100000.00,
        '退钱++++'),
       ('JHTHD-20220523-00003', 'JHD-20220523-00000', 'xiaoshoujingli', '审批完成', '2022-05-23 23:39:30', 200000.00,
        'mmmmm'),
       ('JHTHD-20220523-00004', 'JHD-20220523-00000', '67', '审批完成', '2022-05-23 23:42:32', 200000.00, 'mmmmk'),
       ('JHTHD-20220524-00000', 'JHD-20220523-00001', 'xiaoshoujingli', '审批完成', '2022-05-24 01:00:18', 160000.00, NULL),
       ('JHTHD-20220524-00001', 'JHD-20220523-00002', 'xiaoshoujingli', '审批失败', '2022-05-24 01:00:34', 140000.00, NULL),
       ('JHTHD-20220602-00000', 'JHD-20220523-00000', NULL, '审批失败', '2022-06-02 19:05:22', 1000000.00, NULL),
       ('JHTHD-20220602-00001', 'JHD-20220524-00002', NULL, '待二级审批', '2022-06-02 19:32:03', 1650000.00, NULL),
       ('JHTHD-20220602-00002', 'JHD-20220523-00000', NULL, '审批失败', '2022-06-02 20:17:34', 10000.00, NULL),
       ('JHTHD-20220603-00000', 'JHD-20220523-00000', NULL, '待一级审批', '2022-06-03 09:29:18', 10000.00, NULL),
       ('JHTHD-20220603-00001', 'JHD-20220523-00001', NULL, '待一级审批', '2022-06-03 10:19:51', 11200.00, NULL),
       ('JHTHD-20220603-00002', 'JHD-20220523-00001', NULL, '待一级审批', '2022-06-03 19:34:53', 22000.00, NULL),
       ('JHTHD-20220603-00003', 'JHD-20220524-00002', NULL, '待二级审批', '2022-06-03 19:36:04', 16500.00, NULL),
       ('JHTHD-20220603-00004', 'JHD-20220523-00001', NULL, '待二级审批', '2022-06-03 19:43:37', 22000.00, 'hhh'),
       ('JHTHD-20220705-00000', 'JHD-20220524-00002', 'sky', '审批失败', '2022-07-05 20:07:45', 1650000.00, NULL),
       ('JHTHD-20220705-00001', 'JHD-20220524-00002', 'sky', '审批失败', '2022-07-05 22:20:37', 1650000.00, NULL);

DROP TABLE IF EXISTS `purchase_returns_sheet_content`;
CREATE TABLE `purchase_returns_sheet_content`
(
    `id`                        int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `purchase_returns_sheet_id` varchar(31)    DEFAULT NULL COMMENT '进货退货单id',
    `pid`                       char(16)       DEFAULT NULL COMMENT '商品id',
    `quantity`                  int(11)        DEFAULT NULL COMMENT '数量',
    `total_price`               decimal(10, 2) DEFAULT NULL COMMENT '该商品的总金额',
    `unit_price`                decimal(10, 2) DEFAULT NULL COMMENT '该商品的单价',
    `remark`                    varchar(255)   DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 50
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `purchase_returns_sheet_content` (`id`, `purchase_returns_sheet_id`, `pid`, `quantity`, `total_price`,
                                              `unit_price`, `remark`)
VALUES (23, 'JHTHD-20220523-00000', '0000000000400000', 500, 600000.00, 1200.00, 'b'),
       (24, 'JHTHD-20220523-00000', '0000000000400001', 100, 200000.00, 2000.00, 'b'),
       (25, 'JHTHD-20220523-00001', '0000000000400000', 500, 500000.00, 1000.00, 'a'),
       (26, 'JHTHD-20220523-00002', '0000000000400000', 100, 100000.00, 1000.00, 'a'),
       (27, 'JHTHD-20220523-00003', '0000000000400000', 200, 200000.00, 1000.00, 'a'),
       (28, 'JHTHD-20220523-00004', '0000000000400000', 200, 200000.00, 1000.00, 'a'),
       (29, 'JHTHD-20220524-00000', '0000000000400000', 50, 60000.00, 1200.00, 'b'),
       (30, 'JHTHD-20220524-00000', '0000000000400001', 50, 100000.00, 2000.00, 'b'),
       (31, 'JHTHD-20220524-00001', '0000000000400000', 0, 0.00, 1300.00, 'c'),
       (32, 'JHTHD-20220524-00001', '0000000000400001', 50, 140000.00, 2800.00, 'c'),
       (33, 'JHTHD-20220602-00000', '0000000000400000', 1000, 1000000.00, 1000.00, 'a'),
       (34, 'JHTHD-20220602-00001', '0000000000400000', 300, 570000.00, 1900.00, ''),
       (35, 'JHTHD-20220602-00001', '0000000000400001', 400, 1080000.00, 2700.00, NULL),
       (36, 'JHTHD-20220602-00002', '0000000000400000', 10, 10000.00, 1000.00, 'a'),
       (37, 'JHTHD-20220603-00000', '0000000000400000', 10, 10000.00, 1000.00, 'a'),
       (38, 'JHTHD-20220603-00001', '0000000000400000', 1, 1200.00, 1200.00, 'b'),
       (39, 'JHTHD-20220603-00001', '0000000000400001', 5, 10000.00, 2000.00, 'b'),
       (40, 'JHTHD-20220603-00002', '0000000000400000', 10, 12000.00, 1200.00, 'b'),
       (41, 'JHTHD-20220603-00002', '0000000000400001', 5, 10000.00, 2000.00, 'b'),
       (42, 'JHTHD-20220603-00003', '0000000000400000', 3, 5700.00, 1900.00, ''),
       (43, 'JHTHD-20220603-00003', '0000000000400001', 4, 10800.00, 2700.00, NULL),
       (44, 'JHTHD-20220603-00004', '0000000000400000', 10, 12000.00, 1200.00, 'b'),
       (45, 'JHTHD-20220603-00004', '0000000000400001', 5, 10000.00, 2000.00, 'b'),
       (46, 'JHTHD-20220705-00000', '0000000000400000', 300, 570000.00, 1900.00, ''),
       (47, 'JHTHD-20220705-00000', '0000000000400001', 400, 1080000.00, 2700.00, NULL),
       (48, 'JHTHD-20220705-00001', '0000000000400000', 300, 570000.00, 1900.00, ''),
       (49, 'JHTHD-20220705-00001', '0000000000400001', 400, 1080000.00, 2700.00, NULL);

DROP TABLE IF EXISTS `purchase_sheet`;
CREATE TABLE `purchase_sheet`
(
    `id`           varchar(31) NOT NULL COMMENT '进货单单据编号（格式为：JHD-yyyyMMdd-xxxxx',
    `supplier`     int(11)        DEFAULT NULL COMMENT '供应商',
    `operator`     varchar(31)    DEFAULT NULL COMMENT '操作员',
    `remark`       varchar(255)   DEFAULT NULL COMMENT '备注',
    `total_amount` decimal(10, 2) DEFAULT NULL COMMENT '总额合计',
    `state`        varchar(31)    DEFAULT NULL COMMENT '单据状态',
    `create_time`  datetime       DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `purchase_sheet` (`id`, `supplier`, `operator`, `remark`, `total_amount`, `state`, `create_time`)
VALUES ('JHD-20220523-00000', 1, 'xiaoshoujingli', 'a', 1000000.00, '审批完成', '2022-05-23 23:13:59'),
       ('JHD-20220523-00001', 1, 'xiaoshoujingli', 'b', 2200000.00, '审批完成', '2022-05-23 23:14:34'),
       ('JHD-20220523-00002', 1, 'xiaoshoujingli', 'c', 3450000.00, '审批完成', '2022-05-23 23:15:57'),
       ('JHD-20220524-00000', 1, 'xiaoshoujingli', NULL, 2200000.00, '审批完成', '2022-05-24 00:56:54'),
       ('JHD-20220524-00001', 1, 'xiaoshoujingli', NULL, 3240000.00, '审批完成', '2022-05-24 00:57:29'),
       ('JHD-20220524-00002', 1, 'xiaoshoujingli', NULL, 1650000.00, '审批完成', '2022-05-24 01:02:04'),
       ('JHD-20220704-00000', 11, 'sky', 'www', 100.00, '审批失败', '2022-07-04 22:06:50'),
       ('JHD-20220704-00001', 11, 'sky', '测试红冲', -1000.00, '审批失败', '2022-07-04 22:07:41'),
       ('JHD-20220704-00002', 11, 'sky', 'www', 100.00, '审批失败', '2022-07-04 22:09:27'),
       ('JHD-20220704-00003', 11, 'sky', '红冲', -100.00, '审批失败', '2022-07-04 22:09:47'),
       ('JHD-20220704-00004', 3, 'sky', 'www', 100.00, '审批失败', '2022-07-04 23:10:11'),
       ('JHD-20220705-00000', 3, 'sky', 'asdfasdf', 605.00, '审批失败', '2022-07-05 01:24:42'),
       ('JHD-20220705-00001', 11, 'sky', 'qqq', 100.00, '审批失败', '2022-07-05 15:42:29'),
       ('JHD-20220705-00002', 3, 'sky', '11', 121.00, '审批失败', '2022-07-05 16:24:40'),
       ('JHD-20220705-00003', 1, 'sky', '红冲！！！', -1000000.00, '审批失败', '2022-07-05 17:19:07'),
       ('JHD-20220705-00004', 3, 'sky', 'asdfasdf', -605.00, '审批失败', '2022-07-05 17:27:27'),
       ('JHD-20220705-00005', 3, 'sky', 'asdfasdf', -605.00, '审批失败', '2022-07-05 17:30:02'),
       ('JHD-20220705-00006', 3, 'sky', 'asdfasdf', -605.00, '审批失败', '2022-07-05 17:30:07'),
       ('JHD-20220705-00007', 3, 'sky', 'ww', 121.00, '审批失败', '2022-07-05 17:45:23'),
       ('JHD-20220705-00008', 7, 'sky', 'fad', 111.00, '审批失败', '2022-07-05 17:49:04'),
       ('JHD-20220705-00009', 7, 'sky', 'fff', 25.00, '审批失败', '2022-07-05 17:51:14'),
       ('JHD-20220705-00010', 3, 'sky', 'www', 1.00, '审批失败', '2022-07-05 19:06:32'),
       ('JHD-20220705-00011', 5, 'sky', 'w', 4.00, '审批失败', '2022-07-05 19:06:59'),
       ('JHD-20220705-00012', 5, 'sky', 'w', 4.00, '审批失败', '2022-07-05 19:07:27'),
       ('JHD-20220705-00013', 5, 'sky', 'ww', 1.00, '审批失败', '2022-07-05 19:07:42'),
       ('JHD-20220705-00014', 5, 'sky', 'w', 1.00, '审批失败', '2022-07-05 19:08:17'),
       ('JHD-20220705-00015', 7, 'sky', '???', 1.00, '审批失败', '2022-07-05 19:09:38'),
       ('JHD-20220705-00016', 3, 'sky', NULL, 1.00, '审批失败', '2022-07-05 19:10:49'),
       ('JHD-20220705-00017', 1, 'sky', 'a', -1000000.00, '审批失败', '2022-07-05 19:12:52'),
       ('JHD-20220705-00018', 1, 'sky', 'a', -1000000.00, '审批失败', '2022-07-05 19:13:38'),
       ('JHD-20220705-00019', 1, 'sky', 'a', -1000000.00, '审批失败', '2022-07-05 19:14:07'),
       ('JHD-20220705-00020', 5, 'sky', '1', 1.00, '审批失败', '2022-07-05 21:28:39'),
       ('JHD-20220705-00021', 3, 'sky', '1', 1.00, '审批失败', '2022-07-05 21:31:40'),
       ('JHD-20220705-00022', 3, 'sky', '1', 1.00, '审批失败', '2022-07-05 22:20:13'),
       ('JHD-20220706-00000', 3, 'sky', 'ff', 1.00, '审批完成', '2022-07-06 21:53:00');

DROP TABLE IF EXISTS `purchase_sheet_content`;
CREATE TABLE `purchase_sheet_content`
(
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `purchase_sheet_id` varchar(31)    DEFAULT NULL COMMENT '进货单id',
    `pid`               char(16)       DEFAULT NULL COMMENT '商品id',
    `quantity`          int(11)        DEFAULT NULL COMMENT '数量',
    `unit_price`        decimal(10, 2) DEFAULT NULL COMMENT '单价',
    `total_price`       decimal(10, 2) DEFAULT NULL COMMENT '金额',
    `remark`            varchar(255)   DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 95
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `purchase_sheet_content` (`id`, `purchase_sheet_id`, `pid`, `quantity`, `unit_price`, `total_price`,
                                      `remark`)
VALUES (51, 'JHD-20220523-00000', '0000000000400000', 1000, 1000.00, 1000000.00, 'a'),
       (52, 'JHD-20220523-00001', '0000000000400000', 1000, 1200.00, 1200000.00, 'b'),
       (53, 'JHD-20220523-00001', '0000000000400001', 500, 2000.00, 1000000.00, 'b'),
       (54, 'JHD-20220523-00002', '0000000000400000', 500, 1300.00, 650000.00, 'c'),
       (55, 'JHD-20220523-00002', '0000000000400001', 1000, 2800.00, 2800000.00, 'c'),
       (56, 'JHD-20220524-00000', '0000000000400000', 500, 1500.00, 750000.00, ''),
       (57, 'JHD-20220524-00000', '0000000000400001', 500, 2900.00, 1450000.00, NULL),
       (58, 'JHD-20220524-00001', '0000000000400000', 600, 1900.00, 1140000.00, ''),
       (59, 'JHD-20220524-00001', '0000000000400001', 700, 3000.00, 2100000.00, NULL),
       (60, 'JHD-20220524-00002', '0000000000400000', 300, 1900.00, 570000.00, ''),
       (61, 'JHD-20220524-00002', '0000000000400001', 400, 2700.00, 1080000.00, NULL),
       (62, 'JHD-20220704-00000', '0000000000500001', 10, 10.00, 100.00, 'www'),
       (63, 'JHD-20220704-00001', '0000000000500003', -10, 100.00, -1000.00, '测试红冲'),
       (64, 'JHD-20220704-00002', '0000000000400000', 10, 10.00, 100.00, 'www'),
       (65, 'JHD-20220704-00003', '0000000000400000', -10, 10.00, -100.00, '红冲'),
       (66, 'JHD-20220704-00004', '0000000000400000', 10, 10.00, 100.00, 'www'),
       (67, 'JHD-20220705-00000', '0000000000400000', 11, 11.00, 121.00, 'www'),
       (68, 'JHD-20220705-00000', '0000000000500000', 22, 22.00, 484.00, '111'),
       (69, 'JHD-20220705-00001', '0000000000500000', 10, 10.00, 100.00, 'qqq'),
       (70, 'JHD-20220705-00002', '0000000000500003', 11, 11.00, 121.00, '11'),
       (71, 'JHD-20220705-00003', '0000000000400000', -1000, 1000.00, -1000000.00, 'a'),
       (72, 'JHD-20220705-00004', '0000000000400000', -11, 11.00, -121.00, 'www'),
       (73, 'JHD-20220705-00004', '0000000000500000', -22, 22.00, -484.00, '111'),
       (74, 'JHD-20220705-00005', '0000000000400000', -11, 11.00, -121.00, 'www'),
       (75, 'JHD-20220705-00005', '0000000000500000', -22, 22.00, -484.00, '111'),
       (76, 'JHD-20220705-00006', '0000000000400000', -11, 11.00, -121.00, 'www'),
       (77, 'JHD-20220705-00006', '0000000000500000', -22, 22.00, -484.00, '111'),
       (78, 'JHD-20220705-00007', '0000000000400001', 11, 11.00, 121.00, 'ww'),
       (79, 'JHD-20220705-00008', '0000000000500001', 111, 1.00, 111.00, 'fad'),
       (80, 'JHD-20220705-00009', '0000000000500000', 5, 5.00, 25.00, 'fff'),
       (81, 'JHD-20220705-00010', '0000000000400001', 1, 1.00, 1.00, 'www'),
       (82, 'JHD-20220705-00011', '0000000000400001', 2, 2.00, 4.00, 'w'),
       (83, 'JHD-20220705-00012', '0000000000400001', 2, 2.00, 4.00, 'w'),
       (84, 'JHD-20220705-00013', '0000000000400002', 1, 1.00, 1.00, 'ww'),
       (85, 'JHD-20220705-00014', '0000000000400001', 1, 1.00, 1.00, 'w'),
       (86, 'JHD-20220705-00015', '0000000000400001', 1, 1.00, 1.00, '???'),
       (87, 'JHD-20220705-00016', '0000000000400001', 1, 1.00, 1.00, ''),
       (88, 'JHD-20220705-00017', '0000000000400000', -1000, 1000.00, -1000000.00, 'a'),
       (89, 'JHD-20220705-00018', '0000000000400000', -1000, 1000.00, -1000000.00, 'a'),
       (90, 'JHD-20220705-00019', '0000000000400000', -1000, 1000.00, -1000000.00, 'a'),
       (91, 'JHD-20220705-00020', '0000000000400001', 1, 1.00, 1.00, '1'),
       (92, 'JHD-20220705-00021', '0000000000400001', 1, 1.00, 1.00, '1'),
       (93, 'JHD-20220705-00022', '0000000000500000', 1, 1.00, 1.00, '1'),
       (94, 'JHD-20220706-00000', '0000000000400000', 1, 1.00, 1.00, 'ff');

DROP TABLE IF EXISTS `salary_sheet`;
CREATE TABLE `salary_sheet`
(
    `id`             varchar(31)    NOT NULL COMMENT '工资单id',
    `employee_id`    int(11)        NOT NULL COMMENT '员工id',
    `employee_name`  varchar(255)   DEFAULT NULL COMMENT '员工姓名',
    `create_time`    datetime       NOT NULL COMMENT '创建时间',
    `job`            varchar(255)   DEFAULT NULL COMMENT '岗位',
    `base_wage`      decimal(10, 2) NOT NULL COMMENT '基本工资',
    `post_wage`      decimal(10, 2) NOT NULL COMMENT '岗位工资',
    `housing_fund`   decimal(10, 2) DEFAULT NULL COMMENT '住房公积金',
    `insurance`      decimal(10, 2) DEFAULT NULL COMMENT '失业保险',
    `total_salary`   decimal(10, 2) NOT NULL COMMENT '未税总工资',
    `tax`            decimal(10, 2) DEFAULT NULL COMMENT '税款',
    `taxed_salary`   decimal(10, 2) NOT NULL COMMENT '税后工资',
    `salary_account` varchar(31)    DEFAULT NULL COMMENT '工资卡账户',
    `state`          varchar(31)    NOT NULL COMMENT '状态',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='工资单';

INSERT INTO `salary_sheet` (`id`, `employee_id`, `employee_name`, `create_time`, `job`, `base_wage`, `post_wage`,
                            `housing_fund`, `insurance`, `total_salary`, `tax`, `taxed_salary`, `salary_account`,
                            `state`)
VALUES ('GZD-20221231-00000', 1, 'seecoder', '2022-07-07 00:13:14', 'INVENTORY_MANAGER', 3600.00, 1200.00, 2000.00,
        100.00, 2700.00, 0.00, 2700.00, NULL, '待一级审批'),
       ('GZD-20221231-00001', 2, 'uncln', '2022-07-07 00:13:14', 'INVENTORY_MANAGER', 3750.00, 1200.00, 2000.00, 100.00,
        2850.00, 0.00, 2850.00, NULL, '待一级审批'),
       ('GZD-20221231-00002', 3, 'kucun', '2022-07-07 00:13:15', 'INVENTORY_MANAGER', 3750.00, 1200.00, 2000.00, 100.00,
        2850.00, 0.00, 2850.00, NULL, '待一级审批'),
       ('GZD-20221231-00003', 5, 'zxr', '2022-07-07 00:13:15', 'SALE_MANAGER', 4583.33, 0.00, 1500.00, 100.00, 2983.33,
        0.00, 2983.33, NULL, '待一级审批'),
       ('GZD-20221231-00004', 6, '67', '2022-07-07 00:13:16', 'GM', 8000.00, 120014.00, 2000.00, 100.00, 125914.00,
        39251.30, 86662.70, NULL, '待一级审批'),
       ('GZD-20221231-00005', 7, 'xiaoshou', '2022-07-07 00:13:17', 'SALE_STAFF', 4500.00, 0.00, 1000.00, 200.00,
        3300.00, 0.00, 3300.00, NULL, '待一级审批'),
       ('GZD-20221231-00006', 8, 'Leone', '2022-07-07 00:13:17', 'GM', 8000.00, 131014.00, 2000.00, 100.00, 136914.00,
        44201.30, 92712.70, NULL, '待一级审批'),
       ('GZD-20221231-00007', 9, 'xiaoshoujingli', '2022-07-07 00:13:18', 'SALE_MANAGER', 4216.67, 0.00, 1500.00,
        100.00, 2616.67, 0.00, 2616.67, NULL, '待一级审批'),
       ('GZD-20221231-00008', 10, 'warehouse', '2022-07-07 00:13:19', 'INVENTORY_MANAGER', 3300.00, 2400.00, 2000.00,
        100.00, 3600.00, 0.00, 3600.00, NULL, '待一级审批'),
       ('GZD-20221231-00009', 11, 'hcx', '2022-07-07 00:13:19', 'FINANCIAL_STAFF', 3400.00, 1150.00, 1000.00, 100.00,
        3450.00, 0.00, 3450.00, NULL, '待一级审批'),
       ('GZD-20221231-00010', 19, 'ppw4', '2022-07-07 00:13:20', 'SALE_STAFF', 4500.00, 0.00, 1000.00, 200.00, 3300.00,
        0.00, 3300.00, '234', '待一级审批'),
       ('GZD-20221231-00011', 20, 'hr', '2022-07-07 00:13:20', 'HR', 3000.00, 5500.00, 2000.00, 100.00, 6400.00, 42.00,
        6358.00, '123', '待一级审批');

DROP TABLE IF EXISTS `sale_returns_sheet`;
CREATE TABLE `sale_returns_sheet`
(
    `id`               varchar(31)    DEFAULT NULL COMMENT '销售退货单id',
    `sale_sheet_id`    varchar(31)    DEFAULT NULL COMMENT '关联的销售单id',
    `supplier`         int(11)        DEFAULT NULL COMMENT '供应商',
    `operator`         varchar(31)    DEFAULT NULL COMMENT '操作员',
    `remark`           varchar(255)   DEFAULT NULL COMMENT '备注',
    `state`            varchar(31)    DEFAULT NULL COMMENT '单据状态',
    `create_time`      datetime       DEFAULT NULL COMMENT '创建时间',
    `salesman`         varchar(32)    DEFAULT NULL COMMENT '业务员',
    `raw_total_amount` decimal(10, 2) DEFAULT NULL COMMENT '折让前总金额',
    `discount`         decimal(10, 2) DEFAULT NULL COMMENT '折扣',
    `final_amount`     decimal(10, 2) DEFAULT NULL COMMENT '折让后金额',
    `voucher_amount`   decimal(10, 2) DEFAULT NULL COMMENT '代金券金额'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `sale_returns_sheet` (`id`, `sale_sheet_id`, `supplier`, `operator`, `remark`, `state`, `create_time`,
                                  `salesman`, `raw_total_amount`, `discount`, `final_amount`, `voucher_amount`)
VALUES ('XSTHD-20220523-00000', 'XSD-20220523-00000', 2, 'xiaoshoujingli', '卖卖卖', '审批失败', '2022-05-23 23:46:12',
        'xiaoshoujingli', 1300000.00, 0.80, 1039800.00, 200.00),
       ('XSTHD-20220524-00000', 'XSD-20220524-00000', 2, 'xiaoshoujingli', NULL, '审批完成', '2022-05-24 00:04:37',
        'xiaoshoujingli', 4200000.00, 0.80, 3359800.00, 200.00),
       ('XSTHD-20220524-00001', 'XSD-20220524-00001', 2, 'xiaoshoujingli', NULL, '审批完成', '2022-05-24 00:32:41',
        'xiaoshoujingli', 620000.00, 0.80, 495800.00, 200.00),
       ('XSTHD-20220524-00002', 'XSD-20220524-00002', 2, 'xiaoshoujingli', NULL, '审批完成', '2022-05-24 00:45:25',
        'xiaoshoujingli', 720000.00, 0.80, 575800.00, 200.00),
       ('XSTHD-20220524-00003', 'XSD-20220524-00003', 2, 'xiaoshoujingli', NULL, '审批完成', '2022-05-24 01:05:15',
        'xiaoshoujingli', 660000.00, 0.80, 527700.00, 300.00),
       ('XSTHD-20220524-00004', 'XSD-20220524-00004', 2, 'xiaoshoujingli', NULL, '审批完成', '2022-05-24 01:07:23',
        'xiaoshoujingli', 2900000.00, 0.90, 2609800.00, 200.00),
       ('XSTHD-20220603-00000', 'XSD-20220524-00000', NULL, NULL, 'pmx是阳光男孩', '审批完成', '2022-06-03 11:09:15', NULL,
        42000.00, 0.80, 33600.00, 2.00),
       ('XSTHD-20220603-00001', 'XSD-20220524-00002', 2, NULL, 'hhh', '审批失败', '2022-06-03 19:25:51', NULL, 72000.00,
        0.80, 57580.00, 20.00),
       ('XSTHD-20220603-00002', 'XSD-20220524-00002', 2, 'xiaoshou', '你好', '审批失败', '2022-06-03 19:49:32', 'xiaoshou',
        7200.00, 0.80, 5758.00, 2.00),
       ('XSTHD-20220603-00003', 'XSD-20220524-00002', 2, NULL, 'helloworld', '待二级审批', '2022-06-03 19:54:14', NULL,
        7200.00, 0.80, 5758.00, 2.00),
       ('XSTHD-20220603-00004', 'XSD-20220524-00000', 2, NULL, 'nihao', '审批失败', '2022-06-03 19:56:11', NULL, 45500.00,
        0.80, 36397.83, 2.17),
       ('XSTHD-20220603-00005', 'XSD-20220524-00002', 2, NULL, 'jjj', '审批失败', '2022-06-03 20:00:25', NULL, 24600.00,
        0.80, 19673.17, 6.83),
       ('XSTHD-20220603-00006', 'XSD-20220524-00002', 2, NULL, 'dfvsdfku', '待二级审批', '2022-06-03 20:11:55', NULL,
        14400.00, 0.80, 11516.00, 4.00),
       ('XSTHD-20220603-00007', 'XSD-20220524-00001', 2, NULL, NULL, '待一级审批', '2022-06-03 20:34:32', NULL, 26000.00,
        0.80, 20791.61, 8.39),
       ('XSTHD-20220603-00008', 'XSD-20220524-00001', 2, NULL, NULL, '待一级审批', '2022-06-03 20:35:13', NULL, 26000.00,
        0.80, 20791.61, 8.39),
       ('XSTHD-20220603-00009', 'XSD-20220524-00003', 2, NULL, NULL, '待一级审批', '2022-06-03 20:36:39', NULL, 6600.00,
        0.80, 5277.00, 3.00),
       ('XSTHD-20220603-00010', 'XSD-20220524-00002', 2, NULL, '我是67', '审批失败', '2022-06-03 20:38:05', NULL, 38400.00,
        0.80, 30709.33, 10.67),
       ('XSTHD-20220603-00011', 'XSD-20220524-00001', 2, '67', '你不会就是不会', '审批完成', '2022-06-03 20:39:26', '67', 6200.00,
        0.80, 4958.00, 2.00),
       ('XSTHD-20220606-00000', 'XSD-20220524-00002', 2, 'xiaoshou', '大作业寄了', '审批完成', '2022-06-06 13:24:16', 'xiaoshou',
        7200.00, 0.80, 5758.00, 2.00),
       ('XSTHD-20220606-00001', 'XSD-20220524-00003', 2, 'xiaoshou', 'hhh', '待一级审批', '2022-06-06 13:31:46', 'xiaoshou',
        10400.00, 0.80, 8315.27, 4.73),
       ('XSTHD-20220606-00002', 'XSD-20220524-00000', 2, 'sky', 'affg', '待一级审批', '2022-06-06 18:29:12', 'sky',
        4200000.00, 0.80, 3359800.00, 200.00),
       ('XSTHD-20220628-00000', 'XSD-20220524-00000', 2, 'xiaoshou', 'gjh', '待一级审批', '2022-06-28 19:45:28', 'xiaoshou',
        42000.00, 0.80, 33598.00, 2.00);

DROP TABLE IF EXISTS `sale_returns_sheet_content`;
CREATE TABLE `sale_returns_sheet_content`
(
    `id`                    int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sale_returns_sheet_id` varchar(31)    DEFAULT NULL COMMENT '销售退货单id',
    `pid`                   char(16)       DEFAULT NULL COMMENT '商品id',
    `quantity`              int(11)        DEFAULT NULL COMMENT '数量',
    `total_price`           decimal(10, 2) DEFAULT NULL COMMENT '该商品的总金额',
    `unit_price`            decimal(10, 2) DEFAULT NULL COMMENT '该商品的单价',
    `remark`                varchar(255)   DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 65
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `sale_returns_sheet_content` (`id`, `sale_returns_sheet_id`, `pid`, `quantity`, `total_price`, `unit_price`,
                                          `remark`)
VALUES (23, 'XSTHD-20220523-00000', '0000000000400000', 500, 600000.00, 1200.00, 'b'),
       (24, 'XSTHD-20220523-00000', '0000000000400001', 100, 200000.00, 2000.00, 'b'),
       (25, 'XSTHD-20220523-00001', '0000000000400000', 500, 500000.00, 1000.00, 'a'),
       (26, 'XSTHD-20220523-00002', '0000000000400000', 100, 100000.00, 1000.00, 'a'),
       (30, 'XSTHD-20220524-00000', '0000000000400001', 50, 100000.00, 2000.00, 'b'),
       (31, 'XSTHD-20220524-00001', '0000000000400000', 0, 0.00, 1300.00, 'c'),
       (32, 'XSTHD-20220524-00001', '0000000000400001', 50, 140000.00, 2800.00, 'c'),
       (33, 'XSTHD-20220603-00000', '0000000000400000', 6, 21000.00, 3500.00, ''),
       (34, 'XSTHD-20220603-00000', '0000000000400001', 6, 21000.00, 3500.00, NULL),
       (35, 'XSTHD-20220603-00001', '0000000000400000', 10, 30000.00, 3000.00, ''),
       (36, 'XSTHD-20220603-00001', '0000000000400001', 10, 42000.00, 4200.00, NULL),
       (37, 'XSTHD-20220603-00002', '0000000000400000', 1, 3000.00, 3000.00, ''),
       (38, 'XSTHD-20220603-00002', '0000000000400001', 1, 4200.00, 4200.00, NULL),
       (39, 'XSTHD-20220603-00003', '0000000000400000', 1, 3000.00, 3000.00, ''),
       (40, 'XSTHD-20220603-00003', '0000000000400001', 1, 4200.00, 4200.00, NULL),
       (41, 'XSTHD-20220603-00004', '0000000000400000', 6, 21000.00, 3500.00, ''),
       (42, 'XSTHD-20220603-00004', '0000000000400001', 7, 24500.00, 3500.00, NULL),
       (43, 'XSTHD-20220603-00005', '0000000000400000', 4, 12000.00, 3000.00, ''),
       (44, 'XSTHD-20220603-00005', '0000000000400001', 3, 12600.00, 4200.00, NULL),
       (45, 'XSTHD-20220603-00006', '0000000000400000', 2, 6000.00, 3000.00, ''),
       (46, 'XSTHD-20220603-00006', '0000000000400001', 2, 8400.00, 4200.00, NULL),
       (47, 'XSTHD-20220603-00007', '0000000000400000', 10, 22000.00, 2200.00, ''),
       (48, 'XSTHD-20220603-00007', '0000000000400001', 1, 4000.00, 4000.00, NULL),
       (49, 'XSTHD-20220603-00008', '0000000000400000', 10, 22000.00, 2200.00, ''),
       (50, 'XSTHD-20220603-00008', '0000000000400001', 1, 4000.00, 4000.00, NULL),
       (51, 'XSTHD-20220603-00009', '0000000000400000', 1, 2800.00, 2800.00, ''),
       (52, 'XSTHD-20220603-00009', '0000000000400001', 1, 3800.00, 3800.00, NULL),
       (53, 'XSTHD-20220603-00010', '0000000000400000', 3, 9000.00, 3000.00, ''),
       (54, 'XSTHD-20220603-00010', '0000000000400001', 7, 29400.00, 4200.00, NULL),
       (55, 'XSTHD-20220603-00011', '0000000000400000', 1, 2200.00, 2200.00, ''),
       (56, 'XSTHD-20220603-00011', '0000000000400001', 1, 4000.00, 4000.00, NULL),
       (57, 'XSTHD-20220606-00000', '0000000000400000', 1, 3000.00, 3000.00, ''),
       (58, 'XSTHD-20220606-00000', '0000000000400001', 1, 4200.00, 4200.00, NULL),
       (59, 'XSTHD-20220606-00001', '0000000000400000', 1, 2800.00, 2800.00, ''),
       (60, 'XSTHD-20220606-00001', '0000000000400001', 2, 7600.00, 3800.00, NULL),
       (61, 'XSTHD-20220606-00002', '0000000000400000', 600, 2100000.00, 3500.00, ''),
       (62, 'XSTHD-20220606-00002', '0000000000400001', 600, 2100000.00, 3500.00, NULL),
       (63, 'XSTHD-20220628-00000', '0000000000400000', 6, 21000.00, 3500.00, ''),
       (64, 'XSTHD-20220628-00000', '0000000000400001', 6, 21000.00, 3500.00, NULL);

DROP TABLE IF EXISTS `sale_sheet`;
CREATE TABLE `sale_sheet`
(
    `id`               varchar(31) NOT NULL COMMENT '销售单单据编号（格式为：XSD-yyyyMMdd-xxxxx',
    `supplier`         int(11)        DEFAULT NULL COMMENT '供应商',
    `operator`         varchar(31)    DEFAULT NULL COMMENT '操作员',
    `remark`           varchar(255)   DEFAULT NULL COMMENT '备注',
    `state`            varchar(31)    DEFAULT NULL COMMENT '单据状态',
    `create_time`      datetime       DEFAULT NULL COMMENT '创建时间',
    `salesman`         varchar(32)    DEFAULT NULL COMMENT '业务员',
    `raw_total_amount` decimal(10, 2) DEFAULT NULL COMMENT '折让前总金额',
    `discount`         decimal(10, 2) DEFAULT NULL COMMENT '折扣',
    `final_amount`     decimal(10, 2) DEFAULT NULL COMMENT '折让后再减去代金券的金额',
    `voucher_amount`   decimal(10, 2) DEFAULT NULL COMMENT '代金券金额',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `sale_sheet` (`id`, `supplier`, `operator`, `remark`, `state`, `create_time`, `salesman`,
                          `raw_total_amount`, `discount`, `final_amount`, `voucher_amount`)
VALUES ('XSD-20220523-00000', 2, 'xiaoshoujingli', '卖卖卖', '审批失败', '2022-05-23 23:46:12', 'xiaoshoujingli', 1300000.00,
        0.80, 1039800.00, 200.00),
       ('XSD-20220524-00000', 2, 'xiaoshoujingli', NULL, '审批完成', '2022-05-24 00:04:37', 'xiaoshoujingli', 4200000.00,
        0.80, 3359800.00, 200.00),
       ('XSD-20220524-00001', 2, 'ppw4', NULL, '审批完成', '2022-05-24 00:32:41', 'ppw4', 620000.00, 0.80, 495800.00,
        200.00),
       ('XSD-20220524-00002', 2, 'ppw4', NULL, '审批完成', '2022-05-24 00:45:25', 'ppw4', 720000.00, 0.80, 575800.00,
        200.00),
       ('XSD-20220524-00003', 2, 'xiaoshoujingli', NULL, '待二级审批', '2022-05-24 01:05:15', 'xiaoshoujingli', 660000.00,
        0.80, 527700.00, 300.00),
       ('XSD-20220524-00004', 2, 'xiaoshoujingli', NULL, '审批失败', '2022-05-24 01:07:23', 'xiaoshoujingli', 2900000.00,
        0.90, 2609800.00, 200.00),
       ('XSD-20220612-00000', 6, 'xiaoshou', 'df', '待一级审批', '2022-06-12 17:13:39', 'xiaoshou', 100.00, 0.90, -110.00,
        200.00),
       ('XSD-20220705-00000', 2, 'sky', '11', '审批失败', '2022-07-05 21:49:31', 'sky', 1.00, 1.00, 1.00, 0.00),
       ('XSD-20220705-00001', 2, 'sky', '11', '审批失败', '2022-07-05 21:50:19', 'sky', 1.00, 1.00, 1.00, 0.00),
       ('XSD-20220705-00002', 2, 'sky', '11', '审批失败', '2022-07-05 21:52:22', 'sky', 0.00, 1.00, 0.00, 0.00);

DROP TABLE IF EXISTS `sale_sheet_content`;
CREATE TABLE `sale_sheet_content`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `sale_sheet_id` varchar(31)    DEFAULT NULL COMMENT '销售单id',
    `pid`           char(16)       DEFAULT NULL COMMENT '商品id',
    `quantity`      int(11)        DEFAULT NULL COMMENT '数量',
    `unit_price`    decimal(10, 2) DEFAULT NULL COMMENT '单价',
    `total_price`   decimal(10, 2) DEFAULT NULL COMMENT '金额',
    `remark`        varchar(255)   DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 56
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `sale_sheet_content` (`id`, `sale_sheet_id`, `pid`, `quantity`, `unit_price`, `total_price`, `remark`)
VALUES (26, 'XSD-20220523-00000', '0000000000400000', 100, 5000.00, 500000.00, '卖卖卖1'),
       (27, 'XSD-20220523-00000', '0000000000400001', 400, 2000.00, 800000.00, '卖卖卖2'),
       (28, 'XSD-20220524-00000', '0000000000400000', 600, 3500.00, 2100000.00, ''),
       (29, 'XSD-20220524-00000', '0000000000400001', 600, 3500.00, 2100000.00, NULL),
       (30, 'XSD-20220524-00001', '0000000000400000', 100, 2200.00, 220000.00, ''),
       (31, 'XSD-20220524-00001', '0000000000400001', 100, 4000.00, 400000.00, NULL),
       (32, 'XSD-20220524-00002', '0000000000400000', 100, 3000.00, 300000.00, ''),
       (33, 'XSD-20220524-00002', '0000000000400001', 100, 4200.00, 420000.00, NULL),
       (34, 'XSD-20220524-00003', '0000000000400000', 100, 2800.00, 280000.00, ''),
       (35, 'XSD-20220524-00003', '0000000000400001', 100, 3800.00, 380000.00, NULL),
       (36, 'XSD-20220524-00004', '0000000000400000', 300, 3000.00, 900000.00, ''),
       (37, 'XSD-20220524-00004', '0000000000400001', 500, 4000.00, 2000000.00, NULL),
       (38, 'XSD-20220612-00000', '0000000000400000', 1, 100.00, 100.00, 'dfas'),
       (47, 'XSD-20220705-00000', '0000000000400000', 1, 1.00, 1.00, '11'),
       (48, 'XSD-20220705-00001', '0000000000400000', 1, 1.00, 1.00, '11'),
       (49, 'XSD-20220705-00002', '0000000000400000', 0, 0.00, 0.00, '11');

DROP TABLE IF EXISTS `tax`;
CREATE TABLE `tax`
(
    `base`            decimal(10, 2) NOT NULL COMMENT '税前所得(减去起征点后)',
    `rate`            decimal(10, 2) NOT NULL COMMENT '税率',
    `quick_deduction` decimal(10, 2) DEFAULT '0.00' COMMENT '速算扣除数',
    PRIMARY KEY (`base`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

INSERT INTO `tax` (`base`, `rate`, `quick_deduction`)
VALUES (-5000.00, 0.00, 0.00),
       (0.00, 0.03, 0.00),
       (3000.00, 0.10, 210.00),
       (12000.00, 0.20, 1410.00),
       (25000.00, 0.25, 2660.00),
       (35000.00, 0.30, 4410.00),
       (55000.00, 0.35, 7160.00),
       (80000.00, 0.45, 15160.00);

DROP TABLE IF EXISTS `transfer_list_sheet`;
CREATE TABLE `transfer_list_sheet`
(
    `id`                  int(11) NOT NULL AUTO_INCREMENT COMMENT '转账列表id',
    `company_account_id`  int(11) NOT NULL COMMENT '关联的银行账户id',
    `transfer_amount`     bigint(20)   DEFAULT NULL COMMENT '转账金额',
    `collection_sheet_id` varchar(31)  DEFAULT NULL,
    `remark`              varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `transfer_list_sheet_id_uindex` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 28
  DEFAULT CHARSET = utf8 COMMENT ='转账列表(适用于收款单和付款单)';

INSERT INTO `transfer_list_sheet` (`id`, `company_account_id`, `transfer_amount`, `collection_sheet_id`, `remark`)
VALUES (1, 1, 100000, 'SKD-20220612-00000', '你好'),
       (5, 1, 1200, 'SKD-20220612-00001', '我是6f'),
       (6, 1, 100, 'SKD-20220612-00002', '我是hcx'),
       (7, 1, 90, 'SKD-20220612-00003', '我是czy'),
       (8, 1, 30, 'SKD-20220612-00004', '我是fcr'),
       (9, 1, 40, 'SKD-20220612-00005', '我是万皮皮'),
       (10, 1, 50, 'SKD-20220612-00005', '我是wpp'),
       (11, 3, 100, 'SKD-20220627-00000', 'sfkjb'),
       (12, 1, 10, 'SKD-20220627-00001', 'de'),
       (21, 1, 1, 'SKD-20220706-00000', ''),
       (22, 1, 100000, 'SKD-20220706-00001', '你好'),
       (23, 1, -100000, 'SKD-20220706-00002', '你好'),
       (24, 1, -100000, 'SKD-20220706-00003', '你好'),
       (25, 1, 0, 'SKD-20220706-00004', 'GG'),
       (26, 1, 0, 'SKD-20220706-00005', 'gg'),
       (27, 1, -100000, 'SKD-20220706-00006', '你好');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                int(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `name`              varchar(255) NOT NULL COMMENT '用户名',
    `password`          varchar(255) NOT NULL COMMENT '用户密码',
    `role`              varchar(255) NOT NULL COMMENT '用户身份',
    `attendance`        int(11) DEFAULT '0' COMMENT '打卡次数',
    `last_sign_in_time` date    DEFAULT NULL COMMENT '上次打卡日期',
    `employee_id`       int(11) DEFAULT NULL COMMENT '对应的员工id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 25
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `user` (`id`, `name`, `password`, `role`, `attendance`, `last_sign_in_time`, `employee_id`)
VALUES (1, 'seecoder', '123456', 'INVENTORY_MANAGER', 24, '2022-07-06', 1),
       (2, 'uncln', '123456', 'INVENTORY_MANAGER', 25, '2022-07-06', 2),
       (3, 'kucun', '123456', 'INVENTORY_MANAGER', 25, '2022-07-06', 3),
       (4, 'sky', '123456', 'ADMIN', 25, '2022-07-06', NULL),
       (5, 'zxr', '123456', 'SALE_MANAGER', 25, '2022-07-06', 5),
       (6, '67', '123456', 'GM', 26, '2022-07-06', 6),
       (7, 'xiaoshou', '123456', 'SALE_STAFF', 27, '2022-07-06', 7),
       (8, 'Leone', '123456', 'GM', 24, '2022-07-06', 8),
       (9, 'xiaoshoujingli', '123456', 'SALE_MANAGER', 23, '2022-07-06', 9),
       (12, 'warehouse', 'warehouse', 'INVENTORY_MANAGER', 22, '2022-07-06', 10),
       (13, 'hcx', '123456', 'FINANCIAL_STAFF', 17, '2022-07-06', 11),
       (14, 'bobby', '123456', 'ADMIN', 15, '2022-07-06', NULL),
       (16, 'wpp', 'wpp', 'HR', 17, '2022-07-06', NULL),
       (17, 'ppw', '123456', 'GM', 14, '2022-07-06', NULL),
       (18, 'aaa', 'aaa', 'INVENTORY_MANAGER', 25, '2022-07-06', NULL),
       (21, 'ppw4', '123456', 'SALE_STAFF', 27, '2022-07-06', 19),
       (22, 'Joiffer', '123456', 'GM', 14, '2022-07-06', NULL),
       (23, 'hr', '123456', 'HR', 15, '2022-07-06', 20),
       (24, '123', '123', 'SALE_MANAGER', 20, '2022-07-06', NULL);

DELIMITER ;;

CREATE TRIGGER `check_time_when_sign_in`
    BEFORE UPDATE
    ON `user`
    FOR EACH ROW
BEGIN
    if new.attendance > old.attendance
    then
        SET NEW.last_sign_in_time = now();
    end if;
END;;

DELIMITER ;

DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`
(
    `id`              int(11)        NOT NULL AUTO_INCREMENT COMMENT '库存id',
    `pid`             char(16)       NOT NULL COMMENT '商品编号',
    `quantity`        int(11)        NOT NULL COMMENT '数量',
    `purchase_price`  decimal(10, 2) NOT NULL COMMENT '进价',
    `batch_id`        int(11)        NOT NULL COMMENT '批次',
    `production_date` datetime DEFAULT NULL COMMENT '出厂日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `warehouse` (`id`, `pid`, `quantity`, `purchase_price`, `batch_id`, `production_date`)
VALUES (16, '0000000000400000', 0, 1000.00, 0, NULL),
       (17, '0000000000400000', 152, 1200.00, 1, NULL),
       (18, '0000000000400001', 350, 2000.00, 1, NULL),
       (19, '0000000000400000', 6, 1300.00, 2, NULL),
       (20, '0000000000400001', 108, 2800.00, 2, NULL),
       (21, '0000000000400000', 200, 1900.00, 3, NULL),
       (22, '0000000000400001', 400, 2700.00, 3, NULL);

DROP TABLE IF EXISTS `warehouse_input_sheet`;
CREATE TABLE `warehouse_input_sheet`
(
    `id`                varchar(32) NOT NULL COMMENT 'RKD + 日期 + index = 入库单编号',
    `batch_id`          int(11)     NOT NULL COMMENT '批次',
    `operator`          varchar(255) DEFAULT NULL COMMENT '操作员',
    `create_time`       datetime    NOT NULL COMMENT '创建时间',
    `state`             varchar(31)  DEFAULT NULL COMMENT '单据状态',
    `purchase_sheet_id` varchar(31)  DEFAULT NULL COMMENT '关联的进货单id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `warehouse_input_sheet` (`id`, `batch_id`, `operator`, `create_time`, `state`, `purchase_sheet_id`)
VALUES ('RKD-20220523-00000', 0, 'kucun', '2022-05-23 23:17:41', '审批完成', 'JHD-20220523-00000'),
       ('RKD-20220523-00001', 1, 'kucun', '2022-05-23 23:17:42', '审批完成', 'JHD-20220523-00001'),
       ('RKD-20220523-00002', 2, 'kucun', '2022-05-23 23:17:44', '审批完成', 'JHD-20220523-00002'),
       ('RKD-20220524-00000', 3, 'kucun', '2022-05-24 01:02:31', '审批完成', 'JHD-20220524-00002'),
       ('RKD-20220703-00000', 4, NULL, '2022-07-03 22:06:00', '草稿', 'JHD-20220524-00001'),
       ('RKD-20220706-00000', 5, NULL, '2022-07-06 21:50:41', '草稿', 'JHD-20220524-00001'),
       ('RKD-20220706-00006', 6, NULL, '2022-07-06 21:53:19', '草稿', 'JHD-20220524-00000'),
       ('RKD-20220706-00007', 7, NULL, '2022-07-06 21:53:44', '草稿', 'JHD-20220706-00000');

DROP TABLE IF EXISTS `warehouse_input_sheet_content`;
CREATE TABLE `warehouse_input_sheet_content`
(
    `id`                       int(11)        NOT NULL AUTO_INCREMENT COMMENT '主键',
    `warehouse_input_sheet_id` varchar(31)    NOT NULL COMMENT '入库单编号',
    `pid`                      char(16)       NOT NULL COMMENT '商品id',
    `quantity`                 int(11)        NOT NULL COMMENT '商品数量',
    `purchase_price`           decimal(10, 2) NOT NULL COMMENT '单价',
    `production_date`          datetime     DEFAULT NULL COMMENT '出厂日期',
    `remark`                   varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 61
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `warehouse_input_sheet_content` (`id`, `warehouse_input_sheet_id`, `pid`, `quantity`, `purchase_price`,
                                             `production_date`, `remark`)
VALUES (47, 'RKD-20220523-00000', '0000000000400000', 1000, 1000.00, NULL, 'a'),
       (48, 'RKD-20220523-00001', '0000000000400000', 1000, 1200.00, NULL, 'b'),
       (49, 'RKD-20220523-00001', '0000000000400001', 500, 2000.00, NULL, 'b'),
       (50, 'RKD-20220523-00002', '0000000000400000', 500, 1300.00, NULL, 'c'),
       (51, 'RKD-20220523-00002', '0000000000400001', 1000, 2800.00, NULL, 'c'),
       (52, 'RKD-20220524-00000', '0000000000400000', 300, 1900.00, NULL, ''),
       (53, 'RKD-20220524-00000', '0000000000400001', 400, 2700.00, NULL, NULL),
       (54, 'RKD-20220703-00000', '0000000000400000', 600, 1900.00, NULL, ''),
       (55, 'RKD-20220703-00000', '0000000000400001', 700, 3000.00, NULL, NULL),
       (56, 'RKD-20220706-00000', '0000000000400000', 600, 1900.00, NULL, ''),
       (57, 'RKD-20220706-00000', '0000000000400001', 700, 3000.00, NULL, NULL),
       (58, 'RKD-20220706-00006', '0000000000400000', 500, 1500.00, NULL, ''),
       (59, 'RKD-20220706-00006', '0000000000400001', 500, 2900.00, NULL, NULL),
       (60, 'RKD-20220706-00007', '0000000000400000', 1, 1.00, NULL, 'ff');

DROP TABLE IF EXISTS `warehouse_output_sheet`;
CREATE TABLE `warehouse_output_sheet`
(
    `id`            varchar(31) NOT NULL COMMENT 'CKD + date + index = 出库单id',
    `operator`      varchar(255) DEFAULT NULL COMMENT '操作员名字',
    `create_time`   datetime    NOT NULL COMMENT '创建时间',
    `sale_sheet_id` varchar(31)  DEFAULT NULL COMMENT '销售单id',
    `state`         varchar(31)  DEFAULT NULL COMMENT '单据状态',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `warehouse_output_sheet` (`id`, `operator`, `create_time`, `sale_sheet_id`, `state`)
VALUES ('CKD-20220524-00000', 'kucun', '2022-05-24 00:05:32', 'XSD-20220524-00000', '审批完成'),
       ('CKD-20220524-00001', 'kucun', '2022-05-24 00:33:12', 'XSD-20220524-00001', '审批完成'),
       ('CKD-20220524-00002', 'kucun', '2022-05-24 00:45:38', 'XSD-20220524-00002', '审批完成'),
       ('CKD-20220603-00000', NULL, '2022-06-03 11:53:30', 'XSD-20220524-00003', '审批完成');

DROP TABLE IF EXISTS `warehouse_output_sheet_content`;
CREATE TABLE `warehouse_output_sheet_content`
(
    `id`                        int(11)        NOT NULL AUTO_INCREMENT COMMENT '出库商品列表id',
    `pid`                       char(16)       NOT NULL COMMENT '商品id',
    `warehouse_output_sheet_id` varchar(31)    NOT NULL COMMENT '出库单单据编号',
    `batch_id`                  int(11)      DEFAULT NULL COMMENT '批次',
    `quantity`                  int(11)        NOT NULL COMMENT '数量',
    `sale_price`                decimal(10, 2) NOT NULL COMMENT '对应批次的单价',
    `remark`                    varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 61
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

INSERT INTO `warehouse_output_sheet_content` (`id`, `pid`, `warehouse_output_sheet_id`, `batch_id`, `quantity`,
                                              `sale_price`, `remark`)
VALUES (28, '0000000000400000', 'CKD-20220524-00000', 2, 600, 3500.00, ''),
       (29, '0000000000400000', 'CKD-20220524-00000', 1, 600, 3500.00, ''),
       (30, '0000000000400001', 'CKD-20220524-00000', 2, 600, 3500.00, NULL),
       (35, '0000000000400000', 'CKD-20220524-00001', 1, 100, 2200.00, ''),
       (36, '0000000000400001', 'CKD-20220524-00001', 2, 100, 4000.00, NULL),
       (37, '0000000000400000', 'CKD-20220524-00002', 1, 100, 3000.00, ''),
       (38, '0000000000400001', 'CKD-20220524-00002', 2, 100, 4200.00, NULL),
       (45, '0000000000400000', 'CKD-20220603-00000', 3, 100, 2800.00, ''),
       (46, '0000000000400001', 'CKD-20220603-00000', 2, 100, 3800.00, NULL);

-- 2022-07-06 16:22:09