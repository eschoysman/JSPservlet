--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2023-10-12 12:37:43

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 32792)
-- Name: Anagrafica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Anagrafica" (
    id bigint NOT NULL,
    nome character varying(64) NOT NULL,
    cognome character varying(64) NOT NULL,
    "dataNascita" date NOT NULL,
    "luogoNascita" character varying(64) NOT NULL,
    "idRiferimento" character varying
);


ALTER TABLE public."Anagrafica" OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 32791)
-- Name: Anagrafica_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Anagrafica_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Anagrafica_id_seq" OWNER TO postgres;

--
-- TOC entry 4848 (class 0 OID 0)
-- Dependencies: 219
-- Name: Anagrafica_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Anagrafica_id_seq" OWNED BY public."Anagrafica".id;


--
-- TOC entry 4695 (class 2604 OID 32795)
-- Name: Anagrafica id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Anagrafica" ALTER COLUMN id SET DEFAULT nextval('public."Anagrafica_id_seq"'::regclass);


--
-- TOC entry 4842 (class 0 OID 32792)
-- Dependencies: 220
-- Data for Name: Anagrafica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Anagrafica" (id, nome, cognome, "dataNascita", "luogoNascita", "idRiferimento") FROM stdin;
1	divano	Letto	2023-09-28	Bologna	\N
\.


--
-- TOC entry 4849 (class 0 OID 0)
-- Dependencies: 219
-- Name: Anagrafica_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Anagrafica_id_seq"', 1, true);


--
-- TOC entry 4697 (class 2606 OID 32797)
-- Name: Anagrafica Anagrafica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Anagrafica"
    ADD CONSTRAINT "Anagrafica_pkey" PRIMARY KEY (id);


-- Completed on 2023-10-12 12:37:43

--
-- PostgreSQL database dump complete
--

