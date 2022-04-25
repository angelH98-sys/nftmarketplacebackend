package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "nativecurrency")
public class NativeCurrency {

    @Setter @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nativecurrencyid")
    private Integer nativecurrencyid;

    @Setter @Getter
    @Column(name = "currencyname")
    private String currencyname;
}
