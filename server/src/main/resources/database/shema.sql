CREATE EXTENSION IF NOT EXISTS pgcrypto;

/*######################################################################################*/
/*                                    USER                                              */
/*######################################################################################*/

DROP TABLE IF EXISTS t_user;
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
/*              Encode Password           */
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


DROP TABLE IF EXISTS t_event;
CREATE TABLE t_event (
    event_id bigserial CONSTRAINT t_event_pk PRIMARY KEY,
    event_title varchar(200) NOT NULL,
    event_place varchar(200),
    event_start_date timestamp with time zone DEFAULT now(),
    event_end_date timestamp with time zone DEFAULT null,
    event_image bytea
);
