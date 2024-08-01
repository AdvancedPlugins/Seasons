package net.advancedplugins.seasons.api.calendar.configuration;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import java.time.MonthDay;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import net.advancedplugins.seasons.api.calendar.CalendarType;
import net.advancedplugins.seasons.api.calendar.day.DayLengthData;
import net.advancedplugins.seasons.api.calendar.year.GregorianYearExpects;
import net.advancedplugins.seasons.api.calendar.year.YearExpects;
import net.advancedplugins.seasons.api.season.SeasonType;
import org.jetbrains.annotations.NotNull;

public final class GregorianCalendarConfiguration extends CalendarConfiguration {
  private final @NotNull ImmutableBiMap<SeasonType, MonthDay> seasonStartDates;
  private final @NotNull ImmutableList<SeasonType> seasonSequence;

  public GregorianCalendarConfiguration(@NotNull String name, int dayLength, @NotNull Map<SeasonType, DayLengthData> dayDurationBySeason, int transitionDurationInDays, @NotNull Map<SeasonType, MonthDay> seasonStartDates) {
    super(name, dayLength, dayDurationBySeason, transitionDurationInDays);

    seasonStartDates = seasonStartDates.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (monthDay, monthDay2) -> monthDay, LinkedHashMap::new));

    this.seasonSequence = ImmutableList.copyOf(seasonStartDates.keySet());
    this.seasonStartDates = ImmutableBiMap.copyOf(seasonStartDates);
    // TODO check seasonStartDates matches with seasonSequence
  }

  public @NotNull ImmutableBiMap<SeasonType, MonthDay> seasonStartDates() {
    return seasonStartDates;
  }

  @Override
  public @NotNull CalendarType type() {
    return CalendarType.GREGORIAN;
  }

  @Override
  public @NotNull YearExpects newYearExpects(int year) {
    return new GregorianYearExpects(this, year);
  }

  @Override
  public @NotNull ImmutableList<SeasonType> seasonSequence() {
    return seasonSequence;
  }
}