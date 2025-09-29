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
    private AccountStatus status;
    private Date createdAt = new Date();
    @ManyToOne
    private Client client;
    @JsonBackReference
    @OneToMany(mappedBy = "compte")
    Collection<Operation> operations = new ArrayDeque<>();





}
