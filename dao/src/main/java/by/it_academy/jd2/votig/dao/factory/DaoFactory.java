package by.it_academy.jd2.votig.dao.factory;

import by.it_academy.jd2.votig.dao.ArtistDao;
import by.it_academy.jd2.votig.dao.GenreDao;
//import by.it_academy.jd2.votig.dao.StatisticDao;
import by.it_academy.jd2.votig.dao.StatisticsDaoHibernate;
import by.it_academy.jd2.votig.dao.VoteDao;
import by.it_academy.jd2.votig.dao.api.IArtistDao;
import by.it_academy.jd2.votig.dao.api.IGenreDao;
import by.it_academy.jd2.votig.dao.api.IStatisticDao;
import by.it_academy.jd2.votig.dao.api.IVoteDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    private volatile static IVoteDao voteDao;
    private volatile static IArtistDao artistDao;
    private volatile static IGenreDao genreDao;
    private volatile static IStatisticDao statisticDao;
    private static final String url = "jdbc:postgresql://localhost:15432/voting";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "voting_app");
        props.setProperty("password", "1234");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static IVoteDao getVoteDao(){
        if(voteDao == null){
            synchronized (DaoFactory.class){
                if(voteDao == null){
                    voteDao = new VoteDao();
                }
            }
        }
        return voteDao;
    }

    public static IArtistDao getArtistDao(){
        if(artistDao == null){
            synchronized (DaoFactory.class){
                if(artistDao == null){
                    artistDao = new ArtistDao();
                }
            }
        }
        return artistDao;
    }

    public static IGenreDao getGenreDao(){
        if(genreDao == null){
            synchronized (DaoFactory.class){
                if(genreDao == null){
                    genreDao = new GenreDao();
                }
            }
        }
        return genreDao;
    }

    public static IStatisticDao getStatisticDao(){
        if(statisticDao == null){
            synchronized (DaoFactory.class){
                if(statisticDao == null){
                    statisticDao = new StatisticsDaoHibernate();
                }
            }
        }
        return statisticDao;
    }


    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException e){
            throw new IllegalStateException("Невозможно подключиться к базе данных", e);
        }
    }

}
