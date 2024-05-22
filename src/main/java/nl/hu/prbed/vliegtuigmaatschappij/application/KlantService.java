package nl.hu.prbed.vliegtuigmaatschappij.application;

import nl.hu.prbed.vliegtuigmaatschappij.data.KlantRepository;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Boeking;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Klant;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.KlantNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class KlantService {

    private final KlantRepository klantRepository;

    public KlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    public Klant save(String voornaam, String achternaam, String telefoonnummer, int leeftijd, String emailadres, String nationaliteit, Boeking boeking) {
        Klant klant = new Klant(voornaam, achternaam, telefoonnummer, leeftijd, emailadres, nationaliteit, boeking);
        return this.klantRepository.save(klant);
    }

    public Klant update(Long id, String voornaam, String achternaam, String telefoonnummer, int leeftijd, String emailadres, String nationaliteit, Boeking boeking) {
        Klant klant = findById(id);
        if (klant != null) {
            klant.setVoornaam(voornaam);
            klant.setAchternaam(achternaam);
            klant.setTelefoonnummer(telefoonnummer);
            klant.setLeeftijd(leeftijd);
            klant.setEmailadres(emailadres);
            klant.setNationaliteit(nationaliteit);
            klant.setBoeking(boeking);
            return this.klantRepository.save(klant);
        }
        return null;
    }

    public Klant delete(Long id) {
        Klant klant = findById(id);
        if (klantRepository.findById(id).isEmpty()) {
            return null;
        }
        this.klantRepository.delete(klant);
        return klant;
    }

    public Klant findById(Long id) {
        Klant klant = this.klantRepository.findById(id)
                .orElseThrow(() -> new KlantNotFoundException());
        return klant;
    }

    public List<Klant> getKlanten() {
        List<Klant> klants = new ArrayList<>();
        this.klantRepository.findAll().forEach(klant -> klants.add(klant));
        return klants;
    }
}
