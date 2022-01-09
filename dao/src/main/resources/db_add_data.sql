USE forbidden_polls;

INSERT INTO forbidden_polls.users (name, email, hashed_password, registration_date, birthday, user_role_id, country_id,
                                   gender_id)
VALUES ('Dzianis', 'plizzz.healme@gmail.com',
        '$s0$41010$+LvX+y5UQqJUljC8ViCIuA==$LoqQQKZ15D+wLMVn6GcSMEPScy9HKDkhhGCLP2wXzT8=', '2022-01-05 11:11:56',
        '1989-09-05 11:11:59', 1, 21, 2);

INSERT INTO forbidden_polls.users (name, email, hashed_password, registration_date, birthday, user_role_id, country_id,
                                   gender_id)
VALUES ('Alesia', 'alesia@gmail.com', '$s0$41010$irG01IpqRr3338XIBNascA==$o2pZtEmPtQPKyFHxQso+fXSRuRVs7LdOzfNSDalSPiw=',
        '2022-01-05 11:16:56', '1989-09-21 11:16:59', 2, 21, 1);

-- add surveys categories

INSERT INTO forbidden_polls.categories (name)
VALUES ('politics');

INSERT INTO forbidden_polls.categories (name)
VALUES ('health');

INSERT INTO forbidden_polls.categories (name)
VALUES ('sport');

-- add polls
START TRANSACTION;
INSERT INTO forbidden_polls.surveys (name, creation_date, description, instructions, image_url, category_id)
VALUES ('Poll about smoking', '2022-01-05 11:27:52', null, null, null, 2);

INSERT INTO forbidden_polls.questions (index_number, body, image_url, question_description, survey_id, option_type_id)
VALUES (1, 'Do you smoke?', null, null, 1, 1);

INSERT INTO forbidden_polls.options (body, question_id)
VALUES ('yes', 1);

INSERT INTO forbidden_polls.options (body, question_id)
VALUES ('no', 1);


COMMIT;



