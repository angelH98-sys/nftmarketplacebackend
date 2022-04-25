package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.ProjectMembers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends CrudRepository<ProjectMembers, Integer> {
    Iterable<ProjectMembers> findAllByProjectid(int projectId);
}
