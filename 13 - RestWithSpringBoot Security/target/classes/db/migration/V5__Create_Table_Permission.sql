CREATE TABLE public.permission (
  id bigint NOT NULL,
  description character varying(255) DEFAULT NULL,
  CONSTRAINT permission_pkey PRIMARY KEY (id)
);
