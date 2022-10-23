package com.dell.vendingmachine.service;

import com.dell.vendingmachine.dto.VendingCredit;
import com.dell.vendingmachine.model.CreditOption;
import com.dell.vendingmachine.model.Product;
import com.dell.vendingmachine.model.VendingMachine;
import com.dell.vendingmachine.repository.VendingMachineRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public interface VendingMachineService {


    public VendingMachine AddCredit(Long id, List<CreditOption> credit);

    public Product BuyProduct(long id, long productId);

    public List<CreditOption> GetCreditAmount(long id);

}
