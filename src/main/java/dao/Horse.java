package dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
public class Horse {

  @Getter
  @Setter
  private int number;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private Breed breedOfHorse;

  @Getter
  @Setter
  private Rider rider;
}
