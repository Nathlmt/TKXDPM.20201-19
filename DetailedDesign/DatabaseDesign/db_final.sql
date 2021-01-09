--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4 (Ubuntu 12.4-1.pgdg18.04+1)
-- Dumped by pg_dump version 12.2

-- Started on 2021-01-03 23:32:34

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

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 51956)
-- Name: bikes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bikes (
    bike_name text,
    id integer NOT NULL,
    license_plate text,
    price numeric(13,2),
    bike_type text,
    status text,
    latest_update timestamp with time zone,
    present_station integer
);


ALTER TABLE public.bikes OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 51998)
-- Name: cards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cards (
    id integer NOT NULL,
    balance numeric(13,2),
    card_code text NOT NULL,
    owner text,
    issuing_bank text,
    expiration_date character varying,
    security_code text
);


ALTER TABLE public.cards OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 51992)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id integer NOT NULL,
    customer_name text,
    email text,
    customer_password text,
    birthday date,
    card_id integer
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 52072)
-- Name: electric_bike; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.electric_bike (
    id_bike integer NOT NULL,
    battery_duration character varying,
    remain_battery character varying
);


ALTER TABLE public.electric_bike OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 51968)
-- Name: rental; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rental (
    id integer NOT NULL,
    bike_id integer,
    customer_id integer,
    rent_station_id integer,
    status text,
    time_start timestamp(0) with time zone,
    time_end timestamp(0) with time zone,
    return_station_id integer
);


ALTER TABLE public.rental OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 52100)
-- Name: rental_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rental_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rental_id_seq OWNER TO postgres;

--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 211
-- Name: rental_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rental_id_seq OWNED BY public.rental.id;


--
-- TOC entry 208 (class 1259 OID 52004)
-- Name: rental_transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rental_transaction (
    rental_id integer,
    transaction_id integer,
    id integer NOT NULL
);


ALTER TABLE public.rental_transaction OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 52086)
-- Name: rental_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rental_transaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rental_transaction_id_seq OWNER TO postgres;

--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 210
-- Name: rental_transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rental_transaction_id_seq OWNED BY public.rental_transaction.id;


--
-- TOC entry 203 (class 1259 OID 51962)
-- Name: stations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stations (
    station_name text,
    id integer NOT NULL,
    address text,
    acreage text,
    available_bike integer,
    available_rack integer,
    status text,
    latest_update timestamp(0) with time zone
);


ALTER TABLE public.stations OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 51974)
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    amount numeric(13,2),
    transaction_content text,
    card_code text NOT NULL,
    created_at timestamp(0) with time zone,
    command text,
    api_id text,
    id integer NOT NULL
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 52167)
-- Name: transactions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transactions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_id_seq OWNER TO postgres;

--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 212
-- Name: transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;


--
-- TOC entry 2806 (class 2604 OID 52102)
-- Name: rental id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental ALTER COLUMN id SET DEFAULT nextval('public.rental_id_seq'::regclass);


--
-- TOC entry 2808 (class 2604 OID 52088)
-- Name: rental_transaction id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental_transaction ALTER COLUMN id SET DEFAULT nextval('public.rental_transaction_id_seq'::regclass);


--
-- TOC entry 2807 (class 2604 OID 52169)
-- Name: transactions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);


--
-- TOC entry 2958 (class 0 OID 51956)
-- Dependencies: 202
-- Data for Name: bikes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bikes VALUES ('EB06', 6, '89MD25423', 700000.00, 'Xe đạp điện', 'available', '2020-12-23 05:35:53.115+00', 3);
INSERT INTO public.bikes VALUES ('EB04', 4, '89MD25423', 700000.00, 'Xe đạp điện', 'renting', '2020-12-23 05:36:41.517+00', 1);
INSERT INTO public.bikes VALUES ('NB03', 9, NULL, 400000.00, 'Xe đạp ', 'available', '2020-12-24 14:03:49.136+00', 4);
INSERT INTO public.bikes VALUES ('EB01', 1, '89MD12323', 700000.00, 'Xe đạp điện', 'available', '2020-12-24 19:39:31.127+00', 1);
INSERT INTO public.bikes VALUES ('EB05', 5, '89MD25623', 700000.00, 'Xe đạp điện', 'available', '2020-12-24 19:54:07.65+00', 1);
INSERT INTO public.bikes VALUES ('EB03', 3, '89MD32323', 700000.00, 'Xe đạp điện', 'available', '2020-12-24 20:51:45.005+00', 4);
INSERT INTO public.bikes VALUES ('EB02', 2, '89MD32323', 700000.00, 'Xe đạp điện', 'renting', '2021-01-03 13:23:09.109+00', 4);
INSERT INTO public.bikes VALUES ('NB01', 7, NULL, 400000.00, 'Xe đạp ', 'available', '2021-01-03 13:23:09+00', 3);
INSERT INTO public.bikes VALUES ('NB02', 8, NULL, 2.00, 'Xe đạp ', 'renting', '2021-01-03 16:23:30.364+00', 3);


