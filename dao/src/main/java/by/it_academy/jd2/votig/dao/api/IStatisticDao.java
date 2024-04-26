package by.it_academy.jd2.votig.dao.api;

import by.it_academy.jd2.votig.dao.entity.StatEntity;

import java.util.List;

public interface IStatisticDao {
    List<StatEntity> get();
}