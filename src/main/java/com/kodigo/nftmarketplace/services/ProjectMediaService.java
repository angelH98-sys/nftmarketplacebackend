package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.ProjectMedia;
import com.kodigo.nftmarketplace.repositories.ProjectMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectMediaService {

    @Autowired
    ProjectMediaRepository projectMediaRepository;

    public ProjectMedia createProjectMedia(ProjectMedia projectMedia){
        return projectMediaRepository.save(projectMedia);
    }

    public List<ProjectMedia> findAll(){
        return (List<ProjectMedia>) projectMediaRepository.findAll();
    }

    public ProjectMedia findBySpecificationId(int specificationId){

        Optional<ProjectMedia> optional = projectMediaRepository.findBySpecificationid(specificationId);

        if(optional.isEmpty())
            return null;
        else
            return optional.get();
    }

}
