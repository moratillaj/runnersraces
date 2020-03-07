package com.runnersteam.races.runnersraces.controller;

import com.runnersteam.races.runnersraces.model.Race;
import com.runnersteam.races.runnersraces.model.RaceId;
import com.runnersteam.races.runnersraces.service.RacesService;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class RacesController {

  @Autowired
  private RacesService racesService;

  @GetMapping("/{raceName}/{runnerNickname}/{raceDate}")
  public Race findRaceById(
      @PathVariable("raceName") String raceName,
      @PathVariable("runnerNickname") String runnerNickname,
      @PathVariable("raceDate") LocalDate raceDate) {
    return racesService.findById(new RaceId(raceName, runnerNickname, raceDate))
        .orElseThrow(()-> new RuntimeException("race not found"));
  }

  @PostMapping
  public Race create(@RequestBody Race race) {
    log.info("create-" + race.toString());
    return racesService.create(race);
  }

}
