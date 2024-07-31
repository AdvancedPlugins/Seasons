package net.advancedplugins.seasons.api.calendar.configuration;

import com.google.common.collect.ImmutableList;
import net.advancedplugins.seasons.api.calendar.CalendarType;
import net.advancedplugins.seasons.api.calendar.day.DayLengthData;
import net.advancedplugins.seasons.api.calendar.year.CustomYearExpects;
import net.advancedplugins.seasons.api.calendar.year.YearExpects;
import net.advancedplugins.seasons.api.season.SeasonType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public final class CustomCalendarConfiguration extends CalendarConfiguration {
  private final @NotNull ImmutableList<SeasonType> seasonSequence;
  private final int seasonDurationInDays;

  public CustomCalendarConfiguration(@NotNull String name, int dayLength, @NotNull List<SeasonType> seasonSequence, @NotNull Map<SeasonType, DayLengthData> dayDurationBySeason, int transitionDurationInDays, int seasonDurationInDays) {
    super(name, dayLength, dayDurationBySeason, transitionDurationInDays);
    this.seasonSequence = ImmutableList.copyOf(seasonSequence);
    this.seasonDurationInDays = seasonDurationInDays;
  }

  public int seasonDurationInDays() {
    return seasonDurationInDays;
  }

  @Override
  public @NotNull ImmutableList<SeasonType> seasonSequence() {
    return seasonSequence;
  }

  @Override
  public @NotNull CalendarType type() {
    return CalendarType.CUSTOM;
  }

  @Override
  public @NotNull YearExpects newYearExpects(int year) {
    return new CustomYearExpects(this, year);
  }

  public int fullSeasonDays() {
    return seasonDurationInDays + fullTransitionLength(false);
  }
}