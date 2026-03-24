package com.bank.digitalwallet.controller;

import com.bank.digitalwallet.entity.Wallet;
import com.bank.digitalwallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/create")
    public Wallet createWallet(@RequestParam String name, @RequestParam String email){

        return walletService.createWallet(name,email);

    }

    @PostMapping("/addMoney")
    public Wallet addMoney(@RequestParam Long walletId, @RequestParam Double amount){

        return walletService.addMoney(walletId, amount);

    }
    @GetMapping("/getBalance")
    public Double getBalance(Long walletId){

        return walletService.getBalance(walletId);

    }
}
