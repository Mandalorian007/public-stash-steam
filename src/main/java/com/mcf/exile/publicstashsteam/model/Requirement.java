package com.mcf.exile.publicstashsteam.model;

import lombok.Data;

@Data
public class Requirement {

  private final String[][] values;

  private final String name;

  private final String displayMode;
}
