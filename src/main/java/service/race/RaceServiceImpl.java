package service.race;

import dao.Horse;
import dao.Race;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.horse.HorseService;

@Component("Race")
public class RaceServiceImpl implements RaceService {

  private HorseService horseService;
  private Race race = new Race();

  @Autowired
  private RaceServiceImpl(HorseService horseService) {
    this.horseService = horseService;
  }

  @Override
  public Race getRace() {
    race.setListOfRiders(getHorsesInRace());
    race.setDayOfRace(LocalDateTime.now()
                                   .getDayOfYear() + 1);
    race.setPlace("England");
    race.setTimeOfRace(10);
    return race;
  }

  private List<Horse> getHorsesInRace() {
    int countHorses = horseService.getAllHorses()
                                  .size();
    List<Integer> listOfNumbers = getRandomValues(countHorses);
    return listOfNumbers.stream()
                        .filter(unit -> unit <= COUNT_HORSES_IN_RACE)
                        .map(this::getHorseByNumber)
                        .collect(Collectors.toList());
  }

  public List<Integer> getRandomValues(int countHorses) {
    List<Integer> listOfNumbers = IntStream.iterate(1, a -> a + 1)
                                           .limit(countHorses)
                                           .boxed()
                                           .collect(Collectors.toList());
    Collections.shuffle(listOfNumbers);
    return listOfNumbers;
  }

  private Horse getHorseByNumber(Integer numberOfHorse) {
    return horseService.getAllHorses()
                       .stream()
                       .filter(unit -> unit.getNumber() == numberOfHorse)
                       .findFirst()
                       .get();
  }
}
