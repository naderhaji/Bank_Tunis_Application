package com.TunisBank.services;

import com.TunisBank.dto.OperationDto;
import com.TunisBank.entities.CompteBancaire;

public interface OperationService {

    CompteBancaire effectuerVersement(OperationDto dto);
    CompteBancaire effectuerRetrait(OperationDto dto);
    boolean effectuerVirement(OperationDto dto);




}
