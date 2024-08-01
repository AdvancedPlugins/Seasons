package net.advancedplugins.seasons.api.season;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record SeasonSnapshot(@NotNull SeasonType seasonType, int phase) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SeasonSnapshot that = (SeasonSnapshot) o;
    return phase == that.phase && Objects.equals(seasonType, that.seasonType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seasonType, phase);
  }

  @Override
  public String toString() {
    return "Season '" + seasonType + "', phase '" + phase + "'";
  }
}
