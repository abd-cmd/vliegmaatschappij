package nl.hu.prbed.vliegtuigmaatschappij.application;


import nl.hu.prbed.vliegtuigmaatschappij.EmailSender.NotificationService;
import nl.hu.prbed.vliegtuigmaatschappij.data.BoekingRepository;
import nl.hu.prbed.vliegtuigmaatschappij.data.VluchtRepository;
import nl.hu.prbed.vliegtuigmaatschappij.domain.*;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.VliegtuigNotFoundException;
import nl.hu.prbed.vliegtuigmaatschappij.presentation.dto.BoekingInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class BoekingService {
    private final BoekingRepository boekingRepository;
    private final VluchtRepository vluchtRepository;
    private final LuchthavenService luchthavenService;
    private final NotificationService notificationService;
    private final KlantService klantService;
    private final VluchtService vluchtService;
    private final MedewerkerService medewerkerService;

    public BoekingService(BoekingRepository boekingRepository, VluchtRepository vluchtRepository, LuchthavenService luchthavenService, NotificationService notificationService, KlantService klantService, VluchtService vluchtService, MedewerkerService medewerkerService) {
        this.boekingRepository = boekingRepository;
        this.vluchtRepository = vluchtRepository;
        this.luchthavenService = luchthavenService;
        this.notificationService = notificationService;
        this.klantService = klantService;
        this.vluchtService = vluchtService;
        this.medewerkerService = medewerkerService;
    }

    public Boeking save(boolean bevestiging, Klasse klasse, Long vluchtId, Long klantId, Long medewerkerId) {

        if (bevestiging) {
            Klant klant1 = klantService.findById(klantId);
            Vlucht vlucht = vluchtService.findById(vluchtId);
            Medewerker medewerker = medewerkerService.findById(medewerkerId);
            double prijs = vlucht.berekenPrijs(klasse);

            vlucht.updateStoelenBezet();

            Boeking boeking = new Boeking(prijs, true, klasse, vlucht, klant1, medewerker);


            notificationService.sendSimpleEmail(Collections.singletonList(klant1.getEmailadres()),
                    "Geachte " + klant1.getAchternaam() + "," +
                            " \n\nBedankt dat je onze website gebrukt.\n " + klant1.getBoeking() + "!\n\nMet hartelijke groet,\nService team ",
                    "registreren");

            return this.boekingRepository.save(boeking);
        }
        return null;
    }

    public Boeking update(Long id, double prijs, boolean bevestiging, Klasse klasse, Long vluchtId, Long klantId, Long medewerkerId) {


        Klant klant1 = klantService.findById(klantId);
        Vlucht vlucht = vluchtService.findById(vluchtId);
        Medewerker medewerker = medewerkerService.findById(medewerkerId);
        Boeking boeking = findById(id);
        if (boeking != null) {
            boeking.setPrijs(prijs);
            boeking.setBevestiging(bevestiging);
            boeking.setKlasse(klasse);
            boeking.setVlucht(vlucht);
            boeking.setKlant(klant1);
            boeking.setEmployee(medewerker);
            return this.boekingRepository.save(boeking);
        }
        return null;
    }

    public Boeking delete(Long id) {
        Boeking boeking = findById(id);
        if (boekingRepository.findById(id).isEmpty()) {
            return null;
        }
        this.boekingRepository.delete(boeking);
        return boeking;
    }

    public Boeking findById(Long id) {
        Boeking boeking = this.boekingRepository.findById(id)
                .orElseThrow(() -> new VliegtuigNotFoundException());
        return boeking;
    }

    public List<Boeking> getBoekingen() {
        List<Boeking> boekingen = new ArrayList<>();
        this.boekingRepository.findAll().forEach(boeking -> boekingen.add(boeking));
        return boekingen;
    }

    public List<Boeking> findByVlucht(Vlucht vlucht) {
        List<Boeking> boeking = this.boekingRepository.findByVlucht(vlucht);
        return boeking;
    }


    public List<Vlucht> findByBeginhavenAndEindhavenAndVertrekdatumAndKlasse(String beginhaven, String eindhaven, LocalDateTime vertrekdatum) {
        Luchthaven beginHaven = this.luchthavenService.findByName(beginhaven);

        Luchthaven eindHaven = this.luchthavenService.findByName(eindhaven);

        List<Vlucht> vlucht = this.vluchtRepository.findVluchtByBeginLuchthavenAndEindHavenAndVertrekdatum(
                beginHaven,
                eindHaven,
                vertrekdatum
        );
        return vlucht;

    }

    public double berekenTotaalPrijs(Vlucht vlucht, List<BoekingInfo> boekingInfos) {

        double prijs = 0;
        for (BoekingInfo b : boekingInfos) {
            double prijs1 = vlucht.berekenPrijs(b.klasse);
            prijs = prijs + prijs1;


        }
        return prijs;

    }
}
