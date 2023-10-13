--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2023-10-13 15:47:50

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
-- TOC entry 222 (class 1259 OID 32824)
-- Name: Multa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Multa" (
    id bigint NOT NULL,
    tipo character varying(1),
    importo double precision NOT NULL,
    "idAnagrafica" bigint NOT NULL
);


ALTER TABLE public."Multa" OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 32823)
-- Name: Multa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Multa_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Multa_id_seq" OWNER TO postgres;

--
-- TOC entry 4853 (class 0 OID 0)
-- Dependencies: 221
-- Name: Multa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Multa_id_seq" OWNED BY public."Multa".id;


--
-- TOC entry 4699 (class 2604 OID 32827)
-- Name: Multa id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Multa" ALTER COLUMN id SET DEFAULT nextval('public."Multa_id_seq"'::regclass);


--
-- TOC entry 4847 (class 0 OID 32824)
-- Dependencies: 222
-- Data for Name: Multa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Multa" (id, tipo, importo, "idAnagrafica") FROM stdin;
\.


--
-- TOC entry 4854 (class 0 OID 0)
-- Dependencies: 221
-- Name: Multa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Multa_id_seq"', 1, false);


--
-- TOC entry 4701 (class 2606 OID 32829)
-- Name: Multa Multa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Multa"
    ADD CONSTRAINT "Multa_pkey" PRIMARY KEY ("idAnagrafica");


--
-- TOC entry 4702 (class 2606 OID 32830)
-- Name: Multa idAnagrafica_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Multa"
    ADD CONSTRAINT "idAnagrafica_fk" FOREIGN KEY ("idAnagrafica") REFERENCES public."Anagrafica"(id);


-- Completed on 2023-10-13 15:47:50

--
-- PostgreSQL database dump complete
--

