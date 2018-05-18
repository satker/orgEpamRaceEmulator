package service.horse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.Horse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HorseServiceImpl implements HorseService {

  @Value("classpath:horses.json")
  private String horsesStorageFile;

  private List<Horse> allHorses;

  public HorseServiceImpl() {
    Gson gson = new Gson();
    Type type = new TypeToken<List<Horse>>() {
    }.getType();
    try (Stream<String> stream = Files.lines(Paths.get(horsesStorageFile))) {
      allHorses = gson.fromJson(stream.reduce(String::concat)
                                      .get(), type);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Horse> getAllHorses() {
    return allHorses;
  }
}
