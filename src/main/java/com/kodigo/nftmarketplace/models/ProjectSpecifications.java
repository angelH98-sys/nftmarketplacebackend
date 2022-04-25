package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "projectspecifications")
public class ProjectSpecifications {

    @Setter @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specificationid")
    private Integer specificationid;

    @Getter @Setter
    @Column(name = "projectid")
    private Integer projectid;

    @Getter @Setter
    @Column(name = "projectname")
    private String projectname;

    @Getter @Setter
    @Column(name = "projectdescription")
    private String projectdescription;

    @Getter @Setter
    @Column(name = "projectstatus")
    private String projectstatus;
}
