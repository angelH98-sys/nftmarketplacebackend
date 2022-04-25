package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.ProjectInvestment;
import com.kodigo.nftmarketplace.repositories.ProjectInvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectInvestmentService {

    @Autowired
    ProjectInvestmentRepository projectInvestmentRepository;

    public ProjectInvestment createProjectInvesment(ProjectInvestment projectInvestment){
        return projectInvestmentRepository.save(projectInvestment);
    }

    public List<ProjectInvestment> findAll(){
        return (List<ProjectInvestment>) projectInvestmentRepository.findAll();
    }

    public ProjectInvestment findByProjectid(int projectId){
        Optional<ProjectInvestment> optional = projectInvestmentRepository.findByProjectid(projectId);

        if(optional.isEmpty())
            return null;
        else
            return optional.get();
    }
}
