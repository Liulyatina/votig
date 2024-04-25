package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IStatisticDao;
import by.it_academy.jd2.votig.dao.api.dto.EStatType;
import by.it_academy.jd2.votig.dao.entity.StatEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatisticDao implements IStatisticDao {

    private final static String SELECT_SQL = "SELECT * FROM app.v_statistics";

    @Override
    public List<StatEntity> getFlight() {
        List<StatEntity> dataList = new ArrayList<>();
        try (Connection conn = DaoFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_SQL);
        ) {
            while (rs.next()) {
                StatEntity entity = new StatEntity();
                String stat = rs.getString("stat");
                EStatType.find(stat).getConsumer().accept(entity, rs);
                dataList.add(entity);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка выполнения запроса к БД", e);
        }

        return dataList;
    }

}
