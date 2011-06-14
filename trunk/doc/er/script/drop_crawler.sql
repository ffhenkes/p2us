/* ---------------------------------------------------------------------- */
/* Script generated with: DeZign for Databases v6.2.1                     */
/* Target DBMS:           PostgreSQL 8.3                                  */
/* Project file:          p2us.dez                                        */
/* Project name:                                                          */
/* Author:                                                                */
/* Script type:           Database drop script                            */
/* Created on:            2011-06-13 22:52                                */
/* ---------------------------------------------------------------------- */


/* ---------------------------------------------------------------------- */
/* Drop foreign key constraints                                           */
/* ---------------------------------------------------------------------- */

ALTER TABLE repos_v1c DROP CONSTRAINT cidade_v1c_repos_v1c;

ALTER TABLE crawler.v1c_event DROP CONSTRAINT fk_place_id;

ALTER TABLE crawler.v1c_place DROP CONSTRAINT fk_city_id;

ALTER TABLE crawler.btick_event DROP CONSTRAINT btick_city_btick_event;

/* ---------------------------------------------------------------------- */
/* Drop table "v1c_event"                                                 */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE crawler.v1c_event DROP CONSTRAINT pk_event_id;

/* Drop table */

DROP TABLE crawler.v1c_event;

/* ---------------------------------------------------------------------- */
/* Drop table "v1c_place"                                                 */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE crawler.v1c_place DROP CONSTRAINT pk_place_id;

/* Drop table */

DROP TABLE crawler.v1c_place;

/* ---------------------------------------------------------------------- */
/* Drop table "repos_v1c"                                                 */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE repos_v1c DROP CONSTRAINT PK_repos_v1c;

/* Drop table */

DROP TABLE repos_v1c;

/* ---------------------------------------------------------------------- */
/* Drop table "btick_event"                                               */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE crawler.btick_event DROP CONSTRAINT pk_btick_event_id;

/* Drop table */

DROP TABLE crawler.btick_event;

/* ---------------------------------------------------------------------- */
/* Drop table "btick_city"                                                */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE crawler.btick_city DROP CONSTRAINT pk_btick_city_id;

/* Drop table */

DROP TABLE crawler.btick_city;

/* ---------------------------------------------------------------------- */
/* Drop table "btick_place"                                               */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE crawler.btick_place DROP CONSTRAINT pk_btick_place_id;

/* Drop table */

DROP TABLE crawler.btick_place;

/* ---------------------------------------------------------------------- */
/* Drop table "v1c_city"                                                  */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE crawler.v1c_city DROP CONSTRAINT pk_city_id;

/* Drop table */

DROP TABLE crawler.v1c_city;

/* ---------------------------------------------------------------------- */
/* Drop table "cidade_v1c"                                                */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE cidade_v1c DROP CONSTRAINT PK_cidade_v1c;

/* Drop table */

DROP TABLE cidade_v1c;
