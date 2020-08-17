package com.github.perscholas.utils;

import com.github.perscholas.model.EntityInterface;

/**
 * Created by leon on 8/14/2020.
 */
public class DatabaseResponse<EntityType extends EntityInterface> {
    private Object responseStatus;
    private EntityType responseBody;

    public DatabaseResponse() {
    }

    public DatabaseResponse(Object responseStatus, EntityType responseBody) {
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
    }

    public Object getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Object responseStatus) {
        this.responseStatus = responseStatus;
    }

    public EntityType getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(EntityType responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "DatabaseResponse{" +
                "responseStatus=" + responseStatus +
                ", responseBody=" + responseBody +
                '}';
    }
}
