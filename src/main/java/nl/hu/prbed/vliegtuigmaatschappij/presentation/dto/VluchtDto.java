package nl.hu.prbed.vliegtuigmaatschappij.presentation.dto;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Boeking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VluchtDto {
    public LocalDateTime vertrekdatum;
    public double tijdsduur;
    public Long beginhavenid;
    public Long eindhavenid;
    public int stoelenbezet;
    public List<Boeking> boekingList = new ArrayList<>();
    public Long vliegtuigid;
}
