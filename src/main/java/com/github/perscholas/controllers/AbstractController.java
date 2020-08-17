package com.github.perscholas.controllers;

import com.github.perscholas.service.ServiceInterface;

/**
 * Created by leon on 8/14/2020.
 */
abstract public class AbstractController<
        ServiceType extends ServiceInterface>
        implements ControllerInterface<ServiceType> {
    private ServiceType service;

    public AbstractController(ServiceType service) {
        this.service = service;
    }

    @Override
    public ServiceType getService() {
        return service;
    }
}
