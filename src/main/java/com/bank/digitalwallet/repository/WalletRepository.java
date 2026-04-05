package com.bank.digitalwallet.repository;

import com.bank.digitalwallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

    //Optional<Wallet> findByUserId(Long userId);

}
