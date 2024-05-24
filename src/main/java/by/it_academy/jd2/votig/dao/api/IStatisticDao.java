package by.it_academy.jd2.votig.dao.api;


import by.it_academy.jd2.votig.dao.entity.StatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatisticDao extends JpaRepository<StatEntity, Long> {
}