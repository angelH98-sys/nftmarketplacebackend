package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.ProjectSpecifications;
import com.kodigo.nftmarketplace.models.Projects;
import com.kodigo.nftmarketplace.repositories.ProjectSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectSpecificationsService {

    @Autowired
    ProjectSpecificationRepository specificationRepository;

    public boolean existProjectByProjectname(String projectName){

        return specificationRepository.existsByProjectname(projectName);
    }

    public boolean existsById(int specificationid){

        return specificationRepository.existsById(specificationid);
    }

    public ProjectSpecifications createProjectSpecification(ProjectSpecifications projectSpecifications){

        return specificationRepository.save(projectSpecifications);
    }

    public List<ProjectSpecifications> findAll(){

        return (List<ProjectSpecifications>) specificationRepository.findAll();
    }

    public ProjectSpecifications findByProjectid(int projectId){

        Optional<ProjectSpecifications> optional = specificationRepository.findByProjectid(projectId);

        if(optional.isEmpty())
            return null;
        else
            return optional.get();

    }

}
