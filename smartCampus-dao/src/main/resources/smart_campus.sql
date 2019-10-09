/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.27 : Database - smart_campus
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smart_campus` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `smart_campus`;

/*Table structure for table `smart_campus_auth_function` */

DROP TABLE IF EXISTS `smart_campus_auth_function`;

CREATE TABLE `smart_campus_auth_function` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `page` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_auth_function` */

insert  into `smart_campus_auth_function`(`id`,`name`,`code`,`page`) values ('1','查看当前登录用户发起的签到活动信息','signinactivity_list','signinActivityAction_pageQuery.action'),('10','跳转到批量导入专业班级信息页面','majorclass_batchaddpage','majorClassAction_batchAddMajorClassPage.action'),('11','批量导入专业班级信息','majorclass_batchadd','majorClassAction_batchAddMajorClass.action'),('12','查看当前登录用户所在学校的所有报修单','repairorder_list','repairOrderAction_pageQuery.action'),('13','查看保修单详情','repairorder_find','repairOrderAction_findRepairOrder.action'),('14','改变保修单状态','repairorder_change','repairOrderAction_changeOrderState.action'),('15','加载报修图片','repairorder_findpicture','repairOrderAction_findRepairOrderPicture.action'),('16','查看当前登录用户所在学校的所有学生信息','student_list','studentAction_pageQuery.action'),('17','修改学生信息回显数据','student_find','studentAction_findStudent.action'),('18','修改学生信息','student_update','studentAction_updateStudent.action'),('19','跳转到批量导入学生页面','student_batchaddpage','studentAction_batchAddStudentPage.action'),('2','删除当前登录用户发起的签到活动','signinactivity_delete','signinActivityAction_deleteSigninActivity.action'),('20','批量导入学生信息','student_batchadd','studentAction_batchAddStudent.action'),('21','跳转到添加学生信息的页面','student_addpage','studentAction_addStudentPage.action'),('22','添加学生信息','student_add','studentAction_addStudent.action'),('23','查看当前登录用户所在学校的所有用户信息','user_list','userAction_pageQuery.action'),('24','修改用户信息时回显数据','user_find','userAction_findUser.action'),('25','修改用户信息','user_update','userAction_updateUser.action'),('26','跳转到添加用户信息页面','user_addpage','userAction_addUserPage.action'),('27','添加用户信息','user_add','userAction_addUser.action'),('28','跳转到批量导入用户信息页面','user_batchaddpage','userAction_batchAddUserPage.action'),('29','批量导入学生信息','user_batchadd','userAction_batchAddUser.action'),('3','查看签到活动已签到的签到记录','signindetail_list','signinDetailAction_findSigninPageQuery.action'),('30','查看用户管理模块','user_manager','/'),('31','查看班级管理模块','majorclass_manager','/'),('32','查看课程管理模块','curriculum_manager','/'),('33','查看学生管理模块','student_manager','/'),('34','查看签到管理模块','signin_manager','/'),('35','查看报修模块','repair_manager','/'),('4','查看签到活动未签到的学生','signindetail_listno','signinDetailAction_findNoSigninPageQuery.action'),('5','查看当前登录用户所在学校的所有专业班级','majorclass_list','majorClassAction_pageQuery.action'),('6','修改专业班级信息时回显信息','majorclass_find','majorClassAction_updateMajorClass.action'),('7','修改专业班级信息','majorclass_update','majorClassAction_updateMajorClass.action'),('8','跳转到添加专业班级页面','majorclass_addpage','majorClassAction_addMajorClassPage.action'),('9','添加专业班级信息','majorclass_add','majorClassAction_addMajorClass.action');

/*Table structure for table `smart_campus_auth_role` */

DROP TABLE IF EXISTS `smart_campus_auth_role`;

