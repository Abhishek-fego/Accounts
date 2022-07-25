package com.example.accounts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseDto {

    @JsonIgnore
    public long id;

    @JsonIgnore
    public boolean is_deleted = false;

    @JsonIgnore
    public Date created_at;

    @JsonIgnore
    public Date updated_at;

    @JsonIgnore
    public String created_by;

    @JsonIgnore
    public String updated_by;

    @JsonIgnore
    public String tenant_id;

}
