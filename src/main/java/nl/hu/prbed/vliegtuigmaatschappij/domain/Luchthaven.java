package nl.hu.prbed.vliegtuigmaatschappij.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Luchthaven {

    @Id
    @GeneratedValue
    private Long id;
    private String luchthavennaam;
    private String stad;
    private String land;
    private String code;
    private String lat;
    private String lon;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vlucht> vluchtList = new ArrayList<>();

    public Luchthaven(Long id, String luchthavennaam, String stad, String land, String code, String lat, String lon, List<Vlucht> vluchtList) {
        this.id = id;
        this.luchthavennaam = luchthavennaam;
        this.stad = stad;
        this.land = land;
        this.code = code;
        this.lat = lat;
        this.lon = lon;
        this.vluchtList = vluchtList;
    }

    public Luchthaven() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLuchthavennaam() {
        return luchthavennaam;
    }

    public void setLuchthavennaam(String luchthavennaam) {
        this.luchthavennaam = luchthavennaam;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public List<Vlucht> getVluchtList() {
        return vluchtList;
    }

    public void setVluchtList(List<Vlucht> vluchtList) {
        this.vluchtList = vluchtList;
    }
}
