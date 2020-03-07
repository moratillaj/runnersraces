package com.runnersteam.races.runnersraces.repository;

import com.runnersteam.races.runnersraces.model.Race;
import com.runnersteam.races.runnersraces.model.RaceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacesRepository extends JpaRepository<Race, RaceId> {

}
