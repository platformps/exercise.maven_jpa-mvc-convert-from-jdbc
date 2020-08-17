package com.github.perscholas.controllers;

import com.github.perscholas.service.ServiceInterface;

public interface ControllerInterface<ServiceType extends ServiceInterface> {
    ServiceType getService();
    void displayById();
    void displayAll();
    void delete();
    void update();
    void create();
}
