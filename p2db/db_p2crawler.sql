--
-- PostgreSQL database dump
--

-- Started on 2011-06-08 00:18:40

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1787 (class 1262 OID 57398)
-- Name: P2Us; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE "P2Us" WITH TEMPLATE = template0 ENCODING = 'UTF8';


\connect "P2Us"

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 6 (class 2615 OID 57399)
-- Name: crawler; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA crawler;


--
-- TOC entry 328 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: -
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = public, pg_catalog;

--
-- TOC entry 274 (class 1247 OID 16405)
-- Dependencies: 3 1500
-- Name: breakpoint; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE breakpoint AS (
	func oid,
	linenumber integer,
	targetname text
);


--
-- TOC entry 276 (class 1247 OID 16408)
-- Dependencies: 3 1501
-- Name: frame; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE frame AS (
	level integer,
	targetname text,
	func oid,
	linenumber integer,
	args text
);


--
-- TOC entry 318 (class 1247 OID 16417)
-- Dependencies: 3 1504
-- Name: proxyinfo; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE proxyinfo AS (
	serverversionstr text,
	serverversionnum integer,
	proxyapiver integer,
	serverprocessid integer
);


--
-- TOC entry 314 (class 1247 OID 16411)
-- Dependencies: 3 1502
-- Name: targetinfo; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE targetinfo AS (
	target oid,
	schema oid,
	nargs integer,
	argtypes oidvector,
	targetname name,
	argmodes "char"[],
	argnames text[],
	targetlang oid,
	fqname text,
	returnsset boolean,
	returntype oid
);


--
-- TOC entry 316 (class 1247 OID 16414)
-- Dependencies: 3 1503
-- Name: var; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE var AS (
	name text,
	varclass character(1),
	linenumber integer,
	isunique boolean,
	isconst boolean,
	isnotnull boolean,
	dtype oid,
	value text
);


