package com.sid.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {
    public Retrait() {
        super();
    }

    public Retrait(Date dateOpreation, double montant, Compte compte) {
        super(dateOpreation, montant, compte);
    }
}
