package nl.hu.prbed.vliegtuigmaatschappij.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VliegtuigType {

    @Id
    @GeneratedValue
    private Long id;
    private String vliegtuigType;
    private int firstClassStoelenMax;
    private int businessClassStoelenMax;
    private int economyClassStoelenMax;
    private double prijsFirstclass;
    private double prijsBusinessclass;
    private double prijsEconomyclass;

    public VliegtuigType(String vliegtuigType, int firstClassStoelenMax, int businessClassStoelenMax, int economyClassStoelenMax, double prijsFirstclass, double prijsBusinessclass, double prijsEconomyclass) {
        this.vliegtuigType = vliegtuigType;
        this.firstClassStoelenMax = firstClassStoelenMax;
        this.businessClassStoelenMax = businessClassStoelenMax;
        this.economyClassStoelenMax = economyClassStoelenMax;
        this.prijsFirstclass = prijsFirstclass;
        this.prijsBusinessclass = prijsBusinessclass;
        this.prijsEconomyclass = prijsEconomyclass;
    }

    public VliegtuigType() {
    }

    public double getPrijsFirstclass() {
        return prijsFirstclass;
    }

    public void setPrijsFirstclass(double prijsFirstclass) {
        this.prijsFirstclass = prijsFirstclass;
    }

    public double getPrijsBusinessclass() {
        return prijsBusinessclass;
    }

    public void setPrijsBusinessclass(double prijsBusinessclass) {
        this.prijsBusinessclass = prijsBusinessclass;
    }

    public double getPrijsEconomyclass() {
        return prijsEconomyclass;
    }

    public void setPrijsEconomyclass(double prijsEconomyclass) {
        this.prijsEconomyclass = prijsEconomyclass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVliegtuigType() {
        return vliegtuigType;
    }

    public void setVliegtuigType(String vliegtuigType) {
        this.vliegtuigType = vliegtuigType;
    }

    public int getFirstClassStoelenMax() {
        return firstClassStoelenMax;
    }

    public void setFirstClassStoelenMax(int firstClassStoelen) {
        this.firstClassStoelenMax = firstClassStoelen;
    }

    public int getBusinessClassStoelenMax() {
        return businessClassStoelenMax;
    }

    public void setBusinessClassStoelenMax(int businessClassStoelen) {
        this.businessClassStoelenMax = businessClassStoelen;
    }

    public int getEconomyClassStoelenMax() {
        return economyClassStoelenMax;
    }

    public void setEconomyClassStoelenMax(int economyClassStoelen) {
        this.economyClassStoelenMax = economyClassStoelen;
    }
}
