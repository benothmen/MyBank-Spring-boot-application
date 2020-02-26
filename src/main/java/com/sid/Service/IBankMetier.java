package com.sid.Service;

import com.sid.entity.Compte;
import com.sid.entity.Operation;
import org.springframework.data.domain.Page;

public interface IBankMetier {
    public Compte consulterCompte(String codeCpte);
    public void verser(String codeCpte, double montant);
    public void retirer(String codeCpte, double montant);
    public void virement(String codeCpte1, String codeCpte2, double montant);
    public Page<Operation> listOpreation(String codeCpte, int page, int size);
}
