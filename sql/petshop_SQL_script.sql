DROP DATABASE  IF EXISTS `petshop`;

CREATE DATABASE  IF NOT EXISTS `petshop`;
USE `petshop`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `budget` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.bcryptcalculator.com/
--
-- Default passwords here for admin is: 12345
--

INSERT INTO `user` (username,password,first_name,last_name,email,budget)
VALUES 
('admin','$2a$10$gULggafvj7Gi4LXBdj6Yb.YuUhMUOrm./6vL.XXo54loeP4UaZ6S6','admin','admin','admin@admin.com',50000);


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(1, 2);
--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;

CREATE TABLE `animal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `species` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL, 
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `animal` VALUES 
	(1,'Lucky','M','cat',100,1),
	(2,'Tortilla','F','turtle',1500,1),
    (3,'Wilson','M','dog',450,1),
    (4,'Gold Fish','M','fish',10,25),
    (5,'Gold Fish','F','fish',10,25),
    (6,'Kesha','M','parrot',850,1),
	(7,'Sandra','F','parrot',850,1)
