
CREATE TABLE user (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    first_name varchar(300) NOT NULL DEFAULT '',
    last_name varchar(300) NOT NULL DEFAULT '',
    email varchar(300) NOT NULL DEFAULT '',
    age int,
    address varchar(300) NOT NULL DEFAULT '',
    joining_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_registered BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)
);
