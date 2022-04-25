package com.kodigo.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usersubscriptions")
public class UserSubscriptions {

    @Setter @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriptionid")
    private Integer subscriptionid;

    @Setter @Getter
    @Column(name = "userid")
    private Integer userid;

    @Setter @Getter
    @Column(name = "projectid")
    private Integer projectid;

    @Setter @Getter
    @Column(name = "subscriptionstatus")
    private String subscriptionstatus;
}
