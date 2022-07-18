package com.example.accounts.dto;

import java.util.Date;

public class BaseDto {

    public boolean is_deleted = false;

    public Date created_at;

    public Date updated_at;

    public String created_by;

    public String updated_by;

    public boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "is_deleted=" + is_deleted +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", created_by='" + created_by + '\'' +
                ", updated_by='" + updated_by + '\'' +
                '}';
    }
}
