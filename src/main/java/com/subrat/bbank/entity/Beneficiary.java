package com.subrat.bbank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beneficiaries", indexes = {
        @Index(name = "idx_beneficiaries_user_id", columnList = "user_id"),
        @Index(name = "idx_beneficiaries_account_number", columnList = "accountNumber")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 20)
    private String accountNumber;

    @Column(nullable = false, length = 100)
    private String bankName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
