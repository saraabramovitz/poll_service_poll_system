

CREATE TABLE question(
    question_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    question_title varchar(300) UNIQUE NOT NULL,
    PRIMARY KEY (question_id)
);

CREATE TABLE answer_option (
    answer_option_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    question_id INT NOT NULL,
    option_type VARCHAR(10) NOT NULL,
    answer_option_title VARCHAR(300) NOT NULL,
    PRIMARY KEY (answer_option_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id)
);

CREATE TABLE user_answer (
    user_answer_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    question_id INT NOT NULL,
    answer_option_id INT NOT NULL,
    PRIMARY KEY (user_answer_id),
    FOREIGN KEY (question_id) REFERENCES question (question_id),
    FOREIGN KEY (answer_option_id) REFERENCES answer_option (answer_option_id)
);