--
-- TOC entry 2963 (class 0 OID 51998)
-- Dependencies: 207
-- Data for Name: cards; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cards VALUES (1, NULL, '118609_group19_2020', 'Group 19', 'Vietinbank', '1125', '907');


--
-- TOC entry 2962 (class 0 OID 51992)
-- Dependencies: 206
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customers VALUES (1, 'Nguyen Van A', 'a@gmail.com', '123456', '2001-10-05', 1);


--
-- TOC entry 2965 (class 0 OID 52072)
-- Dependencies: 209
-- Data for Name: electric_bike; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.electric_bike VALUES (5, '12 hours', '80%');
INSERT INTO public.electric_bike VALUES (6, '12 hours', '70%');
INSERT INTO public.electric_bike VALUES (3, '12 hours', '50%');
INSERT INTO public.electric_bike VALUES (4, '12 hours', '100%');


--
-- TOC entry 2960 (class 0 OID 51968)
-- Dependencies: 204
-- Data for Name: rental; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.rental VALUES (59, 8, 1, 5, 'đã trả xe', '2020-12-23 07:13:12+00', '2020-12-23 07:13:29+00', 2);
INSERT INTO public.rental VALUES (60, 8, 1, 2, 'renting', '2020-12-23 07:50:33+00', NULL, NULL);
INSERT INTO public.rental VALUES (61, 8, 1, 2, 'đã trả xe', '2020-12-23 08:00:39+00', '2020-12-23 08:01:26+00', 3);
INSERT INTO public.rental VALUES (62, 8, 1, 3, 'renting', '2020-12-23 08:40:08+00', NULL, NULL);
INSERT INTO public.rental VALUES (63, 8, 1, 3, 'renting', '2020-12-23 08:41:14+00', NULL, NULL);
INSERT INTO public.rental VALUES (64, 8, 1, 3, 'renting', '2020-12-23 08:42:14+00', NULL, NULL);
INSERT INTO public.rental VALUES (65, 8, 1, 3, 'đã trả xe', '2020-12-23 08:43:55+00', '2020-12-23 08:44:46+00', 1);
INSERT INTO public.rental VALUES (66, 8, 1, 1, 'renting', '2020-12-23 08:45:08+00', NULL, NULL);
INSERT INTO public.rental VALUES (67, 8, 1, 1, 'renting', '2020-12-23 08:49:42+00', NULL, NULL);
INSERT INTO public.rental VALUES (68, 8, 1, 1, 'renting', '2020-12-23 08:53:04+00', NULL, NULL);
INSERT INTO public.rental VALUES (69, 8, 1, 1, 'renting', '2020-12-23 09:09:37+00', NULL, NULL);
INSERT INTO public.rental VALUES (70, 8, 1, 1, 'renting', '2020-12-23 09:16:02+00', NULL, NULL);
INSERT INTO public.rental VALUES (71, 8, 1, 1, 'renting', '2020-12-23 09:18:43+00', NULL, NULL);
INSERT INTO public.rental VALUES (72, 1, 1, 4, 'đã trả xe', '2020-12-23 12:37:21+00', '2020-12-23 12:38:42+00', 5);
INSERT INTO public.rental VALUES (73, 1, 1, 5, 'đã trả xe', '2020-12-23 13:24:11+00', '2020-12-23 13:24:43+00', 4);
INSERT INTO public.rental VALUES (74, 8, 1, 1, 'đã trả xe', '2020-12-23 14:06:33+00', '2020-12-23 14:06:57+00', 5);
INSERT INTO public.rental VALUES (75, 1, 1, 4, 'renting', '2020-12-23 14:10:12+00', NULL, NULL);
INSERT INTO public.rental VALUES (76, 5, 1, 1, 'đã trả xe', '2020-12-23 14:12:16+00', '2020-12-23 14:17:44+00', 4);
INSERT INTO public.rental VALUES (77, 8, 1, 5, 'renting', '2020-12-23 14:31:34+00', NULL, NULL);
INSERT INTO public.rental VALUES (78, 1, 1, 4, 'renting', '2020-12-23 14:33:38+00', NULL, NULL);
INSERT INTO public.rental VALUES (79, 8, 1, 5, 'renting', '2020-12-23 14:38:41+00', NULL, NULL);
INSERT INTO public.rental VALUES (80, 6, 1, 2, 'đã trả xe', '2020-12-23 14:44:49+00', '2020-12-23 14:45:22+00', 1);
INSERT INTO public.rental VALUES (81, 8, 1, 5, 'renting', '2020-12-23 16:35:06+00', NULL, NULL);
INSERT INTO public.rental VALUES (82, 3, 1, 1, 'renting', '2020-12-24 01:13:03+00', NULL, NULL);
INSERT INTO public.rental VALUES (83, 1, 1, 4, 'đã trả xe', '2020-12-24 01:17:14+00', '2020-12-24 01:17:26+00', 4);
INSERT INTO public.rental VALUES (84, 8, 1, 5, 'renting', '2020-12-24 01:20:05+00', NULL, NULL);
INSERT INTO public.rental VALUES (85, 4, 1, 2, 'đã trả xe', '2020-12-24 01:27:21+00', '2020-12-24 01:27:33+00', 1);
INSERT INTO public.rental VALUES (86, 8, 1, 5, 'đã trả xe', '2020-12-23 15:47:57+00', '2020-12-23 15:48:27+00', 1);
INSERT INTO public.rental VALUES (87, 8, 1, 1, 'đã trả xe', '2020-12-23 15:57:34+00', '2020-12-23 15:57:52+00', 4);
INSERT INTO public.rental VALUES (45, 3, 1, 1, 'renting', '2020-12-23 00:29:33+00', NULL, NULL);
INSERT INTO public.rental VALUES (46, 8, 1, 3, 'renting', '2020-12-23 00:35:10+00', NULL, NULL);
INSERT INTO public.rental VALUES (47, 4, 1, 2, 'renting', '2020-12-23 00:41:20+00', NULL, NULL);
INSERT INTO public.rental VALUES (88, 8, 1, 4, 'đã trả xe', '2020-12-23 15:58:12+00', '2020-12-23 15:58:20+00', 2);
INSERT INTO public.rental VALUES (49, 5, 1, 2, 'đã trả xe', '2020-12-23 00:59:09+00', '2020-12-23 01:00:24+00', 1);
INSERT INTO public.rental VALUES (50, 1, 1, 1, 'đã trả xe', '2020-12-23 01:40:33+00', '2020-12-23 01:42:07+00', 4);
INSERT INTO public.rental VALUES (51, 8, 1, 3, 'đã trả xe', '2020-12-23 02:17:00+00', '2020-12-23 02:17:09+00', 5);
INSERT INTO public.rental VALUES (52, 8, 1, 5, 'renting', '2020-12-23 02:18:29+00', NULL, NULL);
INSERT INTO public.rental VALUES (53, 8, 1, 5, 'renting', '2020-12-23 03:18:33+00', NULL, NULL);
INSERT INTO public.rental VALUES (54, 5, 1, 1, 'đã trả xe', '2020-12-23 03:19:53+00', '2020-12-23 03:20:06+00', 4);
INSERT INTO public.rental VALUES (55, 5, 1, 4, 'đã trả xe', '2020-12-23 03:20:20+00', '2020-12-23 03:20:27+00', 1);
INSERT INTO public.rental VALUES (89, 8, 1, 2, 'đã trả xe', '2020-12-23 16:00:43+00', '2020-12-23 16:01:08+00', 1);
INSERT INTO public.rental VALUES (90, 8, 1, 1, 'renting', '2020-12-23 16:01:54+00', NULL, NULL);
INSERT INTO public.rental VALUES (91, 9, 1, 3, 'đã trả xe', '2020-12-23 18:21:42+00', '2020-12-23 18:22:12+00', 2);
INSERT INTO public.rental VALUES (92, 6, 1, 1, 'đã trả xe', '2020-12-23 05:35:07+00', '2020-12-23 05:35:52+00', 3);
INSERT INTO public.rental VALUES (93, 4, 1, 1, 'renting', '2020-12-23 05:36:41+00', NULL, NULL);
INSERT INTO public.rental VALUES (94, 9, 1, 2, 'đã trả xe', '2020-12-24 13:04:25+00', '2020-12-24 14:03:48+00', 4);
INSERT INTO public.rental VALUES (95, 1, 1, 4, 'đã trả xe', '2020-12-24 14:04:05+00', '2020-12-24 19:39:29+00', 1);
INSERT INTO public.rental VALUES (96, 5, 1, 2, 'đã trả xe', '2020-12-24 19:44:08+00', '2020-12-24 19:54:06+00', 1);
INSERT INTO public.rental VALUES (97, 3, 1, 1, 'đã trả xe', '2020-12-24 20:12:06+00', '2020-12-24 20:51:41+00', 4);
INSERT INTO public.rental VALUES (98, 2, 1, 1, 'đã trả xe', '2021-01-01 07:34:21+00', '2021-01-01 15:13:56+00', 4);
INSERT INTO public.rental VALUES (99, 2, 1, 4, 'renting', '2021-01-03 13:23:09+00', NULL, NULL);
INSERT INTO public.rental VALUES (100, 8, 1, 1, 'đã trả xe', '2021-01-03 15:55:34+00', '2021-01-03 15:55:41+00', 5);
INSERT INTO public.rental VALUES (101, 8, 1, 5, 'đã trả xe', '2021-01-03 16:22:59+00', '2021-01-03 16:23:07+00', 3);
INSERT INTO public.rental VALUES (102, 8, 1, 3, 'renting', '2021-01-03 16:23:30+00', NULL, NULL);


