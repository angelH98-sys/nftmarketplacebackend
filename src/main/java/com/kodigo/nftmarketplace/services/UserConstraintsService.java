package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.UserConstraints;
import com.kodigo.nftmarketplace.repositories.UserConstraintsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConstraintsService {

    @Autowired
    UserConstraintsRepository constraintsRepository;

    public UserConstraints createConstraint(UserConstraints userConstraints){
        return constraintsRepository.save(userConstraints);
    }

    public UserConstraints getConstraintByUser(int userId){
        return constraintsRepository.findByUserid(userId);
    }
}
