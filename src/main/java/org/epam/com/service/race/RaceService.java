package org.epam.com.service.race;

import java.util.List;
import org.epam.com.dao.Race;

public interface RaceService {

  int COUNT_HORSES_IN_RACE = 4;

  Race getRace();

  List<Integer> getRandomValues(int countHorses);
}
