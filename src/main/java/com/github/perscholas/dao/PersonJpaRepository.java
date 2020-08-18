package com.github.perscholas.dao;

import com.github.perscholas.model.Person;
import com.github.perscholas.model.PersonBuilder;

import javax.persistence.EntityManager;

/**
 * Created by leon on 8/17/2020.
 */
public class PersonJpaRepository extends AbstractJpaRepository<Long, Person> {
    public PersonJpaRepository(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    @Override
    public Class<Person> getEntityClass() {
        return Person.class;
    }

    @Override
    public Person update(Person dataToBeUpdated, Person newEntityData) {
        return new PersonBuilder(dataToBeUpdated)
                .setEmail(newEntityData.getEmail())
                .setName(newEntityData.getName())
                .setPassword(newEntityData.getPassword())
                .build();
    }
}
