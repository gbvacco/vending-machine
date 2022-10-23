package com.dell.vendingmachine.repository;

import com.dell.vendingmachine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String>  {
}
