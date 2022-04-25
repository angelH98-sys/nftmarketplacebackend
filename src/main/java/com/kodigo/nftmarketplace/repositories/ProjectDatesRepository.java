package com.kodigo.nftmarketplace.repositories;

import com.kodigo.nftmarketplace.models.ProjectDates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ProjectDatesRepository extends CrudRepository<ProjectDates, Integer> {

    Optional<ProjectDates> findByProjectid(int projectId);
}
