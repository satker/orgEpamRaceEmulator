package service.race;

import dao.Race;
import java.util.List;

public interface RaceService {

  int COUNT_HORSES_IN_RACE = 4;

  Race getRace();

  List<Integer> getRandomValues(int countHorses);
}
