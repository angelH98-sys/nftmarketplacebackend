package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.Blockchain;
import com.kodigo.nftmarketplace.models.NativeCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockchainRepository extends CrudRepository<Blockchain, Integer> {

    boolean existsByBlockchainame(String blockchaiName);
    Blockchain findByBlockchainame(String Blockchainame);

}
