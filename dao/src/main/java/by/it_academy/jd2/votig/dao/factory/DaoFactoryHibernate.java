package by.it_academy.jd2.votig.dao.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DaoFactoryHibernate {
    private static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-entity-definition");
    }
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
