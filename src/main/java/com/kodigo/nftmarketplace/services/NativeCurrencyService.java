package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.NativeCurrency;
import com.kodigo.nftmarketplace.repositories.NativeCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

@Service
public class NativeCurrencyService {

    @Autowired
    NativeCurrencyRepository nativeCurrencyRepository;

    public boolean existCurrencyName(String currencyName){
        return nativeCurrencyRepository.existsByCurrencyname(currencyName);
    }

    public NativeCurrency getByCurrencyname(String currencyName){
        return nativeCurrencyRepository.findByCurrencyname(currencyName);
    }

    public NativeCurrency createNativeCurrency(NativeCurrency nativeCurrency){
        return nativeCurrencyRepository.save(nativeCurrency);
    }

    public List<NativeCurrency> findAll(){

        return (List<NativeCurrency>) nativeCurrencyRepository.findAll();
    }

    public NativeCurrency findById(int id){
        Optional<NativeCurrency> optional = nativeCurrencyRepository.findById(id);

        if(optional.isEmpty())
            return null;
        else
            return optional.get();
    }
}
