package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "projectcurrency")
public class ProjectCurrency {

    @Id
    @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currencyid")
    private Integer currencyid;

    @Setter @Getter
    @Column(name = "investmentid")
    private Integer investmentid;

    @Setter @Getter
    @Column(name = "nativecurrencyid")
    private Integer nativecurrencyid;

    @Setter @Getter
    @Column(name = "blockchainid")
    private Integer blockchainid;
}
