package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IStatisticDao;
import by.it_academy.jd2.votig.dao.api.dto.EStatType;
import by.it_academy.jd2.votig.dao.entity.StatEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.module.ModuleDescriptor.read;

public class StatisticDao implements IStatisticDao {

    private final static String SELECT_SQL = "SELECT * FROM app.v_statistics";

    @Override
    public List<StatEntity> get() {
        try (Connection conn = DaoFactory.getConnection();
             PreparedStatement st = conn.prepareStatement(SELECT_SQL);
             ResultSet rs = st.executeQuery()) {
            List<StatEntity> data = new ArrayList<>();
            while (rs.next()) {
                data.add(read(rs));
            }
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private StatEntity read(ResultSet rs) throws SQLException {
        StatEntity entity = new StatEntity();
        entity.setStat(rs.getString("stat"));
        entity.setId(rs.getInt("id"));
        entity.setCnt(rs.getInt("cnt"));
        return entity;
    }
}
