package by.it_academy.jd2.votig.dao;

import by.it_academy.jd2.votig.dao.api.IArtistDao;
import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.dao.factory.DaoFactoryHibernate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;
import java.util.Optional;

public class ArtistDao implements IArtistDao {

    @Override
    public ArtistEntity create(ArtistEntity data) {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
        return data;
    }

    @Override
    public List<ArtistEntity> get() {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ArtistEntity> query = cb.createQuery(ArtistEntity.class);
        query.from(ArtistEntity.class);
        List<ArtistEntity> resultList = em.createQuery(query).getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    @Override
    public Optional<ArtistEntity> get(long id) {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        ArtistEntity entity = em.find(ArtistEntity.class, id);
        em.getTransaction().commit();
        return Optional.ofNullable(entity);
    }

    @Override
    public ArtistEntity update(long id, ArtistEntity data) {
        EntityManager em = DaoFactoryHibernate.getEntityManager();
        em.getTransaction().begin();
        ArtistEntity entityToUpdate = em.find(ArtistEntity.class, id);
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
        ArtistEntity entityToDelete = em.find(ArtistEntity.class, id);
        if (entityToDelete != null) {
            em.remove(entityToDelete);
        }
        em.getTransaction().commit();
    }
}
