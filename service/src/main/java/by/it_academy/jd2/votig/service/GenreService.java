package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IGenreDao;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.dto.GenreCUDTO;
import by.it_academy.jd2.votig.service.api.dto.GenreDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreService implements IGenreService {

    private final IGenreDao dao;
    private final IConverter<GenreEntity, GenreDTO> entityToDto;
//    private final IConverter<GenreDTO, GenreEntity> dtoToEntity;

    public GenreService(IGenreDao dao,
                        IConverter<GenreEntity, GenreDTO> entityToDto) {
        this.entityToDto = entityToDto;
        this.dao = dao;
    }

    @Override
    public GenreDTO create(GenreCUDTO data) {
        GenreEntity entity = new GenreEntity();
        entity.setName(data.getName());

        entity = dao.create(entity);
        return entityToDto.convert(entity);
    }

    @Override
    public List<GenreDTO> get() {
        List<GenreDTO> data = new ArrayList<>();
        for (GenreEntity entity : dao.get()) {
            data.add(entityToDto.convert(entity));
        }
        return data;
    }

    @Override
    public Optional<GenreDTO> get(long id) {
        Optional<GenreEntity> optional = dao.get(id);

        return optional.map(entityToDto::convert);
    }

    @Override
    public GenreDTO update(long id, GenreCUDTO data) {

        Optional<GenreEntity> optional = this.dao.get(id);

        if(optional.isEmpty()){
            throw new IllegalArgumentException("Жанра не существует");
        }

        GenreEntity entity = optional.get();
        entity.setName(data.getName());

        entity = dao.update(id, entity);
        return entityToDto.convert(entity);
    }

    @Override
    public void delete(long id) {
        this.dao.delete(id);
    }
}
