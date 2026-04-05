package com.bank.digitalwallet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMoneyRequestDto {
    private Long walletId;
    private Double amount;
}
