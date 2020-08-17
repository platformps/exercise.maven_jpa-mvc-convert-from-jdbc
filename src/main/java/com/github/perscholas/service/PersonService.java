package com.github.perscholas.service;

import com.github.perscholas.dao.RepositoryInterface;
import com.github.perscholas.model.Person;

/**
 * Created by leon on 8/13/2020.
 */ // TODO - extend AbstractService<Long, Person, PersonRepository>
public class PersonService extends AbstractService<Long, Person, RepositoryInterface<Long, Person>> {
    public PersonService(RepositoryInterface<Long, Person> repository) {
        super(repository);
    }
}
