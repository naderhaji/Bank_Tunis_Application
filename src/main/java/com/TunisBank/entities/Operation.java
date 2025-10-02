package com.TunisBank.entities;

import com.TunisBank.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private Date dateOperation;
    @Column(nullable = false)
    private String numOperation;
    @ManyToOne
    private CompteBancaire compte;
    @Column(nullable = false)
    private TypeOperation typeOperation;


    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCompte(CompteBancaire compte) {
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getNumOperation() {
        return numOperation;
    }

    public void setNumOperation(String numOperation) {
        this.numOperation = numOperation;
    }



}
