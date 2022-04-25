package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.Users;
import com.kodigo.nftmarketplace.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public ArrayList<Users> getUsers(){
        return (ArrayList<Users>) usersRepository.findAll();
    }

    public Users getByUsername(String userName){

        return usersRepository.findByUsername(userName);
    }

    public Users createUser(Users user){
        return usersRepository.save(user);
    }

    public boolean existsByUsername(String userName){
        return  usersRepository.existsByUsername(userName);
    }

    public boolean existsByEmail(String email){
        return  usersRepository.existsByEmail(email);
    }

    public boolean isUserTypeInvestor(int id){

        try{

            Users user = usersRepository.findById(id).get();

            if(user.getUsertype().equals("Investor")) return true;

            return false;
        }catch (Exception ex){
            return false;
        }
    }
}
