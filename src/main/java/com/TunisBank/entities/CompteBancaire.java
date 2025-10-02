package com.TunisBank.entities;
import com.TunisBank.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public abstract class CompteBancaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private double balance;
    @Column(nullable = false)
    private String numCompte;
    @Column(nullable = false)
    private String devis = "TND";
    @Column(nullable = false)
    public AccountStatus status;
    private Date createdAt = new Date();
    @ManyToOne
    private Client client;
    @JsonBackReference
    @OneToMany(mappedBy = "compte")
    Collection<Operation> operations = new ArrayDeque<>();


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public String getNumCompte() {
        return numCompte;
    }
    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;

    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;

    }

    public String getDevis() {
        return devis;
    }
    public void setDevis(String devis) {
        this.devis = devis;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }


}
