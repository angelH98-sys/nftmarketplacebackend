package com.kodigo.nftmarketplace.dtos.media;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MediaCreationDTO {

    @Setter @Getter
    @NotNull(message = "Specificationid is required")
    private Integer specificationid;

    @Setter @Getter
    @NotBlank(message = "URLWebsite is required")
    private String urlwebsite;

    @Setter @Getter
    @NotBlank(message = "URLImage is required")
    private String urlimage;

    @Setter @Getter
    @NotBlank(message = "URLVideo is required")
    private String urlvideo;
}
