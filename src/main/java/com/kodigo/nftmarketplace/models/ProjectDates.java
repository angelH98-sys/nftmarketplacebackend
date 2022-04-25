package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projectdates")
public class ProjectDates {

    @Setter @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectdateid")
    private Integer projectdateid;

    @Setter @Getter
    @Column(name = "projectid")
    private Integer projectid;

    @Setter @Getter
    @Column(name = "creationdate")
    private Date creationdate;

    @Setter @Getter
    @Column(name = "testingdate")
    private Date testingdate;

    @Setter @Getter
    @Column(name = "releasedate")
    private Date releasedate;
}
