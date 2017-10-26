package com.mcf.exile.publicstashsteam.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Stash {

  private final String id;

  private final String accountName;

  private final String stash;

  private final List<StashItem> items;

  private final String lastCharacterName;

  @JsonProperty("public")
  private final String isPublic;

  private final String stashType;
}
