package net.advancedplugins.seasons.api.season;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record SeasonSnapshot(@NotNull String name, int transition) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SeasonSnapshot that = (SeasonSnapshot) o;
    return transition == that.transition && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, transition);
  }
}