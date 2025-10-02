package com.TunisBank.entities;

import com.TunisBank.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.DiscriminatorValue;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("1")
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class CompteCourant extends CompteBancaire implements Serializable {

    private double decouvert;

    public static void setStaus(AccountStatus accountStatus) {

    }


    public Double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Double decouvert) {
        this.decouvert = decouvert;
    }


}
