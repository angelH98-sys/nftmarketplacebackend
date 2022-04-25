package com.kodigo.nftmarketplace.services;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.kodigo.nftmarketplace.models.ProjectDates;
import com.kodigo.nftmarketplace.repositories.ProjectDatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectDatesService {

    @Autowired
    ProjectDatesRepository projectDatesRepository;

    public ProjectDates createProjectDates(ProjectDates projectDates){
        return projectDatesRepository.save(projectDates);
    }

    public List<ProjectDates> findAll(){
        return (List<ProjectDates>) projectDatesRepository.findAll();
    }

    public ProjectDates findByProjectid(int projectId){

        Optional<ProjectDates> option = projectDatesRepository.findByProjectid(projectId);

        if(option.isEmpty())
            return null;
        else
            return option.get();

    }
}
