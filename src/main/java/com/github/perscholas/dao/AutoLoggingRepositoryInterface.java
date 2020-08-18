package com.github.perscholas.dao;

import com.github.perscholas.model.EntityInterface;
import com.github.perscholas.utils.Loggable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leon on 8/17/2020.
 */
public interface AutoLoggingRepositoryInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>>
        extends RepositoryInterface<IdType, EntityType>, Loggable {

    RepositoryInterface<IdType, EntityType> getDecoratee();

    default EntityType create(EntityType entity) {
        entity = getDecoratee().create(entity);
        info("Successfully persisted entity:\n\t", toJson(entity));
        return entity;
    }


    default List<EntityType> findAll() {
        List<EntityType> resultList = getDecoratee().findAll();
        info("Successfully retrieved entities:\n\t%s", toJson(resultList));
        return resultList;
    }


    default EntityType update(EntityType existingEntity, EntityType newData) {
        getDecoratee().update(existingEntity, newData);
        info("Successfully updated entity\n\tfrom:\t%s\n\tto:\t%s", toJson(existingEntity), toJson(newData));
        return existingEntity;
    }


    default EntityType delete(EntityType entity) {
        entity = getDecoratee().delete(entity);
        info("Successfully deleted entity:\n\t", toJson(entity));
        return entity;
    }
}
