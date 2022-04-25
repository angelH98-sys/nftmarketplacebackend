package com.kodigo.nftmarketplace.utilities;

import com.kodigo.nftmarketplace.dtos.investment.InvestmentCreationDTO;
import com.kodigo.nftmarketplace.dtos.media.MediaCreationDTO;
import com.kodigo.nftmarketplace.dtos.projects.EmptyProjectCreationDTO;
import com.kodigo.nftmarketplace.dtos.projects.ProjectCompleteDTO;
import com.kodigo.nftmarketplace.dtos.projects.ProjectsCreationDTO;
import com.kodigo.nftmarketplace.dtos.specifications.SpecificationCreationDTO;
import com.kodigo.nftmarketplace.dtos.users.UsersCreationDTO;
import com.kodigo.nftmarketplace.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Parser {

    public static Users userCreationDTOToUsers(UsersCreationDTO usersCreationDTO){
        Users user = new Users();
        user.setFullname(usersCreationDTO.getFullname());
        user.setUsername(usersCreationDTO.getUsername());
        user.setPassword(usersCreationDTO.getPassword());
        user.setEmail(usersCreationDTO.getEmail());

        return user;
    }

    public static Projects projectCreationDTOToProjects(ProjectsCreationDTO projectsCreationDTO){

        Projects project = new Projects();

        project.setUserid(projectsCreationDTO.getUserid());

        return project;
    }

    public static Projects emptyProjectCreationDTOToProjects(EmptyProjectCreationDTO emptyProjectCreationDTO){

        Projects project = new Projects();

        project.setUserid(emptyProjectCreationDTO.getUserid());

        return project;
    }

    public static ProjectSpecifications projectCreationDTOToProjectSpecification(ProjectsCreationDTO projectsCreationDTO){

        ProjectSpecifications specifications = new ProjectSpecifications();

        specifications.setProjectname(projectsCreationDTO.getProjectname());
        specifications.setProjectdescription(projectsCreationDTO.getProjectdescription());

        return specifications;
    }

    public static SpecificationCreationDTO projectCreationDTOToSpecificationCreationDTO(ProjectsCreationDTO projectsCreationDTO){

        SpecificationCreationDTO specificationCreationDTO = new SpecificationCreationDTO();

        specificationCreationDTO.setProjectname(projectsCreationDTO.getProjectname());
        specificationCreationDTO.setProjectdescription(projectsCreationDTO.getProjectdescription());

        return specificationCreationDTO;
    }

    public static ProjectSpecifications specificationCreationDTOToProjectSpecifications(SpecificationCreationDTO specificationCreationDTO){

        ProjectSpecifications specifications = new ProjectSpecifications();

        specifications.setProjectid(specificationCreationDTO.getProjectid());
        specifications.setProjectdescription(specificationCreationDTO.getProjectdescription());
        specifications.setProjectname(specificationCreationDTO.getProjectname());

        return specifications;
    }

    public static ProjectMedia projectCreationDTOToProjectMedia(ProjectsCreationDTO projectsCreationDTO){

        ProjectMedia media = new ProjectMedia();

        media.setUrlimage(projectsCreationDTO.getUrlimage());
        media.setUrlvideo(projectsCreationDTO.getUrlvideo());
        media.setUrlwebsite(projectsCreationDTO.getUrlwebsite());

        return media;
    }

    public static ProjectInvestment projectCreationDTOToProjectInvestment(ProjectsCreationDTO projectsCreationDTO){

        ProjectInvestment projectInvestment = new ProjectInvestment();

        projectInvestment.setCurrentinvestment(projectsCreationDTO.getCurrentinvestment());
        projectInvestment.setPendinginvestment(projectsCreationDTO.getPendinginvestment());
        projectInvestment.setTotalinvestment(projectsCreationDTO.getTotalinvestment());

        return projectInvestment;
    }

    public static NativeCurrency projectCreationDTOToNativeCurrency(ProjectsCreationDTO projectsCreationDTO){

        NativeCurrency nativeCurrency = new NativeCurrency();

        nativeCurrency.setCurrencyname(projectsCreationDTO.getNativecurrency());

        return nativeCurrency;
    }

    public static Blockchain projectCreationDTOToBlockchain(ProjectsCreationDTO projectsCreationDTO){

        Blockchain blockchain = new Blockchain();

        blockchain.setBlockchainame(projectsCreationDTO.getBlockchainame());

        return blockchain;
    }

    public static ProjectDates projectCreationDTOToProjectDates(ProjectsCreationDTO projectsCreationDTO){

        ProjectDates projectDates = new ProjectDates();

        projectDates.setCreationdate(projectsCreationDTO.getCreationdate());
        projectDates.setReleasedate(projectsCreationDTO.getReleasedate());
        projectDates.setTestingdate(projectsCreationDTO.getTestingdate());

        return projectDates;
    }

    public static ProjectMedia mediaCreationDTOToProjectMedia(MediaCreationDTO mediaCreationDTO){

        ProjectMedia media = new ProjectMedia();

        media.setSpecificationid(mediaCreationDTO.getSpecificationid());
        media.setUrlvideo(mediaCreationDTO.getUrlvideo());
        media.setUrlimage(mediaCreationDTO.getUrlimage());
        media.setUrlwebsite(mediaCreationDTO.getUrlwebsite());

        return media;

    }

    public static ProjectInvestment investmentCreationDTOToProjectInvestment(InvestmentCreationDTO investmentCreationDTO){

        ProjectInvestment investment = new ProjectInvestment();

        investment.setProjectid(investmentCreationDTO.getProjectid());
        investment.setPendinginvestment(investmentCreationDTO.getPendinginvestment());
        investment.setCurrentinvestment(investmentCreationDTO.getCurrentinvestment());
        investment.setTotalinvestment(investmentCreationDTO.getTotalinvestment());

        return investment;
    }

    public static NativeCurrency investmentCreationDTOToNativeCurrency(InvestmentCreationDTO investmentCreationDTO){

        NativeCurrency currency = new NativeCurrency();

        currency.setCurrencyname(investmentCreationDTO.getNativecurrency());

        return currency;
    }

    public static Blockchain investmentCreationDTOToBlockchain(InvestmentCreationDTO investmentCreationDTO){

        Blockchain blockchain = new Blockchain();

        blockchain.setBlockchainame(investmentCreationDTO.getBlockchainame());

        return blockchain;
    }

    public static List<ProjectCompleteDTO> toProjectCompleteDTO(HashMap<String, Object> db){

        List<Projects> projects = (List<Projects>) db.get("projects");
        List<ProjectSpecifications> specifications = (List<ProjectSpecifications>) db.get("specifications");
        List<ProjectMedia> medias = (List<ProjectMedia>) db.get("medias");
        List<ProjectInvestment> investments = (List<ProjectInvestment>) db.get("investments");
        List<ProjectCurrency> currencies = (List<ProjectCurrency>) db.get("currencies");
        List<NativeCurrency> nativesCurrencies = (List<NativeCurrency>) db.get("nativeCurrencies");
        List<Blockchain> blockchains = (List<Blockchain>) db.get("blockchains");
        List<ProjectDates> dates = (List<ProjectDates>) db.get("dates");

        List<ProjectCompleteDTO> projectCompletes = new ArrayList<>();
        ProjectCompleteDTO projectCompleteDTO = new ProjectCompleteDTO();

        ProjectSpecifications specification;
        ProjectMedia media;
        ProjectInvestment investment;
        ProjectCurrency currency;
        NativeCurrency nativeCurrency;
        Blockchain blockchain;
        ProjectDates date;

        Optional optional;

        for(Projects p : projects){

            optional = specifications.stream()
                    .filter(s -> s.getProjectid() == p.getProjectid())
                    .findFirst();

            if(!optional.isEmpty())
                specification = (ProjectSpecifications) optional.get();
            else
                specification = new ProjectSpecifications();

            ProjectSpecifications finalSpecification = specification;
            optional = medias.stream()
                    .filter(m -> m.getSpecificationid() == finalSpecification.getSpecificationid())
                    .findFirst();

            if(!optional.isEmpty())
                media = (ProjectMedia) optional.get();
            else
                media = new ProjectMedia();

            optional = investments.stream()
                    .filter(i -> i.getProjectid() == p.getProjectid())
                    .findFirst();

            if(!optional.isEmpty())
                investment = (ProjectInvestment) optional.get();
            else
                investment = new ProjectInvestment();

            ProjectInvestment finalInvestment = investment;
            optional = currencies.stream()
                    .filter(c -> c.getInvestmentid() == finalInvestment.getInvestmentid())
                    .findFirst();

            if(!optional.isEmpty())
                currency = (ProjectCurrency) optional.get();
            else
                currency = new ProjectCurrency();

            ProjectCurrency finalCurrency = currency;
            optional = nativesCurrencies.stream()
                    .filter(n -> n.getNativecurrencyid() == finalCurrency.getNativecurrencyid())
                    .findFirst();

            if(!optional.isEmpty())
                nativeCurrency = (NativeCurrency) optional.get();
            else
                nativeCurrency = new NativeCurrency();

            optional = blockchains.stream()
                    .filter(b -> b.getBlockchainid() == finalCurrency.getBlockchainid())
                    .findFirst();

            if(!optional.isEmpty())
                blockchain = (Blockchain) optional.get();
            else
                blockchain = new Blockchain();

            optional = dates.stream()
                    .filter(d -> d.getProjectid() == p.getProjectid())
                    .findFirst();

            if(!optional.isEmpty())
                date = (ProjectDates) optional.get();
            else
                date = new ProjectDates();

            projectCompleteDTO = new ProjectCompleteDTO();

            projectCompleteDTO.setProjectid(p.getProjectid());
            projectCompleteDTO.setUserid(p.getUserid());
            projectCompleteDTO.setProjectname(specification.getProjectname());
            projectCompleteDTO.setProjectdescription(specification.getProjectdescription());
            projectCompleteDTO.setProjectstatus(specification.getProjectstatus());
            projectCompleteDTO.setUrlwebsite(media.getUrlwebsite());
            projectCompleteDTO.setUrlimage(media.getUrlimage());
            projectCompleteDTO.setUrlvideo(media.getUrlvideo());
            projectCompleteDTO.setCurrentinvestment(investment.getCurrentinvestment());
            projectCompleteDTO.setPendinginvestment(investment.getPendinginvestment());
            projectCompleteDTO.setTotalinvestment(investment.getTotalinvestment());
            projectCompleteDTO.setNativecurrency(nativeCurrency.getCurrencyname());
            projectCompleteDTO.setBlockchain(blockchain.getBlockchainame());
            projectCompleteDTO.setCreationdate(date.getCreationdate());
            projectCompleteDTO.setTestingdate(date.getTestingdate());
            projectCompleteDTO.setReleasedate(date.getReleasedate());

            projectCompletes.add(projectCompleteDTO);

        }

        return projectCompletes;

    }

}
