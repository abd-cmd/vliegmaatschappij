package nl.hu.prbed.vliegtuigmaatschappij.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vliegtuig {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column(nullable = false, unique = true)

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vlucht> vlucht = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private VliegtuigType vliegtuigType;

    public Vliegtuig(Long id, List<Vlucht> vlucht, VliegtuigType vliegtuigType) {
        this.id = id;
        this.vlucht = vlucht;
        this.vliegtuigType = vliegtuigType;
    }

    public Vliegtuig() {
    }

    public int berekenTotaalStoelen() {
        return vliegtuigType.getFirstClassStoelenMax() + vliegtuigType.getBusinessClassStoelenMax() + vliegtuigType.getEconomyClassStoelenMax();
    }

    public List<Vlucht> getVlucht() {
        return vlucht;
    }

    public void setVlucht(List<Vlucht> vlucht) {
        this.vlucht = vlucht;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VliegtuigType getVliegtuigType() {
        return vliegtuigType;
    }

    public void setVliegtuigType(VliegtuigType vliegtuigType) {
        this.vliegtuigType = vliegtuigType;
    }
}
