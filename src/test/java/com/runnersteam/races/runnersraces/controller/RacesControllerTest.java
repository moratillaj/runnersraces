package com.runnersteam.races.runnersraces.controller;

import static java.net.URI.create;
import static java.time.LocalDate.of;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.runnersteam.races.runnersraces.model.Race;
import com.runnersteam.races.runnersraces.service.RacesService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RacesController.class)
@ContextConfiguration(classes = {RacesControllerTest.RacesControllerTestConfig.class})
public class RacesControllerTest {

  @MockBean
  private RacesService racesService;

  @Autowired
  private MockMvc mockMvc;

  private static final String RACE_NAME = "theRaceName";
  private static final String RACE_RUNNER_EMAIL = "theRunner@gmail.com";
  private static final LocalDate RACE_DATE = of(2020, 3, 16);
  private static final Float RACE_DISTANCE_KM = 42f;
  private static final boolean RACE_COMPLETED = true;
  private static final Integer RACE_COMPLETED_TIME_SECONDS = 180;

  @Test
  public void shouldCreate() throws Exception {
    //Given
    Race race = Race.builder()
        .raceName(RACE_NAME)
        .runnerEmail(RACE_RUNNER_EMAIL)
        .raceDate(RACE_DATE)
        .distanceKm(RACE_DISTANCE_KM)
        .completed(RACE_COMPLETED)
        .completedRaceTimeSeconds(RACE_COMPLETED_TIME_SECONDS)
        .build();
    when(racesService.create(race)).thenReturn(race);

    //When && Then
    mockMvc.perform(post(create("/"))
        .content(getJson(race))
        .contentType(APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.raceName", is(RACE_NAME)))
        .andExpect(jsonPath("$.runnerEmail", is(RACE_RUNNER_EMAIL)))
        .andExpect(jsonPath("$.raceDate", is(RACE_DATE.format(ISO_DATE))))
        .andExpect(jsonPath("$.distanceKm", is(RACE_DISTANCE_KM.doubleValue())))
        .andExpect(jsonPath("$.completed", is(RACE_COMPLETED)))
        .andExpect(jsonPath("$.completedRaceTimeSeconds", is(RACE_COMPLETED_TIME_SECONDS)));
    verify(racesService).create(race);
  }

  private static String getJson(Race race) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    return objectMapper.writeValueAsString(race);
  }

  @Configuration
  @SpringBootApplication
  static class RacesControllerTestConfig {

  }
}