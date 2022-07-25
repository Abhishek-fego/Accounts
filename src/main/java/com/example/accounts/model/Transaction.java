package com.example.accounts.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Transaction extends BaseModel{

    public String type;

    public BigDecimal amount;

    public long user_id;

    public long account_id;


}