--
-- TOC entry 2964 (class 0 OID 52004)
-- Dependencies: 208
-- Data for Name: rental_transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.rental_transaction VALUES (47, 31, 5);
INSERT INTO public.rental_transaction VALUES (49, 33, 7);
INSERT INTO public.rental_transaction VALUES (49, 34, 8);
INSERT INTO public.rental_transaction VALUES (50, 35, 9);
INSERT INTO public.rental_transaction VALUES (50, 36, 10);
INSERT INTO public.rental_transaction VALUES (51, 37, 11);
INSERT INTO public.rental_transaction VALUES (51, 38, 12);
INSERT INTO public.rental_transaction VALUES (52, 39, 13);
INSERT INTO public.rental_transaction VALUES (53, 40, 14);
INSERT INTO public.rental_transaction VALUES (54, 41, 15);
INSERT INTO public.rental_transaction VALUES (54, 42, 16);
INSERT INTO public.rental_transaction VALUES (55, 43, 17);
INSERT INTO public.rental_transaction VALUES (55, 44, 18);
INSERT INTO public.rental_transaction VALUES (59, 45, 19);
INSERT INTO public.rental_transaction VALUES (59, 46, 20);
INSERT INTO public.rental_transaction VALUES (60, 47, 21);
INSERT INTO public.rental_transaction VALUES (61, 48, 22);
INSERT INTO public.rental_transaction VALUES (61, 49, 23);
INSERT INTO public.rental_transaction VALUES (62, 50, 24);
INSERT INTO public.rental_transaction VALUES (63, 51, 25);
INSERT INTO public.rental_transaction VALUES (64, 52, 26);
INSERT INTO public.rental_transaction VALUES (65, 53, 27);
INSERT INTO public.rental_transaction VALUES (65, 54, 28);
INSERT INTO public.rental_transaction VALUES (66, 55, 29);
INSERT INTO public.rental_transaction VALUES (67, 56, 30);
INSERT INTO public.rental_transaction VALUES (68, 57, 31);
INSERT INTO public.rental_transaction VALUES (69, 58, 32);
INSERT INTO public.rental_transaction VALUES (70, 59, 33);
INSERT INTO public.rental_transaction VALUES (71, 60, 34);
INSERT INTO public.rental_transaction VALUES (72, 61, 35);
INSERT INTO public.rental_transaction VALUES (72, 62, 36);
INSERT INTO public.rental_transaction VALUES (73, 63, 37);
INSERT INTO public.rental_transaction VALUES (73, 64, 38);
INSERT INTO public.rental_transaction VALUES (74, 65, 39);
INSERT INTO public.rental_transaction VALUES (74, 66, 40);
INSERT INTO public.rental_transaction VALUES (75, 67, 41);
INSERT INTO public.rental_transaction VALUES (76, 68, 42);
INSERT INTO public.rental_transaction VALUES (76, 69, 43);
INSERT INTO public.rental_transaction VALUES (77, 72, 46);
INSERT INTO public.rental_transaction VALUES (78, 73, 47);
INSERT INTO public.rental_transaction VALUES (79, 74, 48);
INSERT INTO public.rental_transaction VALUES (80, 75, 49);
INSERT INTO public.rental_transaction VALUES (80, 76, 50);
INSERT INTO public.rental_transaction VALUES (55, 1, 52);
INSERT INTO public.rental_transaction VALUES (81, 77, 53);
INSERT INTO public.rental_transaction VALUES (82, 78, 54);
INSERT INTO public.rental_transaction VALUES (83, 79, 55);
INSERT INTO public.rental_transaction VALUES (83, 80, 56);
INSERT INTO public.rental_transaction VALUES (84, 81, 57);
INSERT INTO public.rental_transaction VALUES (85, 82, 58);
INSERT INTO public.rental_transaction VALUES (85, 83, 59);
INSERT INTO public.rental_transaction VALUES (86, 84, 60);
INSERT INTO public.rental_transaction VALUES (86, 85, 61);
INSERT INTO public.rental_transaction VALUES (87, 86, 62);
INSERT INTO public.rental_transaction VALUES (87, 87, 63);
INSERT INTO public.rental_transaction VALUES (88, 88, 64);
INSERT INTO public.rental_transaction VALUES (88, 89, 65);
INSERT INTO public.rental_transaction VALUES (89, 90, 66);
INSERT INTO public.rental_transaction VALUES (89, 91, 67);
INSERT INTO public.rental_transaction VALUES (90, 92, 68);
INSERT INTO public.rental_transaction VALUES (91, 93, 69);
INSERT INTO public.rental_transaction VALUES (91, 94, 70);
INSERT INTO public.rental_transaction VALUES (92, 95, 71);
INSERT INTO public.rental_transaction VALUES (92, 96, 72);
INSERT INTO public.rental_transaction VALUES (93, 97, 73);
INSERT INTO public.rental_transaction VALUES (94, 98, 74);
INSERT INTO public.rental_transaction VALUES (94, 99, 75);
INSERT INTO public.rental_transaction VALUES (95, 100, 76);
INSERT INTO public.rental_transaction VALUES (95, 101, 77);
INSERT INTO public.rental_transaction VALUES (96, 102, 78);
INSERT INTO public.rental_transaction VALUES (96, 103, 79);
INSERT INTO public.rental_transaction VALUES (97, 104, 80);
INSERT INTO public.rental_transaction VALUES (97, 105, 81);
INSERT INTO public.rental_transaction VALUES (98, 106, 82);
INSERT INTO public.rental_transaction VALUES (98, 107, 83);
INSERT INTO public.rental_transaction VALUES (99, 108, 84);
INSERT INTO public.rental_transaction VALUES (100, 109, 85);
INSERT INTO public.rental_transaction VALUES (100, 110, 86);
INSERT INTO public.rental_transaction VALUES (101, 111, 87);
INSERT INTO public.rental_transaction VALUES (101, 112, 88);
INSERT INTO public.rental_transaction VALUES (102, 113, 89);


