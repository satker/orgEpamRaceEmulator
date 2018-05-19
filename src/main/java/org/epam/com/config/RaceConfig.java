package org.epam.com.config;

import javax.annotation.PostConstruct;
import org.epam.com.service.emulation.EmulationService;
import org.epam.com.service.emulation.EmulationServiceImpl;
import org.epam.com.service.horse.HorseService;
import org.epam.com.service.horse.HorseServiceImpl;
import org.epam.com.service.race.RaceService;
import org.epam.com.service.race.RaceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RaceConfig {

  private String horsesStorageFile = "D:\\java_projects\\orgEpamRaceEmulator\\src\\main\\resources\\horses.json";

  @Bean
  public HorseService getHorseService() {
    return new HorseServiceImpl(horsesStorageFile);
  }

  @Bean
  public RaceService getRaceService() {
    return new RaceServiceImpl(getHorseService());
  }

  @Bean
  public EmulationService getEmulationService() {
    return new EmulationServiceImpl(getRaceService());
  }

  @PostConstruct
  public void start() {
    getEmulationService().start();
  }
}
