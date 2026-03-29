package com.bank.digitalwallet.service;

import com.bank.digitalwallet.entity.User;
import com.bank.digitalwallet.entity.Wallet;
import com.bank.digitalwallet.repository.UserRepository;
import com.bank.digitalwallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Wallet withdrawMoney(Long id, Double amount){
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
        if (amount <=0){
            throw new RuntimeException("Amount must be greater than 0");
        }
        if (wallet.getBalance() < amount){
            throw new RuntimeException("Insufficient Balance");
        }
        wallet.setBalance(wallet.getBalance() - amount);
        return walletRepository.save(wallet);
    }
    @Transactional
    public String transferMoney(Long senderId, Long receiverId, Double amount){
        if (senderId.equals(receiverId)){
            throw new RuntimeException("Sender and receiver must be different");
        }
        if (amount <=0){
            throw new RuntimeException("Amount must be greater than 0");
        }
        Wallet sender = walletRepository.findById(senderId).orElseThrow(() -> new RuntimeException("Sender Wallet not found"));
        Wallet receiver = walletRepository.findById(receiverId).orElseThrow(() -> new RuntimeException("Receiver Wallet not found"));

        if (sender.getBalance() < amount){
            throw new RuntimeException("Insufficient Balance");
        }
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        walletRepository.save(sender);
        walletRepository.save(receiver);
        return "Transaction completed successfully";
    }
}
