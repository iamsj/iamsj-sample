DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `login_name` varchar(30) NOT NULL DEFAULT '' COMMENT '登录名称',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `mobile` varchar(60)  NOT NULL DEFAULT '' COMMENT '手机',
  `unit_id` bigint(20) NOT NULL  DEFAULT 0 COMMENT '单位id',
  `name` varchar(30) NOT NULL  DEFAULT '' COMMENT '姓名',
  `type` tinyint(6) NOT NULL DEFAULT '0' COMMENT '0、普通用户 1、管理员',
  `status` int(6) NOT NULL DEFAULT '1' COMMENT '0、禁用 1、正常',
  `create_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime  COMMENT '创建时间',
  `last_login_time` datetime   COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL  AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '角色',
  `sort` int(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `description` varchar(60) NOT NULL DEFAULT '' COMMENT '描述',
  `status` int(6) NOT NULL DEFAULT 1 COMMENT '状态（1启用0禁用）',
  `create_user_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime  COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
  `rid` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '上级ID',
  `title` varchar(30) NOT NULL DEFAULT '' COMMENT '标题',
  `type` int(6) NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、功能',
  `status` int(6) NOT NULL DEFAULT '1' COMMENT '状态 1、正常 0、禁用',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `url` varchar(100)  NOT NULL DEFAULT '' COMMENT '地址',
  `permCode` varchar(30)  NOT NULL DEFAULT '' COMMENT '权限编码',
  `icon` varchar(30)  NOT NULL DEFAULT '' COMMENT '图标',
  `description` varchar(80)  NOT NULL DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8   COMMENT='权限表';

DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` bigint(20) NOT NULL DEFAULT 0 COMMENT '角色ID',
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8   COMMENT='角色权限表';