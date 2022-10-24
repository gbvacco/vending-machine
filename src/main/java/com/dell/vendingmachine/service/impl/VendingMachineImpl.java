package com.dell.vendingmachine.service.impl;

import com.dell.vendingmachine.dto.VendingCreditResponse;
import com.dell.vendingmachine.model.CreditOption;
import com.dell.vendingmachine.model.Product;
import com.dell.vendingmachine.model.VendingMachine;
import com.dell.vendingmachine.repository.VendingMachineRepository;
import com.dell.vendingmachine.service.VendingMachineService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VendingMachineImpl implements VendingMachineService {

    final VendingMachineRepository vendingMachineRepository;

    public VendingMachineImpl(VendingMachineRepository vendingMachineRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
    }

    @Override
    public VendingMachine AddCredit(Long id, List<CreditOption> credit) {

        VendingMachine v = vendingMachineRepository.getById(id);
        List<CreditOption> vco = v.getCreditOptions();

        Float addedCreditAmount = 0.0f;
        for (CreditOption c : credit) {

            Optional<CreditOption> found = vco.stream().filter(x -> x.getCreditOptionId().equals(c.getCreditOptionId()))
                    .findFirst();
            if (!found.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit option not found");
            }

            CreditOption co = found.get();
            if (c.getValue() != co.getValue()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Are you trying to hack me?");
            }

            addedCreditAmount += co.getValue();
        }

        DecimalFormat df = new DecimalFormat("0.00");
        v.setCredit(Double.parseDouble(df.format(v.getCredit() + addedCreditAmount)));

        return vendingMachineRepository.save(v);
    }

    @Override
    public Product BuyProduct(long id, long productId) {
        VendingMachine vm = vendingMachineRepository.getById(id);

        Optional<Product> desiredProduct = vm.getProducts().stream().filter(p -> p.getProductId() == productId)
                .findFirst();

        if (!desiredProduct.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }

        Product p = desiredProduct.get();
        Double c = vm.getCredit();

        // Check if there is enough credit
        if (c < p.getValue()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough credit");
        }

        // Check if there is enough stock
        if (p.getQuantity() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough stock");
        }

        // Update the stock
        p.setQuantity(p.getQuantity() - 1);

        // Update the credit
        vm.setCredit(c - p.getValue());

        // Save the changes
        vendingMachineRepository.save(vm);

        return p;
    }

    @Override
    public VendingCreditResponse GetCreditAmount(long id) {
        VendingMachine vm = vendingMachineRepository.getById(id);

        Double creditAmount = vm.getCredit();
        List<CreditOption> creditOptions = vm.getCreditOptions();
        List<CreditOption> credit = new ArrayList<>();

        // Order the credit options by double value
        creditOptions.sort(Comparator.comparing(CreditOption::getValue).reversed());

        VendingCreditResponse vcr = new VendingCreditResponse();

        vcr.TotalCreditAmount = creditAmount.floatValue();
        vcr.CreditCoins = this.GetCreditAmountByCreditOption(creditAmount, creditOptions, credit);

        return vcr;
    }

    // this method will return the credit as a list of credit options
    public List<CreditOption> GetCreditAmountByCreditOption(Double creditAmount, List<CreditOption> creditOptions,
            List<CreditOption> credit) {

        // round
        creditAmount = Math.round(creditAmount * 100.0) / 100.0;

        if (creditAmount > 0) {
            for (CreditOption co : creditOptions) {
                if (creditAmount >= co.getValue()) {
                    credit.add(co);
                    creditAmount -= co.getValue();
                    return this.GetCreditAmountByCreditOption(creditAmount, creditOptions, credit);
                }
            }
        }

        return credit;
    }

}
