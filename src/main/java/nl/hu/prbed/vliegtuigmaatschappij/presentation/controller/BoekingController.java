package nl.hu.prbed.vliegtuigmaatschappij.presentation.controller;

import nl.hu.prbed.vliegtuigmaatschappij.application.BoekingService;
import nl.hu.prbed.vliegtuigmaatschappij.application.VluchtService;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Boeking;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Vlucht;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotCorrectException;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotFoundException;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.VluchtNotFoundException;
import nl.hu.prbed.vliegtuigmaatschappij.presentation.dto.BoekingDto;
import nl.hu.prbed.vliegtuigmaatschappij.presentation.dto.BoekingInfo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vliegmaatschappij")
public class BoekingController {

    private final BoekingService service;
    private final VluchtService vluchtService;


    public BoekingController(BoekingService service, VluchtService vluchtService) {
        this.service = service;
        this.vluchtService = vluchtService;
    }

    @PostMapping("/boeking")
    public Boeking save(@Validated @RequestBody BoekingDto boekingdto) {
        try {
            return this.service.save(
                    boekingdto.bevestiging,
                    boekingdto.klasse,
                    boekingdto.vluchtId, boekingdto.klantId, boekingdto.medewerkerId);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        }
    }

    @PutMapping("/boeking/{id}")
    public Boeking update(@PathVariable("id") Long boekingId, @Validated @RequestBody BoekingDto boekingdto) {
        try {
            return this.service.update(boekingId, boekingdto.prijs,
                    boekingdto.bevestiging,
                    boekingdto.klasse,
                    boekingdto.vluchtId, boekingdto.klantId, boekingdto.medewerkerId);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/boeking/{id}")
    public Boeking delete(@PathVariable("id") Long boekingId) {
        try {
            return this.service.delete(boekingId);

        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.OK, exception.getMessage());
        }
    }

    @GetMapping("/boeking/{id}")
    public Boeking findById(@PathVariable("id") Long boekingId) {
        try {
            return service.findById(boekingId);
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/boekingen")
    public List<Boeking> getAlleBoekingen() {
        try {
            return service.getBoekingen();
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }


    @GetMapping("/boeking")
    public List<Vlucht> findByBeginhavenAndEindhavenAndVertrekdatum(@RequestParam("vertrek") String vertrek,
                                                                    @RequestParam("aankomst") String aankomst,
                                                                    @RequestParam("vertrekdatum") String vertrekdatum) {
        try {
            LocalDateTime date = LocalDateTime.parse(vertrekdatum);
            return this.service.findByBeginhavenAndEindhavenAndVertrekdatumAndKlasse(
                    vertrek,
                    aankomst,
                    date
            );
        } catch (VluchtNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }


    @GetMapping("/boeking/{id}/prijs")
    public double Reservering(@PathVariable("id") Long vluchtId, @Validated @RequestBody List<BoekingInfo> boekingInfos) {
        try {
            Vlucht vlucht = this.vluchtService.findById(vluchtId);

            return this.service.berekenTotaalPrijs(vlucht, boekingInfos);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }

}
