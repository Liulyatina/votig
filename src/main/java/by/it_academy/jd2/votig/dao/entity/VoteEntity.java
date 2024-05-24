package by.it_academy.jd2.votig.dao.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vote", schema = "app")
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @ManyToOne
    @JoinColumn(name = "artist")
    private ArtistEntity artist;

    @ManyToMany
    @JoinTable(
            name = "cross_vote_genre", schema = "app",
            joinColumns = @JoinColumn(name = "vote", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre", referencedColumnName = "id")
    )
    private List<GenreEntity> genres;

    @Column(name = "about")
    private String about;

    public VoteEntity() {
    }

    public VoteEntity(long id, LocalDateTime dtCreate, ArtistEntity artist, List<GenreEntity> genres, String about) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.artist = artist;
        this.genres = genres;
        this.about = about;
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

    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}