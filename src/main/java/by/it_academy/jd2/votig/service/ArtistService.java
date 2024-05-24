package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IArtistDao;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.dto.ArtistCUDTO;
import by.it_academy.jd2.votig.service.api.dto.ArtistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArtistService implements IArtistService {

    private final IArtistDao artistDao;
    private final IConverter<ArtistEntity, ArtistDTO> entityToDto;

    @Autowired
    public ArtistService(IArtistDao dao, IConverter<ArtistEntity, ArtistDTO> entityToDto) {
        this.artistDao = dao;
        this.entityToDto = entityToDto;
    }

    @Override
    @Transactional
    public ArtistEntity create(ArtistCUDTO data) {
        ArtistEntity entity = new ArtistEntity();
        entity.setName(data.getName());

        entity = artistDao.saveAndFlush(entity);
        return entity;
    }

    @Override
    @Transactional
    public List<ArtistEntity> get() {
        return artistDao.findAll();
    }

    @Override
    @Transactional
    public Optional<ArtistEntity> get(long id) {
        return artistDao.findById(id);
    }

    @Override
    @Transactional
    public ArtistEntity update(long id, ArtistCUDTO data) {

        Optional<ArtistEntity> optional = this.artistDao.findById(id);

        if(optional.isEmpty()){
            throw new IllegalArgumentException("Артиста не существует");
        }

        ArtistEntity entity = optional.get();
        entity.setName(data.getName());

        entity = artistDao.saveAndFlush(entity);
        return entity;
    }

    @Override
    public void delete(long id) {
        this.artistDao.deleteById(id);
    }
}
