package com.banking.banking.Service;



import com.banking.banking.Model.Transaction;
import com.banking.banking.Repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
