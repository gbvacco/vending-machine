package com.dell.vendingmachine.dto;

import java.util.List;

import com.dell.vendingmachine.model.CreditOption;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VendingCreditResponse {
    @JsonProperty("totalCreditAmount")
    public float TotalCreditAmount;

    @JsonProperty("creditCoins")
    public List<CreditOption> CreditCoins;
}
