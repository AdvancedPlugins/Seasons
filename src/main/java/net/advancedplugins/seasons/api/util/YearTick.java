package net.advancedplugins.seasons.api.util;

import org.jetbrains.annotations.NotNull;

public class YearTick {

  private final int tick;
  private final int ticksPerDay;

  public YearTick(int tick, int ticksPerDay) {
    this.tick = tick;
    this.ticksPerDay = ticksPerDay;
  }

  public int tick() {
    return tick;
  }

  public int ticksPerDay() {
    return ticksPerDay;
  }

  public int toYearDay() {
    return (int) Math.ceil((double) tick / ticksPerDay);
  }

  public int remainderTicks() {
    return tick % ticksPerDay;
  }

  public @NotNull YearTick addDays(int days) {
    return new YearTick(tick + days * ticksPerDay, ticksPerDay);
  }

  public @NotNull YearTick add(int toAdd) {
    return new YearTick(tick + toAdd, ticksPerDay);
  }

  public @NotNull YearTick subtract(int toSubtract) {
    return new YearTick(tick - toSubtract, ticksPerDay);
  }

  public @NotNull YearTick subtractDays(int days) {
    return new YearTick(tick - days * ticksPerDay, ticksPerDay);
  }
}
