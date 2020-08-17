package com.github.perscholas.dao;

import com.github.perscholas.model.EntityInterface;

import java.io.Serializable;

/**
 * Created by leon on 8/17/2020.
 */
abstract public class AbstractJpaRepository<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>>
        implements JpaRepositoryInterface<IdType, EntityType> {
    private String persistenceUnitName;

    public AbstractJpaRepository(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }
}