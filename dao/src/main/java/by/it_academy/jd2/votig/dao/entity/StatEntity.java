package by.it_academy.jd2.votig.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_statistics", schema = "app")
public class StatEntity {
    @Id
    @Column
    private long id;
    @Column
    private String stat;
    @Column
    private long cnt;

    public StatEntity() {
    }

    public StatEntity(long id, String stat, long cnt) {
        this.id = id;
        this.stat = stat;
        this.cnt = cnt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }
}
