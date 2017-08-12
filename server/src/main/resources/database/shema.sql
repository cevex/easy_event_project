CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS t_contribution cascade;
DROP TABLE IF EXISTS t_expense cascade;
DROP TABLE IF EXISTS t_participant cascade;
DROP TABLE IF EXISTS t_event cascade;
DROP TABLE IF EXISTS t_user cascade;

/*######################################################################################*/
/*                                    USER                                              */
/*######################################################################################*/

CREATE TABLE t_user (
    user_id bigserial CONSTRAINT t_user_pk PRIMARY KEY,
    user_email varchar(260),
    user_password varchar(60),
    user_name varchar(50),
    user_phone varchar(20),
    user_wallet money,
    user_image bytea
);

/*========================================*/
/*       Encode Password                  */
/*========================================*/

CREATE OR REPLACE FUNCTION encode_password_user_before_insert()
    RETURNS trigger AS '
BEGIN
    NEW.user_password := crypt(NEW.user_password, gen_salt(''bf'',8));
    RETURN NEW;
END' LANGUAGE 'plpgsql';

CREATE TRIGGER user_before_insert_trigger
    BEFORE INSERT ON t_user
    FOR EACH ROW
    EXECUTE PROCEDURE encode_password_user_before_insert();

/*######################################################################################*/
/*                     Event                                                            */
/*######################################################################################*/

CREATE TABLE t_event (
    event_id bigserial CONSTRAINT t_event_pk PRIMARY KEY,
    event_title varchar(200) NOT NULL,
    event_place varchar(200),
    event_start_date timestamp with time zone DEFAULT now(),
    event_end_date timestamp with time zone DEFAULT null,
    event_image bytea
);

/*######################################################################################*/
/*                     Participant                                                      */
/*######################################################################################*/

CREATE TABLE t_participant (
    participant_id bigserial CONSTRAINT t_participant_pk PRIMARY KEY,
    participant_event_id bigint references t_event(event_id) NOT NULL,
    participant_user_name varchar(50)
);

/*participant_user_id bigserial references t_user(username)*/

/*######################################################################################*/
/*                     Expense                                                          */
/*######################################################################################*/

CREATE TABLE t_expense (
    expense_id bigserial CONSTRAINT t_expense_pk PRIMARY KEY,
    expense_event_id bigint references t_event(event_id) NOT NULL,
    expense_label varchar(200),
    expense_date timestamp with time zone DEFAULT now()
);

/*######################################################################################*/
/*                     Contribution                                                     */
/*######################################################################################*/

CREATE TABLE t_contribution (
    contribution_expense_id bigint references t_expense(expense_id) NOT NULL,
    contribution_participant_id bigint references t_participant(participant_id) NOT NULL,
    contribution_amount money DEFAULT 0,
    CONSTRAINT t_contribution_pk PRIMARY KEY (contribution_expense_id, contribution_participant_id)
);