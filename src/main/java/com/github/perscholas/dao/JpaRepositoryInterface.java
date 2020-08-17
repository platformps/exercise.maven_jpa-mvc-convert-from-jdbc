package com.github.perscholas.dao;

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
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by leon on 8/17/2020.
 */
public interface JpaRepositoryInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>> {

    default EntityType create(EntityType entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
        return entity;
    }

    default List<EntityType> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EntityType> cq = cb.createQuery(getEntityClass());
        Root<EntityType> rootEntry = cq.from(getEntityClass());
        CriteriaQuery<EntityType> all = cq.select(rootEntry);
        TypedQuery<EntityType> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }

    EntityType update(EntityType dataToBeUpdated, EntityType newEntityData);

    default EntityType deleteById(IdType id) {
        return delete(findById(id).get());
    }

    default EntityType delete(EntityType entityToBeDeleted) {
        getEntityManager().remove(entityToBeDeleted);
        return entityToBeDeleted;
    }

    default List<EntityType> findAllWhere(Predicate<EntityType> filterClause) {
        return findAll()
                .stream()
                .filter(filterClause)
                .collect(Collectors.toList());
    }

    default Optional<EntityType> findById(IdType id) {
        return Optional.of(getEntityManager().find(getEntityClass(), id));
    }

    default EntityType updateById(IdType id, EntityType newEntityData) {
        return update(findById(id).get(), newEntityData);
    }

    Class<EntityType> getEntityClass();

    String getPersistenceUnitName();

    EntityManager getEntityManager();

    default EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(getPersistenceUnitName());
    }

    default void close() {
        getEntityManager().close();
        getEntityManagerFactory().close();
    }
}
