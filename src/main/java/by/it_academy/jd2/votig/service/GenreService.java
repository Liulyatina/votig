package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IGenreDao;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.dto.GenreCUDTO;
import by.it_academy.jd2.votig.service.api.dto.GenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GenreService implements IGenreService {

    private final IGenreDao genreDao;
    private final IConverter<GenreEntity, GenreDTO> entityToDto;

    @Autowired
    public GenreService(IGenreDao dao, IConverter<GenreEntity, GenreDTO> entityToDto) {
        this.genreDao = dao;
        this.entityToDto = entityToDto;
    }

    @Override
    @Transactional
    public GenreEntity create(GenreCUDTO data) {
        GenreEntity entity = new GenreEntity();
        entity.setName(data.getName());

        entity = genreDao.saveAndFlush(entity);
        return entity;
    }

    @Override
    @Transactional
    public List<GenreEntity> get() {
        return genreDao.findAll();
    }

    @Override
    @Transactional
    public Optional<GenreEntity> get(long id) {
        Optional<GenreEntity> optional = genreDao.findById(id);

        return optional;
    }

    @Override
    @Transactional
    public GenreEntity update(long id, GenreCUDTO data) {

        Optional<GenreEntity> optional = this.genreDao.findById(id);

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Жанра не существует");
        }

        GenreEntity entity = optional.get();
        entity.setName(data.getName());

        entity =  genreDao.saveAndFlush(entity);
        return entity;
    }

    @Override
    public void delete(long id) {
        this.genreDao.deleteById(id);
    }
}