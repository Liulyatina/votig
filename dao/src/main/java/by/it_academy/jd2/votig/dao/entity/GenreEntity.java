package by.it_academy.jd2.votig.dao.entity;

import jakarta.persistence.*;

@Table(name = "genre", schema = "app")
@Entity
public class GenreEntity {
    @Id
    @Column
    private long id;
    @ManyToOne
    @JoinColumn(name = "id")
    private String name;

    public GenreEntity() {
    }

    public GenreEntity(long id, String name) {
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
