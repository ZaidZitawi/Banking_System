package com.banking.banking.Repo;

import com.banking.banking.Model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
