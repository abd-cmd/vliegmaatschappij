package nl.hu.prbed.vliegtuigmaatschappij.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medewerker {

    @Id
    @GeneratedValue
    private Long id;
    private String voornaam;
    private String achternaam;
    private int leeftijd;
    private String emailadres;
    private String telefoonnummer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Boeking> boekingList = new ArrayList<>();

    public Medewerker(Long id, String voornaam, String achternaam, int leeftijd, String emailadres, String telefoonnummer, List<Boeking> boekingList) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.leeftijd = leeftijd;
        this.emailadres = emailadres;
        this.telefoonnummer = telefoonnummer;
        this.boekingList = boekingList;
    }

    public Medewerker() {
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public List<Boeking> getBoekingList() {
        return boekingList;
    }

    public void setBoekingList(List<Boeking> boekingList) {
        this.boekingList = boekingList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}