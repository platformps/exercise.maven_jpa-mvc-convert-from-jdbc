package com.github.perscholas;

import com.github.perscholas.model.Person;
import com.github.perscholas.model.PersonBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by leon on 8/17/2020.
 */
public enum PersonDao implements PersistentDaoInterface<Long, Person> {
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