CREATE TABLE `smart_campus_auth_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(10) NOT NULL,
  `code` varchar(50) NOT NULL,
  `information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_auth_role` */

insert  into `smart_campus_auth_role`(`id`,`name`,`code`,`information`) values ('0','超级管理员','admin',NULL),('1','校管理员','school_admin',NULL),('2','普通教师','teacher',NULL),('3','后勤人员','logistics',NULL);

/*Table structure for table `smart_campus_classactivity_relationship` */

DROP TABLE IF EXISTS `smart_campus_classactivity_relationship`;

CREATE TABLE `smart_campus_classactivity_relationship` (
  `signin_activity_id` varchar(32) NOT NULL,
  `major_class_id` varchar(20) NOT NULL,
  KEY `FK3x60abx38edfr4w3kipjeygqv` (`signin_activity_id`),
  KEY `FKbe6wxovws4ent6kledgf953pb` (`major_class_id`),
  CONSTRAINT `FK3x60abx38edfr4w3kipjeygqv` FOREIGN KEY (`signin_activity_id`) REFERENCES `smart_campus_signin_activity` (`id`),
  CONSTRAINT `FKbe6wxovws4ent6kledgf953pb` FOREIGN KEY (`major_class_id`) REFERENCES `smart_campus_major_class` (`id`),
  CONSTRAINT `smart_campus_classactivity_relationship_major_class_id` FOREIGN KEY (`major_class_id`) REFERENCES `smart_campus_major_class` (`id`),
  CONSTRAINT `smart_campus_classactivity_relationship_signin_activity_id` FOREIGN KEY (`signin_activity_id`) REFERENCES `smart_campus_signin_activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_classactivity_relationship` */

insert  into `smart_campus_classactivity_relationship`(`signin_activity_id`,`major_class_id`) values ('1','1'),('2','1'),('2','2');

/*Table structure for table `smart_campus_curriculum` */

DROP TABLE IF EXISTS `smart_campus_curriculum`;

CREATE TABLE `smart_campus_curriculum` (
  `id` varchar(32) NOT NULL,
  `school_id` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp4hl2kgpv60mjq3sc6ubt0o8g` (`school_id`),
  CONSTRAINT `FKp4hl2kgpv60mjq3sc6ubt0o8g` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`),
  CONSTRAINT `smart_campus_curriculum_school_id` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_curriculum` */

insert  into `smart_campus_curriculum`(`id`,`school_id`,`name`,`information`) values ('1','1','大学物理','大学必修课'),('12312','1','大学英语','大学必修课'),('12362','1','大学物理实验','大学必修课'),('2','2','高等数学','大学必修课'),('3','3','线性代数','大学必修课'),('4','1','Java程序设计','计算机专业必修课'),('5','2','C++程序设计','计算机专业必修课'),('6','3','C#程序设计','计算机专业必修课');

/*Table structure for table `smart_campus_major_class` */

DROP TABLE IF EXISTS `smart_campus_major_class`;

