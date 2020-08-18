package com.github.perscholas.jpa.personcontroller;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.config.JdbcConfigurator;
import com.github.perscholas.config.JpaConfigurator;
import com.github.perscholas.controllers.PersonController;
import com.github.perscholas.dao.PersonJdbcRepository;
import com.github.perscholas.dao.PersonJpaRepository;
import com.github.perscholas.service.PersonService;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by leon on 8/14/2020.
 */
public class DisplayAllTest {

    @Before
    public void setup() {
        // given
        JpaConfigurator configurator = new JpaConfigurator("testing");
        configurator.appendSqlScript("testing.person_create-table.sql");
        configurator.appendSqlScript("testing.person_populate-table.sql");
        configurator.initialize();
    }

    @Test
    public void test() {
        // given
        PersonController personController = new PersonController(new PersonService(new PersonJpaRepository("testing")));

        // when
        personController.displayAll();

        // then

    }
}