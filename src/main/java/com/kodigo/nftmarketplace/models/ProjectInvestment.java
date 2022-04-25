package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "projectinvestment")
public class ProjectInvestment {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investmentid")
    private Integer investmentid;

    @Getter @Setter
    @Column(name = "projectid")
    private Integer projectid;

    @Getter @Setter
    @Column(name = "currentinvestment")
    private Double currentinvestment;

    @Getter @Setter
    @Column(name = "pendinginvestment")
    private Double pendinginvestment;

    @Getter @Setter
    @Column(name = "totalinvestment")
    private Double totalinvestment;
}
