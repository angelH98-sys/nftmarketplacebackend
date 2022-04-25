package com.kodigo.nftmarketplace.dtos.projects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class EmptyProjectCreationDTO {

    @Getter @Setter
    @NotNull(message = "Userid cannot be null")
    private Integer userid;
}
