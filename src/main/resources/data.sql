
CREATE TABLE pool_question (
    question_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    question_title varchar(300) NOT NULL DEFAULT '',
    first_answer_option varchar(300) NOT NULL DEFAULT '',
    second_answer_option varchar(300) NOT NULL DEFAULT '',
    third_answer_option varchar(300) NOT NULL DEFAULT '',
    fourth_answer_option varchar(300) NOT NULL DEFAULT '',
    PRIMARY KEY (question_id)

);

CREATE TABLE user_pool_answer (
    user_answer_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    user_id varchar(300) NOT NULL DEFAULT '',
    question_id varchar(300) NOT NULL DEFAULT '',
    user_answer varchar(300) NOT NULL DEFAULT '',
    PRIMARY KEY (user_answer_id),
    FOREIGN KEY (question_id) REFERENCES pool_question (question_id)
);

