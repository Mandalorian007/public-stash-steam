package com.mcf.exile.publicstashsteam.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class StashChange {

  @JsonProperty("next_change_id")
  private final String nextChangeId;
  private final List<Stash> stashes;
}
