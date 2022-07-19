package com.example.accounts.model;

import java.util.List;

public class EntityResponse {

    public Entity entity;

    public EntityResponse() {
    }

    public EntityResponse(Entity entity) {
        this.entity = entity;
    }

    public final String status = "SUCCESS";

    public final String message = "OK";

    public final String responseCode = "200";

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
