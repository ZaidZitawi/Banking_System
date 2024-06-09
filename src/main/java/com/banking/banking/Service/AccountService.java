package com.banking.banking.Service;


import com.banking.banking.Model.Account;
import com.banking.banking.Repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
