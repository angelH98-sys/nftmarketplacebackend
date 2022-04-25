package com.kodigo.nftmarketplace.dtos.specifications;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SpecificationCreationDTO {

    @Getter @Setter
    @NotNull(message = "Projectid is required")
    private Integer projectid;

    @Getter @Setter
    @NotBlank(message = "Projectname is required")
    private String projectname;

    @Getter @Setter
    private String projectdescription;
}
