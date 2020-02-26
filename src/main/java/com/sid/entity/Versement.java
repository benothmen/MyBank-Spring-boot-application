package com.sid.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

    public Versement() {
        super();
    }

    public Versement(Date dateOpreation, double montant, Compte compte) {
        super(dateOpreation, montant, compte);
    }
}
