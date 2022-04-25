package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.ProjectSpecifications;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectSpecificationRepository extends CrudRepository<ProjectSpecifications, Integer> {

    boolean existsByProjectname(String projectName);
    Optional<ProjectSpecifications> findByProjectid(int projectid);

}
