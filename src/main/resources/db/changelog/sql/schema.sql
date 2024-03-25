CREATE TABLE public.news (
    id bigint NOT NULL,
    title character varying(255),
    text character varying(255),
    date timestamp(6) without time zone
);