package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.UserConstraints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConstraintsRepository extends CrudRepository<UserConstraints, Integer> {
    UserConstraints findByUserid(int userid);
}
