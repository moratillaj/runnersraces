package com.runnersteam.races.runnersraces.service;

import static org.springframework.integration.support.MessageBuilder.withPayload;

import com.runnersteam.races.runnersraces.model.Race;
import com.runnersteam.races.runnersraces.model.RaceId;
import com.runnersteam.races.runnersraces.repository.RacesRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class RacesService {

  @Autowired
  private RacesRepository racesRepository;

  @Autowired
  private MessageChannel raceTimeRegistrationOutput;

  public Race create(Race race) {
    raceTimeRegistrationOutput.send(withPayload(race).build());
    return racesRepository.save(race);
  }

  public Optional<Race> findById(RaceId raceId) {
    return racesRepository.findById(raceId);
  }
}
