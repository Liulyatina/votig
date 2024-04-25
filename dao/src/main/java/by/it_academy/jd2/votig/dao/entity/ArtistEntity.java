package by.it_academy.jd2.votig.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "artist", schema = "app")
@Entity
public class ArtistEntity {
    @Id
    @Column
    private long id;
    @Column
    private String name;

    public ArtistEntity() {
    }

    public ArtistEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
