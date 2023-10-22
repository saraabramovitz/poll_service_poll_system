

CREATE TABLE question(
    question_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    question_title varchar(300) UNIQUE NOT NULL,
    PRIMARY KEY (question_id)
);

CREATE TABLE option (
    option_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    option_type VARCHAR(10) NOT NULL,
    option_title VARCHAR(300) NOT NULL,
    question_id INT NOT NULL,
    PRIMARY KEY (option_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id)
);

CREATE TABLE answer (
    answer_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    question_id INT NOT NULL,
    option_id INT NOT NULL,
    PRIMARY KEY (answer_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id),
    FOREIGN KEY (option_id) REFERENCES option (option_id)
);





