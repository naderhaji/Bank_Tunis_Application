package com.TunisBank.services;

import com.TunisBank.dto.CompteDto;
import com.TunisBank.entities.Client;
import com.TunisBank.entities.CompteBancaire;
import com.TunisBank.entities.CompteCourant;
import com.TunisBank.entities.CompteEpargne;
import com.TunisBank.enums.AccountStatus;
import com.TunisBank.repositories.ClientRepository;
import com.TunisBank.repositories.CompteBancaireRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CompteServiceImpl implements CompteService {

    private final CompteBancaireRepository compteBancaireRepository;
    private final ClientRepository clientRepository;


    CompteServiceImpl(final CompteBancaireRepository compteBancaireRepository, final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.compteBancaireRepository = compteBancaireRepository;

    }




    @Override
    public void createAccount(CompteDto compteDto) {

        Optional<Client> clientOpt= this.clientRepository.findById(compteDto.getClientId());
        if(clientOpt.isPresent() && (compteDto.getDecouvert() > 0 && compteDto.getTauxInteret() == 0)){

            CompteCourant compteCourant = new CompteCourant();
            compteCourant.setCreatedAt(new Date());
            compteCourant.setBalance(compteDto.getBalance());
            compteCourant.setDevis(compteDto.getDevis());
            compteCourant.setDecouvert(compteDto.getDecouvert());
            compteCourant.setClient(clientOpt.get());
            CompteCourant.setStaus(AccountStatus.ACTIVATED);
            this.compteBancaireRepository.save(compteCourant);

        }

        if(clientOpt.isPresent() && (compteDto.getDecouvert() == 0 && compteDto.getTauxInteret() > 0)){

            CompteEpargne compteEpargne = new CompteEpargne();
            compteEpargne.setCreatedAt(new Date());
            compteEpargne.setBalance(compteDto.getBalance());
            compteEpargne.setDevis(compteDto.getDevis());
            compteEpargne.setClient(clientOpt.get());
            compteEpargne.setStaus(AccountStatus.ACTIVATED);
            this.compteBancaireRepository.save(null);

        }

    }

    @Override
    public List<CompteEpargne> findComptesEpargne() {
        return List.of();
    }

    @Override
    public List<CompteCourant> findComptesCourant() {
        return List.of();
    }

    @Override
    public CompteBancaire findOne(String numCompte) {
        return null;
    }
}