CREATE TABLE `smart_campus_major_class` (
  `id` varchar(32) NOT NULL,
  `school_id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkq8kwopl1svaq9yfyehkvt9ea` (`school_id`),
  CONSTRAINT `FKkq8kwopl1svaq9yfyehkvt9ea` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`),
  CONSTRAINT `smart_campus_major_class_school_id` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_major_class` */

insert  into `smart_campus_major_class`(`id`,`school_id`,`name`,`information`) values ('1','1','计算机科学与技术16-3','校企合作办学'),('2','1','会计16-1','校企合作办学'),('3','1','通讯工程15-1','中外合作办学'),('4','1','电气工程及其自动化14-1','中外合作办学'),('5','2','计算机科学与技术17-1','17级'),('6','2','英语17-2','17级'),('7','3','化学15-2','15级');

/*Table structure for table `smart_campus_repair_order` */

DROP TABLE IF EXISTS `smart_campus_repair_order`;

CREATE TABLE `smart_campus_repair_order` (
  `id` varchar(32) NOT NULL,
  `school_id` varchar(32) NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `student_id` varchar(10) NOT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `place` varchar(100) NOT NULL,
  `information` varchar(255) DEFAULT NULL,
  `order_state` int(11) DEFAULT '0',
  `rating` int(11) DEFAULT NULL,
  `evaluation_content` varchar(255) DEFAULT NULL,
  `telephone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbkyuuqcv5quvodj8m4rlyyh2e` (`school_id`),
  KEY `FKfigvio7e9idc7yt1k5anjkbob` (`student_id`),
  CONSTRAINT `FKbkyuuqcv5quvodj8m4rlyyh2e` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`),
  CONSTRAINT `FKfigvio7e9idc7yt1k5anjkbob` FOREIGN KEY (`student_id`) REFERENCES `smart_campus_student` (`id`),
  CONSTRAINT `smart_campus_repair_order_school_id` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`),
  CONSTRAINT `smart_campus_repair_order_student_id` FOREIGN KEY (`student_id`) REFERENCES `smart_campus_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_repair_order` */

insert  into `smart_campus_repair_order`(`id`,`school_id`,`order_date`,`student_id`,`picture_url`,`place`,`information`,`order_state`,`rating`,`evaluation_content`,`telephone`) values ('1','1','2017-09-18 21:47:46','121212','/WEB-INF/repairimage/钢之炼金术师fa.jpg','5号楼104','凳子坏了',0,NULL,NULL,'123456'),('2','1','2017-09-18 21:56:26','131313','/WEB-INF/repairimage/gangzhilianjinshushifa.jpg','1号楼301','抽屉坏了',0,NULL,NULL,'123456'),('3','1','2017-09-04 22:00:06','151515','/WEB-INF/repairimage/钢之炼金术师fa.jpg','3号楼501','抽屉坏了',2,5,'好!','123456'),('4','1','2017-09-18 22:03:42','141414','/WEB-INF/repairimage/gangzhilianjinshushifa.jpg','1号楼504','凳子坏了',2,4,'好!','123456'),('5','1','2017-09-18 22:13:27','414141','/WEB-INF/repairimage/钢之炼金术师fa.jpg','3号楼501','凳子坏了',2,3,'好!','123456'),('6','1','2017-09-18 22:13:29','616161','/WEB-INF/repairimage/钢之炼金术师fa.jpg','3号楼501','凳子坏了',2,2,'好!','123456'),('7','1','2017-09-18 22:13:30','212121','/WEB-INF/repairimage/钢之炼金术师fa.jpg','3号楼501','凳子坏了',2,1,'好!','123456'),('8','1','2017-09-18 22:13:36','151515','/WEB-INF/repairimage/钢之炼金术师fa.jpg','3号楼501','凳子坏了',2,4,'好!','123456');

/*Table structure for table `smart_campus_role_function` */

DROP TABLE IF EXISTS `smart_campus_role_function`;

CREATE TABLE `smart_campus_role_function` (
  `auth_function_id` varchar(32) NOT NULL,
  `auth_role_id` varchar(32) NOT NULL,
  KEY `FKpyvhcfpkrn6b31kxu8p5v395p` (`auth_role_id`),
  KEY `FKgpmuj4by6vf9mgwat9cyod6tk` (`auth_function_id`),
  CONSTRAINT `FKgpmuj4by6vf9mgwat9cyod6tk` FOREIGN KEY (`auth_function_id`) REFERENCES `smart_campus_auth_function` (`id`),
  CONSTRAINT `FKpyvhcfpkrn6b31kxu8p5v395p` FOREIGN KEY (`auth_role_id`) REFERENCES `smart_campus_auth_role` (`id`),
  CONSTRAINT `smart_campus_role_function_auth_function_id` FOREIGN KEY (`auth_function_id`) REFERENCES `smart_campus_auth_function` (`id`),
  CONSTRAINT `smart_campus_role_function_auth_role_id` FOREIGN KEY (`auth_role_id`) REFERENCES `smart_campus_auth_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_role_function` */

insert  into `smart_campus_role_function`(`auth_function_id`,`auth_role_id`) values ('1','2'),('2','2'),('3','2'),('4','2'),('5','1'),('6','1'),('7','1'),('8','1'),('9','1'),('10','1'),('11','1'),('12','3'),('13','3'),('14','3'),('15','3'),('16','1'),('17','1'),('18','1'),('19','1'),('20','1'),('21','1'),('22','1'),('23','1'),('24','1'),('25','1'),('26','1'),('27','1'),('28','1'),('29','1'),('1','0'),('2','0'),('3','0'),('4','0'),('5','0'),('6','0'),('7','0'),('8','0'),('9','0'),('10','0'),('11','0'),('20','0'),('21','0'),('22','0'),('23','0'),('24','0'),('25','0'),('26','0'),('27','0'),('28','0'),('29','0'),('30','0'),('31','0'),('32','0'),('33','0'),('34','0'),('35','0'),('30','1'),('31','1'),('32','1'),('33','1'),('34','1'),('35','1'),('34','2'),('35','3');

/*Table structure for table `smart_campus_school` */

DROP TABLE IF EXISTS `smart_campus_school`;

CREATE TABLE `smart_campus_school` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  `information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_school` */

insert  into `smart_campus_school`(`id`,`name`,`information`) values ('1','山东科技大学济南校区',NULL),('2','山东大学',NULL),('3','山东理工大学',NULL);

/*Table structure for table `smart_campus_signin_activity` */

DROP TABLE IF EXISTS `smart_campus_signin_activity`;

CREATE TABLE `smart_campus_signin_activity` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `activity_type` int(11) NOT NULL,
  `curriculum_id` varchar(32) DEFAULT NULL,
  `duration` int(11) NOT NULL,
  `information` varchar(255) DEFAULT NULL,
  `activity_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `total_people` int(11) NOT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK3mf9swj4kljg517i3ki41x763` (`user_id`),
  KEY `FKkfajcln6ubpqqp6o7b4ec6ci2` (`curriculum_id`),
  CONSTRAINT `FK3mf9swj4kljg517i3ki41x763` FOREIGN KEY (`user_id`) REFERENCES `smart_campus_user` (`id`),
  CONSTRAINT `FKkfajcln6ubpqqp6o7b4ec6ci2` FOREIGN KEY (`curriculum_id`) REFERENCES `smart_campus_curriculum` (`id`),
  CONSTRAINT `smart_campus_sign_activity_curriculum_id` FOREIGN KEY (`curriculum_id`) REFERENCES `smart_campus_curriculum` (`id`),
  CONSTRAINT `smart_campus_sign_activity_user_id` FOREIGN KEY (`user_id`) REFERENCES `smart_campus_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_signin_activity` */

insert  into `smart_campus_signin_activity`(`id`,`user_id`,`activity_type`,`curriculum_id`,`duration`,`information`,`activity_date`,`total_people`,`longitude`,`latitude`,`state`) values ('1','212121',1,'1',100,'上午3,4节课签到','2017-09-18 22:29:57',5,12,12,1),('2','212121',1,'4',10,'下午5,6节课签到','2017-09-18 22:30:00',10,12,12,1),('3','121212',0,NULL,100,'学生会会议签到','2017-09-18 22:30:04',10,12,12,1);

/*Table structure for table `smart_campus_signin_detail` */

DROP TABLE IF EXISTS `smart_campus_signin_detail`;

CREATE TABLE `smart_campus_signin_detail` (
  `id` varchar(32) NOT NULL,
  `signin_activity_id` varchar(32) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `signin_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `signinstate` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK75b99pyy8j5r4c5dhhybh185y` (`signin_activity_id`),
  KEY `FKjb2ja2m64gt65ttrt4bd5tv29` (`student_id`),
  CONSTRAINT `FK75b99pyy8j5r4c5dhhybh185y` FOREIGN KEY (`signin_activity_id`) REFERENCES `smart_campus_signin_activity` (`id`),
  CONSTRAINT `FKjb2ja2m64gt65ttrt4bd5tv29` FOREIGN KEY (`student_id`) REFERENCES `smart_campus_student` (`id`),
  CONSTRAINT `t_signin_details_activity_id` FOREIGN KEY (`signin_activity_id`) REFERENCES `smart_campus_signin_activity` (`id`),
  CONSTRAINT `t_signin_details_student_id` FOREIGN KEY (`student_id`) REFERENCES `smart_campus_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_signin_detail` */

insert  into `smart_campus_signin_detail`(`id`,`signin_activity_id`,`student_id`,`signin_date`,`longitude`,`latitude`,`signinstate`) values ('1','1','121212','2017-09-18 20:28:28',12,12,1),('10','2','161616','2017-09-18 21:37:35',NULL,NULL,1),('11','2','212121','2017-09-18 21:23:33',NULL,NULL,0),('12','2','313131','2017-09-18 21:23:45',NULL,NULL,0),('13','2','414141','2017-09-18 21:24:00',NULL,NULL,0),('14','2','515151','2017-09-18 21:24:22',NULL,NULL,0),('15','2','616161','2017-09-18 21:24:30',NULL,NULL,0),('2','1','131313','2017-09-18 20:48:04',NULL,NULL,1),('297e256a5e956f5d015e956fbb1d0000','3','121212','2017-09-18 22:41:21',NULL,NULL,1),('3','1','141414','2017-09-18 21:03:11',NULL,NULL,1),('4','1','151515','2017-09-18 21:01:11',NULL,NULL,0),('5','1','161616','2017-09-18 20:31:48',12,12,1),('6','2','121212','2017-09-18 21:13:51',NULL,NULL,0),('7','2','131313','2017-09-18 21:14:12',NULL,NULL,0),('8','2','141414','2017-09-18 21:22:45',NULL,NULL,0),('9','2','151515','2017-09-18 21:22:53',NULL,NULL,0);

/*Table structure for table `smart_campus_student` */

DROP TABLE IF EXISTS `smart_campus_student`;

CREATE TABLE `smart_campus_student` (
  `id` varchar(32) NOT NULL,
  `school_id` varchar(32) NOT NULL,
  `major_class_id` varchar(32) NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(32) DEFAULT 'e10adc3949ba59abbe56e057f20f883e',
  `openid` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8sfe0o6dhcbskjb2cegse41v8` (`major_class_id`),
  KEY `FK2wcr5blcwwvp35tm74gm1f79d` (`school_id`),
  CONSTRAINT `FK2wcr5blcwwvp35tm74gm1f79d` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`),
  CONSTRAINT `FK8sfe0o6dhcbskjb2cegse41v8` FOREIGN KEY (`major_class_id`) REFERENCES `smart_campus_major_class` (`id`),
  CONSTRAINT `smart_campus_student_major_class_id` FOREIGN KEY (`major_class_id`) REFERENCES `smart_campus_major_class` (`id`),
  CONSTRAINT `smart_campus_student_school_id` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_student` */

insert  into `smart_campus_student`(`id`,`school_id`,`major_class_id`,`name`,`password`,`openid`,`telephone`) values ('121212','1','1','张三','e10adc3949ba59abbe56e057f20f883e','让我滚去学习',NULL),('131313','1','1','李四','e10adc3949ba59abbe56e057f20f883e',NULL,NULL),('141414','1','1','王五','e10adc3949ba59abbe56e057f20f883e',NULL,NULL),('151515','1','1','李六','e10adc3949ba59abbe56e057f20f883e',NULL,NULL),('161616','1','1','王八','e10adc3949ba59abbe56e057f20f883e',NULL,NULL),('212121','1','2','小明','e10adc3949ba59abbe56e057f20f883e',NULL,NULL),('313131','1','2','小红','e10adc3949ba59abbe56e057f20f883e',NULL,NULL),('414141','1','2','小白','e10adc3949ba59abbe56e057f20f883e',NULL,NULL),('515151','1','2','小黑','e10adc3949ba59abbe56e057f20f883e',NULL,NULL),('616161','1','2','小紫','e10adc3949ba59abbe56e057f20f883e',NULL,NULL);

/*Table structure for table `smart_campus_user` */

DROP TABLE IF EXISTS `smart_campus_user`;

CREATE TABLE `smart_campus_user` (
  `id` varchar(32) NOT NULL,
  `school_id` varchar(32) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT 'e10adc3949ba59abbe56e057f20f883e',
  `openid` varchar(50) DEFAULT NULL,
  `userstate` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FKaaiphryic3c3imbkmfevgp1kt` (`school_id`),
  CONSTRAINT `FKaaiphryic3c3imbkmfevgp1kt` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`),
  CONSTRAINT `smart_campus_user_school_id` FOREIGN KEY (`school_id`) REFERENCES `smart_campus_school` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_user` */

insert  into `smart_campus_user`(`id`,`school_id`,`name`,`telephone`,`password`,`openid`,`userstate`) values ('111111','1','山东科技大学济南校区教师及后勤','123456','e10adc3949ba59abbe56e057f20f883e',NULL,1),('121212','1','山东科技大学济南校区校管理员','123456','e10adc3949ba59abbe56e057f20f883e',NULL,1),('131313','2','山东大学校管理员','123456','e10adc3949ba59abbe56e057f20f883e',NULL,1),('141414','2','山东大学普通教师','123456','e10adc3949ba59abbe56e057f20f883e',NULL,1),('212121','1','山东科技大学济南校区普通教师','123456','e10adc3949ba59abbe56e057f20f883e',NULL,1),('313131','1','山东科技大学济南校区后勤','123456','e10adc3949ba59abbe56e057f20f883e',NULL,1),('admin','1','超级管理员','123456','e10adc3949ba59abbe56e057f20f883e',NULL,1);

/*Table structure for table `smart_campus_user_role` */

DROP TABLE IF EXISTS `smart_campus_user_role`;

CREATE TABLE `smart_campus_user_role` (
  `auth_role_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  KEY `FKlfpbrhncbd4js7haxjcxuln0h` (`user_id`),
  KEY `FK7q662qjfxbavm8n9b7fpi5p18` (`auth_role_id`),
  CONSTRAINT `FK7q662qjfxbavm8n9b7fpi5p18` FOREIGN KEY (`auth_role_id`) REFERENCES `smart_campus_auth_role` (`id`),
  CONSTRAINT `FKlfpbrhncbd4js7haxjcxuln0h` FOREIGN KEY (`user_id`) REFERENCES `smart_campus_user` (`id`),
  CONSTRAINT `smart_campus_auth_role_auth_role_id` FOREIGN KEY (`auth_role_id`) REFERENCES `smart_campus_auth_role` (`id`),
  CONSTRAINT `smart_campus_auth_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `smart_campus_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_user_role` */

insert  into `smart_campus_user_role`(`auth_role_id`,`user_id`) values ('0','admin'),('1','121212'),('1','131313'),('2','141414'),('2','212121'),('2','111111'),('3','111111'),('3','313131');

/*Table structure for table `smart_campus_usercurriculum_relationship` */

DROP TABLE IF EXISTS `smart_campus_usercurriculum_relationship`;

CREATE TABLE `smart_campus_usercurriculum_relationship` (
  `user_id` varchar(32) NOT NULL,
  `curriculum_id` varchar(32) NOT NULL,
  KEY `FKlovu5tn54bmhyhcjp2q92dxic` (`user_id`),
  KEY `FKftp5i464jebwg6p0lrgvvjnce` (`curriculum_id`),
  CONSTRAINT `FKftp5i464jebwg6p0lrgvvjnce` FOREIGN KEY (`curriculum_id`) REFERENCES `smart_campus_curriculum` (`id`),
  CONSTRAINT `FKlovu5tn54bmhyhcjp2q92dxic` FOREIGN KEY (`user_id`) REFERENCES `smart_campus_user` (`id`),
  CONSTRAINT `smart_campus_usercurriculum_relationship_curriculum_id` FOREIGN KEY (`curriculum_id`) REFERENCES `smart_campus_curriculum` (`id`),
  CONSTRAINT `smart_campus_usercurriculum_relationship_user_id` FOREIGN KEY (`user_id`) REFERENCES `smart_campus_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `smart_campus_usercurriculum_relationship` */

insert  into `smart_campus_usercurriculum_relationship`(`user_id`,`curriculum_id`) values ('212121','1'),('212121','4'),('111111','4');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
