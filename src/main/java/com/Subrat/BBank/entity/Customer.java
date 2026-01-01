package com.Subrat.BBank.entity;

import com.Subrat.BBank.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Customer {
    public Customer(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cutomer_id;

    @NotBlank
    private String firstName;

    private String lastName;

    @NotNull(message = "date of birth must be in past")
    @Past
    private LocalDate dateOfBirth;

    @NotBlank
    private String gender;

    @NotBlank
    @Email(message = "enter a valid email")
    private String email;

    @NotNull
    @Size(min = 10, max = 10)
    @Column(nullable = false)
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotBlank
    private String panNumber;

    @NotBlank
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
