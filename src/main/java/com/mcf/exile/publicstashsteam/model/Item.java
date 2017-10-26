package com.mcf.exile.publicstashsteam.model;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Item {

  private final String typeLine;

  private final String identified;

  private final String lockedToCharacter;

  private final List<Requirement> requirements;

  private final List<String> explicitMods;

  private final String frameType;

  private final String id;

  private final String verified;

  private final String name;

  private final List<NextLevelRequirement> nextLevelRequirements;

  private final String icon;

  private final String support;

  private final List<Property> properties;

  private final String league;

  private final int socket;

  private final String colour;

  private final List<Item> socketedItems;

  private final String h;

  private final String w;

  private final List<Socket> sockets;

  private final String corrupted;

  private final String descrText;

  private final String secDescrText;

  private final List<AdditionalProperty> additionalProperties;

  private final String ilvl;

  private final List<String> flavourText;
}
