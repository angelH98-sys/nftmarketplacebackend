package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.ProjectInvestment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectInvestmentRepository extends CrudRepository<ProjectInvestment, Integer> {
    Optional<ProjectInvestment> findByProjectid(int projectId);
}
