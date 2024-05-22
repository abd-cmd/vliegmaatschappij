package nl.hu.prbed.vliegtuigmaatschappij.presentation.controller;

import nl.hu.prbed.vliegtuigmaatschappij.application.LuchthavenService;
import nl.hu.prbed.vliegtuigmaatschappij.domain.Luchthaven;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotCorrectException;
import nl.hu.prbed.vliegtuigmaatschappij.domain.exceptions.BoekingNotFoundException;
import nl.hu.prbed.vliegtuigmaatschappij.presentation.dto.LuchthavenDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vliegmaatschappij")
public class LuchthavenController {

    private final LuchthavenService luchthavenService;

    public LuchthavenController(LuchthavenService luchthavenService) {
        this.luchthavenService = luchthavenService;
    }

    @PostMapping("/luchthaven")
    public Luchthaven save(@Validated @RequestBody LuchthavenDto luchthavenDto) {
        try {
            Luchthaven luchthaven = new Luchthaven();
            return this.luchthavenService.save(luchthaven.getId(), luchthavenDto.luchthavennaam, luchthavenDto.stad,
                    luchthavenDto.land, luchthavenDto.code, luchthavenDto.lat,
                    luchthavenDto.lon, luchthavenDto.vluchtList);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        }
    }

    @PutMapping("/luchthaven/{id}")
    public Luchthaven update(@PathVariable("id") Long luchthavenId, @Validated @RequestBody LuchthavenDto luchthavenDto) {
        try {
            return this.luchthavenService.update(luchthavenId, luchthavenDto.luchthavennaam, luchthavenDto.stad,
                    luchthavenDto.land, luchthavenDto.code, luchthavenDto.lat,
                    luchthavenDto.lon, luchthavenDto.vluchtList);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @DeleteMapping("/luchthaven/{id}")
    public Luchthaven delete(@PathVariable("id") Long luchthavenId) {
        try {
            return this.luchthavenService.delete(luchthavenId);

        } catch (BoekingNotCorrectException exception) {
            throw new ResponseStatusException(HttpStatus.OK, exception.getMessage());
        }
    }

    @GetMapping("/luchthaven/{id}")
    public Luchthaven findById(@PathVariable("id") Long LuchtHavenId) {
        try {
            return this.luchthavenService.findById(LuchtHavenId);
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/luchthavens")
    public List<Luchthaven> getAlleLuchthavens() {
        try {
            return this.luchthavenService.getLuchthavens();
        } catch (BoekingNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
