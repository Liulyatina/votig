package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IGenreDao;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactoryHibernate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDao implements IGenreDao {

    @Override
    public GenreEntity create(GenreEntity data) {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
        return data;
    }

    @Override
    public List<GenreEntity> get() {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GenreEntity> query = cb.createQuery(GenreEntity.class);
        query.from(GenreEntity.class);
        List<GenreEntity> resultList = em.createQuery(query).getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    @Override
    public Optional<GenreEntity> get(long id) {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        GenreEntity entity = em.find(GenreEntity.class, id);
        em.getTransaction().commit();
        return Optional.ofNullable(entity);
    }

    @Override
    public GenreEntity update(long id, GenreEntity data) {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        GenreEntity entityToUpdate = em.find(GenreEntity.class, id);
        if (entityToUpdate != null) {
            entityToUpdate.setName(data.getName());
            em.merge(entityToUpdate);
        }
        em.getTransaction().commit();
        return entityToUpdate;
    }

    @Override
    public void delete(long id) {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        GenreEntity entityToDelete = em.find(GenreEntity.class, id);
        if (entityToDelete != null) {
            em.remove(entityToDelete);
        }
        em.getTransaction().commit();
    }
}
