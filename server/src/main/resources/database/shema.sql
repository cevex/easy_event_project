DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    user_id bigserial CONSTRAINT t_user_pk PRIMARY KEY,
    user_name varchar(50),
    user_phone varchar(20),
    user_mail varchar(260),
    user_wallet money,
    user_image bytea
);

DROP TABLE IF EXISTS t_event;
CREATE TABLE t_event (
    event_id bigserial CONSTRAINT t_event_pk PRIMARY KEY,
    event_title varchar(200) NOT NULL,
    event_place varchar(200),
    event_start_date timestamp with time zone DEFAULT now(),
    event_end_date timestamp with time zone DEFAULT null,
    event_image bytea
);
