package com.bank.digitalwallet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawRequestDto {
    private Long walletId;
    private Double amount;
}
