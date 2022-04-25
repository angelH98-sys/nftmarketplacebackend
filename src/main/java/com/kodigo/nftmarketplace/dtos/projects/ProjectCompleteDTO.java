package com.kodigo.nftmarketplace.dtos.projects;

import com.kodigo.nftmarketplace.models.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class ProjectCompleteDTO {

    public ProjectCompleteDTO(){}

    public ProjectCompleteDTO(
            Projects project,
            ProjectSpecifications specifications,
            ProjectMedia media,
            ProjectInvestment investment,
            Blockchain blockchain,
            NativeCurrency nativeCurrency,
            ProjectDates dates){

        this.projectid = project.getProjectid();
        this.userid = project.getUserid();
        this.projectname = specifications.getProjectname();
        this.projectdescription = specifications.getProjectdescription();
        this.projectstatus = specifications.getProjectstatus();
        this.urlimage = media.getUrlimage();
        this.urlvideo = media.getUrlvideo();
        this.urlwebsite = media.getUrlwebsite();
        this.pendinginvestment = investment.getPendinginvestment();
        this.currentinvestment = investment.getCurrentinvestment();
        this.totalinvestment = investment.getTotalinvestment();
        this.nativecurrency = nativeCurrency.getCurrencyname();
        this.blockchain = blockchain.getBlockchainame();
        this.creationdate = dates.getCreationdate();
        this.testingdate = dates.getTestingdate();
        this.releasedate = dates.getReleasedate();
    }

    @Setter @Getter
    private Integer projectid;

    @Setter @Getter
    private Integer userid;

    @Getter @Setter
    private String projectname;

    @Getter @Setter
    private String projectdescription;

    @Getter @Setter
    private String projectstatus;

    @Setter @Getter
    private String urlwebsite;

    @Setter @Getter
    private String urlimage;

    @Setter @Getter
    private String urlvideo;

    @Getter @Setter
    private Double currentinvestment;

    @Getter @Setter
    private Double pendinginvestment;

    @Getter @Setter
    private Double totalinvestment;

    @Setter @Getter
    private String nativecurrency;

    @Setter @Getter
    private String blockchain;

    @Setter @Getter
    private Date creationdate;

    @Setter @Getter
    private Date testingdate;

    @Setter @Getter
    private Date releasedate;
}
