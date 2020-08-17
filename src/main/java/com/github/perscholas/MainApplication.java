package com.github.perscholas;

import com.github.perscholas.config.JdbcConfigurator;
import com.github.perscholas.config.JpaConfigurator;

/**
 * Created by leon on 8/13/2020.
 */
public class MainApplication {
    public static void main(String[] args) {
        JdbcConfigurator configurator = new JdbcConfigurator(DatabaseConnection.PRODUCTION_DATABASE);
        JpaConfigurator jpaConfigurator = new JpaConfigurator(configurator);
        configurator.appendSqlScript("production.person_create-table.sql");
        configurator.appendSqlScript("production.person_populate-table.sql");
        jpaConfigurator.initialize();
        Runnable applicationRunner = new ApplicationRunner();
        applicationRunner.run();
    }
}
