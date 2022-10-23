package com.dell.vendingmachine.model;

import org.hibernate.annotations.Type;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public @Data @Builder @NoArgsConstructor
@AllArgsConstructor class VendingMachine implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double credit;

    private String address;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CreditOption> creditOptions;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Product> products;

}
