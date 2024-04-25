package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IArtistDao;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.dto.ArtistCUDTO;
import by.it_academy.jd2.votig.service.api.dto.ArtistDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistService implements IArtistService {

    private final IArtistDao dao;
    private final IConverter<ArtistEntity, ArtistDTO> entityToDto;
//    private final IConverter<ArtistDTO, ArtistEntity> dtoToEntity;

    public ArtistService(IArtistDao dao,
                         IConverter<ArtistEntity, ArtistDTO> entityToDto) {
        this.entityToDto = entityToDto;
        this.dao = dao;
    }

    @Override
    public ArtistDTO create(ArtistCUDTO data) {
        ArtistEntity entity = new ArtistEntity();
        entity.setName(data.getName());

        entity = dao.create(entity);
        return entityToDto.convert(entity);
    }

    @Override
    public List<ArtistDTO> get() {
        List<ArtistDTO> data = new ArrayList<>();
        for (ArtistEntity entity : dao.get()) {
            data.add(entityToDto.convert(entity));
        }
        return data;
    }

    @Override
    public Optional<ArtistDTO> get(long id) {
        Optional<ArtistEntity> optional = dao.get(id);

        return optional.map(entityToDto::convert);
    }

    @Override
    public ArtistDTO update(long id, ArtistCUDTO data) {

        Optional<ArtistEntity> optional = this.dao.get(id);

        if(optional.isEmpty()){
            throw new IllegalArgumentException("Артиста не существует");
        }

        ArtistEntity entity = optional.get();
        entity.setName(data.getName());

        entity = dao.update(id, entity);
        return entityToDto.convert(entity);
    }

    @Override
    public void delete(long id) {
        this.dao.delete(id);
    }
}
