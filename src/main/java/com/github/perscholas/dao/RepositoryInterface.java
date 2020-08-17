package com.github.perscholas.dao;


import com.github.perscholas.DatabaseConnectionInterface;
import com.github.perscholas.model.EntityInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by leon on 8/14/2020.
 */
public interface RepositoryInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface> {
    DatabaseConnectionInterface getDatabaseConnection();

    EntityType create(EntityType entity);

    List<EntityType> findAll();

    EntityType update(EntityType existingEntity, EntityType newData);

    EntityType delete(EntityType entity);

    void commit();

    default EntityType delete(IdType id) {
        return delete(findById(id).get());
    }

    default List<EntityType> findAllWhere(Predicate<EntityType> filterClause) {
        return new ArrayList<>(findAll()
                .stream()
                .filter(filterClause)
                .collect(Collectors.toList()));
    }

    default Optional<EntityType> findById(IdType id) {
        return Optional.of(findAllWhere(entity -> entity.getId().equals(id)).get(0));
    }

    default EntityType updateById(IdType id, EntityType newData) {
        return updateWhere(
                entityToBeEvaluated -> entityToBeEvaluated.getId().equals(id),
                matchedEntity -> update(matchedEntity, newData))
                .get(0);
    }

    default List<EntityType> updateWhere(Predicate<EntityType> filterClause, Function<EntityType, EntityType> updateFunction) {
        return findAllWhere(filterClause)
                .stream()
                .map(updateFunction::apply)
                .collect(Collectors.toList());
    }
}
