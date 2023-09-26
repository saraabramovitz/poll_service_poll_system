
CREATE TABLE pool_question (
    question_id int(11) unsigned NOT NULL AUTO_INCREMENT,
    question_title varchar(300) NOT NULL DEFAULT '',
    first_answer_option varchar(300) NOT NULL DEFAULT '',
    second_answer_option varchar(300) NOT NULL DEFAULT '',
    third_answer_option varchar(300) NOT NULL DEFAULT '',
    fourth_answer_option varchar(300) NOT NULL DEFAULT '',
    PRIMARY KEY (question_id)
);
