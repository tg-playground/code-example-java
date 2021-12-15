create table if not exists `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT NOW(),
  `modify_time` datetime NULL ON UPDATE NOW(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
