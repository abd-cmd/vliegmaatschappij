package nl.hu.prbed.vliegtuigmaatschappij.presentation.dto;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Klasse;

public class BoekingDto {
    public double prijs;
    public boolean bevestiging;
    public Klasse klasse;
    public Long vluchtId;
    public Long klantId;
    public Long medewerkerId;

}
