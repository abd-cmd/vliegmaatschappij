package nl.hu.prbed.vliegtuigmaatschappij.presentation.dto;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Boeking;

import java.util.ArrayList;
import java.util.List;

public class MedewerkerDto {
    public Long id;
    public String voornaam;
    public String achternaam;
    public int leeftijd;
    public String emailadres;
    public String telefoonnummer;
    public List<Boeking> boekingList = new ArrayList<>();
}
