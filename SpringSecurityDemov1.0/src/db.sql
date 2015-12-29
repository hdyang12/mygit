DROP DATABASE IF EXISTS  _security;
CREATE DATABASE _security;
USE _security;
-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itcast_privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('1', '/role_list', '岗位管理');
INSERT INTO `privilege` VALUES ('2', '/department_list', '部门管理');
INSERT INTO `privilege` VALUES ('3', '/user_list', '用户管理');
INSERT INTO `privilege` VALUES ('4', '/hello', '测试');
-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itcast_role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '程序员', '');
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itcast_user
-- ----------------------------
INSERT INTO `user` VALUES ('101','zhangsan','1234');
-- ----------------------------
-- Table structure for role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege` (
  `privilegeId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleId`,`privilegeId`),
  KEY `FK45FBD6287A3A50B9` (`privilegeId`),
  KEY `FK45FBD628E0BF6C8B` (`roleId`),
  CONSTRAINT `FK45FBD6287A3A50B9` FOREIGN KEY (`privilegeId`) REFERENCES `privilege` (`id`),
  CONSTRAINT `FK45FBD628E0BF6C8B` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itcast_role_privilege
-- ----------------------------
INSERT INTO `role_privilege` VALUES ('1', '1');
INSERT INTO `role_privilege` VALUES ('2', '1');
INSERT INTO `role_privilege` VALUES ('3', '1');
INSERT INTO `role_privilege` VALUES ('4', '1');
-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `roleId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK143BF46AE0BF6C8B` (`roleId`),
  KEY `FK143BF46AE614C1F5` (`userId`),
  CONSTRAINT `FK143BF46AE0BF6C8B` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `FK143BF46AE614C1F5` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itcast_user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '101');
