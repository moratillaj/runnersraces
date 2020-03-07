package com.runnersteam.races.runnersraces.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceId implements Serializable {

  private String raceName;
  private String runnerEmail;
  private LocalDate raceDate;
}
