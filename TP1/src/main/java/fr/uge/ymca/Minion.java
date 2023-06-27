package fr.uge.ymca;

import java.util.Objects;

public record Minion(String name) implements People {

  public Minion {
    Objects.requireNonNull(name);
  }

  @Override
  public String toString() {
    return name + " (MINION)";
  }

  /*@Override
  public int price() {
    return 1;
  }*/
}
