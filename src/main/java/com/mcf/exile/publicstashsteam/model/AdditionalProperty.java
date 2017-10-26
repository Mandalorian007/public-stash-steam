package com.mcf.exile.publicstashsteam.model;

import java.util.List;
import lombok.Data;

@Data
public class AdditionalProperty {

  private final String progress;

  private final List<List<String>> values;

  private final String name;

  private final String displayMode;
}
