package com.github.perscholas.config;

import org.mariadb.jdbc.Driver;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by leon on 8/17/2020.
 */
public class JpaConfigurator extends AbstractConfiguration {
    private final String persistenceUnitName;

    public JpaConfigurator(String persistenceUnitName, String... scriptFiles) {
        super(scriptFiles);
        this.persistenceUnitName = persistenceUnitName;
        try { // Attempt to register JDBC Driver
            DriverManager.registerDriver(Driver.class.newInstance());
        } catch (InstantiationException | IllegalAccessException | SQLException e1) {
            throw new Error(e1);
        }
    }

    @Override
    public void executeStatement(String statement, Object... arguments) {
        EntityManager em = Persistence
                .createEntityManagerFactory(persistenceUnitName)
                .createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(statement);
        for (int argumentNumber = 1; argumentNumber < arguments.length + 1; argumentNumber++) {
            Object argument = arguments[argumentNumber - 1];
            query.setParameter(argumentNumber, argument);
        }
        query.executeUpdate();
        em.getTransaction().commit();
    }
}
