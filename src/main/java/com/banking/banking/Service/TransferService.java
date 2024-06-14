package com.banking.banking.Service;

import com.banking.banking.DTO.TransferDTO;
import com.banking.banking.Model.Account;
import com.banking.banking.Model.Transfer;
import com.banking.banking.Repo.AccountRepository;
import com.banking.banking.Repo.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transfer transfer(TransferDTO transferDTO) {
        Account fromAccount = accountRepository.findById(transferDTO.getFromAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("From account not found"));
        Account toAccount = accountRepository.findById(transferDTO.getToAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("To account not found"));

        if (fromAccount.getBalance() < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance());
        toAccount.setBalance(toAccount.getBalance());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transfer transfer = new Transfer();
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setAmount(transferDTO.getAmount());
        return transferRepository.save(transfer);
    }
}
