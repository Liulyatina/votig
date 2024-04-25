CREATE SCHEMA app
    AUTHORIZATION voting_app;

CREATE TABLE app.artist
(
    id bigserial,
    name character varying,
    CONSTRAINT artist_id_pk PRIMARY KEY (id),
    CONSTRAINT artist_name_unq UNIQUE (name)
);

ALTER TABLE IF EXISTS app.artist
    OWNER to voting_app;
	
CREATE TABLE app.genre
(
    id bigserial,
    name character varying,
    CONSTRAINT genre_id_pk PRIMARY KEY (id),
    CONSTRAINT genre_name_unq UNIQUE (name)
);

ALTER TABLE IF EXISTS app.genre
    OWNER to voting_app;
	
CREATE TABLE app.vote
(
    id bigserial,
	dt_create timestamp(3) NOT NULL,
	artist bigint, -- Many to One
    about character varying,
    CONSTRAINT vote_id_pk PRIMARY KEY (id),
	CONSTRAINT vote_artist_fk FOREIGN KEY (artist) REFERENCES app.artist (id)
);

ALTER TABLE IF EXISTS app.vote
    OWNER to voting_app;
	
CREATE TABLE app.cross_vote_genre -- Many To Many
(
	vote bigint NOT NULL,
    genre bigint NOT NULL,
	CONSTRAINT cross_vote_genre_vote_fk FOREIGN KEY (vote) REFERENCES app.vote (id),
	CONSTRAINT cross_vote_genre_genre_fk FOREIGN KEY (genre) REFERENCES app.genre (id)
);

ALTER TABLE IF EXISTS app.cross_vote_genre
    OWNER to voting_app;

CREATE OR REPLACE VIEW app.v_statistics
AS
SELECT
    'genre' AS stat,
    genre as id,
    count(genre) as cnt
FROM
    app.cross_vote_genre
GROUP BY
    genre
UNION ALL
SELECT
    'artist' AS stat,
    artist as id,
    count(artist) as cnt
FROM
    app.vote
GROUP BY
    artist;

ALTER TABLE IF EXISTS app.v_statistics
    OWNER to voting_app;