package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "blockchain")
public class Blockchain {

    @Setter @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blockchainid")
    private Integer blockchainid;

    @Setter @Getter
    @Column(name = "blockchainame")
    private String blockchainame;
}
