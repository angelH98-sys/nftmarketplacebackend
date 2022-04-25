package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "projectmedia")
public class ProjectMedia {

    @Setter @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mediaid")
    private Integer mediaid;

    @Setter @Getter
    @Column(name = "specificationid")
    private Integer specificationid;

    @Setter @Getter
    @Column(name = "urlwebsite")
    private String urlwebsite;

    @Setter @Getter
    @Column(name = "urlimage")
    private String urlimage;

    @Setter @Getter
    @Column(name = "urlvideo")
    private String urlvideo;
}
