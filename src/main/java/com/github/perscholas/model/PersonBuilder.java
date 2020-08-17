package com.github.perscholas.model;

public class PersonBuilder {
    private Long id;
    private String email;
    private String name;
    private String password;

    public PersonBuilder() {
    }

    public PersonBuilder(Person dataToBeUpdated) {
        this.id = dataToBeUpdated.getId();
        this.email = dataToBeUpdated.getEmail();
        this.name = dataToBeUpdated.getName();
        this.password = dataToBeUpdated.getPassword();
    }

    public PersonBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public PersonBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public Person build() {
        return new Person(id, email, name, password);
    }
}