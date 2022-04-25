package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.ProjectCurrency;
import com.kodigo.nftmarketplace.repositories.ProjectCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectCurrencyService {

    @Autowired
    ProjectCurrencyRepository projectCurrencyRepository;

    public ProjectCurrency createProjectCurrency(ProjectCurrency projectCurrency){
        return projectCurrencyRepository.save(projectCurrency);
    }

    public List<ProjectCurrency> findAll(){
        return (List<ProjectCurrency>) projectCurrencyRepository.findAll();
    }

    public ProjectCurrency findByInvestmentid(int investmentId){

        Optional<ProjectCurrency> optional = projectCurrencyRepository.findByInvestmentid(investmentId);

        if(optional.isEmpty())
            return null;
        else
            return optional.get();
    }
}
