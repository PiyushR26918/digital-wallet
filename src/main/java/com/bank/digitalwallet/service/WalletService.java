package com.bank.digitalwallet.service;

import com.bank.digitalwallet.entity.User;
import com.bank.digitalwallet.entity.Wallet;
import com.bank.digitalwallet.repository.UserRepository;
import com.bank.digitalwallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    //Method to create new user and wallet
    public Wallet createWallet(String name, String email){

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.0);

        return walletRepository.save(wallet);
    }

    public Wallet addMoney(Long walletId, Double amount){

        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.setBalance(wallet.getBalance() + amount);
        return walletRepository.save(wallet);

    }

    public Double getBalance(Long walletId){

        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        return wallet.getBalance();
    }
}
