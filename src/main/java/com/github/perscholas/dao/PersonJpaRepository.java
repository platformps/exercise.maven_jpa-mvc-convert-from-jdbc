package com.github.perscholas.dao;

import com.github.perscholas.model.Person;
import com.github.perscholas.model.PersonBuilder;

import javax.persistence.EntityManager;

/**
 * Created by leon on 8/17/2020.
 */
public enum PersonJpaRepository implements JpaRepositoryInterface<Long, Person> {
    PRODUCTION;

    private EntityManager entityManager;

    @Override
    public String getPersistenceUnitName() {
        return name().toLowerCase();
    }

    @Override
    public EntityManager getEntityManager() {
        if(this.entityManager == null) {
            this.entityManager = getEntityManagerFactory().createEntityManager();
        }
        return this.entityManager;
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
