package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usercontraints")
public class UserConstraints {

    @Id
    @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constraintid")
    private int constraintid;

    @Setter @Getter
    @Column(name = "userid")
    private int userid;

    @Setter @Getter
    @Column(name = "constraintname")
    private String constraintname;

    @Setter @Getter
    @Column(name = "constraintvalue")
    private String constraintvalue;

}
