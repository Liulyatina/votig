package by.it_academy.jd2.votig.dao.factory;

import by.it_academy.jd2.votig.dao.StatisticsDaoHibernate;
import by.it_academy.jd2.votig.dao.api.IStatisticDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DaoFactoryHibernate {
    private volatile static IStatisticDao StatisticsDaoHibernate;
    private static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-entity-definition");
    }



    public static IStatisticDao getStatisticsDaoHibernate() {
        if (StatisticsDaoHibernate == null) {
            synchronized (DaoFactoryHibernate.class) {
                if (StatisticsDaoHibernate == null) {
                    StatisticsDaoHibernate = new StatisticsDaoHibernate();
                }
            }
        }
        return StatisticsDaoHibernate;
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
