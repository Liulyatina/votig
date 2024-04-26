package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IStatisticDao;
import by.it_academy.jd2.votig.dao.entity.StatEntity;
import by.it_academy.jd2.votig.service.api.IConverter;
import by.it_academy.jd2.votig.service.api.IStatService;
import by.it_academy.jd2.votig.service.api.IVoteService;
import by.it_academy.jd2.voting.core.dto.HibStatDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StatService implements IStatService {

    private final IStatisticDao dao;
    private final IVoteService voteService;
    private final IConverter<StatEntity, HibStatDTO> converter;

    public StatService(IStatisticDao dao, IVoteService voteService, IConverter<StatEntity, HibStatDTO> converter) {
        this.dao = dao;
        this.voteService = voteService;
        this.converter = converter;
    }

    @Override
    public List<HibStatDTO> get() {
        List<StatEntity> entities = dao.get();
        return entities.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

}