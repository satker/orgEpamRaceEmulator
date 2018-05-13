package service.emulation;

import dao.Horse;
import dao.Race;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import service.race.RaceService;

public class EmulationServiceImpl implements EmulationService {

  public static final String LIMITER = "------------------------------------------------------------";
  private Map<Integer, Horse> winners = new HashMap<>();

  private RaceService raceService;

  private EmulationServiceImpl(RaceService raceService) {
    this.raceService = raceService;
  }


  @Override
  public void start() {
    while (true) {
      Race race = raceService.getRace();
      informationForRace(race);
      Horse myHorse = makeABet(race);
      delayBeforeRace();
      System.out.println(LIMITER);
      for (int secondOfRace = 1; secondOfRace <= race.getTimeOfRace(); secondOfRace++) {
        sleepOneSecond();
        raceInCurrentTime(myHorse, race, secondOfRace);
      }
      if (!playAgain().equals("y")) {
        break;
      }
    }
  }

  private String playAgain() {
    System.out.println("If you want to play again enter \"y\", if not any any other character");
    Scanner scan = new Scanner(System.in);
    return scan.nextLine();
  }

  private void delayBeforeRace() {
    System.out.println("Race start after 5 sec.");
    for (int i = 0; i < 5; i++) {
      System.out.println(i + 1 + " sec.");
      sleepOneSecond();
    }
    System.out.println("Race starts");
  }

  private Horse makeABet(Race race) {
    System.out.println(
        "Please, choose winner horse from this positions (1-" + RaceService.COUNT_HORSES_IN_RACE
            + "): ");
    int number;
    while (true) {
      number = getNumberOfHorse();
      if (number > 0 && number <= RaceService.COUNT_HORSES_IN_RACE) {
        break;
      } else {
        System.out.println("This position illegal. Try again.");
      }
    }
    Horse myHorse = race.getListOfRiders()
                        .get(number - 1);
    System.out.println("You make a bet to horse " + myHorse.getName());

    System.out.println(LIMITER);
    return myHorse;
  }

  private int getNumberOfHorse() {
    Scanner scan = new Scanner(System.in);
    return scan.nextInt();
  }

  private void informationForRace(Race race) {
    System.out.println("Information of the race:");
    System.out.println(LIMITER);
    System.out.println("Location of the race: " + race.getPlace());
    System.out.println("Day of race: " + race.getTimeOfRace() + " May.");
    System.out.println("Race duration: " + race.getTimeOfRace() + " seconds.");
    System.out.println("Number of horses " + RaceService.COUNT_HORSES_IN_RACE + ".");
    System.out.println(LIMITER);
    System.out.println("List of horses:");
    for (int i = 0; i < RaceService.COUNT_HORSES_IN_RACE; i++) {
      Horse horse = race.getListOfRiders()
                        .get(i);
      System.out.println(
          (i + 1) + ". Horse " + horse.getName() + " number " + horse.getNumber() + ". Breed: "
              + horse.getBreedOfHorse() + ". Governed by " + horse.getRider()
                                                                  .getName());
    }
    System.out.println(LIMITER);
  }

  private void raceInCurrentTime(Horse myHorse, Race race, int secondOfRace) {
    if (secondOfRace == race.getTimeOfRace()) {
      displayPositionHorses(race, true);
      displayPlaces(myHorse);
    } else {
      displayPositionHorses(race, false);
    }
  }

  private void displayPlaces(Horse myHorse) {
    System.out.println(LIMITER);
    for (Integer place : winners.keySet()) {
      if (place.equals(1)) {
        System.out.println("Our winner: " + winners.get(place)
                                                   .getName());
      } else {
        System.out.println("Position " + place + ": " + winners.get(place)
                                                               .getName());
      }
    }
    if (myHorse.equals(winners.get(1))) {
      System.out.println("Your horse win. Best regards!!!!");
    } else {
      System.out.println("Unfortunately you lose, try next time.");
    }
  }

  private void sleepOneSecond() {
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void displayPositionHorses(Race race, boolean lastRace) {
    List<Integer> randomPositions = raceService.getRandomValues(RaceService.COUNT_HORSES_IN_RACE);
    for (int numberHorse = 0; numberHorse < RaceService.COUNT_HORSES_IN_RACE; numberHorse++) {
      if (numberHorse == 0) {
        System.out.print("|");
      }
      if (lastRace == true) {
        winners.put(randomPositions.get(numberHorse), race.getListOfRiders()
                                                          .get(numberHorse));
      }
      if (numberHorse == RaceService.COUNT_HORSES_IN_RACE - 1) {
        System.out.println("| " + race.getListOfRiders()
                                      .get(numberHorse)
                                      .getName() + " : " + randomPositions.get(numberHorse)
            + " ||");
      } else {
        System.out.print("| " + race.getListOfRiders()
                                    .get(numberHorse)
                                    .getName() + " : " + randomPositions.get(numberHorse) + " |");
      }
    }
  }
}
