package com.bank.digitalwallet.controller;

import com.bank.digitalwallet.entity.Wallet;
import com.bank.digitalwallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    }@PostMapping("/withdraw")
    public Wallet withdrawMoney(@RequestBody Map<String, Object> request){
        Long id = Long.valueOf(request.get("id").toString());
        Double amount = Double.valueOf(request.get("amount").toString());

        return walletService.withdrawMoney(id, amount);
    }
    @PostMapping("/transfer")
    public String transferMoney(@RequestBody Map<String, Object> request){
        Long senderId = Long.valueOf(request.get("senderId").toString());
        Long receiverId = Long.valueOf(request.get("receiverId").toString());
        Double amount = Double.valueOf(request.get("amount").toString());

        return walletService.transferMoney(senderId, receiverId, amount);
    }

}
