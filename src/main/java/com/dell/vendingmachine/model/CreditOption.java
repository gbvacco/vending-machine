package com.dell.vendingmachine.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public @Data @Builder @NoArgsConstructor @AllArgsConstructor class CreditOption implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditOptionId;

    private String label;
    private float value;
}
