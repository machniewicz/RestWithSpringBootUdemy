CREATE TABLE public.books (
  id bigint NOT NULL,
  author character varying(100) NOT NULL,
  launch_date timestamp without time zone NOT NULL,
  price decimal(65,2) NOT NULL,
  title character varying(100),
  CONSTRAINT books_pkey PRIMARY KEY (id)
);

