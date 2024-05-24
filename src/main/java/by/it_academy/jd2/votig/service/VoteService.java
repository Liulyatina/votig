package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IVoteDao;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.dao.entity.VoteEntity;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.IVoteService;
import by.it_academy.jd2.votig.service.api.dto.VoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class VoteService implements IVoteService {

    private final IVoteDao voteDao;
    private final IGenreService genreService;
    private final IArtistService artistService;

    @Autowired
    public VoteService(IVoteDao voteDao, IGenreService genreService, IArtistService artistService) {
        this.voteDao = voteDao;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    @Override
    @Transactional
    public void save(VoteDTO vote) {

        Long[] genres = vote.getGenres();

        if (genres == null || genres.length < 3 || genres.length > 5) {
            throw new IllegalArgumentException("Необходимо выбрать от 3 до 5 жанров");
        }

        List<GenreEntity> genreEntities = new ArrayList<>();
        for (Long genreId : genres) {
            Optional<GenreEntity> genreDTO = this.genreService.get(genreId);
            if (genreDTO.isEmpty()) {
                throw new IllegalArgumentException("Выбран не существующий жанр");
            }
            GenreEntity genreEntity = new GenreEntity();
            genreEntity.setId(genreId);
            genreEntities.add(genreEntity);
        }

        if (vote.getArtist() == null) {
            throw new IllegalArgumentException("Необходимо выбрать артиста");
        }

        Optional<ArtistEntity> artistDTO = this.artistService.get(vote.getArtist());

        if (artistDTO.isEmpty()) {
            throw new IllegalArgumentException("Выбран не существующий артист");
        }

        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setId(vote.getArtist());

        VoteEntity entity = new VoteEntity();
        entity.setDtCreate(LocalDateTime.now());
        entity.setArtist(artistEntity);
        entity.setGenres(genreEntities);
        entity.setAbout(vote.getAbout());

        this.voteDao.saveAndFlush(entity);
    }
}
