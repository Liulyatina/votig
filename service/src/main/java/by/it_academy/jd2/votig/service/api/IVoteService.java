package by.it_academy.jd2.votig.service.api;

import by.it_academy.jd2.votig.service.api.dto.VoteDTO;

import java.util.List;
import java.util.Map;

public interface IVoteService {

    /**
     * Сохранение голоса
     * @param vote голос
     * @throws IllegalArgumentException в случае ошибки валидации
     */
    void save(VoteDTO vote);
}
