package com.github.perscholas.utils;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.DatabaseConnectionInterface;

import java.lang.reflect.Field;

/**
 * Created by leon on 8/14/2020.
 */
public class PojoToSqlConverter {
    private DatabaseConnectionInterface dbc;

    public PojoToSqlConverter(DatabaseConnectionInterface dbc) {
        this.dbc = dbc;
    }

    public String getInsertStatement(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String commas = "";
        String values = "";
        for (int fieldNumber = 0; fieldNumber < fields.length; fieldNumber++) {
            Field field = fields[fieldNumber];
            boolean accessLevel = field.isAccessible();
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                Object fieldValue = field.get(obj);
                String value = fieldValue.toString();
                if (fieldNumber != 0) {
                    commas = commas + ",";
                    values = values + ",";
                }
                if (fieldValue instanceof String) {
                    values += "'" + value + "'";
                } else {
                    values = values + value;
                }
                commas = commas + fieldName;
            } catch (IllegalAccessException ex) {
            }
            field.setAccessible(accessLevel);
        }
        return String.format("INSERT INTO %s.%s (%s) VALUES (%s);",
                dbc.getDatabaseName(),
                obj.getClass().getSimpleName().toLowerCase(),
                commas,
                values);
    }
}
