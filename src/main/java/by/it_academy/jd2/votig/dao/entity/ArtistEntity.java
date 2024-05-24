package by.it_academy.jd2.votig.dao.entity;

import jakarta.persistence.*;

@Table(name = "artist", schema = "app")
@Entity
@SequenceGenerator(name = "artist_seq", sequenceName = "artist_id_seq", allocationSize = 1)
public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_seq")
    @Column
    private Long id;
    @Column
    private String name;

    public ArtistEntity() {
    }

    public ArtistEntity(long id, String name) {
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
