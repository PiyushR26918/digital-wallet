package com.bank.digitalwallet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDto {
    private Long senderId;
    private Long receiverId;
    private Double amount;
}
