CREATE TABLE table_access
(
    id            INT NOT NULL AUTO_INCREMENT,
    timestamp     DATETIME     DEFAULT NULL,
    class         VARCHAR(255) DEFAULT NULL,
    event         VARCHAR(255) DEFAULT NULL,
    connection_id VARCHAR(255) DEFAULT NULL,
    account_user  VARCHAR(255) DEFAULT NULL,
    account_host  VARCHAR(255) DEFAULT NULL,
    login_ip      VARCHAR(255) DEFAULT NULL,
    db            VARCHAR(255) DEFAULT NULL,
    `table`       VARCHAR(255) DEFAULT NULL, -- 用反引号引用保留字
    sql_command   VARCHAR(2550) CHARACTER SET utf8,
    query         VARCHAR(2550) CHARACTER SET utf8,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE connection_access
(
    id              INT NOT NULL AUTO_INCREMENT,
    timestamp       DATETIME     DEFAULT NULL,
    class           VARCHAR(255) DEFAULT NULL,
    event           VARCHAR(255) DEFAULT NULL,
    account_user    VARCHAR(255) DEFAULT NULL,
    account_host    VARCHAR(255) DEFAULT NULL,
    login_ip        VARCHAR(255) DEFAULT NULL,
    connection_type VARCHAR(255) DEFAULT NULL,
    status          VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE general_access
(
    id            INT NOT NULL AUTO_INCREMENT,
    timestamp     DATETIME     DEFAULT NULL,
    class         VARCHAR(255) DEFAULT NULL,
    event         VARCHAR(255) DEFAULT NULL,
    connection_id VARCHAR(255) DEFAULT NULL,
    account_user  VARCHAR(255) DEFAULT NULL,
    account_host  VARCHAR(255) DEFAULT NULL,
    login_ip      VARCHAR(255) DEFAULT NULL,
    status        VARCHAR(255) DEFAULT NULL,
    sql_command   VARCHAR(2550) CHARACTER SET utf8,
    query         VARCHAR(2550) CHARACTER SET utf8,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE rule_template
(
    id          INT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) DEFAULT NULL,
    description VARCHAR(2550),
    source      VARCHAR(255) DEFAULT NULL,
    create_time DATETIME     DEFAULT NULL,
    edit_time   DATETIME     DEFAULT NULL,
    primary key (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE template_details
(
    id          INT          NOT NULL AUTO_INCREMENT,
    template_id INT          NOT NULL,
    classify_1  VARCHAR(255) DEFAULT NULL,
    classify_2  VARCHAR(255) DEFAULT NULL,
    classify_3  VARCHAR(255) DEFAULT NULL,
    classify_4  VARCHAR(255) DEFAULT NULL,
    rule_name   VARCHAR(255) NOT NULL,
    rule_name_eng VARCHAR(255) NOT NULL,
    rule_rank   VARCHAR(255) NOT NULL,
    primary key (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;


