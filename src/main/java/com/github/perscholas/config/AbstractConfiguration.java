package com.github.perscholas.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by leon on 8/17/2020.
 */
abstract public class AbstractConfiguration implements ConfigurationInterface {
    private List<String> scriptFiles;

    public AbstractConfiguration(String... scriptFiles) {
        this.scriptFiles = new ArrayList<>(Arrays.asList(scriptFiles));
    }

    @Override
    public List<String> getScriptFiles() {
        return scriptFiles;
    }
}
