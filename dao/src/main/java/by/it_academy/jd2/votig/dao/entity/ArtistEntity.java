package by.it_academy.jd2.votig.dao.entity;

import jakarta.persistence.*;

@Table(name = "artist", schema = "app")
@Entity
public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_seq")
    @SequenceGenerator(name = "artist_id_seq", sequenceName = "artist_id_seq", allocationSize = 1)
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
