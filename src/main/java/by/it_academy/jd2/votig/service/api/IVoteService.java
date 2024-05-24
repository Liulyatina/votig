package by.it_academy.jd2.votig.service.api;

import by.it_academy.jd2.votig.service.api.dto.VoteDTO;

public interface IVoteService {

    /**
     * Сохранение голоса
     * @param vote голос
     * @throws IllegalArgumentException в случае ошибки валидации
     */
    void save(VoteDTO vote);
}
