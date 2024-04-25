package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IVoteDao;
import by.it_academy.jd2.votig.dao.entity.VoteEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactory;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class VoteDao implements IVoteDao {

    private final static DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral(' ')
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .toFormatter();

    @Override
    public void save(VoteEntity entity) {
        String sql = "INSERT INTO app.vote(dt_create, artist, about) VALUES (?, ?, ?) RETURNING id;";

        try(Connection conn = DaoFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
        ){
            st.setString(1, entity.getDtCreate().format(formatter));
            st.setLong(2, entity.getArtist());
            st.setString(3, entity.getAbout());
            ResultSet rs = st.executeQuery();

            long id = -1;

            while (rs.next()){
                id = rs.getLong("id");
            }

            entity.setId(id);

            StringBuilder builderCrossInsert = new StringBuilder("INSERT INTO app.cross_vote_genre(vote, genre) VALUES ");

            long[] genres = entity.getGenres();

            boolean needComma = false;
            for (long genre : genres) {
                if(needComma){
                    builderCrossInsert.append(", ");
                } else {
                    needComma = true;
                }
                builderCrossInsert.append("(")
                        .append(id).append(",")
                        .append(genre)
                        .append(")");
            }

            st.executeUpdate(builderCrossInsert.toString());
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }

    @Override
    public long maxId() {
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(id) as max FROM app.vote");
        ){
            while (rs.next()) {
                return rs.getLong("max");
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
        return 0;
    }
}
