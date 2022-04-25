package com.kodigo.nftmarketplace.controllers;

import com.kodigo.nftmarketplace.dtos.specifications.SpecificationCreationDTO;
import com.kodigo.nftmarketplace.models.ProjectSpecifications;
import com.kodigo.nftmarketplace.services.ProjectSpecificationsService;
import com.kodigo.nftmarketplace.utilities.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects/specification")
public class SpecificationController {

    @Autowired
    ProjectSpecificationsService specificationsService;

    @PostMapping("/create")
    public ResponseEntity createSpecification(@Valid @RequestBody SpecificationCreationDTO specificationCreationDTO){

        try{

            if(specificationsService.existProjectByProjectname(specificationCreationDTO.getProjectname()))
                return ResponseEntity.badRequest().body("Project name not available");

            ProjectSpecifications specifications = Parser.specificationCreationDTOToProjectSpecifications(specificationCreationDTO);

            specifications.setProjectstatus("Active");

            specifications = specificationsService.createProjectSpecification(specifications);

            return ResponseEntity.ok().body(specifications);
        }catch(Exception es){

            return ResponseEntity.internalServerError().body(new SpecificationCreationDTO());
        }
    }
}
