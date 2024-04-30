package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IGenreDao;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.dto.GenreCUDTO;
import by.it_academy.jd2.votig.service.api.dto.GenreDTO;

import java.util.List;
import java.util.Optional;

public class GenreService implements IGenreService {

    private final IGenreDao genreDao;
    private final IConverter<GenreEntity, GenreDTO> entityToDto;


    public GenreService(IGenreDao dao, IConverter<GenreEntity, GenreDTO> entityToDto) {
        this.genreDao = dao;
        this.entityToDto = entityToDto;
    }

    @Override
    public GenreEntity create(GenreCUDTO data) {
        GenreEntity entity = new GenreEntity();
        entity.setName(data.getName());

        return genreDao.create(entity);
    }

    @Override
    public List<GenreEntity> get() {
        return genreDao.get();
    }

    @Override
    public Optional<GenreEntity> get(long id) {
        return genreDao.get(id);
    }

    @Override
    public GenreEntity update(long id, GenreCUDTO data) {

        Optional<GenreEntity> optional = this.genreDao.get(id);

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Жанра не существует");
        }

        GenreEntity entity = optional.get();
        entity.setName(data.getName());

        return genreDao.update(id, entity);
    }

    @Override
    public void delete(long id) {
        this.genreDao.delete(id);
    }
}