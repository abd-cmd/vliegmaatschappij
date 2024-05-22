package nl.hu.prbed.vliegtuigmaatschappij.domain;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Luchtvaartmaatschappij {
    private int id;
    private String naam;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vliegtuig> vliegtuigList = new ArrayList<>();


    public Luchtvaartmaatschappij(int id, String naam, List<Vliegtuig> vliegtuigList) {
        this.id = id;
        this.naam = naam;
        this.vliegtuigList = vliegtuigList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<Vliegtuig> getVliegtuigList() {
        return vliegtuigList;
    }

    public void setVliegtuigList(List<Vliegtuig> vliegtuigList) {
        this.vliegtuigList = vliegtuigList;
    }
}
