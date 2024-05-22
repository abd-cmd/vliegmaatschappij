package nl.hu.prbed.vliegtuigmaatschappij.application;

import nl.hu.prbed.vliegtuigmaatschappij.data.MedewerkersRepository;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Boeking;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Medewerker;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.MedewerkerNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MedewerkerService {

    private final MedewerkersRepository medewerkersRepository;

    public MedewerkerService(MedewerkersRepository medewerkersRepository) {
        this.medewerkersRepository = medewerkersRepository;
    }

    public Medewerker save(Long id, String voornaam, String achternaam, int leeftijd, String emailadres, String telefoonnummer, List<Boeking> boekingList) {
        Medewerker medewerker = new Medewerker(id, voornaam, achternaam, leeftijd, emailadres, telefoonnummer, boekingList);
        return this.medewerkersRepository.save(medewerker);
    }

    public Medewerker update(Long id, String voornaam, String achternaam, int leeftijd, String emailadres, String telefoonnummer, List<Boeking> boekingList) {
        Medewerker medewerker = findById(id);
        if (medewerker != null) {
            medewerker.setVoornaam(voornaam);
            medewerker.setAchternaam(achternaam);
            medewerker.setLeeftijd(leeftijd);
            medewerker.setEmailadres(emailadres);
            medewerker.setTelefoonnummer(telefoonnummer);
            medewerker.setBoekingList(boekingList);
            return this.medewerkersRepository.save(medewerker);
        }
        return null;
    }

    public Medewerker delete(Long id) {
        Medewerker medewerker = findById(id);
        if (medewerkersRepository.findById(id).isEmpty()) {
            return null;
        }
        this.medewerkersRepository.delete(medewerker);
        return medewerker;
    }

    public Medewerker findById(Long id) {
        Medewerker medewerker = this.medewerkersRepository.findById(id)
                .orElseThrow(() -> new MedewerkerNotFoundException());
        return medewerker;
    }

    public List<Medewerker> getMedewerkers() {
        List<Medewerker> medewerkers = new ArrayList<>();
        this.medewerkersRepository.findAll().forEach(medewerker -> medewerkers.add(medewerker));
        return medewerkers;
    }
}
