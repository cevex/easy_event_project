/*######################################################################################*/
/*                      USER                                                            */
/*######################################################################################*/

INSERT INTO t_user (user_name, user_password, user_phone, user_email)
    VALUES  ('Boby', '123', '0645896285', 'boby@test.com'),
            ('Joe', 'MonMotDePasse','0646846298', 'joe.blo@test.com'),
            ('Mel', '123','0645896269', 'boby@test.com'),
            ('Will', '123','0645896269', 'boby@test.com');

/*######################################################################################*/
/*                     Event                                                            */
/*######################################################################################*/

INSERT INTO t_event (event_title, event_place, event_start_date, event_end_date)
    VALUES  ('RoadTrip ', 'East-Cost', '2017-01-01 08:00:00+01', '2017-01-10 21:00:00+01'),
            ('Coloc', 'Lannion', '2017-02-01 10:23:54+01', '2017-03-01 08:00:00+01'),
            ('New Years', 'Chalet', '2017-12-31 08:00:00+01', '2018-01-03 08:00:00+01'),
            ('Week End with the buddies', 'Lac', '2017-12-31 08:00:00+01', '2018-01-03 08:00:00+01');

/*============================================================
                     Road Trip : 1
============================================================*/

INSERT INTO t_participant (participant_event_id, participant_user_name)
    VALUES  (1, 'Boby'),
            (1, 'Joe'),
            (1, 'Mel');

INSERT INTO t_expense (expense_event_id, expense_label, expense_date)
    VALUES  (1, 'Car rent', '2017-01-01 10:23:54+01'),
            (1, 'Food', '2017-05-01 12:23:45+01'),
            (1, 'AirBnB NYC', '2017-03-01 20:46:08+01');

/* Car rent */
INSERT INTO t_contribution (contribution_expense_id, contribution_participant_id, contribution_amount, contribution_currency)
    VALUES  (1, 1, 100, '€'), /* Boby */
            (1, 2, 50, '€'),  /* Joe */
            (1, 3, 0, '€');   /* Mel */

/* Food */
INSERT INTO t_contribution (contribution_expense_id, contribution_participant_id, contribution_amount, contribution_currency)
    VALUES  (2, 1, 0, '€'),   /* Boby */
            (2, 2, 0, '€'),   /* Joe */
            (2, 3, 50, '€');  /* Mel */

/* AirBnB NYC */
INSERT INTO t_contribution (contribution_expense_id, contribution_participant_id, contribution_amount, contribution_currency)
    VALUES  (3, 1, 150, '€'), /* Boby */
            (3, 2, 50, '€'),  /* Joe */
            (3, 3, 100, '€'); /* Mel */


