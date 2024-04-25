package by.it_academy.jd2.votig.service;

import by.it_academy.jd2.votig.dao.api.IStatisticDao;

import by.it_academy.jd2.votig.dao.entity.StatEntity;
import by.it_academy.jd2.votig.service.api.IStatService;
import by.it_academy.jd2.votig.service.api.IVoteService;
import by.it_academy.jd2.voting.core.dto.StatDTO;

import java.util.List;

public class StatService implements IStatService {

    private final IStatisticDao dao;
    private final IVoteService voteService;

    public StatService(IStatisticDao dao, IVoteService voteService) {
        this.dao = dao;
        this.voteService = voteService;
    }

    @Override
    public List<StatDTO> get() {
        StatEntity entity = dao.get(convertToEntity(filter));
        return convertToDto(entity);
    }

    private StatEntity convertToEntity(StatDTO dto) {
        return new StatEntity(dto.getId(), dto.getStat(), dto.getCnt());
    }

    private StatDTO convertToDto(StatEntity entity) {
        return new StatDTO(entity.getId(), entity.getStat(), entity.getCnt());
    }
}
