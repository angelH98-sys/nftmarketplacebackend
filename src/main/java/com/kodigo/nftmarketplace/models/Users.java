package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "userid")
    private Integer userid;
    @Getter @Setter
    @Column(name = "username")
    private String username;
    @Getter @Setter
    @Column(name = "password")
    private String password;
    @Getter @Setter
    @Column(name = "fullname")
    private String fullname;
    @Getter @Setter
    @Column(name = "email")
    private String email;
    @Getter @Setter
    @Column(name = "userstatus")
    private String userstatus;
    @Getter @Setter
    @Column(name = "usertype")
    private String usertype;
}
