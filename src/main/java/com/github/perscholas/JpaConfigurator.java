package com.github.perscholas;

import com.github.perscholas.dao.PersonJpaRepository;
import com.github.perscholas.model.Person;

/**
 * Created by leon on 8/17/2020.
 */
public class JpaConfigurator {
    private JdbcConfigurator jdbcConfigurator;

    public JpaConfigurator(JdbcConfigurator jdbcConfigurator) {
        this.jdbcConfigurator = jdbcConfigurator;
    }

    public void initialize() {
        jdbcConfigurator.initialize();
        PersonJpaRepository.PRODUCTION.create(new Person());
    }

}
