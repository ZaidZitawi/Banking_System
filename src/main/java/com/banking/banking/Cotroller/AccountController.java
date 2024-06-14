package com.banking.banking.Cotroller;

import com.banking.banking.DTO.AccountDTO;
import com.banking.banking.Model.Account;
import com.banking.banking.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;


    //Broken Object Level Authorization
    // Vulnerable endpoint
    @GetMapping("/vulnerable/{accountId}")
    public ResponseEntity<AccountDTO> getAccountVulnerable(@PathVariable int accountId) {
        Account account = accountService.getAccountById(accountId);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setOwnerId(account.getOwner().getId());
        accountDTO.setBalance(account.getBalance());
        return ResponseEntity.ok(accountDTO);
    }

    //Broken Object Level Authorization API1
    // Secure endpoint
    @GetMapping("/secure/{accountId}")
    public ResponseEntity<AccountDTO> getAccountSecure(@PathVariable int accountId, Principal principal) throws AccessDeniedException {
        Account account = accountService.getAccountById(accountId);
        if (!account.getOwner().getUsername().equals(principal.getName())) {
            throw new AccessDeniedException("You are not authorized to access this account");
        }
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setOwnerId(account.getOwner().getId());
        accountDTO.setBalance(account.getBalance());
        return ResponseEntity.ok(accountDTO);
    }


    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        Account account = accountService.createAccount(accountDTO);
        AccountDTO responseAccountDTO = new AccountDTO();
        responseAccountDTO.setId(account.getId());
        responseAccountDTO.setOwnerId(account.getOwner().getId());
        responseAccountDTO.setBalance(account.getBalance());
        return ResponseEntity.ok(responseAccountDTO);
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable int accountId, @RequestBody AccountDTO accountDTO) {
        Account account = accountService.updateAccount(accountId, accountDTO);
        AccountDTO responseAccountDTO = new AccountDTO();
        responseAccountDTO.setId(account.getId());
        responseAccountDTO.setOwnerId(account.getOwner().getId());
        responseAccountDTO.setBalance(account.getBalance());
        return ResponseEntity.ok(responseAccountDTO);
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok().build();
    }
}