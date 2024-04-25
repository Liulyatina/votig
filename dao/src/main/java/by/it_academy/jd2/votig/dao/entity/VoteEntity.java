package by.it_academy.jd2.votig.dao.entity;


import java.time.LocalDateTime;



public class VoteEntity {
    private long id;

    private LocalDateTime dtCreate;

    private long artist;
    private long[] genres;
    private String about;

    public VoteEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public long getArtist() {
        return artist;
    }

    public void setArtist(long artist) {
        this.artist = artist;
    }

    public long[] getGenres() {
        return genres;
    }

    public void setGenres(long[] genres) {
        this.genres = genres;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
