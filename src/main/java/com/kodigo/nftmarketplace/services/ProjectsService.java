package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.Projects;
import com.kodigo.nftmarketplace.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {

    @Autowired
    ProjectsRepository projectsRepository;

    public Projects createProject(Projects project){
        return projectsRepository.save(project);
    }

    public boolean existsById(int projectId){
        return projectsRepository.existsById(projectId);
    }

    public List<Projects> findAll(){
        return (List<Projects>) projectsRepository.findAll();
    }

    public Projects findById(int id){

        Optional<Projects> optional = projectsRepository.findById(id);

        if(optional.isEmpty())
            return null;
        else
            return optional.get();
    }

}
