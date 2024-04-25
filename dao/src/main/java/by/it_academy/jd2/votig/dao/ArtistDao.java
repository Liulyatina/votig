package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IArtistDao;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistDao implements IArtistDao {

    private final static String INSERT = "INSERT INTO app.artist (?) VALUES ";
    private final static String GET_LIST = "SELECT id, name FROM app.artist ORDER BY id";
    private final static String GET_BY_ID = "SELECT id, name FROM app.artist WHERE id = ?";
    private final static String UPDATE_BY_ID = "UPDATE app.artist SET name = ? WHERE id = ? RETURNING id, name";
    private final static String DELETE_BY_ID = "DELETE FROM app.artist WHERE id = ?";

    @Override
    public ArtistEntity create(ArtistEntity data) {
        try(Connection conn = DaoFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(INSERT);
        ){
            st.setString(1, data.getName());
            ResultSet rs = st.executeQuery();
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
            PreparedStatement st = conn.prepareStatement(GET_LIST)

        ){
            ResultSet rs = st.executeQuery();
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
            PreparedStatement st = conn.prepareStatement(GET_BY_ID);
        ){
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
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
            PreparedStatement st = conn.prepareStatement(UPDATE_BY_ID);
        ){
            st.setString(1, data.getName());
            st.setLong(2, id);
            ResultSet rs = st.executeQuery();
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
            PreparedStatement st = conn.prepareStatement(DELETE_BY_ID);
        ){
            st.setLong(1, id);
            int affectedRows = st.executeUpdate();

            if(affectedRows == 0){
                throw new IllegalArgumentException("Артиста не существует");
            }
        } catch (SQLException e){
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }

}
