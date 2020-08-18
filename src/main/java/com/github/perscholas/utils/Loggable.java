package com.github.perscholas.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leon on 8/17/2020.
 */
public interface Loggable {
    default Logger getLogger() {
        return Logger.getLogger(getClass().getSimpleName());
    }

    default void log(Level level, String message, Object... args) {
       getLogger().log(level, String.format(message, args));
    }

    default void info(String message, Object... args) {
        log(Level.INFO, message, args);
    }

    default String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
