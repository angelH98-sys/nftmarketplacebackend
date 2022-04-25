package com.kodigo.nftmarketplace.dtos.projects;

import lombok.Getter;
import lombok.Setter;

public class ProjectMembersCreationDTO {

    @Setter @Getter
    private Integer projectid;

    @Setter @Getter
    private String membername;

    @Setter @Getter
    private String urlprofile;
}
