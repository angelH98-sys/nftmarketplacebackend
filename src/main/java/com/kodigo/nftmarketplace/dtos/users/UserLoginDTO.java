package com.kodigo.nftmarketplace.dtos.users;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class UserLoginDTO {

    @Getter @Setter
    @NotBlank(message = "Username is required")
    private String username;

    @Getter @Setter
    @NotBlank(message = "Password is required")
    private String password;
}
