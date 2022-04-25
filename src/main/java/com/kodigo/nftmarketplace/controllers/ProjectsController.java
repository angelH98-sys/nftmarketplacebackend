package com.kodigo.nftmarketplace.controllers;


import com.kodigo.nftmarketplace.dtos.investment.InvestmentCreationDTO;
import com.kodigo.nftmarketplace.dtos.projects.EmptyProjectCreationDTO;
import com.kodigo.nftmarketplace.dtos.projects.ProjectCompleteDTO;
import com.kodigo.nftmarketplace.dtos.projects.ProjectsCreationDTO;
import com.kodigo.nftmarketplace.models.*;
import com.kodigo.nftmarketplace.services.*;
import com.kodigo.nftmarketplace.utilities.JwtUtil;
import com.kodigo.nftmarketplace.utilities.Parser;
import com.kodigo.nftmarketplace.utilities.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.annotation.Native;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectsController {

    @Autowired
    ProjectsService projectsService;

    @Autowired
    ProjectSpecificationsService specificationsService;

    @Autowired
    ProjectMediaService projectMediaService;

    @Autowired
    ProjectInvestmentService projectInvestmentService;

    @Autowired
    NativeCurrencyService nativeCurrencyService;

    @Autowired
    BlockchainService blockchainService;

    @Autowired
    ProjectCurrencyService projectCurrencyService;

    @Autowired
    ProjectDatesService projectDatesService;

    @Autowired
    UsersService usersService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity createCompleteProject(@RequestHeader("token") String jwt, @Valid @RequestBody ProjectsCreationDTO projectsCreationDTO){
        /*
        * This method executes creation methods for tables:
        * -> Projects
        * -> ProjectSpecifications
        * -> ProjectMedia
        * -> ProjectInvestment
        * -> ProjectCurrency
        * -> NativeCurrency(just if is a new one)
        * -> Blockchain(just if is a new one)
        * -> ProjectDates
        * */
        try{

            //if(jwtUtil.isTokenExpired(jwt))
              //  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token expired");

            if(!jwtUtil.validateToken(jwt, String.valueOf(projectsCreationDTO.getUserid())))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");

            //userid shold be real and Investor type
            if(!usersService.isUserTypeInvestor(projectsCreationDTO.getUserid()))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Userid is invalid");

            //Dates validations: creation date < testingdate < releasedate
            if(projectsCreationDTO.getCreationdate().after(projectsCreationDTO.getTestingdate()))
                return ResponseEntity.badRequest().body("Creation date cannot be after testing date");

            if(projectsCreationDTO.getTestingdate().after(projectsCreationDTO.getReleasedate()))
                return ResponseEntity.badRequest().body("Testing date cannot be after release date");

            //totalinvestment == (currentinvestment + pendinginvestment)
            double totalInvestment = projectsCreationDTO.getCurrentinvestment();
            totalInvestment += projectsCreationDTO.getPendinginvestment();

            if(projectsCreationDTO.getTotalinvestment() != totalInvestment)
                return ResponseEntity.badRequest().body("Investment values invalid");


            //Media data should be a valid URL
            if(!Validators.isValidURL(projectsCreationDTO.getUrlimage()))
                return ResponseEntity.badRequest().body("Image URL invalid");

            if(!Validators.isValidURL(projectsCreationDTO.getUrlvideo()))
                return ResponseEntity.badRequest().body("Video URL invalid");

            if(!Validators.isValidURL(projectsCreationDTO.getUrlwebsite()))
                return ResponseEntity.badRequest().body("Website URL invalid");


            //projectname should be unique
            if(specificationsService.existProjectByProjectname(projectsCreationDTO.getProjectname()))
                return ResponseEntity.badRequest().body("Project name not available");

            //create project, get projectid
            Projects project = Parser.projectCreationDTOToProjects(projectsCreationDTO);

            project = projectsService.createProject(project);

            //create projectspecification, provied projectid
            ProjectSpecifications specifications = Parser.projectCreationDTOToProjectSpecification(projectsCreationDTO);

            specifications.setProjectid(project.getProjectid());

            specifications.setProjectstatus("Active");

            specifications = specificationsService.createProjectSpecification(specifications);

            //create projectmedia, provied specificationid
            ProjectMedia media = Parser.projectCreationDTOToProjectMedia(projectsCreationDTO);

            media.setSpecificationid(specifications.getSpecificationid());

            projectMediaService.createProjectMedia(media);

            //create projectinvestment, provied projectid
            ProjectInvestment investment = Parser.projectCreationDTOToProjectInvestment(projectsCreationDTO);

            investment.setProjectid(project.getProjectid());

            projectInvestmentService.createProjectInvesment(investment);

            //create NativeCurrency if doesnt exist, or get nativecurrencyid
            NativeCurrency nativeCurrency = Parser.projectCreationDTOToNativeCurrency(projectsCreationDTO);

            if(nativeCurrencyService.existCurrencyName(nativeCurrency.getCurrencyname()))
                nativeCurrency = nativeCurrencyService.getByCurrencyname(nativeCurrency.getCurrencyname());
            else
                nativeCurrency = nativeCurrencyService.createNativeCurrency(nativeCurrency);


            //create Blockchain if doesnt exist, or get blockchainid
            Blockchain blockchain = Parser.projectCreationDTOToBlockchain(projectsCreationDTO);

            if(blockchainService.existsByBlockchainname(blockchain.getBlockchainame()))
                blockchain = blockchainService.findByBlockchainame(blockchain.getBlockchainame());
            else
                blockchain = blockchainService.createBlockchain(blockchain);

            //create ProjectCurrency, provied investmentid, nativecurrencyid, blockchainid
            ProjectCurrency currency = new ProjectCurrency();

            currency.setBlockchainid(blockchain.getBlockchainid());
            currency.setInvestmentid(investment.getInvestmentid());

            currency.setNativecurrencyid(nativeCurrency.getNativecurrencyid());

            projectCurrencyService.createProjectCurrency(currency);

            //create ProjectDate, provied projectid
            ProjectDates dates = Parser.projectCreationDTOToProjectDates(projectsCreationDTO);
            dates.setProjectid(project.getProjectid());

            projectDatesService.createProjectDates(dates);

            return ResponseEntity.ok().body(project);
        }catch (Exception ex){

            return ResponseEntity.internalServerError().body(ex);
        }

    }

    @PostMapping("/create/empty")
    public ResponseEntity createEmptyProject(@RequestHeader("token") String jwt, @Valid @RequestBody EmptyProjectCreationDTO emptyProjectCreationDTO){

        try{

            if(jwtUtil.isTokenExpired(jwt))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token expired");

            if(!jwtUtil.validateToken(jwt, String.valueOf(emptyProjectCreationDTO.getUserid())))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");

            //userid shold be real and Investor type
            if(!usersService.isUserTypeInvestor(emptyProjectCreationDTO.getUserid()))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Userid is invalid");

            Projects projects = Parser.emptyProjectCreationDTOToProjects(emptyProjectCreationDTO);

            projects = projectsService.createProject(projects);

            return ResponseEntity.ok().body(projects);

        }catch (Exception ex){

            return ResponseEntity.internalServerError().body("");
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity getAllProjects(){
        try{

            List<Projects> projects = projectsService.findAll();
            List<ProjectSpecifications> specifications = specificationsService.findAll();
            List<ProjectMedia> medias = projectMediaService.findAll();
            List<ProjectInvestment> investments = projectInvestmentService.findAll();
            List<ProjectCurrency> currencies = projectCurrencyService.findAll();
            List<NativeCurrency> nativesCurrencies = nativeCurrencyService.findAll();
            List<Blockchain> blockains = blockchainService.findAll();
            List<ProjectDates> dates = projectDatesService.findAll();

            HashMap<String, Object> db = new HashMap<>();

            db.put("projects", projects);
            db.put("specifications", specifications);
            db.put("medias", medias);
            db.put("investments", investments);
            db.put("currencies", currencies);
            db.put("nativeCurrencies", nativesCurrencies);
            db.put("blockchains", blockains);
            db.put("dates", dates);

            List<ProjectCompleteDTO> projectCompletes = Parser.toProjectCompleteDTO(db);

            return ResponseEntity.ok().body(projectCompletes);

        }catch (Exception ex){

            return ResponseEntity.internalServerError().body(ex);
        }
    }

    @GetMapping("/get")
    public ResponseEntity getProject(@RequestParam(name = "id") int id){

        try{

            Projects project = projectsService.findById(id);

            if(project == null)
                return ResponseEntity.badRequest().body("Project not found");

            ProjectSpecifications specifications = specificationsService.findByProjectid(id);

            if(specifications == null)
                return ResponseEntity.internalServerError().body("Something goes wrong with data...");

            ProjectMedia media = projectMediaService.findBySpecificationId(specifications.getSpecificationid());

            if(media == null)
                return ResponseEntity.internalServerError().body("Something goes wrong with data...");

            ProjectInvestment investment = projectInvestmentService.findByProjectid(id);

            if(investment == null)
                return ResponseEntity.internalServerError().body("Something goes wrong with data...");

            ProjectCurrency currency = projectCurrencyService.findByInvestmentid(investment.getInvestmentid());

            if(currency == null)
                return ResponseEntity.internalServerError().body("Something goes wrong with data...");

            NativeCurrency nativeCurrency = nativeCurrencyService.findById(currency.getNativecurrencyid());

            if(nativeCurrency == null)
                return ResponseEntity.internalServerError().body("Something goes wrong with data...");

            Blockchain blockchain = blockchainService.findById(currency.getBlockchainid());

            if(blockchain == null)
                return ResponseEntity.internalServerError().body("Something goes wrong with data...");

            ProjectDates dates = projectDatesService.findByProjectid(project.getProjectid());

            if(dates == null)
                return ResponseEntity.internalServerError().body("Something goes wrong with data...");

            ProjectCompleteDTO projectCompleteDTO = new ProjectCompleteDTO(
                    project, specifications, media,investment, blockchain, nativeCurrency, dates);

            return ResponseEntity.ok().body(projectCompleteDTO);

        }catch (Exception ex){

            return ResponseEntity.internalServerError().body("");
        }
    }

}
