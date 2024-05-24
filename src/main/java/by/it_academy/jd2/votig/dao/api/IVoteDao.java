package by.it_academy.jd2.votig.dao.api;

import by.it_academy.jd2.votig.dao.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVoteDao extends JpaRepository<VoteEntity, Long> {
}