package com.github.perscholas.config;

import com.github.perscholas.utils.DirectoryReference;
import com.github.perscholas.utils.FileReader;

import java.io.File;
import java.util.List;

public interface ConfigurationInterface {
    List<String> getScriptFiles();

    void executeStatement(String statement, Object... arguments);

    default void initialize() {
        getScriptFiles().forEach(this::executeSqlFile);
    }

    default void appendSqlScript(String fileName) {
        getScriptFiles().add(fileName);
    }

    default void executeSqlFile(String fileName) {
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory(fileName);
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String[] statements = fileReader.toString().split(";");
        for (int i = 0; i < statements.length; i++) {
            String statement = statements[i];
            executeStatement(statement);
        }
    }
}
