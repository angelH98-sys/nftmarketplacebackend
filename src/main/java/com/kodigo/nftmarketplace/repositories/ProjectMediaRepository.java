package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.ProjectMedia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectMediaRepository extends CrudRepository<ProjectMedia, Integer> {
    Optional<ProjectMedia> findBySpecificationid(int specificationId);
}
