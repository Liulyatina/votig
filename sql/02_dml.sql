--Сделать запорос получающи голоса с их жанрами (имена жанров)
SELECT
 	vote.id,
	vote.dt_create,
	vote.artist,
	vote.about,
	genre.name
FROM app.vote as vote
LEFT JOIN app.cross_vote_genre as crs ON vote.id = crs.vote
LEFT JOIN app.genre as genre ON crs.genre = genre.id

SELECT
 	vote.id,
	vote.dt_create,
	vote.artist,
	vote.about,
	--crs.genre,
	genre.name
FROM app.cross_vote_genre as crs
LEFT JOIN app.vote as vote ON vote.id = crs.vote
LEFT JOIN app.genre as genre ON genre.id = crs.genre


SELECT
 	vote.id,
	vote.dt_create,
	vote.artist,
	vote.about,
	(SELECT ARRAY(
		SELECT genre.name
		FROM app.cross_vote_genre as crs
		LEFT JOIN app.genre as genre ON crs.genre = genre.id
		WHERE crs.vote = vote.id
	)) as genres
FROM app.vote as vote