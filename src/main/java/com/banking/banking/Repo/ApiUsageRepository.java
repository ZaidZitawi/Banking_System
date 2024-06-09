package com.banking.banking.Repo;


import com.banking.banking.Model.ApiUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUsageRepository extends JpaRepository<ApiUsage, Long> {
}
