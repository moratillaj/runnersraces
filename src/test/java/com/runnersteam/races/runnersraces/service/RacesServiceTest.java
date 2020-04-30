package com.runnersteam.races.runnersraces.service;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.runnersteam.races.runnersraces.model.Race;
import com.runnersteam.races.runnersraces.model.RaceId;
import com.runnersteam.races.runnersraces.repository.RacesRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@ExtendWith(MockitoExtension.class)
class RacesServiceTest {

  @InjectMocks
  private RacesService racesService;

  @Mock
  private RacesRepository racesRepository;

  @Mock
  private MessageChannel raceTimeRegistrationOutput;

  @Mock
  private Race race;

  @Mock
  private RaceId raceId;

  @Test
  public void shouldCreate() {
    //Given
    when(racesRepository.save(race)).thenReturn(race);

    //When
    Race created = racesService.create(race);

    //Then
    InOrder inOrder = inOrder(raceTimeRegistrationOutput, racesRepository);
    inOrder.verify(raceTimeRegistrationOutput).send(any(Message.class));
    inOrder.verify(racesRepository).save(race);
    assertThat(created).isEqualTo(created);
  }

  @Test
  public void shouldFindById() {
    //Given
    when(racesRepository.findById(raceId)).thenReturn(of(race));

    //When
    Optional<Race> found = racesService.findById(raceId);

    //Then
    verify(racesRepository).findById(raceId);
    assertThat(found).isPresent();
    assertThat(found.get()).isEqualTo(race);

  }
}