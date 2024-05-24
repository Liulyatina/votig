package by.it_academy.jd2.votig.service.converter;

import by.it_academy.jd2.votig.dao.entity.StatEntity;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.dto.HibStatDTO;
import org.springframework.stereotype.Component;

@Component
public class StatEntityToHibStatDTOConverter implements IConverter<StatEntity, HibStatDTO> {
    @Override
    public HibStatDTO convert(StatEntity entity) {
        return new HibStatDTO(entity.getId(), entity.getStat(), entity.getCnt());
    }
}