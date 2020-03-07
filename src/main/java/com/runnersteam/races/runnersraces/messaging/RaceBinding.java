package com.runnersteam.races.runnersraces.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RaceBinding {

  String OUTPUT = "raceTimeRegistrationOutput";

  @Output(RaceBinding.OUTPUT)
  MessageChannel raceTimeRegistrationOutput();

}
