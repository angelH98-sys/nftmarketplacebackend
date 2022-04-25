package com.kodigo.nftmarketplace.dtos.investment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InvestmentCreationDTO {

    @Getter @Setter
    @NotNull(message = "Projectid is required")
    private Integer projectid;

    @Getter @Setter
    @NotNull(message = "Current investment is required")
    @Min(value = 0, message = "Current investment not be less than 0")
    private Double currentinvestment;

    @Getter @Setter
    @NotNull(message = "Pending investment is required")
    @Min(value = 0, message = "Pending investment not be less than 0")
    private Double pendinginvestment;

    @Getter @Setter
    @NotNull(message = "Total investment is required")
    @Min(value = 0, message = "Total investment not be less than 0")
    private Double totalinvestment;

    @Setter @Getter
    @NotBlank(message = "Native currency is required")
    private String nativecurrency;
    @Setter @Getter
    @NotBlank(message = "Blockchain is required")
    private String blockchainame;
}
