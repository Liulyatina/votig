package by.it_academy.jd2.votig.dao.api;

import by.it_academy.jd2.votig.dao.entity.VoteEntity;

import java.util.List;
import java.util.Map;

public interface IVoteDao {

    /**
     * Сохранение голоса
     */
    void save(VoteEntity entity);

    long maxId();
}
