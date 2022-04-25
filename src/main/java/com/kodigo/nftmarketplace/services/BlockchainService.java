package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.Blockchain;
import com.kodigo.nftmarketplace.repositories.BlockchainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockchainService {

    @Autowired
    BlockchainRepository blockchainRepository;

    public boolean existsByBlockchainname(String blockchainName){
        return blockchainRepository.existsByBlockchainame(blockchainName);
    }

    public Blockchain findByBlockchainame(String blockchainName){
        return  blockchainRepository.findByBlockchainame(blockchainName);
    }

    public Blockchain createBlockchain(Blockchain blockchain){
        return blockchainRepository.save(blockchain);
    }

    public List<Blockchain> findAll(){
        return (List<Blockchain>) blockchainRepository.findAll();
    }

    public Blockchain findById(int id){

        Optional<Blockchain> optional = blockchainRepository.findById(id);

        if(optional.isEmpty())
            return null;
        else
            return optional.get();
    }
}
