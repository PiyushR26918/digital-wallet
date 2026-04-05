package com.bank.digitalwallet.controller;

import com.bank.digitalwallet.dto.AddMoneyRequestDto;
import com.bank.digitalwallet.dto.TransferRequestDto;
import com.bank.digitalwallet.dto.WithdrawRequestDto;
import com.bank.digitalwallet.entity.Wallet;
import com.bank.digitalwallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet){
        return ResponseEntity.ok(walletService.createWallet(wallet.getOwnerName()));
    }

    @PostMapping("/addMoney")
    public ResponseEntity<Wallet> addMoney(@RequestBody AddMoneyRequestDto request){
        return ResponseEntity.ok(walletService.addMoney(request.getWalletId(), request.getAmount()));
    }

    @GetMapping("{walletId}/Balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long walletId){
        return ResponseEntity.ok(walletService.getBalance(walletId));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Wallet> withdraw(@RequestBody WithdrawRequestDto request){
        return ResponseEntity.ok(walletService.withdrawMoney(request.getWalletId(), request.getAmount()));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDto request){
        return ResponseEntity.ok(walletService.transferMoney(request.getSenderId(), request.getReceiverId(), request.getAmount()));
    }

}
