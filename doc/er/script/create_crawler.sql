/* ---------------------------------------------------------------------- */
/* Script generated with: DeZign for Databases v6.2.1                     */
/* Target DBMS:           PostgreSQL 8.3                                  */
/* Project file:          p2us.dez                                        */
/* Project name:                                                          */
/* Author:                                                                */
/* Script type:           Database creation script                        */
/* Created on:            2011-06-13 22:52                                */
/* ---------------------------------------------------------------------- */


/* ---------------------------------------------------------------------- */
/* Tables                                                                 */
/* ---------------------------------------------------------------------- */

/* ---------------------------------------------------------------------- */
/* Add table "repos_v1c"                                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE repos_v1c (
    id_v1c INTEGER   NOT NULL,
    titulo CHARACTER VARYING(100) ,
    data CHARACTER VARYING(100) ,
    local_v1c CHARACTER VARYING(100) ,
    descricao CHARACTER VARYING(2000) ,
    data_proc TIMESTAMP  DEFAULT 'now()'  NOT NULL,
    flag_proc INTEGER  DEFAULT 0  NOT NULL,
    id_cidade INTEGER ,
    CONSTRAINT PK_repos_v1c PRIMARY KEY (id_v1c)
);

/* ---------------------------------------------------------------------- */
/* Add table "cidade_v1c"                                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE cidade_v1c (
    id_cidade INTEGER   NOT NULL,
    nome CHARACTER VARYING(200)   NOT NULL,
    url CHARACTER VARYING(200) ,
    data_proc TIMESTAMP  DEFAULT 'now()'  NOT NULL,
    flag_proc INTEGER  DEFAULT 0  NOT NULL,
    CONSTRAINT PK_cidade_v1c PRIMARY KEY (id_cidade)
);

/* ---------------------------------------------------------------------- */
/* Add table "v1c_event"                                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE crawler.v1c_event (
    id_v1c_event SERIAL   NOT NULL,
    date DATE ,
    name TEXT ,
    show TEXT ,
    image_url CHARACTER VARYING(500) ,
    id_place INTEGER ,
    data_processamento TIMESTAMP ,
    flag_processamento INTEGER ,
    CONSTRAINT pk_event_id PRIMARY KEY (id_v1c_event)
);

/* ---------------------------------------------------------------------- */
/* Add table "v1c_place"                                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE crawler.v1c_place (
    id_v1c_place SERIAL   NOT NULL,
    address TEXT ,
    contact TEXT ,
    city_id INTEGER ,
    flag_processamento INTEGER ,
    data_processamento TIMESTAMP ,
    CONSTRAINT pk_place_id PRIMARY KEY (id_v1c_place)
);

/* ---------------------------------------------------------------------- */
/* Add table "v1c_city"                                                   */
/* ---------------------------------------------------------------------- */

CREATE TABLE crawler.v1c_city (
    id_v1c_city SERIAL   NOT NULL,
    name CHARACTER VARYING(250) ,
    data_processamento TIMESTAMP ,
    flag_processamento INTEGER ,
    CONSTRAINT pk_city_id PRIMARY KEY (id_v1c_city)
);

/* ---------------------------------------------------------------------- */
/* Add table "btick_place"                                                */
/* ---------------------------------------------------------------------- */

CREATE TABLE crawler.btick_place (
    id_btick_place INTEGER   NOT NULL,
    address TEXT ,
    contact TEXT ,
    flag_processamento INTEGER ,
    data_processamento TIMESTAMP ,
    CONSTRAINT pk_btick_place_id PRIMARY KEY (id_btick_place)
);

/* ---------------------------------------------------------------------- */
/* Add table "btick_city"                                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE crawler.btick_city (
    id_btick_city SERIAL   NOT NULL,
    name CHARACTER VARYING(250) ,
    data_processamento TIMESTAMP ,
    flag_processamento INTEGER ,
    CONSTRAINT pk_btick_city_id PRIMARY KEY (id_btick_city)
);

/* ---------------------------------------------------------------------- */
/* Add table "btick_event"                                                */
/* ---------------------------------------------------------------------- */

CREATE TABLE crawler.btick_event (
    id_btick_event SERIAL   NOT NULL,
    date DATE ,
    name TEXT ,
    show TEXT ,
    image_url CHARACTER VARYING(500) ,
    place CHARACTER VARYING(250) ,
    place_address CHARACTER VARYING(250) ,
    place_phone CHARACTER VARYING(80) ,
    place_site CHARACTER(100) ,
    place_image_url CHARACTER VARYING(500) ,
    data_processamento TIMESTAMP ,
    flag_processamento INTEGER ,
    id_btick_city INTEGER ,
    CONSTRAINT pk_btick_event_id PRIMARY KEY (id_btick_event)
);

/* ---------------------------------------------------------------------- */
/* Foreign key constraints                                                */
/* ---------------------------------------------------------------------- */

ALTER TABLE repos_v1c ADD CONSTRAINT cidade_v1c_repos_v1c 
    FOREIGN KEY (id_cidade) REFERENCES cidade_v1c (id_cidade);

ALTER TABLE crawler.v1c_event ADD CONSTRAINT fk_place_id 
    FOREIGN KEY (id_place) REFERENCES crawler.v1c_place (id_v1c_place);

ALTER TABLE crawler.v1c_place ADD CONSTRAINT fk_city_id 
    FOREIGN KEY (city_id) REFERENCES crawler.v1c_city (id_v1c_city);

ALTER TABLE crawler.btick_event ADD CONSTRAINT btick_city_btick_event 
    FOREIGN KEY (id_btick_city) REFERENCES crawler.btick_city (id_btick_city);