--
-- TOC entry 22 (class 1255 OID 16419)
-- Dependencies: 3
-- Name: pldbg_abort_target(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_abort_target(session integer) RETURNS SETOF boolean
    AS '$libdir/pldbgapi', 'pldbg_abort_target'
    LANGUAGE c STRICT;


--
-- TOC entry 23 (class 1255 OID 16420)
-- Dependencies: 3
-- Name: pldbg_attach_to_port(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_attach_to_port(portnumber integer) RETURNS integer
    AS '$libdir/pldbgapi', 'pldbg_attach_to_port'
    LANGUAGE c STRICT;


--
-- TOC entry 24 (class 1255 OID 16421)
-- Dependencies: 274 3
-- Name: pldbg_continue(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_continue(session integer) RETURNS breakpoint
    AS '$libdir/pldbgapi', 'pldbg_continue'
    LANGUAGE c STRICT;


--
-- TOC entry 25 (class 1255 OID 16422)
-- Dependencies: 3
-- Name: pldbg_create_listener(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_create_listener() RETURNS integer
    AS '$libdir/pldbgapi', 'pldbg_create_listener'
    LANGUAGE c STRICT;


--
-- TOC entry 26 (class 1255 OID 16423)
-- Dependencies: 3
-- Name: pldbg_deposit_value(integer, text, integer, text); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_deposit_value(session integer, varname text, linenumber integer, value text) RETURNS boolean
    AS '$libdir/pldbgapi', 'pldbg_deposit_value'
    LANGUAGE c STRICT;


--
-- TOC entry 27 (class 1255 OID 16424)
-- Dependencies: 3
-- Name: pldbg_drop_breakpoint(integer, oid, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_drop_breakpoint(session integer, func oid, linenumber integer) RETURNS boolean
    AS '$libdir/pldbgapi', 'pldbg_drop_breakpoint'
    LANGUAGE c STRICT;


--
-- TOC entry 28 (class 1255 OID 16425)
-- Dependencies: 274 3
-- Name: pldbg_get_breakpoints(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_get_breakpoints(session integer) RETURNS SETOF breakpoint
    AS '$libdir/pldbgapi', 'pldbg_get_breakpoints'
    LANGUAGE c STRICT;


--
-- TOC entry 31 (class 1255 OID 16428)
-- Dependencies: 3 318
-- Name: pldbg_get_proxy_info(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_get_proxy_info() RETURNS proxyinfo
    AS '$libdir/pldbgapi', 'pldbg_get_proxy_info'
    LANGUAGE c STRICT;


--
-- TOC entry 29 (class 1255 OID 16426)
-- Dependencies: 3
-- Name: pldbg_get_source(integer, oid); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_get_source(session integer, func oid) RETURNS text
    AS '$libdir/pldbgapi', 'pldbg_get_source'
    LANGUAGE c STRICT;


--
-- TOC entry 30 (class 1255 OID 16427)
-- Dependencies: 276 3
-- Name: pldbg_get_stack(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_get_stack(session integer) RETURNS SETOF frame
    AS '$libdir/pldbgapi', 'pldbg_get_stack'
    LANGUAGE c STRICT;


--
-- TOC entry 40 (class 1255 OID 16437)
-- Dependencies: 314 3
-- Name: pldbg_get_target_info(text, "char"); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_get_target_info(signature text, targettype "char") RETURNS targetinfo
    AS '$libdir/targetinfo', 'pldbg_get_target_info'
    LANGUAGE c STRICT;


--
-- TOC entry 32 (class 1255 OID 16429)
-- Dependencies: 3 316
-- Name: pldbg_get_variables(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_get_variables(session integer) RETURNS SETOF var
    AS '$libdir/pldbgapi', 'pldbg_get_variables'
    LANGUAGE c STRICT;


--
-- TOC entry 33 (class 1255 OID 16430)
-- Dependencies: 274 3
-- Name: pldbg_select_frame(integer, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_select_frame(session integer, frame integer) RETURNS breakpoint
    AS '$libdir/pldbgapi', 'pldbg_select_frame'
    LANGUAGE c STRICT;


--
-- TOC entry 34 (class 1255 OID 16431)
-- Dependencies: 3
-- Name: pldbg_set_breakpoint(integer, oid, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_set_breakpoint(session integer, func oid, linenumber integer) RETURNS boolean
    AS '$libdir/pldbgapi', 'pldbg_set_breakpoint'
    LANGUAGE c STRICT;


--
-- TOC entry 35 (class 1255 OID 16432)
-- Dependencies: 3
-- Name: pldbg_set_global_breakpoint(integer, oid, integer, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_set_global_breakpoint(session integer, func oid, linenumber integer, targetpid integer) RETURNS boolean
    AS '$libdir/pldbgapi', 'pldbg_set_global_breakpoint'
    LANGUAGE c;


--
-- TOC entry 36 (class 1255 OID 16433)
-- Dependencies: 274 3
-- Name: pldbg_step_into(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_step_into(session integer) RETURNS breakpoint
    AS '$libdir/pldbgapi', 'pldbg_step_into'
    LANGUAGE c STRICT;


--
-- TOC entry 37 (class 1255 OID 16434)
-- Dependencies: 3 274
-- Name: pldbg_step_over(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_step_over(session integer) RETURNS breakpoint
    AS '$libdir/pldbgapi', 'pldbg_step_over'
    LANGUAGE c STRICT;


--
-- TOC entry 38 (class 1255 OID 16435)
-- Dependencies: 274 3
-- Name: pldbg_wait_for_breakpoint(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_wait_for_breakpoint(session integer) RETURNS breakpoint
    AS '$libdir/pldbgapi', 'pldbg_wait_for_breakpoint'
    LANGUAGE c STRICT;


--
-- TOC entry 39 (class 1255 OID 16436)
-- Dependencies: 3
-- Name: pldbg_wait_for_target(integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION pldbg_wait_for_target(session integer) RETURNS integer
    AS '$libdir/pldbgapi', 'pldbg_wait_for_target'
    LANGUAGE c STRICT;


--
-- TOC entry 21 (class 1255 OID 16418)
-- Dependencies: 3
-- Name: plpgsql_oid_debug(oid); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION plpgsql_oid_debug(functionoid oid) RETURNS integer
    AS '$libdir/plugins/plugin_debugger', 'plpgsql_oid_debug'
    LANGUAGE c STRICT;


SET search_path = crawler, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1507 (class 1259 OID 57412)
-- Dependencies: 6
-- Name: v1c_city; Type: TABLE; Schema: crawler; Owner: -; Tablespace: 
--

CREATE TABLE v1c_city (
    id integer NOT NULL,
    name character varying(250),
    data_processamento timestamp with time zone,
    flag_processamento integer
);


--
-- TOC entry 1505 (class 1259 OID 57400)
-- Dependencies: 6
-- Name: v1c_event; Type: TABLE; Schema: crawler; Owner: -; Tablespace: 
--

CREATE TABLE v1c_event (
    id integer NOT NULL,
    date date,
    name text,
    show text,
    image_url character varying(500),
    id_place integer,
    data_processamento timestamp with time zone,
    flag_processamento integer
);


--
-- TOC entry 1506 (class 1259 OID 57406)
-- Dependencies: 6
-- Name: v1c_place; Type: TABLE; Schema: crawler; Owner: -; Tablespace: 
--

CREATE TABLE v1c_place (
    id integer NOT NULL,
    address text,
    contact text,
    city_id integer,
    flag_processamento integer,
    data_processamento timestamp with time zone
);


--
-- TOC entry 1784 (class 0 OID 57412)
-- Dependencies: 1507
-- Data for Name: v1c_city; Type: TABLE DATA; Schema: crawler; Owner: -
--

COPY v1c_city (id, name, data_processamento, flag_processamento) FROM stdin;
\.


--
-- TOC entry 1782 (class 0 OID 57400)
-- Dependencies: 1505
-- Data for Name: v1c_event; Type: TABLE DATA; Schema: crawler; Owner: -
--

COPY v1c_event (id, date, name, show, image_url, id_place, data_processamento, flag_processamento) FROM stdin;
\.


--
-- TOC entry 1783 (class 0 OID 57406)
-- Dependencies: 1506
-- Data for Name: v1c_place; Type: TABLE DATA; Schema: crawler; Owner: -
--

COPY v1c_place (id, address, contact, city_id, flag_processamento, data_processamento) FROM stdin;
\.


--
-- TOC entry 1779 (class 2606 OID 57418)
-- Dependencies: 1507 1507
-- Name: pk_city_id; Type: CONSTRAINT; Schema: crawler; Owner: -; Tablespace: 
--

ALTER TABLE ONLY v1c_city
    ADD CONSTRAINT pk_city_id PRIMARY KEY (id);


--
-- TOC entry 1775 (class 2606 OID 57416)
-- Dependencies: 1505 1505
-- Name: pk_event_id; Type: CONSTRAINT; Schema: crawler; Owner: -; Tablespace: 
--

ALTER TABLE ONLY v1c_event
    ADD CONSTRAINT pk_event_id PRIMARY KEY (id);


--
-- TOC entry 1777 (class 2606 OID 57420)
-- Dependencies: 1506 1506
-- Name: pk_place_id; Type: CONSTRAINT; Schema: crawler; Owner: -; Tablespace: 
--

ALTER TABLE ONLY v1c_place
    ADD CONSTRAINT pk_place_id PRIMARY KEY (id);


--
-- TOC entry 1781 (class 2606 OID 57421)
-- Dependencies: 1507 1778 1506
-- Name: fk_city_id; Type: FK CONSTRAINT; Schema: crawler; Owner: -
--

ALTER TABLE ONLY v1c_place
    ADD CONSTRAINT fk_city_id FOREIGN KEY (city_id) REFERENCES v1c_city(id);


--
-- TOC entry 1780 (class 2606 OID 57426)
-- Dependencies: 1506 1776 1505
-- Name: fk_place_id; Type: FK CONSTRAINT; Schema: crawler; Owner: -
--

ALTER TABLE ONLY v1c_event
    ADD CONSTRAINT fk_place_id FOREIGN KEY (id_place) REFERENCES v1c_place(id);


--
-- TOC entry 1789 (class 0 OID 0)
-- Dependencies: 3
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-06-08 00:18:41

--
-- PostgreSQL database dump complete
--

