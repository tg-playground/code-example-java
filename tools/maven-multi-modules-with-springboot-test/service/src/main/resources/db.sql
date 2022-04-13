CREATE TABLE `user`
(
    `user_id`       INT(10) NOT NULL AUTO_INCREMENT,
    `user_name`     VARCHAR(32) NOT NULL COLLATE 'utf8_general_ci',
    `user_password` VARCHAR(32) NOT NULL COLLATE 'utf8_general_ci',
    `user_email`    VARCHAR(32) NOT NULL COLLATE 'utf8_general_ci',
    `create_time`   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`) USING BTREE,
    INDEX           `idx_name` (`user_name`) USING BTREE
) COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;
