package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.Projects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends CrudRepository<Projects, Integer> {
}
