package com.github.perscholas.dao;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.DatabaseConnectionInterface;
import com.github.perscholas.model.EntityInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leon on 8/14/2020.
 */
public abstract class AbstractJdbcRepository<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>>
        implements JdbcRepositoryInterface<IdType, EntityType> {

    private DatabaseConnectionInterface databaseConnection;

    public AbstractJdbcRepository(DatabaseConnectionInterface databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public DatabaseConnectionInterface getDatabaseConnection() {
        return this.databaseConnection;
    }
}
