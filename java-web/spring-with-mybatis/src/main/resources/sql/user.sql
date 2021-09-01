CREATE TABLE `user`
(
    `user_id`       INT         NOT NULL AUTO_INCREMENT,
    `user_name`     VARCHAR(32) NOT NULL,
    `user_password` VARCHAR(32) NOT NULL,
    `user_email`    VARCHAR(32) NOT NULL,
    PRIMARY KEY (`user_id`),
    INDEX           `idx_name` (`user_name`)
) COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;
