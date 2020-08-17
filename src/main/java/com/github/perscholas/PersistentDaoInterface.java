package com.github.perscholas;

import com.github.perscholas.model.EntityInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by leon on 8/17/2020.
 */
public interface PersistentDaoInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface> {

    EntityType update(EntityType dataToBeUpdated, EntityType newEntityData);

    Class<EntityType> getEntityClass();

    String getPersistenceUnitName();

    EntityManager getEntityManager();

    default EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(getPersistenceUnitName());
    }

    default List<EntityType> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EntityType> cq = cb.createQuery(getEntityClass());
        Root<EntityType> rootEntry = cq.from(getEntityClass());
        CriteriaQuery<EntityType> all = cq.select(rootEntry);
        TypedQuery<EntityType> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }

    default EntityType create(EntityType entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
        return entity;
    }

    default EntityType find(IdType id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    default EntityType deleteById(IdType id) {
        return delete(find(id));
    }

    default EntityType delete(EntityType entityToBeDeleted) {
        getEntityManager().remove(entityToBeDeleted);
        return entityToBeDeleted;
    }

    default void close() {
        getEntityManager().close();
        getEntityManagerFactory().close();
    }
}
