package com.dell.vendingmachine.repository;

import com.dell.vendingmachine.model.CreditOption;
import com.dell.vendingmachine.model.Product;
import com.dell.vendingmachine.model.VendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class VendingMachineLoader implements CommandLineRunner {

    @Autowired
    VendingMachineRepository vendingMachineRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        loadVendingMachine();
    }

    private void configureCreditOptions(VendingMachine vendingMachine) {

        CreditOption penny = CreditOption.builder().label("penny").value(0.01).build();
        CreditOption nickel = CreditOption.builder().label("nickel").value(0.05).build();
        CreditOption dime = CreditOption.builder().label("dime").value(0.10).build();
        CreditOption quarter = CreditOption.builder().label("quarter").value(0.25).build();
        CreditOption fiftyCent = CreditOption.builder().label("fiftyCent").value(0.50).build();
        CreditOption dollar = CreditOption.builder().label("dollar").value(1.00).build();

        vendingMachine.setCreditOptions(Arrays.asList(penny, nickel, dime, quarter, fiftyCent, dollar));

        vendingMachineRepository.save(vendingMachine);
    }

    private void loadVendingMachine() {

        if (vendingMachineRepository.count() == 0) {
            VendingMachine vendingMachine1 = VendingMachine.builder().credit(0).address("Golden Gate").build();

            Product product1 = Product.builder().name("Guarana").value(5).quantity(5).build();

            Product product2 = Product.builder().name("Mandarin").value((float) 0.5).quantity(5).build();

            vendingMachine1.setProducts(Arrays.asList(product1, product2));

            this.configureCreditOptions(vendingMachine1);
            vendingMachineRepository.save(vendingMachine1);

            // ---------------------- Machine 2 ----------------------

            VendingMachine vendingMachine2 = VendingMachine.builder().credit(0).address("Statue of Liberty").build();

            Product product3 = Product.builder().name("Coke").value(2).quantity(5).build();

            Product product4 = Product.builder().name("Apple").value((float) 0.5).quantity(5).build();

            vendingMachine2.setProducts(Arrays.asList(product3, product4));

            this.configureCreditOptions(vendingMachine2);
            vendingMachineRepository.save(vendingMachine2);

        }
    }

}
