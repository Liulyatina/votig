package by.it_academy.jd2.votig.dao.api;

import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArtistDao extends JpaRepository<ArtistEntity, Long> {
}
