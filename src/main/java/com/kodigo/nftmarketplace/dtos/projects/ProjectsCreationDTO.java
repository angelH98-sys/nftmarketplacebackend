package com.kodigo.nftmarketplace.dtos.projects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProjectsCreationDTO {

    //Projects
    @Getter @Setter
    @NotNull(message = "Userid cannot be null")
    private Integer userid;

    //ProjectSpecification
    @Getter @Setter
    @NotBlank(message = "Projectname is required")
    private String projectname;
    @Getter @Setter
    private String projectdescription;

    //ProjectMedia
    @Setter @Getter
    @NotBlank(message = "URLWebsite is required")
    private String urlwebsite;
    @Setter @Getter
    @NotBlank(message = "URLImage is required")
    private String urlimage;
    @Setter @Getter
    @NotBlank(message = "URLVideo is required")
    private String urlvideo;

    //ProjectInvestment
    @Getter @Setter
    @NotNull(message = "Current investment cannot be null")
    @Min(value = 0, message = "Current investment not be less than 0")
    private Double currentinvestment;
    @Getter @Setter
    @NotNull(message = "Pending investment cannot be null")
    @Min(value = 0, message = "Pending investment not be less than 0")
    private Double pendinginvestment;
    @Getter @Setter
    @NotNull(message = "Total investment cannot be null")
    @Min(value = 0, message = "Total investment not be less than 0")
    private Double totalinvestment;

    //ProjectCurrency
    @Setter @Getter
    @NotBlank(message = "Native currency is required")
    private String nativecurrency;
    @Setter @Getter
    @NotBlank(message = "Blockchain is required")
    private String blockchainame;

    //ProjectDates
    @Setter @Getter
    @NotNull(message = "Creation date is required")
    private Date creationdate;
    @Setter @Getter
    @NotNull(message = "Testing date is required")
    private Date testingdate;
    @Setter @Getter
    @NotNull(message = "Release date is required")
    private Date releasedate;

}
