package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.repositories.UserSubscriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionsService {

    @Autowired
    UserSubscriptionsRepository userSubscriptionsRepository;
}
