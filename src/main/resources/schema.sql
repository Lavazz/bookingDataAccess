drop table if exists ticket;
drop table if exists user;
drop table if exists event;
drop table if exists user_account;


CREATE TABLE public.user_account
(
    id bigserial,
    amount bigint,
    CONSTRAINT user_account_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user
(
    id bigserial,
    name character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",
    user_account_id bigint,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_user_account_id_fkey FOREIGN KEY (user_account_id)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.event
(
    id bigserial,
    title character varying COLLATE pg_catalog."default",
    date date,
    ticket_price bigint,
    CONSTRAINT event_pkey PRIMARY KEY (id)
);

CREATE TABLE public.ticket
(
    id bigserial,
    event_id bigint,
    user_id bigint,
    place integer,
    category character varying COLLATE pg_catalog."default",
    CONSTRAINT ticket_pkey PRIMARY KEY (id),
    CONSTRAINT ticket_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ticket_event_id_fkey FOREIGN KEY (event_id)
        REFERENCES public.event (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);