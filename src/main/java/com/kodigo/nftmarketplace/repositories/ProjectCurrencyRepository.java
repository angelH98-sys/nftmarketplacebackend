package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.ProjectCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectCurrencyRepository extends CrudRepository<ProjectCurrency, Integer> {
    Optional<ProjectCurrency> findByInvestmentid(int investmentId);
}
