package nl.hu.prbed.vliegtuigmaatschappij.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vlucht {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime vertrekdatum;

    private int stoelenbezet;
    private double tijdsduur;
    @ManyToOne(cascade = CascadeType.ALL)
    private Luchthaven beginLuchthaven;

    @ManyToOne(cascade = CascadeType.ALL)
    private Luchthaven eindHaven;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Boeking> boekingList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Vliegtuig vliegtuig;

    public Vlucht(Long id, LocalDateTime vertrekdatum, Luchthaven beginLuchthaven, Luchthaven eindHaven, int stoelenbezet, double tijdsduur, List<Boeking> boekingList, Vliegtuig vliegtuig) {
        this.id = id;
        this.vertrekdatum = vertrekdatum;
        this.beginLuchthaven = beginLuchthaven;
        this.eindHaven = eindHaven;
        this.stoelenbezet = stoelenbezet;
        this.tijdsduur = tijdsduur;
        this.boekingList = boekingList;
        this.vliegtuig = vliegtuig;
    }

    public Vlucht() {
    }

    public double berekenPrijs(Klasse klasse) {
        double prijs = 0;
        if (klasse == Klasse.FIRSTCLASS) {
            prijs = vliegtuig.getVliegtuigType().getPrijsFirstclass();
        }
        if (klasse == Klasse.BUSINESCLASS) {
            prijs = vliegtuig.getVliegtuigType().getPrijsBusinessclass();
        }
        if (klasse == Klasse.ECONOMYCLASS) {
            prijs = vliegtuig.getVliegtuigType().getPrijsEconomyclass();
        }
        return prijs;
    }

    public void voegBoekingToe(Boeking boeking) {
        boekingList.add(boeking);
    }

    public void verwijderBoeking(Boeking boeking) {
        boekingList.remove(boeking);
    }


    public boolean berekenGenoegPlek(int aantal) {
        int nieuwAantal = stoelenbezet + aantal;
        return vliegtuig.berekenTotaalStoelen() >= nieuwAantal;
    }

    public void updateStoelenBezet() {
        this.stoelenbezet = stoelenbezet + 1;
    }

    public LocalDateTime getVertrekdatum() {
        return vertrekdatum;
    }

    public void setVertrekdatum(LocalDateTime vertrekdatum) {
        this.vertrekdatum = vertrekdatum;
    }


    public Luchthaven getBeginLuchthaven() {
        return beginLuchthaven;
    }

    public void setBeginLuchthaven(Luchthaven beginLuchthaven) {
        this.beginLuchthaven = beginLuchthaven;
    }

    public Luchthaven getEindHaven() {
        return eindHaven;
    }

    public void setEindHaven(Luchthaven eindHaven) {
        this.eindHaven = eindHaven;
    }

    public int getStoelenbezet() {
        return stoelenbezet;
    }

    public void setStoelenbezet(int stoelenbezet) {
        this.stoelenbezet = stoelenbezet;
    }

    public double getTijdsduur() {
        return tijdsduur;
    }

    public void setTijdsduur(double tijdsduur) {
        this.tijdsduur = tijdsduur;
    }

    public List<Boeking> getBoekingList() {
        return boekingList;
    }

    public void setBoekingList(List<Boeking> boekingList) {
        this.boekingList = boekingList;
    }

    public Vliegtuig getVliegtuig() {
        return vliegtuig;
    }

    public void setVliegtuig(Vliegtuig vliegtuig) {
        this.vliegtuig = vliegtuig;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}