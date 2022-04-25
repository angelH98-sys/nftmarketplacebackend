package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
    boolean existsByUsername(String UserName);
    boolean existsByEmail(String Email);
    Users findByUsername(String userName);
}
