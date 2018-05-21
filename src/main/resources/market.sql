/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.7.10-log : Database - market
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

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) DEFAULT NULL COMMENT '管理员账号',
  `password` varchar(255) DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`) values (1,'shilim','11188b5a4afe3af259a6ce12fdd80d84');

/*Table structure for table `classify` */

DROP TABLE IF EXISTS `classify`;

CREATE TABLE `classify` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `classify_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `classify` */

insert  into `classify`(`c_id`,`classify_name`) values (1,'电器'),(2,'杰哥'),(3,'佳杰'),(4,'杰出少年'),(5,'少年'),(6,'电竞');

/*Table structure for table `good` */

DROP TABLE IF EXISTS `good`;

CREATE TABLE `good` (
  `g_id` int(11) NOT NULL AUTO_INCREMENT,
  `good_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `good_price` int(11) DEFAULT NULL COMMENT '商品价格',
  `good_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `good_surplus` int(11) DEFAULT '0' COMMENT '商品剩余量',
  `good_description` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `good_content` text COMMENT '商品详情',
  `good_pics` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0 待审核,1 审核通过,2审核不通过,3下架商品',
  `u_id` int(11) NOT NULL COMMENT '所属用户id',
  `c_id` int(11) NOT NULL COMMENT '所属分类id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`g_id`),
  KEY `u_id` (`u_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `good_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE,
  CONSTRAINT `good_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `classify` (`c_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `good` */

insert  into `good`(`g_id`,`good_name`,`good_price`,`good_count`,`good_surplus`,`good_description`,`good_content`,`good_pics`,`status`,`u_id`,`c_id`,`create_time`) values (13,'鸡蛋',111,111,111,'111','<p>实力家乐福的说服力加了电视剧浪费的老师 分</p>','market_upload/user/6/goods/9cdb4054-64df-48f6-8f21-aca6fc4af206.jpeg',2,6,4,'2018-05-14 17:00:19'),(14,'汽车',122,2,330,'   Freekids这个品牌可以在淘宝搜索 我这个是充气胎，淘宝最低是1358元，我这个是全新的绿色，充气胎，没有打开过 全新未拆封 1100转让。这个车可躺 也可以坐 也可以折叠。  转让原因：因为自己有一辆一模一样的了，所以转让。 ',NULL,'market_upload/user/6/goods/19fd8a22-2c6e-4950-bb8d-568d93870238.jpeg',1,6,1,'2018-05-15 00:13:03'),(15,'2222',11,11,11,'111',NULL,'market_upload/user/6/goods/29290cf9-b181-4508-958c-80812d309471.jpeg',0,6,5,'2018-05-20 19:41:52'),(16,'44',4,44,44,'44','','market_upload/user/6/goods/db7a2212-db56-4782-a4cc-d7393bedacae.jpeg',0,6,2,'2018-05-20 19:43:19'),(17,'4555',55,55,55,'555','<p>55555<img src=\"market_upload/user/6/goods/b5c9db1f-9f78-4cf6-b488-26997790bc1a.jpeg\" alt=\"\" width=\"947\" height=\"631\" /></p>','market_upload/user/6/goods/c8a091a0-8246-43a4-a77a-0ee84188dc9f.jpeg',1,6,4,'2018-05-20 19:47:46'),(18,'11',1100,111,111,'11','<p>1131313</p>','market_upload/user/6/goods/499f8694-ed30-47a3-9b1b-b49bde04ef06.jpeg',0,6,3,'2018-05-20 21:58:15'),(20,'多福多寿',11111,111,111,'11','','market_upload/user/6/goods/239c1489-468f-4efe-ad7b-e7f971cd8425.jpeg',0,6,5,'2018-05-21 03:24:36'),(21,'1',100,1,1,'1','','market_upload/user/6/goods/3b8145a2-ac7b-4ccf-a9b0-f5de3961f9fa.jpeg',0,6,4,'2018-05-21 03:27:13');

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
  `pay_serials_number` varchar(255) DEFAULT NULL COMMENT '支付序列号',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态 0,待付款 1,已付款 2,已发货, 3已收货',
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `m_order` */

insert  into `m_order`(`o_id`,`order_number`,`good_id`,`good_name`,`good_count`,`good_price`,`total_money`,`create_time`,`buy_user`,`buy_user_id`,`sold_user`,`sold_user_id`,`address`,`phone`,`pay_serials_number`,`status`) values (1,'2018050612193341',4,'11',1,11,11,'2018-05-18 14:22:05','shilim',6,'shilim',6,NULL,NULL,NULL,3),(2,'2018050612193342',4,'11',1,11,11,'2018-05-06 12:19:32','shilim',6,'shilim',6,NULL,NULL,NULL,0),(3,'2018051416515743',4,'11',1,11,11,'2018-05-14 16:51:57','shilim',6,'shilim',6,'市里面',NULL,NULL,0),(5,'20180518213635145',14,'汽车',3,122,366,'2018-05-18 21:36:35','shilim',6,'shilim',6,'',NULL,NULL,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`m_id`,`content`,`create_time`,`from_user`,`to_user`,`status`) values (3,'333','2018-05-14 21:28:26',6,6,1),(4,'11','2018-05-06 20:39:16',6,6,1),(5,'图片不符合','2018-05-14 21:48:54',NULL,6,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `sex` tinyint(1) DEFAULT '0' COMMENT '0,男, 1,女',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0待审核,1审核通过,2审核不通过,3冻结',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`u_id`,`username`,`nickname`,`password`,`phone`,`sex`,`email`,`status`,`create_time`) values (6,'shilim','shilim','11188b5a4afe3af259a6ce12fdd80d84','13420190332',0,'11@qq.com',0,'2018-05-20 14:43:27'),(7,'shilim2',NULL,'11188b5a4afe3af259a6ce12fdd80d84','140342423',0,NULL,0,'2018-05-20 14:43:29'),(8,'shili2','shilim','11188b5a4afe3af259a6ce12fdd80d84','shilim',1,'shilim',0,'2018-05-21 01:41:30'),(9,'admin','shilim','11188b5a4afe3af259a6ce12fdd80d84','11111111111',0,'1@qq.com',0,'2018-05-21 02:33:27');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
