package com.runnersteam.races.runnersraces;

import com.runnersteam.races.runnersraces.messaging.RaceBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(RaceBinding.class)
public class RunnersracesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunnersracesApplication.class, args);
	}

}
