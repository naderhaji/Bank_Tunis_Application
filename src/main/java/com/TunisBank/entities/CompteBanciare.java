package com.TunisBank.entities;
import com.TunisBank.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public abstract class CompteBanciare implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double balance;
    private String numCompte;
    private String devis = "TND";
    private AccountStatus status;
    private Date createdAt = new Date();





}
