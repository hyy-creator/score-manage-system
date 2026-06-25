CREATE DATABASE IF NOT EXISTS school_score DEFAULT CHARACTER SET utf8mb4;
USE school_score; 


CREATE TABLE `clazz` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `clazz_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '班级名称',
  `major_id` int NOT NULL COMMENT '所属专业ID',
  PRIMARY KEY (`id`),
  KEY `fk_clazz_major` (`major_id`),
  CONSTRAINT `fk_clazz_major` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci 




 CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `course_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程名称',
  `credit` tinyint NOT NULL COMMENT '学分',
  `teacher_id` int NOT NULL COMMENT '授课教师用户ID',
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci   




CREATE TABLE `major` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '专业ID',
  `major_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '专业名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci 



CREATE TABLE `score` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '成绩主键',
  `student_id` int NOT NULL COMMENT '学生用户ID',
  `course_id` int NOT NULL COMMENT '课程ID',
  `teacher_id` int DEFAULT NULL,
  `score` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stu_course_unique` (`student_id`,`course_id`),
  KEY `fk_score_course` (`course_id`),
  CONSTRAINT `fk_score_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE,
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci




CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录账号',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录密码',
  `real_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '真实姓名',
  `role` tinyint NOT NULL COMMENT '0管理员 1教师 2学生',
  `clazz_id` int DEFAULT NULL COMMENT '学生所属班级ID，教师/管理员为空',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci

