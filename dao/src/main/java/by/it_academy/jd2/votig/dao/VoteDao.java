package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IVoteDao;
import by.it_academy.jd2.votig.dao.entity.VoteEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactoryHibernate;
import jakarta.persistence.EntityManager;

import java.util.List;

public class VoteDao implements IVoteDao {

    @Override
    public void save(VoteEntity entity) {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public long maxId() {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        Long maxId = (Long) em.createQuery("SELECT MAX(v.id) FROM VoteEntity v").getSingleResult();
        em.getTransaction().commit();
        return maxId != null ? maxId : 0;
    }
}
