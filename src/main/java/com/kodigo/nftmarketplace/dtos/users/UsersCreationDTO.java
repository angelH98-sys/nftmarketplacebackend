package com.kodigo.nftmarketplace.dtos.users;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

public class UsersCreationDTO {

    @Getter @Setter
    @NotBlank(message = "Username is required")
    private String username;

    @Getter @Setter
    @NotBlank(message = "Fullname is required")
    private String fullname;

    @Getter @Setter
    @NotBlank(message = "Password is required")
    private String password;

    @Getter @Setter
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
}
