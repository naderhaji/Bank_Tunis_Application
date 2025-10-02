package com.TunisBank.services;

import com.TunisBank.dto.OperationDto;
import com.TunisBank.entities.CompteBancaire;
import com.TunisBank.entities.Operation;
import com.TunisBank.enums.AccountStatus;
import com.TunisBank.enums.TypeOperation;
import com.TunisBank.repositories.CompteBancaireRepository;
import com.TunisBank.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OperationServiceImpl implements OperationService{

    private final OperationRepository operationRepository;
    private final CompteBancaireRepository compteBancaireRepository;
    OperationServiceImpl(final OperationRepository operationRepository, final CompteBancaireRepository compteBancaireRepository) {
        this.operationRepository = operationRepository;
        this.compteBancaireRepository = compteBancaireRepository;
    }


    @Override
    public CompteBancaire effectuerVersement(OperationDto dto) {
        Optional<CompteBancaire> compteOpt = this.compteBancaireRepository.findByNumCompte(dto.getNumCompteSource());
        if(compteOpt.isPresent()){

            CompteBancaire compte = compteOpt.get();
            if(compte.getStatus().equals(AccountStatus.ACTIVATED))
            {
                compte.setBalance(compte.getBalance() + dto.getAmount());
                Operation operation = new Operation();
                operation.setAmount(dto.getAmount());
                operation.setTypeOperation(TypeOperation.CREDIT);
                operation.setCompte(compte);
                operation.setNumOperation(generateAccountNumber());
                operation.setDateOperation(new Date());
                this.operationRepository.save(operation);
                return this.compteBancaireRepository.save(compte);

            }else{
                throw new RuntimeException(" solde insuffisant");
            }

        }else{
            throw new RuntimeException("Compte introuvable");
        }
    }

    @Override
    public CompteBancaire effectuerRetrait(OperationDto dto) {
        Optional<CompteBancaire> compteOpt = this.compteBancaireRepository.findByNumCompte(dto.getNumCompteSource());
        if (compteOpt.isPresent()) {

            CompteBancaire compte = compteOpt.get();
            if (compte.getStatus().equals(AccountStatus.ACTIVATED) && compte.getBalance() > dto.getAmount()) {
                compte.setBalance(compte.getBalance() - dto.getAmount());
                Operation operation = new Operation();
                operation.setAmount(dto.getAmount());
                operation.setTypeOperation(TypeOperation.CREDIT);
                operation.setCompte(compte);
                operation.setNumOperation(generateAccountNumber());
                operation.setDateOperation(new Date());
                this.operationRepository.save(operation);
                return compte;

            } else {
                throw new RuntimeException(" solde insuffisant");
            }

        } else {
            throw new RuntimeException("Compte introuvable");
        }
    }

    @Override
    public boolean effectuerVirement(OperationDto dto) {
        String numCompteSource = dto.getNumCompteSource();
        OperationDto dtoSource = new OperationDto(numCompteSource, null, dto.getAmount());
        CompteBancaire compteSource = effectuerRetrait(dtoSource);
        if(compteSource != null){
            String numCompteDestination = dto.getNumCompteDestination();
            OperationDto dtoDestination = new OperationDto(numCompteDestination, null, dto.getAmount());
            effectuerVersement(dtoDestination);
            return true;
        }
        return false;
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
}
