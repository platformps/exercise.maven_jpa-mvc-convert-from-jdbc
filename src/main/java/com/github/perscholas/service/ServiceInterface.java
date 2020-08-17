package com.github.perscholas.service;

import com.github.perscholas.dao.RepositoryInterface;
import com.github.perscholas.model.EntityInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leon on 8/14/2020.
 */
public interface ServiceInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>,
        RepositoryType extends RepositoryInterface<IdType, EntityType>> {
    RepositoryType getRepository();

    default List<EntityType> findAll() {
        return getRepository().findAll();
    }

    default EntityType update(EntityType existingEntity, EntityType newData) {
        return getRepository().update(existingEntity, newData);
    }

    default EntityType create(EntityType entity) {
        return getRepository().create(entity);
    }

    default EntityType delete(IdType id) {
        return getRepository().delete(id);
    }

    default EntityType delete(EntityType entity) {
        return getRepository().delete(entity);
    }

    default EntityType findById(IdType id) {
        return getRepository().findById(id).get();
    }

    default EntityType updateById(IdType id, EntityType newData) {
        return getRepository().updateById(id, newData);
    }

}
