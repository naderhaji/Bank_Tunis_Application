package com.TunisBank.services;

import com.TunisBank.dto.CompteDto;
import com.TunisBank.entities.Client;
import com.TunisBank.entities.CompteBancaire;
import com.TunisBank.entities.CompteCourant;
import com.TunisBank.entities.CompteEpargne;
import com.TunisBank.enums.AccountStatus;
import com.TunisBank.repositories.ClientRepository;
import com.TunisBank.repositories.CompteBancaireRepository;

import java.util.*;

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
            compteCourant.setNumCompte(generateAccountNumber());
            this.compteBancaireRepository.save(compteCourant);

        }

        if(clientOpt.isPresent() && (compteDto.getDecouvert() == 0 && compteDto.getTauxInteret() > 0)){

            CompteEpargne compteEpargne = new CompteEpargne();
            compteEpargne.setCreatedAt(new Date());
            compteEpargne.setBalance(compteDto.getBalance());
            compteEpargne.setDevis(compteDto.getDevis());
            compteEpargne.setClient(clientOpt.get());
            compteEpargne.setStaus(AccountStatus.ACTIVATED);
            compteEpargne.setTauxInteret(compteDto.getTauxInteret());
            compteEpargne.setNumCompte(generateAccountNumber());
            this.compteBancaireRepository.save(compteEpargne);

        }

    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        //les 4 premiers chiffres sont 0
        for (int i = 0; i < 4; i++) {
            sb.append('0');
        }
        //les 4 chiffres suivants sont 0 ou 1
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(2));
        }
        //les 10 derniers chiffres sont générés aléatoirement
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public List<CompteEpargne> findComptesEpargne() {
        List<CompteEpargne> list = new ArrayList<>();
        for(CompteBancaire c: compteBancaireRepository.findAll()){
            if(c instanceof CompteEpargne){
                list.add((CompteEpargne) c);
            }
        }
        return list;
    }

    @Override
    public List<CompteCourant> findComptesCourant() {
        List<CompteCourant> list = new ArrayList<>();
        for(CompteBancaire c: compteBancaireRepository.findAll()){
            if(c instanceof CompteCourant){
                list.add((CompteCourant) c);
            }
        }
        return list;
    }

    @Override
    public CompteBancaire findOne(String numCompte) {
        return this.compteBancaireRepository.findByNumCompte(numCompte).get();
    }
}
