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

CREATE TABLE templates
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

CREATE TABLE template_and_rules
(
    id          INT          NOT NULL AUTO_INCREMENT,
    template_id INT          NOT NULL,
    classify_1  VARCHAR(255) DEFAULT NULL,
    classify_2  VARCHAR(255) DEFAULT NULL,
    classify_3  VARCHAR(255) DEFAULT NULL,
    classify_4  VARCHAR(255) DEFAULT NULL,
    rule_name   VARCHAR(255) NOT NULL,
    rule_rank   VARCHAR(255) NOT NULL,
    primary key (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE rule
(
    id              INT          NOT NULL AUTO_INCREMENT,
    name            VARCHAR(255) NOT NULL,
    description     VARCHAR(2550),
    match_operators VARCHAR(255) NOT NULL,
    source          VARCHAR(255) NOT NULL,
    status          INT          NOT NULL,
    content_rule    VARCHAR(2550) default NULL,
    meta_rule       VARCHAR(2550) default NULL,
    primary key (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE classification
(
    id     INT          NOT NULL AUTO_INCREMENT,
    name   VARCHAR(255) NOT NULL,
    source VARCHAR(255) NOT NULL,
    primary key (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE data_source_config
(
    id               INT          NOT NULL AUTO_INCREMENT,
    data_source_type VARCHAR(255) NOT NULL,
    name             VARCHAR(255) NOT NULL,
    description      VARCHAR(2550),
    ip               VARCHAR(255) NOT NULL,
    port             VARCHAR(255) NOT NULL,
    userName         VARCHAR(255) NOT NULL,
    passwd           VARCHAR(64)  NOT NULL,
    `database`       VARCHAR(255) NOT NULL,
    config           VARCHAR(255) DEFAULT NULL,
    primary key (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE sensitive_data
(
    id             INT          NOT NULL AUTO_INCREMENT,
    data_source_id INT          NOT NULL,
    database_name  VARCHAR(255) NOT NULL,
    table_name     VARCHAR(255) NOT NULL,
    field_name     VARCHAR(255) NOT NULL,
    rule_id        INT          NOT NULL,
    rule_rank      VARCHAR(255) NOT NULL,
    primary key (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE classify_task
(
    id                 INT          NOT NULL AUTO_INCREMENT,
    name               VARCHAR(255) NOT NULL,
    description        VARCHAR(2550),
    data_source_id     INT          NOT NULL,
    template_id        INT          NOT NULL,
    execute_plan       VARCHAR(255) NOT NULL,
    cycle              VARCHAR(255) NOT NULL,
    start_time         DATETIME DEFAULT NULL,
    last_finished_time DATETIME DEFAULT NULL,
    status             VARCHAR(255) NOT NULL,
    primary key (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE sensitive_data_query_log
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    timestamp     DATETIME,
    account_user  VARCHAR(255),
    account_host  VARCHAR(255),
    login_ip      VARCHAR(255),
    db            VARCHAR(255),
    table_name    VARCHAR(255),
    sql_command   VARCHAR(255),
    query         TEXT,
    datasource_id INT
);

CREATE TABLE privileged_command_execution_log
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    timestamp     DATETIME,
    account_user  VARCHAR(255),
    account_host  VARCHAR(255),
    login_ip      VARCHAR(255),
    sql_command   VARCHAR(255),
    query         TEXT,
    datasource_id INT
);

CREATE TABLE account_creation_deletion_log
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    timestamp     DATETIME,
    account_user  VARCHAR(255),
    account_host  VARCHAR(255),
    login_ip      VARCHAR(255),
    event         VARCHAR(50),
    datasource_id INT
);

CREATE TABLE permission_change_log
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    timestamp     DATETIME,
    account_user  VARCHAR(255),
    account_host  VARCHAR(255),
    login_ip      VARCHAR(255),
    sql_command   VARCHAR(255),
    query         TEXT,
    datasource_id INT
);

CREATE TABLE unauthorized_access_log
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    timestamp     DATETIME,
    account_user  VARCHAR(255),
    account_host  VARCHAR(255),
    login_ip      VARCHAR(255),
    event         VARCHAR(50),
    datasource_id INT
);

CREATE TABLE login_failure_log
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    timestamp     DATETIME,
    account_user  VARCHAR(255),
    account_host  VARCHAR(255),
    login_ip      VARCHAR(255),
    datasource_id INT
);

CREATE TABLE risk_log
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    timestamp     DATETIME,
    type          VARCHAR(50),
    description   TEXT,
    datasource_id INT
);









