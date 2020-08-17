package com.github.perscholas.dao;

import com.github.perscholas.model.EntityInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by leon on 8/17/2020.
 */
public interface RepositoryInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>> {

    EntityType create(EntityType entity);

    List<EntityType> findAll();

    EntityType update(EntityType existingEntity, EntityType newData);

    EntityType delete(EntityType entity);

    EntityType deleteById(IdType id);

    List<EntityType> findAllWhere(Predicate<EntityType> filterClause);

    Optional<EntityType> findById(IdType id);

    EntityType updateById(IdType id, EntityType newData);

    List<EntityType> updateAllWhere(Predicate<EntityType> filterClause, Function<EntityType, EntityType> updateFunction);
}
