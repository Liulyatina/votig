package by.it_academy.jd2.votig.dao.entity;

import jakarta.persistence.*;


public class GenreEntity {

    private long id;

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
