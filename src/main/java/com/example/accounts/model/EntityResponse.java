package com.example.accounts.model;

import java.util.List;

public class EntityResponse {

    public Entity entity;

    public EntityResponse() {
    }

    public EntityResponse(Entity entity) {
        this.entity = entity;
    }

    public String status = "SUCCESS";

    public String message = "OK";

    public String responseCode =  "200";

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
