package nl.hu.prbed.vliegtuigmaatschappij.domain;

import javax.persistence.*;

@Entity
public class Boeking {

    @Id
    @GeneratedValue
    private Long id;
    private double prijs;
    private boolean bevestiging;
    private Klasse klasse;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vlucht vlucht;

    @OneToOne(cascade = CascadeType.ALL)
    private Klant klant;

    @ManyToOne(cascade = CascadeType.ALL)
    private Medewerker medewerker;

    public Boeking(double prijs, boolean bevestiging, Klasse klasse, Vlucht vlucht, Klant klant, Medewerker medewerker) {
        this.prijs = prijs;
        this.bevestiging = bevestiging;
        this.klasse = klasse;
        this.vlucht = vlucht;
        this.klant = klant;
        this.medewerker = medewerker;
    }

    public Boeking() {
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public boolean isBevestiging() {
        return bevestiging;
    }

    public void setBevestiging(boolean bevestiging) {
        this.bevestiging = bevestiging;
    }

    public Vlucht getVlucht() {
        return vlucht;
    }

    public void setVlucht(Vlucht vlucht) {
        this.vlucht = vlucht;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Medewerker getEmployee() {
        return medewerker;
    }

    public void setEmployee(Medewerker medewerker) {
        this.medewerker = medewerker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
