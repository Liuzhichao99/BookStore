/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2022-11-06 22:00:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `img_path` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('42', '解忧杂货店', '东野圭吾', '27.20', '101', '99', 'static/uploads/jieyouzahuodian.jpg');
INSERT INTO `t_book` VALUES ('43', '边城', '沈从文', '23.00', '101', '99', 'static/uploads/biancheng.jpg');
INSERT INTO `t_book` VALUES ('44', '中国哲学史', '冯友兰', '44.50', '102', '98', 'static/uploads/zhongguozhexueshi.jpg');
INSERT INTO `t_book` VALUES ('45', '忽然七日', ' 劳伦', '19.33', '100', '100', 'static/uploads/huranqiri.jpg');
INSERT INTO `t_book` VALUES ('46', '苏东坡传', '林语堂', '19.30', '100', '100', 'static/uploads/sudongpozhuan.jpg');
INSERT INTO `t_book` VALUES ('47', '百年孤独', '马尔克斯', '29.50', '100', '100', 'static/uploads/bainiangudu.jpg');
INSERT INTO `t_book` VALUES ('48', '扶桑', '严歌苓', '19.80', '100', '100', 'static/uploads/fusang.jpg');
INSERT INTO `t_book` VALUES ('49', '给孩子的诗', '北岛', '22.20', '100', '100', 'static/uploads/geihaizideshi.jpg');
INSERT INTO `t_book` VALUES ('50', '为奴十二年', '所罗门', '16.50', '100', '100', 'static/uploads/weinushiernian.jpg');
INSERT INTO `t_book` VALUES ('51', '平凡的世界', '路遥', '55.00', '100', '100', 'static/uploads/pingfandeshijie.jpg');
INSERT INTO `t_book` VALUES ('52', '悟空传', '今何在', '14.00', '100', '100', 'static/uploads/wukongzhuan.jpg');
INSERT INTO `t_book` VALUES ('53', '硬派健身', '斌卡', '31.20', '100', '100', 'static/uploads/yingpaijianshen.jpg');
INSERT INTO `t_book` VALUES ('54', '从晚清到民国', '唐德刚', '39.90', '100', '100', 'static/uploads/congwanqingdaominguo.jpg');
INSERT INTO `t_book` VALUES ('55', '三体', '刘慈欣', '56.50', '100', '100', 'static/uploads/santi.jpg');
INSERT INTO `t_book` VALUES ('56', '看见', '柴静', '19.50', '100', '100', 'static/uploads/kanjian.jpg');
INSERT INTO `t_book` VALUES ('57', '活着', '余华', '11.00', '100', '100', 'static/uploads/huozhe.jpg');
INSERT INTO `t_book` VALUES ('58', '小王子', '安托万', '19.20', '100', '100', 'static/uploads/xiaowangzi.jpg');
INSERT INTO `t_book` VALUES ('59', '我们仨', '杨绛', '11.30', '100', '100', 'static/uploads/womensa.jpg');
INSERT INTO `t_book` VALUES ('60', '生命不息,折腾不止', '罗永浩', '25.20', '100', '100', 'static/uploads/shengmingbuxi.jpg');
INSERT INTO `t_book` VALUES ('61', '皮囊', '蔡崇达', '23.90', '100', '100', 'static/uploads/pinang.jpg');
INSERT INTO `t_book` VALUES ('62', '恰到好处的幸福', '毕淑敏', '16.40', '100', '100', 'static/uploads/qiadaohaochudexingfu.jpg');
INSERT INTO `t_book` VALUES ('63', '大数据预测', '埃里克', '37.20', '100', '100', 'static/uploads/dashujuyuce.jpg');
INSERT INTO `t_book` VALUES ('64', '人月神话', '布鲁克斯', '55.90', '100', '100', 'static/uploads/renyueshenhua.jpg');
INSERT INTO `t_book` VALUES ('65', 'C语言入门经典', '霍尔顿', '45.00', '100', '100', 'static/uploads/cyuyanrumenjingdian.jpg');
INSERT INTO `t_book` VALUES ('66', '数学之美', '吴军', '29.90', '100', '100', 'static/uploads/shuxuezhimei.jpg');
INSERT INTO `t_book` VALUES ('67', 'Java编程思想', '埃史尔', '70.50', '100', '100', 'static/uploads/Javabianchengsixiang.jpg');
INSERT INTO `t_book` VALUES ('68', '设计模式之禅', '秦小波', '20.20', '100', '100', 'static/uploads/shejimoshizhichan.jpg');
INSERT INTO `t_book` VALUES ('69', '图解机器学习', '杉山将', '33.80', '100', '100', 'static/uploads/tujiejiqixuexi.jpg');
INSERT INTO `t_book` VALUES ('70', '艾伦图灵传', '安德鲁', '47.20', '100', '100', 'static/uploads/ailuntulingzhuan.jpg');
INSERT INTO `t_book` VALUES ('71', '教父', '马里奥普佐', '29.00', '100', '100', 'static/uploads/jiaofu.jpg');
INSERT INTO `t_book` VALUES ('72', 'asdlkfj', 'sfdkl', '123.00', '324', '234', 'static/uploads/jieyouzahuodian.jpg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` int(11) DEFAULT NULL,
  `order_sequence` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `total_count` int(11) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (null, '7cd38eb9e6b44272b4b262c643d234a1', '2022-10-05 18:29:36', '4', '139.20', '0', '3');

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `item_id` int(11) DEFAULT NULL,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `item_count` int(11) DEFAULT NULL,
  `item_amount` decimal(10,2) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES (null, '解忧杂货店', '27.20', 'static/uploads/jieyouzahuodian.jpg', '1', '27.20', null);
INSERT INTO `t_order_item` VALUES (null, '边城', '23.00', 'static/uploads/biancheng.jpg', '1', '23.00', null);
INSERT INTO `t_order_item` VALUES (null, '中国哲学史', '44.50', 'static/uploads/zhongguozhexueshi.jpg', '2', '89.00', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `user_pwd` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('2', 'asd123', '82b56fc7d58737a96a35830272f03fe72a3a414a2a31925c', 'asd@q.c');
INSERT INTO `t_user` VALUES ('3', 'ikun123', '77c82dc7a764c9f790303210372f3498ef29b0483b708100', 'ikun123@q.c');
INSERT INTO `t_user` VALUES ('4', 'ikun1231', 'a75b94b25569355b1831f532c6718362f41642bc5576473f', 'ikun123@q.c');
INSERT INTO `t_user` VALUES ('5', 'iqiu123', 'b72a5331677203df88e4d63f825f60882d37c9120874930d', 'iqiu123@q.c');