--
-- TOC entry 2959 (class 0 OID 51962)
-- Dependencies: 203
-- Data for Name: stations; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.stations VALUES ('Trạm rừng cọ', 2, 'Lô A khu căn hộ rừng cọ, Khu đô thị Ecopark, Văn Giang, Hưng Yên', '200', 35, 35, 'active', '2020-12-24 19:44:08+00');
INSERT INTO public.stations VALUES ('Trạm vườn tùng', 4, 'Khu biệt thự Vườn Tùng, Khu đô thị Ecopark, Văn Giang, Hưng Yên', '200', 42, 28, 'active', '2021-01-03 13:23:09+00');
INSERT INTO public.stations VALUES ('Trạm Aqua Bay', 1, 'Khu AquaBay, Khu đô thị Ecopark', '200', -16, 36, 'active', '2021-01-03 15:55:34+00');
INSERT INTO public.stations VALUES ('Trạm văn phòng CDT', 5, 'Khu văn phòng CDT, Khu đô thị Ecopark, Văn Giang, Hưng Yên', '200', 40, 30, 'active', '2021-01-03 16:22:59+00');
INSERT INTO public.stations VALUES ('Trạm hồ thiên nga', 3, 'Hồ Thiên Nga, Khu đô thị Ecopark, Văn Giang, Hưng Yên', '200', 35, 35, 'active', '2021-01-03 16:23:31+00');


