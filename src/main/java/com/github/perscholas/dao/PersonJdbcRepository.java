package com.github.perscholas.dao;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.DatabaseConnectionInterface;
import com.github.perscholas.orm.PersonOrm;
import com.github.perscholas.model.Person;
import com.github.perscholas.utils.PojoToSqlConverter;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by leon on 8/13/2020.
 */ // TODO - extend `RepositoryInterface<Long, Person>`
public class PersonJdbcRepository extends AbstractJdbcRepository<Long, Person> {
    public PersonJdbcRepository(DatabaseConnectionInterface databaseConnection) {
        super(databaseConnection);
    }

    public PersonJdbcRepository() {
        this(DatabaseConnection.PRODUCTION_DATABASE);
    }

    @Override
    public Person create(Person entity) {
        PojoToSqlConverter pojoToSqlConverter = new PojoToSqlConverter(getDatabaseConnection());
        String insertStatement = pojoToSqlConverter.getInsertStatement(entity);
        getDatabaseConnection().executeStatement(insertStatement);
        return findById(entity.getId()).get();
    }

    @Override
    public List<Person> findAll() {
        String query = "SELECT * FROM Person;";
        ResultSet resultSet = getDatabaseConnection().executeQuery(query);
        return new PersonOrm(resultSet).toList();
    }

    @Override
    public Person update(Person existingEntity, Person newData) {
        existingEntity.setName(newData.getName());
        existingEntity.setPassword(newData.getPassword());
        existingEntity.setEmail(newData.getEmail());
        return existingEntity;
    }

    @Override
    public Person delete(Person entity) {
        Long id = entity.getId();
        String statement = "DELETE FROM Person WHERE id = " + id + ";";
        Person person = this.findById(id).get();
        getDatabaseConnection().executeStatement(statement);
        return person;
    }
}
