package com.TunisBank.services;

import com.TunisBank.dto.CompteDto;
import com.TunisBank.entities.CompteBancaire;
import com.TunisBank.entities.CompteCourant;
import com.TunisBank.entities.CompteEpargne;

import java.util.List;

public interface CompteService {

    void createAccount(CompteDto compteDto);

    List<CompteEpargne> findComptesEpargne();
    List<CompteCourant> findComptesCourant();

    CompteBancaire findOne(String numCompte);
}
