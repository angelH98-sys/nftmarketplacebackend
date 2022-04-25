package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.NativeCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NativeCurrencyRepository extends CrudRepository<NativeCurrency, Integer> {

    boolean existsByCurrencyname(String currencyName);
    NativeCurrency findByCurrencyname(String currencyName);
}
