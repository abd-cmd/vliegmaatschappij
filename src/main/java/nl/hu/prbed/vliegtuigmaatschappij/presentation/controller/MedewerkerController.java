package nl.hu.prbed.vliegtuigmaatschappij.presentation.controller;

import nl.hu.prbed.vliegtuigmaatschappij.application.MedewerkerService;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Medewerker;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotCorrectException;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotFoundException;
import nl.hu.prbed.vliegtuigmaatschappij.presentation.dto.MedewerkerDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vliegmaatschappij")
public class MedewerkerController {

    private final MedewerkerService medewerkerService;

    public MedewerkerController(MedewerkerService medewerkerService) {
        this.medewerkerService = medewerkerService;
    }

    @PostMapping("/medewerker")
    public Medewerker save(@Validated @RequestBody MedewerkerDto medewerkerDto) {
        try {
            return this.medewerkerService.save(medewerkerDto.id, medewerkerDto.voornaam, medewerkerDto.achternaam,
                    medewerkerDto.leeftijd, medewerkerDto.emailadres, medewerkerDto.telefoonnummer,
                    medewerkerDto.boekingList);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        }
    }

    @PutMapping("/medewerker/{id}")
    public Medewerker update(@PathVariable("id") Long medewerkerId, @Validated @RequestBody MedewerkerDto medewerkerDto) {
        try {
            return this.medewerkerService.update(medewerkerId, medewerkerDto.voornaam, medewerkerDto.achternaam,
                    medewerkerDto.leeftijd, medewerkerDto.emailadres, medewerkerDto.telefoonnummer,
                    medewerkerDto.boekingList);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/medewerker/{id}")
    public Medewerker delete(@PathVariable("id") Long medewerkerId, @Validated @RequestBody MedewerkerDto medewerkerDto) {
        try {
            return this.medewerkerService.delete(medewerkerId);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.OK, exception.getMessage());
        }
    }

    @GetMapping("/medewerker/{id}")
    public Medewerker findById(@PathVariable("id") Long medewerkerId) {
        try {
            return this.medewerkerService.findById(medewerkerId);
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/medewerkers")
    public List<Medewerker> getAlleBoekingen() {
        try {
            return this.medewerkerService.getMedewerkers();
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
