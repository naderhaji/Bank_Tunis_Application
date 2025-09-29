package com.TunisBank.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteDto {

    private String devis;
    private double balance;
    private double tauxInteret;
    private double decouvert;
    private Long clientID;


    public Double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Double decouvert) {
        this.decouvert = decouvert;
    }


    public Double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Long getClientId() {
        return clientID;
    }

    public void setClientId(Long clientId) {
        this.clientID = clientId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDevis() {
        return devis;
    }
    public void setDevis(String devis) {
        this.devis = devis;
    }




}
