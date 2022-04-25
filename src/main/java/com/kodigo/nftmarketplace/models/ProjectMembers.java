package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "projectmembers")
public class ProjectMembers {

    @Setter @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberid")
    private Integer memberid;

    @Setter @Getter
    @Column(name = "projectid")
    private Integer projectid;

    @Setter @Getter
    @Column(name = "membername")
    private String membername;

    @Setter @Getter
    @Column(name = "urlprofile")
    private String urlprofile;

    @Setter @Getter
    @Column(name = "memberstatus")
    private String memberstatus;
}
