package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IVoteDao;
import by.it_academy.jd2.votig.dao.entity.VoteEntity;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.IVoteService;
import by.it_academy.jd2.votig.service.api.dto.ArtistDTO;
import by.it_academy.jd2.votig.service.api.dto.GenreDTO;
import by.it_academy.jd2.votig.service.api.dto.VoteDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

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

        Long[] genres = vote.getGenres();

        if(genres == null || genres.length < 3 || genres.length > 5){
            throw new IllegalArgumentException("Необходимо выбрать от 3 до 5 жанров");
        }

        for (Long genreId : genres) {
            Optional<GenreDTO> genreDTO = this.genreService.get(genreId);
            if(genreDTO.isEmpty()){
                throw new IllegalArgumentException("Выбран не существующий жанр");
            }
        }

        if(vote.getArtist() == null){
            throw new IllegalArgumentException("Необходимо выбрать артиста");
        }

        Optional<ArtistDTO> artistDTO = this.artistService.get(vote.getArtist());

        if(artistDTO.isEmpty()){
            throw new IllegalArgumentException("Выбран не существующий артист");
        }

        VoteEntity entity = new VoteEntity();
//        entity.setId(counter.incrementAndGet());
        entity.setDtCreate(LocalDateTime.now());
        entity.setArtist(vote.getArtist());

        long[] genresId = new long[genres.length];

        for (int i = 0; i < genres.length; i++) {
            genresId[i] = genres[i];
        }

        entity.setGenres(genresId);
        entity.setAbout(vote.getAbout());

        this.voteDao.save(entity);
    }

}
