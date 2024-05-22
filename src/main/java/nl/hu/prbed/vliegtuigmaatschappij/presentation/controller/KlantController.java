package nl.hu.prbed.vliegtuigmaatschappij.presentation.controller;

import nl.hu.prbed.vliegtuigmaatschappij.application.KlantService;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Klant;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotCorrectException;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotFoundException;
import nl.hu.prbed.vliegtuigmaatschappij.presentation.dto.KlantDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vliegmaatschappij")
public class KlantController {

    private final KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @PostMapping("/klant")
    public Klant save(@Validated @RequestBody KlantDto klantDto) {
        try {
            return this.klantService.save(klantDto.voornaam, klantDto.achternaam,
                    klantDto.telefoonnummer, klantDto.leeftijd, klantDto.emailadres,
                    klantDto.nationaliteit, klantDto.boeking);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        }
    }

    @PutMapping("/klant/{id}")
    public Klant update(@PathVariable("id") Long KlantId, @Validated @RequestBody KlantDto klantDto) {
        try {
            return this.klantService.update(KlantId, klantDto.voornaam, klantDto.achternaam,
                    klantDto.telefoonnummer, klantDto.leeftijd, klantDto.emailadres,
                    klantDto.nationaliteit, klantDto.boeking);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/klant/{id}")
    public Klant delete(@PathVariable("id") Long klantId) {
        try {
            return this.klantService.delete(klantId);

        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.OK, exception.getMessage());
        }
    }

    @GetMapping("/klant/{id}")
    public Klant findById(@PathVariable("id") Long KlantId) {
        try {
            return this.klantService.findById(KlantId);
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/klanten")
    public List<Klant> getAlleKlanten() {
        try {
            return this.klantService.getKlanten();
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
