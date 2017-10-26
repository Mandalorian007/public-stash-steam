package com.mcf.exile.publicstashsteam.model;

import lombok.Data;

@Data
public class Property {

  private final Object[] values;

  private final String name;

  private final String displayMode;

  private final int type;
}
