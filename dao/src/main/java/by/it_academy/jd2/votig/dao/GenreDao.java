package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IGenreDao;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDao implements IGenreDao {

    private final static String INSERT = "INSERT INTO app.genre (name) VALUES (?) RETURNING id, name";
    private final static String GET_LIST = "SELECT id, name FROM app.genre ORDER BY id";
    private final static String GET_BY_ID = "SELECT id, name FROM app.genre WHERE id = (?)";
    private final static String UPDATE_BY_ID = "UPDATE app.genre SET name = (?) WHERE id = (?) RETURNING id, name";
    private final static String DELETE_BY_ID = "DELETE FROM app.genre WHERE id = (?)";

    @Override
    public GenreEntity create(GenreEntity data) {

        try (Connection conn = DaoFactory.getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT);
        ) {
            st.setString(1, data.getName());
            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()) {
                    GenreEntity entity = new GenreEntity();
                    entity.setId(rs.getLong("id"));
                    entity.setName(rs.getString("name"));
                    return entity;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
        throw new IllegalStateException("При вставке данных произошла ошибка");
    }

    @Override
    public List<GenreEntity> get() {
        List<GenreEntity> data = new ArrayList<>();
        try (Connection conn = DaoFactory.getConnection();
             PreparedStatement st = conn.prepareStatement(GET_LIST);
             ResultSet rs = st.executeQuery();
        ) {
            while (rs.next()) {
                GenreEntity entity = new GenreEntity();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("name"));
                data.add(entity);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }

        return data;
    }

    @Override
    public Optional<GenreEntity> get(long id) {
        try (Connection conn = DaoFactory.getConnection();
             PreparedStatement st = conn.prepareStatement(GET_BY_ID);
        ) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery();) {
                GenreEntity entity = null;
                while (rs.next()) {
                    if (entity == null) {
                        entity = new GenreEntity();
                        entity.setId(rs.getLong("id"));
                        entity.setName(rs.getString("name"));
                    } else {
                        throw new IllegalStateException("В запросе получилось больше одного значения");
                    }
                }

                if (entity == null) {
                    return Optional.empty();
                } else {
                    return Optional.of(entity);
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }

    @Override
    public GenreEntity update(long id, GenreEntity data) {
        try (Connection conn = DaoFactory.getConnection();
             PreparedStatement st = conn.prepareStatement(UPDATE_BY_ID);
        ) {
            st.setString(1, data.getName());
            st.setLong(2, id);
            try (ResultSet rs = st.executeQuery();) {
                while (rs.next()) {
                    GenreEntity entity = new GenreEntity();
                    entity.setId(rs.getLong("id"));
                    entity.setName(rs.getString("name"));
                    return entity;
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
        throw new IllegalStateException("При обновлении данных произошла ошибка");
    }

    @Override
    public void delete(long id) {
        try (Connection conn = DaoFactory.getConnection();
             PreparedStatement st = conn.prepareStatement(DELETE_BY_ID);
        ) {
            st.setLong(1, id);
            int count = st.executeUpdate();

            if (count == 0) {
                throw new IllegalArgumentException("Жанра не существует");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }
    }
}
