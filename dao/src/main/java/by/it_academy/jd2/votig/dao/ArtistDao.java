package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IArtistDao;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistDao implements IArtistDao {

    private final static String INSERT = "INSERT INTO app.artist (name) VALUES ";
    private final static String GET_LIST = "SELECT id, name FROM app.artist ORDER BY id";
    private final static String GET_BY_ID = "SELECT id, name FROM app.artist WHERE id = ";
    private final static String UPDATE_BY_ID = "UPDATE app.artist SET name = '%s' WHERE id = %d RETURNING id, name";
    private final static String DELETE_BY_ID = "DELETE FROM app.artist WHERE id = %d";

    @Override
    public ArtistEntity create(ArtistEntity data) {
        StringBuilder sql = new StringBuilder(INSERT);

        sql.append("('")
                .append(data.getName())
                .append("')  RETURNING id, name");

        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql.toString());
        ){
            while (rs.next()) {
                ArtistEntity entity = new ArtistEntity();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("name"));
                return entity;
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
        throw new IllegalStateException("При вставке данных произошла ошибка");
    }

    @Override
    public List<ArtistEntity> get() {
        List<ArtistEntity> data = new ArrayList<>();
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GET_LIST);
        ){
            while (rs.next()) {
                ArtistEntity entity = new ArtistEntity();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("name"));
                data.add(entity);
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }

        return data;
    }

    @Override
    public Optional<ArtistEntity> get(long id) {
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(GET_BY_ID + id);
        ){
            ArtistEntity entity = null;
            while (rs.next()) {
                if(entity == null){
                    entity = new ArtistEntity();
                    entity.setId(rs.getLong("id"));
                    entity.setName(rs.getString("name"));
                } else {
                    throw new IllegalStateException("В запросе получилось больше одного значения");
                }
            }

            if(entity == null){
                return Optional.empty();
            } else {
                return Optional.of(entity);
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }

    @Override
    public ArtistEntity update(long id, ArtistEntity data) {
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(String.format(UPDATE_BY_ID, data.getName(), data.getId()));
        ){
            while (rs.next()) {
                ArtistEntity entity = new ArtistEntity();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("name"));
                return entity;
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
        throw new IllegalStateException("При обновлении данных произошла ошибка");
    }

    @Override
    public void delete(long id) {
        try(Connection conn = DaoFactory.getConnection();
            Statement st = conn.createStatement();
        ){
            int count = st.executeUpdate(String.format(DELETE_BY_ID, id));

            if(count == 0){
                throw new IllegalArgumentException("Артиста не существует");
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }
}
