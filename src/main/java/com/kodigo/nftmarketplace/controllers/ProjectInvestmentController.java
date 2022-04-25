package com.kodigo.nftmarketplace.controllers;

import com.kodigo.nftmarketplace.dtos.investment.InvestmentCreationDTO;
import com.kodigo.nftmarketplace.models.Blockchain;
import com.kodigo.nftmarketplace.models.NativeCurrency;
import com.kodigo.nftmarketplace.models.ProjectCurrency;
import com.kodigo.nftmarketplace.models.ProjectInvestment;
import com.kodigo.nftmarketplace.services.*;
import com.kodigo.nftmarketplace.utilities.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects/investment")
public class ProjectInvestmentController {

    @Autowired
    ProjectInvestmentService investmentService;

    @Autowired
    ProjectsService projectsService;

    @Autowired
    ProjectCurrencyService currencyService;

    @Autowired
    NativeCurrencyService nativeCurrencyService;

    @Autowired
    BlockchainService blockchainService;

    @PostMapping("/create")
    public ResponseEntity createInvestment(@Valid @RequestBody InvestmentCreationDTO investmentCreationDTO){

        try{

            if(!projectsService.existsById(investmentCreationDTO.getProjectid()))
                return ResponseEntity.badRequest().body("Projectid invalid");

            double totalInvestment = investmentCreationDTO.getCurrentinvestment();
            totalInvestment += investmentCreationDTO.getPendinginvestment();

            if(investmentCreationDTO.getTotalinvestment() != totalInvestment)
                return ResponseEntity.badRequest().body("Investment values invalid");

            //create NativeCurrency if doesnt exist, or get nativecurrencyid
            NativeCurrency nativeCurrency = Parser.investmentCreationDTOToNativeCurrency(investmentCreationDTO);

            if(nativeCurrencyService.existCurrencyName(nativeCurrency.getCurrencyname()))
                nativeCurrency = nativeCurrencyService.getByCurrencyname(nativeCurrency.getCurrencyname());
            else
                nativeCurrency = nativeCurrencyService.createNativeCurrency(nativeCurrency);


            //create Blockchain if doesnt exist, or get blockchainid
            Blockchain blockchain = Parser.investmentCreationDTOToBlockchain(investmentCreationDTO);

            if(blockchainService.existsByBlockchainname(blockchain.getBlockchainame()))
                blockchain = blockchainService.findByBlockchainame(blockchain.getBlockchainame());
            else
                blockchain = blockchainService.createBlockchain(blockchain);

            //create projectinvestment
            ProjectInvestment investment = Parser.investmentCreationDTOToProjectInvestment(investmentCreationDTO);

            investment = investmentService.createProjectInvesment(investment);

            ProjectCurrency currency = new ProjectCurrency();

            currency.setBlockchainid(blockchain.getBlockchainid());
            currency.setInvestmentid(investment.getInvestmentid());

            currency.setNativecurrencyid(nativeCurrency.getNativecurrencyid());

            currencyService.createProjectCurrency(currency);

            return ResponseEntity.ok().body("");

        }catch (Exception ex){

            return ResponseEntity.internalServerError().body("");
        }
    }

}
