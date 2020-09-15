CREATE TABLE public.users (
  id bigint NOT NULL,
  user_name character varying(255) DEFAULT NULL,
  full_name character varying(255) DEFAULT NULL,
  password character varying(255) DEFAULT NULL,
  account_non_expired bit(1) DEFAULT NULL,
  account_non_locked bit(1) DEFAULT NULL,
  credentials_non_expired bit(1) DEFAULT NULL,
  enabled bit(1) DEFAULT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id)
);