package com.banking.banking.Cotroller;


import com.banking.banking.Model.Account;
import com.banking.banking.Model.User;
import com.banking.banking.Service.AccountService;
import com.banking.banking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        Optional<Account> account = accountService.getAccountById(accountId);
        if (account.isPresent() && account.get().getUser().getUserId().equals(user.getUserId())) {
            return ResponseEntity.ok(account.get());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
