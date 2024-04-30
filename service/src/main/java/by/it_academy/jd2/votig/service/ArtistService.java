package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IArtistDao;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.dto.ArtistCUDTO;
import by.it_academy.jd2.votig.service.api.dto.ArtistDTO;


import java.util.List;
import java.util.Optional;

public class ArtistService implements IArtistService {

    private final IArtistDao artistDao;
    private final IConverter<ArtistEntity, ArtistDTO> entityToDto;

    public ArtistService(IArtistDao dao, IConverter<ArtistEntity, ArtistDTO> entityToDto) {
        this.artistDao = dao;
        this.entityToDto = entityToDto;
    }

    @Override
    public ArtistEntity create(ArtistCUDTO data) {
        ArtistEntity entity = new ArtistEntity();
        entity.setName(data.getName());

        return artistDao.create(entity);
    }

    @Override
    public List<ArtistEntity> get() {
        return artistDao.get();
    }

    @Override
    public Optional<ArtistEntity> get(long id) {
        return artistDao.get(id);
    }

    @Override
    public ArtistEntity update(long id, ArtistCUDTO data) {

        Optional<ArtistEntity> optional = this.artistDao.get(id);

        if(optional.isEmpty()){
            throw new IllegalArgumentException("Артиста не существует");
        }

        ArtistEntity entity = optional.get();
        entity.setName(data.getName());

        return artistDao.update(id, entity);
    }

    @Override
    public void delete(long id) {
        this.artistDao.delete(id);
    }
}
