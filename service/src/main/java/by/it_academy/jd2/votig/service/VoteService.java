package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IVoteDao;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.dao.entity.VoteEntity;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.IVoteService;
import by.it_academy.jd2.votig.service.api.dto.ArtistDTO;
import by.it_academy.jd2.votig.service.api.dto.GenreDTO;
import by.it_academy.jd2.votig.service.api.dto.VoteDTO;

import java.time.LocalDateTime;
import java.util.*;

public class VoteService implements IVoteService {

    private final IVoteDao voteDao;
    private final IGenreService genreService;
    private final IArtistService artistService;

    public VoteService(IVoteDao voteDao, IGenreService genreService, IArtistService artistService) {
        this.voteDao = voteDao;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    @Override
    public void save(VoteDTO vote) {

        List<Long> genres = List.of(vote.getGenres());

        if(genres == null || genres.size() < 3 || genres.size() > 5){
            throw new IllegalArgumentException("Необходимо выбрать от 3 до 5 жанров");
        }

        List<GenreEntity> genreEntities = new ArrayList<>();
        for (Long genreId : genres) {
            Optional<GenreEntity> genreDTO = this.genreService.get(genreId);
            if(genreDTO.isEmpty()){
                throw new IllegalArgumentException("Выбран не существующий жанр");
            }
            GenreEntity genreEntity = new GenreEntity();
            genreEntity.setId(genreId);
            genreEntities.add(genreEntity);
        }

        if(vote.getArtist() == null){
            throw new IllegalArgumentException("Необходимо выбрать артиста");
        }

        Optional<ArtistEntity> artistDTO = this.artistService.get(vote.getArtist());

        if(artistDTO.isEmpty()){
            throw new IllegalArgumentException("Выбран не существующий артист");
        }

        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setId(vote.getArtist());

        VoteEntity entity = new VoteEntity();
        entity.setDtCreate(LocalDateTime.now());
        entity.setArtist(artistEntity);
        entity.setGenres(genreEntities);
        entity.setAbout(vote.getAbout());

        this.voteDao.save(entity);
    }


}
