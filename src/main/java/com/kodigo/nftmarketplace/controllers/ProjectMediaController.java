package com.kodigo.nftmarketplace.controllers;

import com.kodigo.nftmarketplace.dtos.media.MediaCreationDTO;
import com.kodigo.nftmarketplace.models.ProjectMedia;
import com.kodigo.nftmarketplace.services.ProjectMediaService;
import com.kodigo.nftmarketplace.services.ProjectSpecificationsService;
import com.kodigo.nftmarketplace.utilities.Parser;
import com.kodigo.nftmarketplace.utilities.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects/media")
public class ProjectMediaController {

    @Autowired
    ProjectMediaService projectMediaService;

    @Autowired
    ProjectSpecificationsService specificationsService;

    @PostMapping("/create")
    public ResponseEntity createProjectMedia(@Valid @RequestBody MediaCreationDTO mediaCreationDTO){

        try{

            //Media data should be a valid URL
            if(!Validators.isValidURL(mediaCreationDTO.getUrlimage()))
                    return ResponseEntity.badRequest().body("Image URL invalid");

            if(!Validators.isValidURL(mediaCreationDTO.getUrlvideo()))
                return ResponseEntity.badRequest().body("Video URL invalid");

            if(!Validators.isValidURL(mediaCreationDTO.getUrlwebsite()))
                return ResponseEntity.badRequest().body("Website URL invalid");


            if(!specificationsService.existsById(mediaCreationDTO.getSpecificationid()))
                return ResponseEntity.badRequest().body("Specificationid is invalid");

            ProjectMedia media = Parser.mediaCreationDTOToProjectMedia(mediaCreationDTO);

            projectMediaService.createProjectMedia(media);

            return ResponseEntity.ok().body("");

        }catch (Exception ex){

            return ResponseEntity.internalServerError().body("");
        }
    }
}
