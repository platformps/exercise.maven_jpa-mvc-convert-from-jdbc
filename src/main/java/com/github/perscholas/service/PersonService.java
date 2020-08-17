package com.github.perscholas.service;

import com.github.perscholas.dao.PersonJdbcRepository;
import com.github.perscholas.model.Person;

/**
 * Created by leon on 8/13/2020.
 */ // TODO - extend AbstractService<Long, Person, PersonRepository>
public class PersonService extends AbstractService<Long, Person, PersonJdbcRepository> {
    public PersonService(PersonJdbcRepository repository) {
        super(repository);
    }
}
