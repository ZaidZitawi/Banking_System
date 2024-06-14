package com.banking.banking.Service;

import com.banking.banking.DTO.AccountDTO;
import com.banking.banking.Model.Account;
import com.banking.banking.Model.User;
import com.banking.banking.Repo.AccountRepository;
import com.banking.banking.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account getAccountById(int accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    public Account createAccount(AccountDTO accountDTO) {
        //check if the user exists
        Account account = new Account();
        User owner = userRepository.findById(accountDTO.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        //create the account
        account.setOwner(owner);
        account.setBalance(accountDTO.getBalance());
        return accountRepository.save(account);
    }

    public Account updateAccount(int accountId, AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        User owner = userRepository.findById(accountDTO.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        account.setOwner(owner);
        account.setBalance(accountDTO.getBalance());
        return accountRepository.save(account);
    }

    public void deleteAccount(int accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        accountRepository.delete(account);
    }
}
