package by.it_academy.jd2.votig.service.converter;

import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.dto.ArtistDTO;
import org.springframework.stereotype.Component;

@Component
public class ArtistEntityToDTOConverter implements IConverter<ArtistEntity, ArtistDTO> {
    @Override
    public ArtistDTO convert(ArtistEntity item) {
        return ArtistDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .build();
    }
}
