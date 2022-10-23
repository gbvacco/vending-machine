package com.dell.vendingmachine.repository;

import com.dell.vendingmachine.model.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendingMachineRepository  extends JpaRepository<VendingMachine, Long> {

//    VendingMachine findById(long id);

}
