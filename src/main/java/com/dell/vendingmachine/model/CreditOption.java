package com.dell.vendingmachine.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public @Data @Builder @NoArgsConstructor
@AllArgsConstructor
class CreditOption  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(columnDefinition = "serial", name = "productId")
    private Long creditOptionId;

    private  String label;

    private double value;
}
