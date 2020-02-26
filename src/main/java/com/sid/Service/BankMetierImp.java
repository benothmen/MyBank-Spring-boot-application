package com.sid.Service;

import com.sid.dao.CompteRepository;
import com.sid.dao.OperationRepository;
import com.sid.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class BankMetierImp implements IBankMetier {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Override
    public Compte consulterCompte(String codeCpte) {
        Compte cp = compteRepository.findById(codeCpte).get();
        if (cp==null) throw new RuntimeException("Compte Introuvable");
        return cp;
    }

    @Override
    public void verser(String codeCpte, double montant) {
        Compte cp =consulterCompte(codeCpte);
        Versement v=new Versement(new Date(),montant,cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde()+montant);
        compteRepository.save(cp);
    }

    @Override
    public void retirer(String codeCpte, double montant) {
        Compte cp =consulterCompte(codeCpte);
        double facilitesCaisse=0;
        if (cp instanceof CompteCourant)
            facilitesCaisse=((CompteCourant) cp).getDecouvert();
        if (facilitesCaisse+cp.getSolde()<montant) throw new RuntimeException("Solde insuffisant");
        Retrait r= new Retrait(new Date(),montant,cp);
        operationRepository.save(r);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);
    }

    @Override
    public void virement(String codeCpte1, String codeCpte2, double montant) {
        if(codeCpte1.equals(codeCpte2)) throw new RuntimeException("Impossible virement sur le même compte");
        retirer(codeCpte1,montant);
        verser(codeCpte2,montant);
    }

    @Override
    public Page<Operation> listOpreation(String codeCpte, int page, int size) {
        return operationRepository.listOperation(codeCpte,PageRequest.of(page,size));
    }
}
