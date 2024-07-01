package net.advancedplugins.seasons.api.calendar.day;

import java.util.Objects;

public record DayDuration(int dayTicks, int nightTicks) {

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DayDuration that = (DayDuration) o;
    return dayTicks == that.dayTicks && nightTicks == that.nightTicks;
  }

  @Override
  public int hashCode() {
    return Objects.hash(dayTicks, nightTicks);
  }
}