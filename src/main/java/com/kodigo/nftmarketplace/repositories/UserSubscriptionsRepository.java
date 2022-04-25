package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.UserSubscriptions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionsRepository extends CrudRepository<UserSubscriptions, Integer> {
}
