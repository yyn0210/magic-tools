
CREATE TABLE `student`  (
							`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键(学号)',
							`last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
							`email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱',
							`gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
							`m_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '专业id',
							PRIMARY KEY (`id`)
);
