package by.it_academy.jd2.votig.dao.api;

import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGenreDao extends JpaRepository<GenreEntity, Long> {
}