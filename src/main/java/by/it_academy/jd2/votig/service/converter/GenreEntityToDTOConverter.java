package by.it_academy.jd2.votig.service.converter;

import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.dto.GenreDTO;
import org.springframework.stereotype.Component;

@Component
public class GenreEntityToDTOConverter implements IConverter<GenreEntity, GenreDTO> {
    @Override
    public GenreDTO convert(GenreEntity item) {
        return GenreDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .build();
    }
}
