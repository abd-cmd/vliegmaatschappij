package nl.hu.prbed.vliegtuigmaatschappij.domain;


import javax.persistence.*;

@Entity
public class Klant {

    @Id
    @GeneratedValue
    private Long id;
    private String voornaam;
    private String achternaam;
    private String telefoonnummer;

    private int leeftijd;
    private String emailadres;
    private String nationaliteit;

    @OneToOne(cascade = CascadeType.ALL)
    private Boeking boeking;

    public Klant(String voornaam, String achternaam, String telefoonnummer, int leeftijd, String emailadres, String nationaliteit, Boeking boeking) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoonnummer = telefoonnummer;
        this.leeftijd = leeftijd;
        this.emailadres = emailadres;
        this.nationaliteit = nationaliteit;
        this.boeking = boeking;
    }

    public Klant() {
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

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
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

    public String getNationaliteit() {
        return nationaliteit;
    }

    public void setNationaliteit(String nationaliteit) {
        this.nationaliteit = nationaliteit;
    }

    public Boeking getBoeking() {
        return boeking;
    }

    public void setBoeking(Boeking boeking) {
        this.boeking = boeking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
