package nl.hu.prbed.vliegtuigmaatschappij.presentation.dto;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Boeking;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Klasse;

public class KlantDto {
    public String voornaam;
    public String achternaam;
    public String telefoonnummer;
    public int leeftijd;
    public String emailadres;
    public String nationaliteit;
    public Klasse klasse;
    public Boeking boeking;
}
