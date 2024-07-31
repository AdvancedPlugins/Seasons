package net.advancedplugins.seasons.api.calendar.day;

import java.io.Serializable;
import java.util.Objects;

public class DayLengthData implements Serializable {

  private final int dayTicks;
  private final int nightTicks;
  private final double dayTimeProgressPerTick;
  private final double nightTimeProgressPerTick;

  public DayLengthData(int dayTicks, int nightTicks) {
    this.dayTicks = dayTicks;
    this.nightTicks = nightTicks;
    this.dayTimeProgressPerTick = 12000D / dayTicks;
    this.nightTimeProgressPerTick = 12000D / nightTicks;
  }

  public double dayTimeProgressPerTick() {
    return dayTimeProgressPerTick;
  }

  public double nightTimeProgressPerTick() {
    return nightTimeProgressPerTick;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DayLengthData that = (DayLengthData) o;
    return dayTicks == that.dayTicks && nightTicks == that.nightTicks;
  }

  @Override
  public int hashCode() {
    return Objects.hash(dayTicks, nightTicks);
  }

  @Override
  public String toString() {
    return "DayLengthData{" +
        "dayTicks=" + dayTicks +
        ", nightTicks=" + nightTicks +
        ", dayTimeProgressPerTick=" + dayTimeProgressPerTick +
        ", nightTimeProgressPerTick=" + nightTimeProgressPerTick +
        '}';
  }
}