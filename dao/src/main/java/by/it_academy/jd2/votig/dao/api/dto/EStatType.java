package by.it_academy.jd2.votig.dao.api.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;

public enum EStatType {
    GENRE("genre", ((statDTO, rs) -> {
        try {
            statDTO.addGenre(rs.getLong("id"), rs.getLong("cnt"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    })),
    ARTIST("artist", ((statDTO, rs) -> {
        try {
            statDTO.addArtist(rs.getLong("id"), rs.getLong("cnt"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    })),
    ;

    private String name;
    private BiConsumer<StatDTO, ResultSet> consumer;

    EStatType(String name, BiConsumer<StatDTO, ResultSet> consumer) {
        this.name = name;
        this.consumer = consumer;
    }

    public String getName() {
        return name;
    }

    public BiConsumer<StatDTO, ResultSet> getConsumer() {
        return consumer;
    }

    public static EStatType find(String statName){
        for (EStatType value : values()) {
            if(value.getName().equals(statName)){
                return value;
            }
        }

        throw new IllegalStateException("Невозможно обработать статистику");
    }
}
