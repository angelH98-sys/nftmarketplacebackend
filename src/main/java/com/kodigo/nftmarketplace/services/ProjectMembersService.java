package com.kodigo.nftmarketplace.services;

import com.kodigo.nftmarketplace.models.ProjectMembers;
import com.kodigo.nftmarketplace.repositories.ProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMembersService {

    @Autowired
    ProjectMemberRepository projectMemberRepository;

    public List<ProjectMembers> findAllByProjectid(int projectId){

        Iterable<ProjectMembers> iterable = projectMemberRepository.findAllByProjectid(projectId);

        return (List<ProjectMembers>) iterable;

    }
}
