package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IStatisticDao;
import by.it_academy.jd2.votig.dao.entity.StatEntity;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.IStatService;
import by.it_academy.jd2.votig.service.api.IVoteService;
import by.it_academy.jd2.votig.service.api.dto.HibStatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StatService implements IStatService {

    private final IStatisticDao statisticDao;
    private final IConverter<StatEntity, HibStatDTO> converter;

    @Autowired
    public StatService(IStatisticDao dao, IConverter<StatEntity, HibStatDTO> converter) {
        this.statisticDao = dao;
        this.converter = converter;
    }

    @Override
    @Transactional
    public List<HibStatDTO> get() {
        List<StatEntity> entities = statisticDao.findAll();
        return entities.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

}