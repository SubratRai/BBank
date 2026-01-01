package com.Subrat.BBank.entity;

import com.Subrat.BBank.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Customer {
    public Customer(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cutomer_id;

    @NonNull
    private String first_name;

    private String last_name;

    @NonNull
    private LocalDate date_of_birth;

    @NonNull
    private String gender;

    @NonNull()
    private String email;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String address;

    @NonNull
    private String panNumber;

    @NonNull
    private String aadhaarNumber;

    @Enumerated(EnumType.STRING)
    private AccountType customer_status; /*(active or blocked)*/

    private LocalDateTime created_At;

    @PrePersist
    public void onCreated(){
        this.created_At=LocalDateTime.now();
        this.customer_status=AccountType.Active;
    }

}
