package com.TunisBank.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {

    private long compteID;
    private double amount;
    private String numCompteSource;
    private String numCompteDestination;

    public String getNumCompteSource() {
        return numCompteSource;
    }

    public OperationDto(String numCompteSource, String numCompteDestination, double amount) {
        this.numCompteSource = numCompteSource;
        this.numCompteDestination = numCompteDestination;
        this.amount = amount;
    }

    public void setNumCompteSource(String numCompteSource) {
        this.numCompteSource = numCompteSource;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNumCompteDestination() {
        return numCompteDestination;
    }

}
