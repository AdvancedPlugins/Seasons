package net.advancedplugins.seasons.api.calendar.configuration;

import com.google.common.collect.ImmutableList;
import net.advancedplugins.seasons.api.calendar.CalendarType;
import net.advancedplugins.seasons.api.calendar.year.YearExpects;
import net.advancedplugins.seasons.api.calendar.day.DayLengthData;
import net.advancedplugins.seasons.api.season.SeasonType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.Serializable;
import java.util.*;

public abstract class CalendarConfiguration implements Serializable {

  private final @NotNull String name;
  private final int dayLength;
  private final @UnmodifiableView Map<SeasonType, DayLengthData> dayDurationBySeason;
  private final int transitionDurationInDays;

  CalendarConfiguration(@NotNull String name, int dayLength, @NotNull Map<SeasonType, DayLengthData> dayDurationBySeason, int transitionDurationInDays) {
    this.name = name;
    this.dayLength = dayLength;
    this.dayDurationBySeason = Collections.unmodifiableMap(dayDurationBySeason);
    this.transitionDurationInDays = transitionDurationInDays;
    // TODO validate that a year has minimum 12 days.
    // TODO validate dayDurationInTicks with the total duration of the seasons' days
    // TODO check no repeated seasons on seasonSequence
    // TODO check that all season types are in seasonSequence (or maybe not, idk if evict this check to let the user remove not wanted seasons)
  }

  public @NotNull String name() {
    return name;
  }

  public int dayLength() {
    return dayLength;
  }

  public @UnmodifiableView Map<SeasonType, DayLengthData> dayDurationBySeason() {
    return dayDurationBySeason;
  }

  public int fullTransitionLength(boolean inTicks) {
    return inTicks ? transitionDurationInDays * dayLength : transitionDurationInDays;
  }

  public int singleTransitionLengthInTicks() {
    return (transitionDurationInDays * dayLength) / 3;
  }

  public @NotNull SeasonType nextTo(@NotNull SeasonType type) {
    int index = seasonSequence().indexOf(type);
    int next = index + 1;
    next = next >= SeasonType.values().length ? 0 : next;

    return seasonSequence().get(next);
  }

  public @NotNull SeasonType previousTo(@NotNull SeasonType type) {
    int index = seasonSequence().indexOf(type);
    int previous = index - 1;
    previous = previous > 0 ? SeasonType.values().length - 1 : previous;

    return seasonSequence().get(previous);
  }

  public abstract @NotNull ImmutableList<SeasonType> seasonSequence();
  
  public abstract @NotNull CalendarType type();

  public abstract @NotNull YearExpects newYearExpects(int year);

}