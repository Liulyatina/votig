package by.it_academy.jd2.votig.dao.entity;

import jakarta.persistence.*;

@Table(name = "genre", schema = "app")
@Entity
@SequenceGenerator(name = "genre_seq", sequenceName = "genre_id_seq", allocationSize = 1)
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq")
    @Column
    private Long id;

    @Column
    private String name;

    public GenreEntity() {
    }

    public GenreEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

