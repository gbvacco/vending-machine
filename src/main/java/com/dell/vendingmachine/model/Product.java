package com.dell.vendingmachine.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public @Data @Builder @NoArgsConstructor
@AllArgsConstructor
class Product  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(columnDefinition = "serial", name = "productId")
    private Long productId;

    private  String name;

    private double value;

    private int quantity;

}
