package com.github.perscholas.controllers;

import com.github.perscholas.model.Person;
import com.github.perscholas.model.PersonBuilder;
import com.github.perscholas.service.PersonService;
import com.github.perscholas.utils.IOConsole;

/**
 * Created by leon on 8/14/2020.
 */ // TODO - extend AbstractController<PersonService>
public class PersonController extends AbstractController<PersonService> {
    private IOConsole console = new IOConsole(IOConsole.AnsiColor.PURPLE);

    public PersonController(PersonService service) {
        super(service);
    }

    @Override
    public void displayById() {
        Long id = console.getLongInput("Enter id of person to be displayed");
        console.println(getService()
                .findById(id)
                .toString());
    }

    @Override
    public void displayAll() {
        console.println(getService()
                .findAll()
                .toString()
                .replaceAll(", ", "\n")
                .replaceAll("Person\\{", "\n\nPerson\\{"));

    }

    @Override
    public void delete() {
        Long id = console.getLongInput("Enter id of person to be deleted");
        Person personToBeDeleted = getService().findById(id);
        getService().delete(personToBeDeleted);
        console.println("Deleted:\n\t" + personToBeDeleted.toString());
    }

    @Override
    public void update() {
        Long id = console.getLongInput("Enter id of person to be updated");
        Person personToBeUpdated = getService().findById(id);
        Person newData = buildPerson();
        personToBeUpdated.setEmail(newData.getEmail());
        personToBeUpdated.setName(newData.getName());
        personToBeUpdated.setPassword(newData.getPassword());
    }

    @Override
    public void create() {
        Person person = buildPerson();
        getService().create(person);
        console.println("Created:\n\t" + person.toString());
    }

    private Person buildPerson() {
        return new PersonBuilder()
                .setId(console.getLongInput("Enter id of person to be created."))
                .setEmail(console.getStringInput("Enter email of person to be created."))
                .setName(console.getStringInput("Enter name of person to be created."))
                .setPassword(console.getStringInput("Enter password of person to be created."))
                .build();
    }
}