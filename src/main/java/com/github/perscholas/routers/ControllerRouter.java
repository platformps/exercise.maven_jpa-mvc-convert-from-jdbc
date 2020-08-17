package com.github.perscholas.routers;

import com.github.perscholas.controllers.ControllerInterface;

import java.util.function.Consumer;

public enum ControllerRouter {
    DISPLAY_BY_ID(ControllerInterface::displayById),
    DISPLAY_ALL(ControllerInterface::displayAll),
    DELETE(ControllerInterface::delete),
    UPDATE(ControllerInterface::update),
    CREATE(ControllerInterface::create);

    private final Consumer<ControllerInterface> controllerOperation;

    ControllerRouter(Consumer<ControllerInterface> controllerOperation) {
        this.controllerOperation = controllerOperation;
    }

    public void perform(ControllerInterface controllerInterface) {
        controllerOperation.accept(controllerInterface);
    }

    public static ControllerRouter getValueOf(String userInput) {
        return valueOf(userInput
                .toUpperCase()
                .replaceAll(" ", "_")
                .replaceAll("-", "_"));
    }
}
