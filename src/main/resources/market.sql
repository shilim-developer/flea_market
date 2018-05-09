/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.46-log : Database - market
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`market` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `market`;

/*Table structure for table `classify` */

DROP TABLE IF EXISTS `classify`;

CREATE TABLE `classify` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `classify_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `classify` */

/*Table structure for table `good` */

DROP TABLE IF EXISTS `good`;

CREATE TABLE `good` (
  `g_id` int(11) NOT NULL AUTO_INCREMENT,
  `good_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `good_price` int(11) DEFAULT NULL COMMENT '商品价格',
  `good_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `good_surplus` int(11) DEFAULT '0' COMMENT '商品剩余量',
  `good_description` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `good_pics` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0 待审核,1 审核通过,2审核不通过,3下架商品',
  `u_id` int(11) NOT NULL COMMENT '所属用户id',
  `c_id` int(11) NOT NULL COMMENT '所属分类id',
  PRIMARY KEY (`g_id`),
  KEY `u_id` (`u_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `good_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE,
  CONSTRAINT `good_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `classify` (`c_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `good` */

/*Table structure for table `m_order` */

DROP TABLE IF EXISTS `m_order`;

CREATE TABLE `m_order` (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(255) DEFAULT NULL COMMENT '订单号',
  `good_id` int(11) DEFAULT NULL COMMENT '商品id',
  `good_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `good_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `good_price` int(11) DEFAULT NULL COMMENT '商品价格',
  `total_money` int(11) DEFAULT NULL COMMENT '总价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `buy_user` varchar(255) DEFAULT NULL COMMENT '买家',
  `buy_user_id` int(11) DEFAULT NULL COMMENT '买家id',
  `sold_user` varchar(255) DEFAULT NULL COMMENT '卖家',
  `sold_user_id` int(11) DEFAULT NULL COMMENT '买家id',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态 0,待付款 1,已付款 2,已发货, 3已收货',
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `m_order` */

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COMMENT '回复内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `from_user` int(11) DEFAULT NULL COMMENT '来自用户id',
  `to_user` int(11) DEFAULT NULL COMMENT '目的用户id',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态 0未读 1已读',
  PRIMARY KEY (`m_id`),
  KEY `from_user` (`from_user`),
  KEY `to_user` (`to_user`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`from_user`) REFERENCES `user` (`u_id`) ON DELETE CASCADE,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`to_user`) REFERENCES `user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0待审核,1审核通过,2审核不通过,3冻结',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`u_id`,`username`,`password`,`phone`,`status`) values (7,'shilim','11188b5a4afe3af259a6ce12fdd80d84','1234567',0),(8,'liwei','a45958517604f5cd90d6ee51ad9cfdb6','15863428695',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
