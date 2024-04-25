package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IStatisticDao;


import by.it_academy.jd2.votig.dao.entity.StatEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactoryHibernate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class StatisticsDaoHibernate implements IStatisticDao {
    @Override
    public List<StatEntity> getFlight() {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StatEntity> query = cb.createQuery(StatEntity.class);
        query.from(StatEntity.class);
        List<StatEntity> resultList = em.createQuery(query).getResultList();
        em.getTransaction().commit();
        return resultList;
    }
}