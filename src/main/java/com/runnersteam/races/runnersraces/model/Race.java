package com.runnersteam.races.runnersraces.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "races")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@IdClass(RaceId.class)
public class Race {

  @Id
  private String raceName;

  @Id
  private String runnerEmail;

  @Id
  private LocalDate raceDate;

  private Float distanceKm;
  private boolean completed;
  private Integer completedRaceTimeSeconds;
}
