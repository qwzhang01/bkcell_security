/*
Navicat MySQL Data Transfer

Source Server         : beikeo2o_dev
Source Server Version : 50544
Source Host           : 192.168.10.20:3306
Source Database       : bkcell_security

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2018-02-08 14:30:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for global_org_tb
-- ----------------------------
DROP TABLE IF EXISTS `global_org_tb`;
CREATE TABLE `global_org_tb` (
  `OrgId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `OrgNo` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '组织编号',
  `OrgName` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '组织名称',
  `ParentId` int(11) NOT NULL DEFAULT '0' COMMENT '上级组织Id：如果是顶组则为0。',
  `LevelCode` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '层级代码（所有的父级组织的id:如,1,2,5,）',
  `Remark` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `CreatedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '添加人',
  `CreatedOn` datetime DEFAULT NULL COMMENT '添加时间',
  `ModifiedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `ModifiedOn` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`OrgId`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组织架构表';

-- ----------------------------
-- Records of global_org_tb
-- ----------------------------

-- ----------------------------
-- Table structure for global_permission_role_tb
-- ----------------------------
DROP TABLE IF EXISTS `global_permission_role_tb`;
CREATE TABLE `global_permission_role_tb` (
  `RpId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleId` int(11) DEFAULT NULL,
  `PermissionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`RpId`),
  KEY `global_permission_role_ibfk1` (`PermissionId`),
  CONSTRAINT `global_permission_role_ibfk1` FOREIGN KEY (`PermissionId`) REFERENCES `global_permission_tb` (`PermissionId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21725 DEFAULT CHARSET=utf8 COMMENT='权限角色 中间表';

-- ----------------------------
-- Records of global_permission_role_tb
-- ----------------------------
INSERT INTO `global_permission_role_tb` VALUES ('21708', '1', '11');
INSERT INTO `global_permission_role_tb` VALUES ('21709', '1', '12');
INSERT INTO `global_permission_role_tb` VALUES ('21710', '1', '13');
INSERT INTO `global_permission_role_tb` VALUES ('21711', '1', '7');
INSERT INTO `global_permission_role_tb` VALUES ('21712', '1', '8');
INSERT INTO `global_permission_role_tb` VALUES ('21713', '1', '9');
INSERT INTO `global_permission_role_tb` VALUES ('21714', '1', '10');
INSERT INTO `global_permission_role_tb` VALUES ('21715', '1', '6');
INSERT INTO `global_permission_role_tb` VALUES ('21716', '1', '1');
INSERT INTO `global_permission_role_tb` VALUES ('21717', '1', '3');
INSERT INTO `global_permission_role_tb` VALUES ('21718', '1', '4');
INSERT INTO `global_permission_role_tb` VALUES ('21719', '1', '2');
INSERT INTO `global_permission_role_tb` VALUES ('21720', '1', '5');
INSERT INTO `global_permission_role_tb` VALUES ('21721', '2', '11');
INSERT INTO `global_permission_role_tb` VALUES ('21722', '2', '12');
INSERT INTO `global_permission_role_tb` VALUES ('21723', '2', '13');
INSERT INTO `global_permission_role_tb` VALUES ('21724', '2', '7');

-- ----------------------------
-- Table structure for global_permission_tb
-- ----------------------------
DROP TABLE IF EXISTS `global_permission_tb`;
CREATE TABLE `global_permission_tb` (
  `PermissionId` int(11) NOT NULL AUTO_INCREMENT,
  `Menu` varchar(50) NOT NULL DEFAULT '' COMMENT '主模块名称',
  `SubMenu` varchar(50) DEFAULT NULL COMMENT '子模块名称',
  `Operate` varchar(50) DEFAULT NULL COMMENT '操作名称',
  `SortNo` varchar(10) DEFAULT NULL,
  `MenuIcon` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单图标的css的class(同一子菜单的全部相同)',
  `MenuFlag` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单控制器缩写(同一子菜单的全部相同)',
  `MenuHost` varchar(100) NOT NULL DEFAULT '' COMMENT '目录网址',
  `MenuRoute` varchar(100) NOT NULL DEFAULT '' COMMENT '目录路由',
  PRIMARY KEY (`PermissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=1767 DEFAULT CHARSET=utf8 COMMENT='权限信息表';

-- ----------------------------
-- Records of global_permission_tb
-- ----------------------------
INSERT INTO `global_permission_tb` VALUES ('1', '系统管理', '参数管理', '查看', '122000', 'fa fa-gear', '', 'http://canyou.xyz:8086', '#');
INSERT INTO `global_permission_tb` VALUES ('2', '系统管理', '参数配置', '查看', '123000', 'fa fa-gear', '', 'http://canyou.xyz:8086', '#');
INSERT INTO `global_permission_tb` VALUES ('3', '系统管理', '参数管理', '正常锁定', '122100', 'fa fa-gear', '', 'http://canyou.xyz:8086', '#');
INSERT INTO `global_permission_tb` VALUES ('4', '系统管理', '参数管理', '编辑', '122200', 'fa fa-gear', '', 'http://canyou.xyz:8086', '#');
INSERT INTO `global_permission_tb` VALUES ('5', '系统管理', '参数配置', '编辑', '123100', 'fa fa-gear', '', 'http://canyou.xyz:8086', '#');
INSERT INTO `global_permission_tb` VALUES ('6', '系统管理', '角色管理', '分配权限', '121030', 'fa fa-gear', 'role', 'http://canyou.xyz:8088', '/system/role');
INSERT INTO `global_permission_tb` VALUES ('7', '系统管理', '用户管理', '重置密码', '120030', 'fa fa-gear', 'user', 'http://canyou.xyz:8088', '/system/user');
INSERT INTO `global_permission_tb` VALUES ('8', '系统管理', '角色管理', '查看', '121000', 'fa fa-gear', 'role', 'http://canyou.xyz:8088', '/system/role');
INSERT INTO `global_permission_tb` VALUES ('9', '系统管理', '角色管理', '新增', '121010', 'fa fa-gear', 'role', 'http://canyou.xyz:8088', '/system/role');
INSERT INTO `global_permission_tb` VALUES ('10', '系统管理', '角色管理', '编辑', '121020', 'fa fa-gear', 'role', 'http://canyou.xyz:8088', '/system/role');
INSERT INTO `global_permission_tb` VALUES ('11', '系统管理', '用户管理', '新增', '120000', 'fa fa-gear', 'user', 'http://canyou.xyz:8088', '/system/user');
INSERT INTO `global_permission_tb` VALUES ('12', '系统管理', '用户管理', '编辑', '120010', 'fa fa-gear', 'user', 'http://canyou.xyz:8088', '/system/user');
INSERT INTO `global_permission_tb` VALUES ('13', '系统管理', '用户管理', '删除', '120020', 'fa fa-gear', 'user', 'http://canyou.xyz:8088', '/system/user');

-- ----------------------------
-- Table structure for global_user_tb
-- ----------------------------
DROP TABLE IF EXISTS `global_user_tb`;
CREATE TABLE `global_user_tb` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `Password` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '密码',
  `PasswordType` int(1) NOT NULL DEFAULT '0' COMMENT '0常规密码，1免密登陆',
  `RealName` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '真实姓名',
  `PhoneNum` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机号码',
  `OrgId` int(11) NOT NULL DEFAULT '0' COMMENT '所属组织id',
  `CreatedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `ModifiedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ModifiedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=711 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表';

-- ----------------------------
-- Records of global_user_tb
-- ----------------------------
INSERT INTO `global_user_tb` VALUES ('1', 'admin', '67B14728AD9902AECBA32E22FA4F6BD', '0', '管理员', '13994219417', '89', 'admin', '2013-10-11 15:16:56', 'admin', '2018-02-06 17:13:20');
INSERT INTO `global_user_tb` VALUES ('710', 'test', '67B14728AD9902AECBA32E22FA4F6BD', '0', '张奇文', '13994219417', '0', null, null, null, null);

-- ----------------------------
-- Table structure for param_code_tb
-- ----------------------------
DROP TABLE IF EXISTS `param_code_tb`;
CREATE TABLE `param_code_tb` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` int(50) NOT NULL DEFAULT '0' COMMENT '编码',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '编码类别',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `is_locked` int(11) NOT NULL DEFAULT '0' COMMENT '0正常，1锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12915 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='字典信息表：分类字典信息表';

-- ----------------------------
-- Records of param_code_tb
-- ----------------------------
INSERT INTO `param_code_tb` VALUES ('10001', '1', '预约成功', 'APSTA', '预约状态', '1');
INSERT INTO `param_code_tb` VALUES ('10002', '3', '已取样', 'APSTA', '预约状态', '0');
INSERT INTO `param_code_tb` VALUES ('10003', '2', '取消预约', 'APSTA', '预约状态', '0');
INSERT INTO `param_code_tb` VALUES ('10101', '8', '未签署', 'COSTA', '合同状态', '0');
INSERT INTO `param_code_tb` VALUES ('10102', '1', '已签署', 'COSTA', '合同状态', '0');
INSERT INTO `param_code_tb` VALUES ('10103', '2', '已过期', 'COSTA', '合同状态', '0');
INSERT INTO `param_code_tb` VALUES ('10104', '-1', '已作废', 'COSTA', '合同状态', '0');
INSERT INTO `param_code_tb` VALUES ('10210', '1', '收入', 'POST', '积分状态', '0');
INSERT INTO `param_code_tb` VALUES ('10211', '2', '支出', 'POST', '积分状态', '0');
INSERT INTO `param_code_tb` VALUES ('10212', '3', '锁定', 'POST', '积分状态', '0');
INSERT INTO `param_code_tb` VALUES ('10213', '4', '取消订单返还', 'POST', '积分状态', '0');
INSERT INTO `param_code_tb` VALUES ('10220', '1', '申请待审核', 'ENCHSTA', '提现状态', '0');
INSERT INTO `param_code_tb` VALUES ('10221', '2', '发放成功', 'ENCHSTA', '提现状态', '0');
INSERT INTO `param_code_tb` VALUES ('10222', '3', '拒绝发放', 'ENCHSTA', '提现状态', '0');
INSERT INTO `param_code_tb` VALUES ('10230', '1', '微信', 'ACCSTY', '提现账号类型', '0');
INSERT INTO `param_code_tb` VALUES ('10231', '2', '支付宝', 'ACCSTY', '提现账号类型', '0');
INSERT INTO `param_code_tb` VALUES ('10240', '1', '正常', 'RESTA', '现金红包状态', '0');
INSERT INTO `param_code_tb` VALUES ('10241', '2', '锁定', 'RESTA', '现金红包状态', '0');
INSERT INTO `param_code_tb` VALUES ('10250', '1', '赠送', 'RCDTY', '消费类型', '0');
INSERT INTO `param_code_tb` VALUES ('10251', '2', '消费', 'RCDTY', '消费类型', '0');
INSERT INTO `param_code_tb` VALUES ('10301', '10', '新客户', 'CTYPE', '客户类型', '0');
INSERT INTO `param_code_tb` VALUES ('10302', '30', '无意向客户', 'CTYPE', '客户类型', '0');
INSERT INTO `param_code_tb` VALUES ('10303', '40', '意向客户', 'CTYPE', '客户类型', '0');
INSERT INTO `param_code_tb` VALUES ('10304', '50', '成交客户', 'CTYPE', '客户类型', '0');
INSERT INTO `param_code_tb` VALUES ('10305', '60', '退款客户', 'CTYPE', '客户类型', '0');
INSERT INTO `param_code_tb` VALUES ('10350', '0', '后台', 'FBSOUR', '请求终端来源', '0');
INSERT INTO `param_code_tb` VALUES ('10351', '1', '安卓客户端', 'FBSOUR', '请求终端来源', '0');
INSERT INTO `param_code_tb` VALUES ('10352', '2', '苹果客户端', 'FBSOUR', '请求终端来源', '0');
INSERT INTO `param_code_tb` VALUES ('10353', '3', '安卓顾问端', 'FBSOUR', '请求终端来源', '0');
INSERT INTO `param_code_tb` VALUES ('10354', '4', '苹果顾问端', 'FBSOUR', '请求终端来源', '0');
INSERT INTO `param_code_tb` VALUES ('10355', '5', 'WebApp', 'FBSOUR', '请求终端来源', '0');
INSERT INTO `param_code_tb` VALUES ('10356', '6', '微信公众号', 'FBSOUR', '请求终端来源', '0');
INSERT INTO `param_code_tb` VALUES ('10357', '7', '微信小程序', 'FBSOUR', '请求终端来源', '0');
INSERT INTO `param_code_tb` VALUES ('10360', '-1', '非法来源', 'FBSOUR', '请求终端来源', '0');
INSERT INTO `param_code_tb` VALUES ('10401', '1', '待付款', 'ORST', '订单业务状态', '0');
INSERT INTO `param_code_tb` VALUES ('10402', '2', '待预约', 'ORST', '订单业务状态', '0');
INSERT INTO `param_code_tb` VALUES ('10403', '3', '待取样', 'ORST', '订单业务状态', '0');
INSERT INTO `param_code_tb` VALUES ('10404', '4', '制备中', 'ORST', '订单业务状态', '0');
INSERT INTO `param_code_tb` VALUES ('10405', '5', '已存储', 'ORST', '订单业务状态', '0');
INSERT INTO `param_code_tb` VALUES ('10501', '1', '新建', 'ACTST', '活动状态', '0');
INSERT INTO `param_code_tb` VALUES ('10502', '3', '待审核', 'ACTST', '活动状态', '0');
INSERT INTO `param_code_tb` VALUES ('10503', '4', '已审核', 'ACTST', '活动状态', '0');
INSERT INTO `param_code_tb` VALUES ('10504', '11', '已过期', 'ACTST', '活动状态', '0');
INSERT INTO `param_code_tb` VALUES ('10506', '1', '团购活动', 'ACTTYPE', '活动类型', '0');
INSERT INTO `param_code_tb` VALUES ('10507', '2', '常规活动', 'ACTTYPE', '活动类型', '0');
INSERT INTO `param_code_tb` VALUES ('10508', '3', '员工优惠', 'ACTTYPE', '活动类型', '0');
INSERT INTO `param_code_tb` VALUES ('10510', '1', 'VIP卡', 'COUTY', '卡券类型', '0');
INSERT INTO `param_code_tb` VALUES ('10511', '2', '优惠券', 'COUTY', '卡券类型', '0');
INSERT INTO `param_code_tb` VALUES ('10521', '1', '未审批', 'CTSTA', '卡券类型状态', '0');
INSERT INTO `param_code_tb` VALUES ('10522', '2', '已审批', 'CTSTA', '卡券类型状态', '0');
INSERT INTO `param_code_tb` VALUES ('10530', '1', '未激活', 'CCSTA', '卡卷使用状态', '0');
INSERT INTO `param_code_tb` VALUES ('10531', '2', '已分享', 'CCSTA', '卡卷使用状态', '0');
INSERT INTO `param_code_tb` VALUES ('10532', '3', '未使用', 'CCSTA', '卡卷使用状态', '0');
INSERT INTO `param_code_tb` VALUES ('10533', '4', '已使用', 'CCSTA', '卡卷使用状态', '0');
INSERT INTO `param_code_tb` VALUES ('10534', '5', '已作废', 'CCSTA', '卡卷使用状态', '0');
INSERT INTO `param_code_tb` VALUES ('10535', '6', '已过期', 'CCSTA', '卡卷使用状态', '0');
INSERT INTO `param_code_tb` VALUES ('10601', '1', '支付宝', 'PAYTY', '支付方式', '0');
INSERT INTO `param_code_tb` VALUES ('10602', '2', '微信支付', 'PAYTY', '支付方式', '0');
INSERT INTO `param_code_tb` VALUES ('10603', '3', '银联支付', 'PAYTY', '支付方式', '0');
INSERT INTO `param_code_tb` VALUES ('10604', '4', '卡券支付', 'PAYTY', '支付方式', '0');
INSERT INTO `param_code_tb` VALUES ('10605', '5', '线下支付', 'PAYTY', '支付方式', '0');
INSERT INTO `param_code_tb` VALUES ('10607', '6', '其他', 'PAYTY', '支付方式', '0');
INSERT INTO `param_code_tb` VALUES ('10630', '1', '待支付', 'PAYST', '支付记录支付状态', '0');
INSERT INTO `param_code_tb` VALUES ('10631', '2', '支付成功', 'PAYST', '支付记录支付状态', '0');
INSERT INTO `param_code_tb` VALUES ('10632', '3', '支付失败', 'PAYST', '支付记录支付状态', '0');
INSERT INTO `param_code_tb` VALUES ('10633', '4', '关闭', 'PAYST', '支付记录支付状态', '0');
INSERT INTO `param_code_tb` VALUES ('10701', '10', '接收平台推荐', 'SELTY', '顾问类型', '0');
INSERT INTO `param_code_tb` VALUES ('10702', '20', '不接受平台推荐', 'SELTY', '顾问类型', '0');
INSERT INTO `param_code_tb` VALUES ('10710', '0', '正式顾问', 'SELST', '顾问状态', '0');
INSERT INTO `param_code_tb` VALUES ('10711', '1', '离职', 'SELST', '顾问状态', '0');
INSERT INTO `param_code_tb` VALUES ('10712', '2', '暂停', 'SELST', '顾问状态', '0');
INSERT INTO `param_code_tb` VALUES ('10713', '3', '待审核', 'SELST', '顾问状态', '0');
INSERT INTO `param_code_tb` VALUES ('10714', '4', '实习顾问', 'SELST', '顾问状态', '0');
INSERT INTO `param_code_tb` VALUES ('10720', '1', '一级顾问', 'SELLV', '顾问级别', '0');
INSERT INTO `param_code_tb` VALUES ('10721', '2', '二级顾问', 'SELLV', '顾问级别', '0');
INSERT INTO `param_code_tb` VALUES ('10900', '1', '袋', 'SACTY', '制备样本容器类型', '0');
INSERT INTO `param_code_tb` VALUES ('10901', '2', '管', 'SACTY', '制备样本容器类型', '0');
INSERT INTO `param_code_tb` VALUES ('11000', '0', '未付款', 'OST', '订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11001', '1', '已取消', 'OST', '订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11002', '2', '已付款', 'OST', '订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11003', '3', '申请退款', 'OST', '订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11004', '4', '已退款', 'OST', '订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11005', '5', '已升级', 'OST', '订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11006', '6', '支付中', 'OST', '订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11100', '10', '运营商', 'ORGTYPE', '组织类别', '0');
INSERT INTO `param_code_tb` VALUES ('11101', '20', '推广商', 'ORGTYPE', '组织类别', '0');
INSERT INTO `param_code_tb` VALUES ('11102', '30', '经销商', 'ORGTYPE', '组织类别', '0');
INSERT INTO `param_code_tb` VALUES ('11103', '40', '集团客户', 'ORGTYPE', '组织类别', '0');
INSERT INTO `param_code_tb` VALUES ('11104', '50', '制备中心', 'ORGTYPE', '组织类别', '0');
INSERT INTO `param_code_tb` VALUES ('11199', '-1', '未回访', 'QSCH', '回访情况', '0');
INSERT INTO `param_code_tb` VALUES ('11200', '0', '待回访', 'QSCH', '回访情况', '0');
INSERT INTO `param_code_tb` VALUES ('11201', '1', '无变化', 'QSCH', '回访情况', '0');
INSERT INTO `param_code_tb` VALUES ('11202', '2', '有变化', 'QSCH', '回访情况', '0');
INSERT INTO `param_code_tb` VALUES ('11300', '0', '未审核', 'QSRW', '审核结果', '0');
INSERT INTO `param_code_tb` VALUES ('11301', '1', '合格', 'QSRW', '审核结果', '0');
INSERT INTO `param_code_tb` VALUES ('11302', '2', '不完全合格', 'QSRW', '审核结果', '0');
INSERT INTO `param_code_tb` VALUES ('11303', '3', '不合格', 'QSRW', '审核结果', '0');
INSERT INTO `param_code_tb` VALUES ('11304', '4', '待医学主管审核', 'QSRW', '审核结果', '0');
INSERT INTO `param_code_tb` VALUES ('11400', '1', '是', 'YORN', '是否', '0');
INSERT INTO `param_code_tb` VALUES ('11401', '2', '否', 'YORN', '是否', '0');
INSERT INTO `param_code_tb` VALUES ('11500', '1', '服务站已取样', 'SPSTA', '样本业务状态', '0');
INSERT INTO `param_code_tb` VALUES ('11501', '2', '已安排运输', 'SPSTA', '样本业务状态', '0');
INSERT INTO `param_code_tb` VALUES ('11502', '3', '服务站已交接', 'SPSTA', '样本业务状态', '0');
INSERT INTO `param_code_tb` VALUES ('11503', '4', '实验室已接收', 'SPSTA', '样本业务状态', '0');
INSERT INTO `param_code_tb` VALUES ('11510', '1', '合格', 'SPRER', '实验室接收结果', '0');
INSERT INTO `param_code_tb` VALUES ('11511', '2', '有偏差', 'SPRER', '实验室接收结果', '0');
INSERT INTO `param_code_tb` VALUES ('11512', '3', '拒收', 'SPRER', '实验室接收结果', '0');
INSERT INTO `param_code_tb` VALUES ('11520', '10', '待处理', 'SADSTA', '补采订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11521', '20', '已处理', 'SADSTA', '补采订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11522', '30', '客户放弃', 'SADSTA', '补采订单状态', '0');
INSERT INTO `param_code_tb` VALUES ('11600', '10', '电子发票', 'INVOTY', '发票类型', '0');
INSERT INTO `param_code_tb` VALUES ('11601', '20', '纸质发票', 'INVOTY', '发票类型', '0');
INSERT INTO `param_code_tb` VALUES ('11602', '30', '不开票', 'INVOTY', '发票类型', '0');
INSERT INTO `param_code_tb` VALUES ('11603', '10', '个人', 'ITTPY', '发票抬头类型', '0');
INSERT INTO `param_code_tb` VALUES ('11604', '20', '单位', 'ITTPY', '发票抬头类型', '0');
INSERT INTO `param_code_tb` VALUES ('11650', '0', '未开票', 'BILSTA', '开票状态', '0');
INSERT INTO `param_code_tb` VALUES ('11651', '1', '已开票', 'BILSTA', '开票状态', '0');
INSERT INTO `param_code_tb` VALUES ('11710', '1', '待推出', 'PTSTA', '产品状态', '0');
INSERT INTO `param_code_tb` VALUES ('11711', '2', '上架', 'PTSTA', '产品状态', '0');
INSERT INTO `param_code_tb` VALUES ('11712', '3', '下架', 'PTSTA', '产品状态', '0');
INSERT INTO `param_code_tb` VALUES ('11720', '10', '普通版', 'PTVER', '产品版本', '0');
INSERT INTO `param_code_tb` VALUES ('11722', '30', '定制版', 'PTVER', '产品版本', '0');
INSERT INTO `param_code_tb` VALUES ('11724', '50', '关联版', 'PTVER', '产品版本', '0');
INSERT INTO `param_code_tb` VALUES ('11730', '0', '不推荐', 'PTPUS', '是否推荐', '0');
INSERT INTO `param_code_tb` VALUES ('11731', '1', '推荐', 'PTPUS', '是否推荐', '0');
INSERT INTO `param_code_tb` VALUES ('11800', '0', '阴性', 'PTVNTV', '阴性阳性', '0');
INSERT INTO `param_code_tb` VALUES ('11801', '1', '阳性', 'PTVNTV', '阴性阳性', '0');
INSERT INTO `param_code_tb` VALUES ('11830', '0', '正常', 'NOMAL', '正常异常', '0');
INSERT INTO `param_code_tb` VALUES ('11831', '1', '异常', 'NOMAL', '正常异常', '0');
INSERT INTO `param_code_tb` VALUES ('11900', '1', '个人邀请', 'CSTYPE', '客户来源类型', '0');
INSERT INTO `param_code_tb` VALUES ('11901', '2', '活动邀请', 'CSTYPE', '客户来源类型', '0');
INSERT INTO `param_code_tb` VALUES ('11902', '3', '卡券邀请', 'CSTYPE', '客户来源类型', '0');
INSERT INTO `param_code_tb` VALUES ('11903', '4', '扫码组织', 'CSTYPE', '客户来源类型', '0');
INSERT INTO `param_code_tb` VALUES ('12000', '1', '补采', 'DEHMD', '偏差处理方式', '0');
INSERT INTO `param_code_tb` VALUES ('12001', '2', '补充告知', 'DEHMD', '偏差处理方式', '0');
INSERT INTO `param_code_tb` VALUES ('12002', '3', '合同终止', 'DEHMD', '偏差处理方式', '0');
INSERT INTO `param_code_tb` VALUES ('12003', '4', '其他', 'DEHMD', '偏差处理方式', '0');
INSERT INTO `param_code_tb` VALUES ('12100', '1', '待处理', 'STATUS', '偏差处理状态', '0');
INSERT INTO `param_code_tb` VALUES ('12101', '2', '处理中', 'STATUS', '偏差处理状态', '0');
INSERT INTO `param_code_tb` VALUES ('12102', '3', '已处理', 'STATUS', '偏差处理状态', '0');
INSERT INTO `param_code_tb` VALUES ('12200', '1', '储存免疫细胞', 'TRETYP', '免疫细胞处理类型', '0');
INSERT INTO `param_code_tb` VALUES ('12201', '2', '废弃', 'TRETYP', '免疫细胞处理类型', '0');
INSERT INTO `param_code_tb` VALUES ('12202', '3', '其他', 'TRETYP', '免疫细胞处理类型', '0');
INSERT INTO `param_code_tb` VALUES ('12210', '0', '待审核', 'REFSTA', '退款申请状态', '0');
INSERT INTO `param_code_tb` VALUES ('12211', '1', '已退款', 'REFSTA', '退款申请状态', '0');
INSERT INTO `param_code_tb` VALUES ('12212', '2', '客户放弃', 'REFSTA', '退款申请状态', '0');
INSERT INTO `param_code_tb` VALUES ('12215', '3', '审核中', 'REFSTA', '退款申请状态', '0');
INSERT INTO `param_code_tb` VALUES ('12310', '1', '直接订单', 'SBTYPE', '顾问绩效类型', '0');
INSERT INTO `param_code_tb` VALUES ('12311', '2', '间接订单', 'SBTYPE', '顾问绩效类型', '0');
INSERT INTO `param_code_tb` VALUES ('12312', '3', '安康奖励', 'SBTYPE', '顾问绩效类型', '0');
INSERT INTO `param_code_tb` VALUES ('12313', '4', '绩优奖励', 'SBTYPE', '顾问绩效类型', '0');
INSERT INTO `param_code_tb` VALUES ('12314', '5', '管理津贴', 'SBTYPE', '顾问绩效类型', '0');
INSERT INTO `param_code_tb` VALUES ('12315', '6', '卓越之星平均津贴', 'SBTYPE', '顾问绩效类型', '0');
INSERT INTO `param_code_tb` VALUES ('12316', '7', '卓越之星加权津贴', 'SBTYPE', '顾问绩效类型', '0');
INSERT INTO `param_code_tb` VALUES ('12320', '1', '正常', 'SBSTAT', '顾问绩效状态', '0');
INSERT INTO `param_code_tb` VALUES ('12321', '2', '作废', 'SBSTAT', '顾问绩效状态', '0');
INSERT INTO `param_code_tb` VALUES ('12410', '1', '身份证', 'CDTYPE', '证件类型', '0');
INSERT INTO `param_code_tb` VALUES ('12411', '2', '港澳通行证', 'CDTYPE', '证件类型', '0');
INSERT INTO `param_code_tb` VALUES ('12412', '3', '台湾通行证', 'CDTYPE', '证件类型', '0');
INSERT INTO `param_code_tb` VALUES ('12413', '4', '护照', 'CDTYPE', '证件类型', '0');
INSERT INTO `param_code_tb` VALUES ('12414', '5', '军官证', 'CDTYPE', '证件类型', '0');
INSERT INTO `param_code_tb` VALUES ('12415', '6', '士兵证', 'CDTYPE', '证件类型', '0');
INSERT INTO `param_code_tb` VALUES ('12510', '1', '补贴费用\r\n', 'STBOTY', '站点收入类型', '0');
INSERT INTO `param_code_tb` VALUES ('12511', '2', '业务提成\r\n', 'STBOTY', '站点收入类型', '0');
INSERT INTO `param_code_tb` VALUES ('12512', '3', '劳务费用\r\n', 'STBOTY', '站点收入类型', '0');
INSERT INTO `param_code_tb` VALUES ('12513', '4', '运输费用\r\n', 'STBOTY', '站点收入类型', '0');
INSERT INTO `param_code_tb` VALUES ('12530', '0', '未答', 'EAMRLT', '考试结果类型', '0');
INSERT INTO `param_code_tb` VALUES ('12531', '1', '通过', 'EAMRLT', '考试结果类型', '0');
INSERT INTO `param_code_tb` VALUES ('12532', '2', '未通过', 'EAMRLT', '考试结果类型', '0');
INSERT INTO `param_code_tb` VALUES ('12540', '1', '健康顾问', 'SWEIGH', '顾问等级', '0');
INSERT INTO `param_code_tb` VALUES ('12541', '2', '健康之星', 'SWEIGH', '顾问等级', '0');
INSERT INTO `param_code_tb` VALUES ('12542', '3', '健康大使', 'SWEIGH', '顾问等级', '0');
INSERT INTO `param_code_tb` VALUES ('12543', '4', '生命天使', 'SWEIGH', '顾问等级', '0');
INSERT INTO `param_code_tb` VALUES ('12550', '1', '收入', 'LTSEPO', '二级顾问类型', '0');
INSERT INTO `param_code_tb` VALUES ('12551', '2', '支出', 'LTSEPO', '二级顾问类型', '0');
INSERT INTO `param_code_tb` VALUES ('12552', '3', '作废', 'LTSEPO', '二级顾问类型', '0');
INSERT INTO `param_code_tb` VALUES ('12611', '1', '男', 'SEXTY', '性别类型', '0');
INSERT INTO `param_code_tb` VALUES ('12612', '2', '女', 'SEXTY', '性别类型', '0');
INSERT INTO `param_code_tb` VALUES ('12700', '1', '建设银行', 'DEPOSIT', '顾问提现开户行', '0');
INSERT INTO `param_code_tb` VALUES ('12701', '2', '工商银行', 'DEPOSIT', '顾问提现开户行', '0');
INSERT INTO `param_code_tb` VALUES ('12702', '3', '中国银行', 'DEPOSIT', '顾问提现开户行', '0');
INSERT INTO `param_code_tb` VALUES ('12703', '4', '农业银行', 'DEPOSIT', '顾问提现开户行', '0');
INSERT INTO `param_code_tb` VALUES ('12800', '1', '收入', 'CCUTYPE', '细胞币状态类型', '0');
INSERT INTO `param_code_tb` VALUES ('12810', '2', '支出', 'CCUTYPE', '细胞币状态类型', '0');
INSERT INTO `param_code_tb` VALUES ('12820', '3', '锁定', 'CCUTYPE', '细胞币状态类型', '0');
INSERT INTO `param_code_tb` VALUES ('12910', '1', 'APP欢迎页', 'IMGTYPE', '图片类型', '0');
INSERT INTO `param_code_tb` VALUES ('12911', '2', '海报', 'IMGTYPE', '图片类型', '0');
INSERT INTO `param_code_tb` VALUES ('12912', '1', '文章内容', 'CYTYPE', '内容类型', '0');
INSERT INTO `param_code_tb` VALUES ('12913', '2', '视频内容', 'CYTYPE', '内容类型', '0');
INSERT INTO `param_code_tb` VALUES ('12914', '3', '问答', 'CYTYPE', '内容类型', '0');

-- ----------------------------
-- Table structure for param_value_tb
-- ----------------------------
DROP TABLE IF EXISTS `param_value_tb`;
CREATE TABLE `param_value_tb` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Name` varchar(50) NOT NULL DEFAULT '' COMMENT '系统参数名(参数名唯一)',
  `Value` varchar(500) NOT NULL DEFAULT '' COMMENT '系统参数值',
  `Remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='字典信息表：键值类型字典参数';

-- ----------------------------
-- Records of param_value_tb
-- ----------------------------
INSERT INTO `param_value_tb` VALUES ('1', 'M_LoginFirstAPP', '50', '首次登录APP赠送客户细胞币');
INSERT INTO `param_value_tb` VALUES ('2', 'M_LoginFirstWeChat', '50', '首次登录微信赠送客户细胞币');
INSERT INTO `param_value_tb` VALUES ('3', 'M_Recom_Reg', '25', '推荐客户注册成功赠送细胞币');
INSERT INTO `param_value_tb` VALUES ('4', 'M_Recom_Ord', '388', '推荐客户储存送现金红红包额度');
INSERT INTO `param_value_tb` VALUES ('5', 'M_InOfHOBO', '0.9', '子公司收入占汇款比例');
INSERT INTO `param_value_tb` VALUES ('6', 'WxUrl', 'http://testing.celife.cc', '微信URL');
INSERT INTO `param_value_tb` VALUES ('7', 'WebUrl', 'http://canyou.sedns.cn:8086', '系统URL');
INSERT INTO `param_value_tb` VALUES ('8', 'SHARE_QRCODE', '/customer/mine/share?inviteCode=%s&inviteType=%s', '分享二维码URL规则');
INSERT INTO `param_value_tb` VALUES ('9', 'CLIENT_VALIDATE_DAY', '60', '注册（60）天后天后客户不是成交客户，按照新客户处理');
INSERT INTO `param_value_tb` VALUES ('10', 'TransPlanMsg', '尊敬的%s，您有一批样本待运送，请于%s %s前往%s交接。', '样本运输通知短信');
INSERT INTO `param_value_tb` VALUES ('11', 'SampleMsg', '样本%s：合同号【%s】，客户姓名【%s】,授权码【%s】。', '样本运输通知短信');
INSERT INTO `param_value_tb` VALUES ('12', 'M_MAX_DISTINCT_OPERCOST', '8000', '优惠券VIP卡总优惠金额大于后不算运营费用');
INSERT INTO `param_value_tb` VALUES ('13', 'P_AOLLOW_GIVE_MIN', '0', '自己积分少于指定值不能赠送');
INSERT INTO `param_value_tb` VALUES ('14', 'S_Check_Sample_OK', '1', '时间内检测样本不需要重复采集（单位：天）');
INSERT INTO `param_value_tb` VALUES ('15', 'GROUP_DEFAULT_PORTRAITURI', '/upload/article/01ff2de1c56c4bb1a8be8db1a083c741.png', '群组默认头像地址');
INSERT INTO `param_value_tb` VALUES ('16', 'SELLER_EXAM_PASS_SCORE', '70', '顾问考试通过分数');
INSERT INTO `param_value_tb` VALUES ('17', 'M_When_Allow_RedEncash', '1', '什么阶段现金红包可以提现（1实验室接收，2录入制备信息）');
INSERT INTO `param_value_tb` VALUES ('18', 'AppUrl', 'http://o2oapp.celife.cc', 'AppUrl');
INSERT INTO `param_value_tb` VALUES ('19', 'SELLER_BOUNS_ORGID_A', '142', 'A顾问绩效规则——对应组织ID（规则对用KEY的开头SELLER_BOUNS_A_）');
INSERT INTO `param_value_tb` VALUES ('20', 'SELLER_BOUNS_DB_N_A', '2000', '普通产品订单直接提成');
INSERT INTO `param_value_tb` VALUES ('21', 'SELLER_BOUNS_DB_U_A', '4000', '安康产品订单直接提成');
INSERT INTO `param_value_tb` VALUES ('22', 'SELLER_BOUNS_IDB_N_A', '600', '普通产品订单间接提成');
INSERT INTO `param_value_tb` VALUES ('23', 'SELLER_BOUNS_IDB_U_A', '1200', '安康产品订单间接提成');
INSERT INTO `param_value_tb` VALUES ('24', 'SELLER_BOUNS_UPSEB_N_A', '500', '安康顾问普通产品订单增加奖励');
INSERT INTO `param_value_tb` VALUES ('25', 'SELLER_BOUNS_UPSEB_U_A', '1000', '安康顾问安康产品订单增加奖励');
INSERT INTO `param_value_tb` VALUES ('26', 'SELLER_BOUNS_SUM_N_A', '500', '两个自然月内的累加奖（普通订单累加至6单）');
INSERT INTO `param_value_tb` VALUES ('27', 'SELLER_BOUNS_SUM_U_A', '1000', '两个自然月内的累加奖（安康订单累加至6单）');
INSERT INTO `param_value_tb` VALUES ('28', 'SELLER_BOUNS_PROID_N_A', '35', 'A顾问绩效规则计算绩效产品——普通版产品ID');
INSERT INTO `param_value_tb` VALUES ('29', 'SELLER_BOUNS_PROID_U_A', '59', 'A顾问绩效规则计算绩效产品——安康版产品ID');
INSERT INTO `param_value_tb` VALUES ('30', 'SELLER_BOUNS_PROID_E_A', '95', 'A顾问绩效规则计算绩效产品——臻享版产品ID');
INSERT INTO `param_value_tb` VALUES ('31', 'SELLER_BOUNS_DB_E_A', '7000', '臻享产品订单直接提成');
INSERT INTO `param_value_tb` VALUES ('32', 'SELLER_BOUNS_IDB_E_A', '2000', '臻享产品订单间接提成');
INSERT INTO `param_value_tb` VALUES ('33', 'SELLER_WEIGHT_LEVEL_A0', '1', '健康大使/生命天使基础分');
INSERT INTO `param_value_tb` VALUES ('34', 'SELLER_WEIGHT_LEVEL_A1', '0.3', '第一代健康大使/生命天使加分');
INSERT INTO `param_value_tb` VALUES ('35', 'SELLER_WEIGHT_LEVEL_A2', '0.2', '第二代健康大使/生命天使加分');
INSERT INTO `param_value_tb` VALUES ('36', 'SELLER_UPGRADE_SNUM_F_A', '6', '顾问升级需要的下级顾问数量');
INSERT INTO `param_value_tb` VALUES ('37', 'SELLER_UPGRADE_SNUM_P_A', '2', '顾问升级需要的下级顾问最小数量');
INSERT INTO `param_value_tb` VALUES ('38', 'SELLER_UPGRADE_OAMO_P_A1', '120000', '顾问升级健康之星需要的团队业绩');
INSERT INTO `param_value_tb` VALUES ('39', 'SELLER_UPGRADE_OAMO_P_A2', '1200000', '顾问升级健康大使需要的团队业绩');
INSERT INTO `param_value_tb` VALUES ('40', 'SELLER_UPGRADE_OAMO_P_A3', '12000000', '顾问升级健康天使需要的团队业绩');
INSERT INTO `param_value_tb` VALUES ('41', 'SELLER_ALLOWANCE_AMOUNT_A2', '28000', '健康大使获得津贴至少需要团队达到业绩金额');
INSERT INTO `param_value_tb` VALUES ('42', 'SELLER_ALLOWANCE_GROUP_A2', '2', '健康大使获得津贴至少需要达到业绩要求团队数');
INSERT INTO `param_value_tb` VALUES ('43', 'SELLER_ALLOWANCE_RATE_A3', '0.03', '健康大使津贴占经销商总回款额的比例');
INSERT INTO `param_value_tb` VALUES ('44', 'SELLER_ALLOWANCE_RATE_A4', '0.01', '生命天使津贴占经销商总回款额的比例');
INSERT INTO `param_value_tb` VALUES ('45', 'InvoicesMsg', '尊敬的%s，您有%s个订单待开发票，请及时处理。', '财务待开票订单数通知短信');
INSERT INTO `param_value_tb` VALUES ('46', 'LTSELLER_POINT_COUPONS', '222', '二级顾问积分兑换卡券\r\n');
INSERT INTO `param_value_tb` VALUES ('47', 'SELLER_BOUNS_UPSEB_E_A', '2000', '安康、臻享顾问臻享产品订单增加奖励');
INSERT INTO `param_value_tb` VALUES ('48', 'SELLER_BOUNS_SUM_E_A', '2000', '两个自然月内的累加奖 订单累加至6单 臻享产品奖励');
INSERT INTO `param_value_tb` VALUES ('49', 'PAY_RECORD_USEFUL_MINUTE', '30', '支付记录有效分钟数');
INSERT INTO `param_value_tb` VALUES ('51', 'VIP_CLIENT_STATIONID', '57', '特殊VIP客户预约站点ID');
INSERT INTO `param_value_tb` VALUES ('52', 'VIP_CLIENT_SELLERID_AaBaCcDd', '456', '胡博士VIP客户的顾问');
INSERT INTO `param_value_tb` VALUES ('53', 'VIP_CLIENT_SELLERID_EeFfGgHh', '168', '叶总VIP客户的顾问');
INSERT INTO `param_value_tb` VALUES ('54', 'VIP_CLIENT_PRODUCTID_AaBaCcDd', '35', 'VIP客户储存安心版');
INSERT INTO `param_value_tb` VALUES ('55', 'VIP_CLIENT_PRODUCTID_EeFfGgHh', '59', 'VIP客户储存安康版');
INSERT INTO `param_value_tb` VALUES ('56', 'VIP_CLIENT_PRODUCTID_IiJjKkLl', '95', 'VIP客户储存臻享版');
INSERT INTO `param_value_tb` VALUES ('57', 'VIP_CLIENT_COUPONID_AaBaCcDd', '104', 'VIP客户储存安心版免费卡');
INSERT INTO `param_value_tb` VALUES ('58', 'VIP_CLIENT_COUPONID_EeFfGgHh', '105', 'VIP客户储存安康版免费卡');
INSERT INTO `param_value_tb` VALUES ('59', 'VIP_CLIENT_COUPONID_IiJjKkLl', '106', 'VIP客户储存臻享版免费卡');
INSERT INTO `param_value_tb` VALUES ('60', 'SELLER_ALLOWANCE_RATE_EXCELLENCE', '0.01', '卓越之星顾问津贴占经销商总回款额的比例');
INSERT INTO `param_value_tb` VALUES ('61', 'SELLER_ALLOWANCE_RATE_EXCELLENCE_AVG', '0.5', '卓越之星平分津贴占发放金额的比例');
INSERT INTO `param_value_tb` VALUES ('62', 'SELLER_CONTENT_ROLE_ID', '2', '顾问内容管理员');
INSERT INTO `param_value_tb` VALUES ('63', 'CLIENT_CONTENT_ROLE_ID', '3', '客户内容管理员');
INSERT INTO `param_value_tb` VALUES ('64', 'OLD_PRODUCT_ID', '|35|59|69|70|91|92|93|94|95|', '老的产品ID');
INSERT INTO `param_value_tb` VALUES ('65', 'M_CellPointMAX', '500', '最大可用细胞币（兼容老版本、实际业务没有用处）');
INSERT INTO `param_value_tb` VALUES ('66', 'CLOUDFLOW_APPID', 'SP10583854', '云之家审批appID');
INSERT INTO `param_value_tb` VALUES ('67', 'CLOUDFLOW_SECRET', '1Cd1mdRkKhaLljskaD4l2Kcu5Aox9w', '云之家开发者screct');
INSERT INTO `param_value_tb` VALUES ('68', 'YUNZHIJIA_HOST', 'http://yunzhijia.com', '云之家host');
INSERT INTO `param_value_tb` VALUES ('69', 'CLOUDFLOW_REFUND_ID', 'e57c9823ec644552966a6b555670c5e5', '退款申请审批模板ID');
INSERT INTO `param_value_tb` VALUES ('70', 'CMS_URL', 'http://canyou.xyz:8089/login', '北科生命内容管理系统');
INSERT INTO `param_value_tb` VALUES ('71', 'OMS_URL', 'http://canyou.xyz:8086', '北科生命细胞云运营管理平台');

-- ----------------------------
-- Table structure for rbac_role_tb
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_tb`;
CREATE TABLE `rbac_role_tb` (
  `RoleId` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `Description` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CreatedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `ModifiedBy` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ModifiedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色信息表';

-- ----------------------------
-- Records of rbac_role_tb
-- ----------------------------
INSERT INTO `rbac_role_tb` VALUES ('1', '系统管理员', '平台系统管理人员', 'admin', '2014-01-03 13:40:10', 'admin', '2017-04-10 14:53:12');
INSERT INTO `rbac_role_tb` VALUES ('2', '内容管理员', '内容管理员', 'admin', '2014-01-03 13:40:10', 'admin', '2017-04-10 14:53:12');

-- ----------------------------
-- Table structure for rbac_role_user_tb
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_user_tb`;
CREATE TABLE `rbac_role_user_tb` (
  `RoleId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  PRIMARY KEY (`RoleId`,`UserId`),
  KEY `RoleId` (`RoleId`),
  KEY `UserId` (`UserId`),
  CONSTRAINT `rbac_role_user_tb_ibfk_1` FOREIGN KEY (`RoleId`) REFERENCES `rbac_role_tb` (`RoleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rbac_role_user_tb_ibfk_2` FOREIGN KEY (`UserId`) REFERENCES `global_user_tb` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色用户中间表';

-- ----------------------------
-- Records of rbac_role_user_tb
-- ----------------------------
INSERT INTO `rbac_role_user_tb` VALUES ('1', '1');
INSERT INTO `rbac_role_user_tb` VALUES ('2', '1');
INSERT INTO `rbac_role_user_tb` VALUES ('2', '710');
