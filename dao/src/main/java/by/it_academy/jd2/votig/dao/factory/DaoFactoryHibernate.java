package by.it_academy.jd2.votig.dao.factory;

import by.it_academy.jd2.votig.dao.ArtistDao;
import by.it_academy.jd2.votig.dao.GenreDao;
import by.it_academy.jd2.votig.dao.StatisticsDao;
import by.it_academy.jd2.votig.dao.VoteDao;
import by.it_academy.jd2.votig.dao.api.IArtistDao;
import by.it_academy.jd2.votig.dao.api.IGenreDao;
import by.it_academy.jd2.votig.dao.api.IStatisticDao;
import by.it_academy.jd2.votig.dao.api.IVoteDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DaoFactoryHibernate {
    private volatile static IStatisticDao statisticsDao;
    private volatile static IArtistDao artistDao;
    private volatile static IVoteDao voteDao;
    private volatile static IGenreDao genreDao;
    private static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-entity-definition");
    }

    public static IStatisticDao getStatisticsDaoHibernate() {
        if (statisticsDao == null) {
            synchronized (DaoFactoryHibernate.class) {
                if (statisticsDao == null) {
                    statisticsDao = new StatisticsDao();
                }
            }
        }
        return statisticsDao;
    }

    public static IArtistDao getArtistDao() {
        if (artistDao == null) {
            synchronized (DaoFactoryHibernate.class) {
                if (artistDao == null) {
                    artistDao = new ArtistDao();
                }
            }
        }
        return artistDao;
    }
    public static IVoteDao getVoteDao() {
        if (voteDao == null) {
            synchronized (DaoFactoryHibernate.class) {
                if (voteDao == null) {
                    voteDao = new VoteDao();
                }
            }
        }
        return voteDao;
    }
    public static IGenreDao getGenreDao() {
        if (genreDao == null) {
            synchronized (DaoFactoryHibernate.class) {
                if (genreDao == null) {
                    genreDao = new GenreDao();
                }
            }
        }
        return genreDao;
    }


    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
