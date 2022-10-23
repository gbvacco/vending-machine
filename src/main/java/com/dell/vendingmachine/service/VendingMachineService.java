package com.dell.vendingmachine.service;

import com.dell.vendingmachine.dto.VendingCreditResponse;
import com.dell.vendingmachine.model.CreditOption;
import com.dell.vendingmachine.model.Product;
import com.dell.vendingmachine.model.VendingMachine;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface VendingMachineService {

    public VendingMachine AddCredit(Long id, List<CreditOption> credit);

    public Product BuyProduct(long id, long productId);

    public VendingCreditResponse GetCreditAmount(long id);

}
