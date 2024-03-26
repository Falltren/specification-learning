CREATE TABLE public.categories (
    id bigserial primary key,
    title character varying(255)
);

CREATE TABLE public.news (
    id bigserial primary key,
    title character varying(255),
    text character varying(255),
    date timestamp(6) without time zone,
    category_id bigserial references categories(id)
);


