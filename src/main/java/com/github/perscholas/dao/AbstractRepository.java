package com.github.perscholas.dao;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.DatabaseConnectionInterface;
import com.github.perscholas.model.EntityInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leon on 8/14/2020.
 */
public abstract class AbstractRepository<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>>
        implements RepositoryInterface<IdType, EntityType> {

    private DatabaseConnectionInterface databaseConnection;

    public AbstractRepository(DatabaseConnectionInterface databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public AbstractRepository() {
        this(DatabaseConnection.PRODUCTION_DATABASE);
    }

    @Override
    public DatabaseConnectionInterface getDatabaseConnection() {
        return this.databaseConnection;
    }

    /**
     * // TODO - 1. fetch all of the respective entity type from the database as `ResultSet`
     * // TODO - 2. convert from `ResultSet` to `List<Entity>`
     *
     * @return
     */
    @Override
    abstract public List<EntityType> findAll();

    @Override
    abstract public EntityType update(EntityType existingEntity, EntityType newData);

    @Override // TODO - Persist data from current JVM instance to database
    abstract public void commit();
}
