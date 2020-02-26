package com.sid.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OP",discriminatorType = DiscriminatorType.STRING,length = 1)
public abstract class Operation implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numero;
    private Date dateOpreation;
    private double montant;
    @ManyToOne
    @JoinColumn(name = "CODE_CPTE")
    private Compte compte;

    public Operation() {
        super();
    }

    public Operation(Date dateOpreation, double montant, Compte compte) {
        this.dateOpreation = dateOpreation;
        this.montant = montant;
        this.compte = compte;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Date getDateOpreation() {
        return dateOpreation;
    }

    public void setDateOpreation(Date dateOpreation) {
        this.dateOpreation = dateOpreation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