--
-- TOC entry 2961 (class 0 OID 51974)
-- Dependencies: 205
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 00:29:34+00', 'pay', '5fe28f6d08cce400170a5ead', 28);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 00:35:10+00', 'pay', '5fe290bc08cce400170a5eb1', 30);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 00:41:20+00', 'pay', '5fe2922f08cce400170a5eb2', 31);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 00:59:23+00', 'pay', '5fe2965d08cce400170a5eb5', 33);
INSERT INTO public.transactions VALUES (489990.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 01:00:38+00', 'refund', '5fe296ac08cce400170a5eb6', 34);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 01:40:34+00', 'pay', '5fe2a011470a840017cbc85c', 35);
INSERT INTO public.transactions VALUES (489990.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 01:42:09+00', 'refund', '5fe2a070470a840017cbc85d', 36);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 02:17:01+00', 'pay', '5fe2a89b470a840017cbc867', 37);
INSERT INTO public.transactions VALUES (8.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2020-12-23 02:17:11+00', 'pay', '5fe2a8a5470a840017cbc868', 38);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 02:18:30+00', 'pay', '5fe2a8f3470a840017cbc869', 39);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 03:18:33+00', 'pay', '5fe2b707470a840017cbc896', 40);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 03:19:53+00', 'pay', '5fe2b757470a840017cbc897', 41);
INSERT INTO public.transactions VALUES (489990.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 03:20:07+00', 'refund', '5fe2b766470a840017cbc898', 42);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 03:20:20+00', 'pay', '5fe2b772470a840017cbc899', 43);
INSERT INTO public.transactions VALUES (489990.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 03:20:28+00', 'refund', '5fe2b77a470a840017cbc89a', 44);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 07:13:13+00', 'pay', '5fe2ee06470a840017cbc919', 45);
INSERT INTO public.transactions VALUES (8.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2020-12-23 07:13:31+00', 'pay', '5fe2ee18470a840017cbc91a', 46);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 07:50:33+00', 'pay', '5fe2f6c6470a840017cbc926', 47);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 08:00:39+00', 'pay', '5fe2f924470a840017cbc92f', 48);
INSERT INTO public.transactions VALUES (8.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2020-12-23 08:01:27+00', 'pay', '5fe2f955470a840017cbc931', 49);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 08:40:08+00', 'pay', '5fe30266470a840017cbc949', 50);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 08:41:14+00', 'pay', '5fe302a7470a840017cbc94a', 51);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 08:42:15+00', 'pay', '5fe302e3470a840017cbc94c', 52);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 08:43:55+00', 'pay', '5fe30348470a840017cbc94e', 53);
INSERT INTO public.transactions VALUES (8.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2020-12-23 08:44:47+00', 'pay', '5fe3037d470a840017cbc94f', 54);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 08:45:09+00', 'pay', '5fe30392470a840017cbc950', 55);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 08:49:42+00', 'pay', '5fe304a3470a840017cbc953', 56);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 08:53:05+00', 'pay', '5fe3056e470a840017cbc955', 57);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 09:09:38+00', 'pay', '5fe3094f470a840017cbc95f', 58);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 09:16:02+00', 'pay', '5fe30acf470a840017cbc965', 59);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 09:18:44+00', 'pay', '5fe30b71470a840017cbc968', 60);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 12:37:21+00', 'pay', '5fe33a00470a840017cbc9d5', 61);
INSERT INTO public.transactions VALUES (489990.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 12:38:44+00', 'refund', '5fe33a53470a840017cbc9d9', 62);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 13:24:12+00', 'pay', '5fe344fb470a840017cbca09', 63);
INSERT INTO public.transactions VALUES (489990.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 13:24:46+00', 'refund', '5fe3451d470a840017cbca0b', 64);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 14:06:34+00', 'pay', '5fe34ee9470a840017cbca23', 65);
INSERT INTO public.transactions VALUES (8.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2020-12-23 14:07:00+00', 'pay', '5fe34f03470a840017cbca25', 66);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 14:10:13+00', 'pay', '5fe34fc4470a840017cbca2a', 67);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 14:12:16+00', 'pay', '5fe3503f470a840017cbca2b', 68);
INSERT INTO public.transactions VALUES (423990.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 14:17:46+00', 'refund', '5fe35189470a840017cbca2e', 69);
INSERT INTO public.transactions VALUES (1000.00, 'Hello', '118609_group19_2020', '2020-12-23 14:20:19+00', 'refund', '5fe35221470a840017cbca31', 70);
INSERT INTO public.transactions VALUES (1000.00, 'Hello', '118609_group19_2020', '2020-12-23 14:28:11+00', 'refund', '5fe353f8470a840017cbca3b', 71);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 14:31:34+00', 'pay', '5fe354c3470a840017cbca3f', 72);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 14:33:38+00', 'pay', '5fe3553f470a840017cbca41', 73);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 14:38:41+00', 'pay', '5fe3566f470a840017cbca49', 74);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 14:44:49+00', 'pay', '5fe357de470a840017cbca52', 75);
INSERT INTO public.transactions VALUES (471000.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 14:45:24+00', 'refund', '5fe35801470a840017cbca53', 76);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 16:35:07+00', 'pay', '5fe373a1470a840017cbcab9', 77);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-24 01:13:04+00', 'pay', '5fe3eb1eafd3800017a282de', 78);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-24 01:17:15+00', 'pay', '5fe3ec1aafd3800017a282df', 79);
INSERT INTO public.transactions VALUES (480000.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-24 01:17:28+00', 'refund', '5fe3ec27afd3800017a282e0', 80);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-24 01:20:06+00', 'pay', '5fe3ecc4afd3800017a282e2', 81);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-24 01:27:21+00', 'pay', '5fe3ee78afd3800017a282e5', 82);
INSERT INTO public.transactions VALUES (475000.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-24 01:27:34+00', 'refund', '5fe3ee85afd3800017a282e6', 83);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 15:47:58+00', 'pay', '5fe40f78cc47dd0017ab65da', 84);
INSERT INTO public.transactions VALUES (14998.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2020-12-23 15:48:28+00', 'pay', '5fe40f96cc47dd0017ab65db', 85);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 15:57:34+00', 'pay', '5fe411b8cc47dd0017ab65dd', 86);
INSERT INTO public.transactions VALUES (14998.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2020-12-23 15:57:53+00', 'pay', '5fe411cbcc47dd0017ab65de', 87);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 15:58:13+00', 'pay', '5fe411dfcc47dd0017ab65df', 88);
INSERT INTO public.transactions VALUES (14998.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2020-12-23 15:58:21+00', 'pay', '5fe411e7cc47dd0017ab65e0', 89);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 16:00:44+00', 'pay', '5fe41276cc47dd0017ab65e1', 90);
INSERT INTO public.transactions VALUES (14998.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2020-12-23 16:01:09+00', 'pay', '5fe4128fcc47dd0017ab65e2', 91);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 16:01:55+00', 'pay', '5fe412bdcc47dd0017ab65e3', 92);
INSERT INTO public.transactions VALUES (400000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 18:21:43+00', 'pay', '5fe4338165ba3f00178feefc', 93);
INSERT INTO public.transactions VALUES (265000.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 18:22:14+00', 'refund', '5fe433a065ba3f00178feefd', 94);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 05:35:07+00', 'pay', '5fe436b365ba3f00178feefe', 95);
INSERT INTO public.transactions VALUES (475000.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-23 05:35:53+00', 'refund', '5fe436e265ba3f00178feeff', 96);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-23 05:36:42+00', 'pay', '5fe4371265ba3f00178fef00', 97);
INSERT INTO public.transactions VALUES (400000.00, 'Trả tiền cọc:Tuấn test', '118609_group19_2020', '2020-12-24 13:04:26+00', 'pay', '5fe491d9d5e9da0017f4f425', 98);
INSERT INTO public.transactions VALUES (247000.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-24 14:03:50+00', 'refund', '5fe49fc5d5e9da0017f4f441', 99);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-24 14:04:06+00', 'pay', '5fe49fd5d5e9da0017f4f442', 100);
INSERT INTO public.transactions VALUES (371500.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-24 19:39:32+00', 'refund', '5fe550e2dd007c001773709c', 101);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-24 19:44:09+00', 'pay', '5fe551f7dd007c001773709f', 102);
INSERT INTO public.transactions VALUES (475000.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-24 19:54:09+00', 'refund', '5fe5544fdd007c00177370a1', 103);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2020-12-24 20:12:07+00', 'pay', '5fe55885dd007c00177370c0', 104);
INSERT INTO public.transactions VALUES (461500.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2020-12-24 20:51:46+00', 'refund', '5fe561d0dd007c00177370d8', 105);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2021-01-01 07:34:21+00', 'pay', '5fef32ecc46e49001743aeeb', 106);
INSERT INTO public.transactions VALUES (335500.00, 'Hoàn tiền trả xe', '118609_group19_2020', '2021-01-01 15:13:58+00', 'refund', '5fef3c35c46e49001743aef9', 107);
INSERT INTO public.transactions VALUES (700000.00, 'Trả tiền cọc:', '118609_group19_2020', '2021-01-03 13:23:10+00', 'pay', '5ff1c53dbf9b6700173272e7', 108);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2021-01-03 15:55:34+00', 'pay', '5ff1e8f5bf9b6700173272fc', 109);
INSERT INTO public.transactions VALUES (14998.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2021-01-03 15:55:43+00', 'pay', '5ff1e8febf9b6700173272fd', 110);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2021-01-03 16:22:59+00', 'pay', '5ff1ef62bf9b670017327304', 111);
INSERT INTO public.transactions VALUES (14998.60, 'Thu thêm tiền thuê xe', '118609_group19_2020', '2021-01-03 16:23:08+00', 'pay', '5ff1ef6bbf9b670017327305', 112);
INSERT INTO public.transactions VALUES (2.00, 'Trả tiền cọc:', '118609_group19_2020', '2021-01-03 16:23:31+00', 'pay', '5ff1ef81bf9b670017327306', 113);


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 211
-- Name: rental_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rental_id_seq', 102, true);


--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 210
-- Name: rental_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rental_transaction_id_seq', 89, true);


--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 212
-- Name: transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transactions_id_seq', 113, true);


--
-- TOC entry 2810 (class 2606 OID 52021)
-- Name: bikes bike_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bikes
    ADD CONSTRAINT bike_pkey PRIMARY KEY (id);


--
-- TOC entry 2818 (class 2606 OID 52111)
-- Name: cards cards_card_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT cards_card_code_key UNIQUE (card_code);


--
-- TOC entry 2820 (class 2606 OID 52030)
-- Name: cards cards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT cards_pkey PRIMARY KEY (id);


--
-- TOC entry 2816 (class 2606 OID 52036)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- TOC entry 2824 (class 2606 OID 52082)
-- Name: electric_bike electric_bike_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.electric_bike
    ADD CONSTRAINT electric_bike_pk PRIMARY KEY (id_bike);


--
-- TOC entry 2814 (class 2606 OID 52032)
-- Name: rental rental_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT rental_pkey PRIMARY KEY (id);


--
-- TOC entry 2812 (class 2606 OID 52023)
-- Name: stations stations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stations
    ADD CONSTRAINT stations_pkey PRIMARY KEY (id);


--
-- TOC entry 2822 (class 1259 OID 52080)
-- Name: electric_bike_id_bike_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX electric_bike_id_bike_uindex ON public.electric_bike USING btree (id_bike);


--
-- TOC entry 2821 (class 1259 OID 52092)
-- Name: rental_transaction_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX rental_transaction_id_uindex ON public.rental_transaction USING btree (id);


--
-- TOC entry 2826 (class 2606 OID 52057)
-- Name: rental bike_create_rental; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT bike_create_rental FOREIGN KEY (bike_id) REFERENCES public.bikes(id);


--
-- TOC entry 2827 (class 2606 OID 52143)
-- Name: rental costomer_rental; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT costomer_rental FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- TOC entry 2829 (class 2606 OID 52037)
-- Name: customers customer_has_card; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customer_has_card FOREIGN KEY (card_id) REFERENCES public.cards(id);


--
-- TOC entry 2828 (class 2606 OID 52148)
-- Name: rental customer_rental; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental
    ADD CONSTRAINT customer_rental FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- TOC entry 2831 (class 2606 OID 52075)
-- Name: electric_bike electric_bike_bikes_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.electric_bike
    ADD CONSTRAINT electric_bike_bikes_id_fk FOREIGN KEY (id_bike) REFERENCES public.bikes(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2830 (class 2606 OID 52047)
-- Name: rental_transaction rental_trasaction_has_rental; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental_transaction
    ADD CONSTRAINT rental_trasaction_has_rental FOREIGN KEY (rental_id) REFERENCES public.rental(id);


--
-- TOC entry 2825 (class 2606 OID 52024)
-- Name: bikes station_has_bikes; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bikes
    ADD CONSTRAINT station_has_bikes FOREIGN KEY (present_station) REFERENCES public.stations(id);


-- Completed on 2021-01-03 23:33:04

--
-- PostgreSQL database dump complete
--

