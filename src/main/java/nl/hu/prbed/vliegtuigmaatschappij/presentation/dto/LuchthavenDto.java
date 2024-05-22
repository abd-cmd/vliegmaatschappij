package nl.hu.prbed.vliegtuigmaatschappij.presentation.dto;

import nl.hu.prbed.vliegtuigmaatschappij.domain.Vlucht;

import java.util.ArrayList;
import java.util.List;

public class LuchthavenDto {
    public Long id;
    public String luchthavennaam;
    public String stad;
    public String land;
    public String code;
    public String lat;
    public String lon;
    public List<Vlucht> vluchtList = new ArrayList<>();
}
