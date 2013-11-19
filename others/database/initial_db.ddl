-- ###############
--    create database , if need create, cancel the comment
-- ###############
-- create database if not exists jiuzhai default character set utf8;
-- use jiuzhai set default character = utf8;

-- ###############
--    grant privileges  to jiuzhai/jiuzhai
-- ###############
-- GRANT ALL PRIVILEGES ON jiuzhai.* TO jiuzhai@localhost IDENTIFIED BY "jiuzhai";

-- ###############
-- Domain:  User
-- ###############
Drop table  if exists user_;
CREATE TABLE `user_` (
  `id` int(11) NOT NULL auto_increment,
  `guid` varchar(255) not null unique,
  `create_time` datetime ,
  `archived` tinyint(1) default '0',
  `email` varchar(255),
  `password` varchar(255) not null,
  `phone` varchar(255),
  `username` varchar(255) not null unique,
   `default_user` tinyint(1) default '0',
   `last_login_time` datetime ,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

