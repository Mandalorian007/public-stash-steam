package com.mcf.exile.publicstashsteam.queue;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcf.exile.publicstashsteam.model.Stash;
import com.mcf.exile.publicstashsteam.model.StashChange;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncPrintQueue implements Queue {

  private ObjectMapper objectMapper;

  @PostConstruct
  public void setup() {
    this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Async
  @Override
  public void send(byte[] bytes) {
    try {
      StashChange stashChange = objectMapper.readValue(bytes, StashChange.class);
      stashChange.getStashes().stream()
          .filter(stash -> stash != null)
          .map(Stash::getItems)
          .filter(items -> items != null)
          .flatMap(items -> items.stream())
          .filter(item -> {
            List<String> explicitMods = item.getExplicitMods();
            if(explicitMods == null) {
              return false;
            }
            return explicitMods.stream()
            .filter(explicitMod -> explicitMod.contains("Divination Card"))
                .count() > 0;
          })
          .map(item -> item.getTypeLine())
          .forEach(System.out::println);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
