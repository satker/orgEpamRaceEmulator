package org.epam.com.dao;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Race {

  @Getter
  @Setter
  private int dayOfRace;

  @Getter
  @Setter
  private String place;

  @Getter
  @Setter
  private int timeOfRace;

  @Getter
  @Setter
  private List<Horse> listOfRiders = new ArrayList<>();
}
