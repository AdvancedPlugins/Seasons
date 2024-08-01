package net.advancedplugins.seasons.api.calendar.year;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import net.advancedplugins.seasons.api.calendar.configuration.GregorianCalendarConfiguration;
import net.advancedplugins.seasons.api.season.SeasonType;
import net.advancedplugins.seasons.api.util.CircularIntRange;
import org.jetbrains.annotations.NotNull;

public class GregorianYearExpects extends AbstractYearExpects<GregorianCalendarConfiguration> {

  public GregorianYearExpects(@NotNull GregorianCalendarConfiguration configuration, int year) {
    super(configuration, year);
  }

  private static int toYearDay(int year, MonthDay monthDay) {
    return LocalDate.of(year, monthDay.getMonthValue(), monthDay.getDayOfMonth()).getDayOfYear();
  }

  @Override
  protected CircularIntRange calculateSeasonRange(int start, int end) {
    int endYear = this.year;
    if (start > end) { // different years, lets think that "end" is one year ahead
      endYear += 1;
    }

    LocalDate startDate = LocalDate.ofYearDay(this.year, start);
    LocalDate endDate = LocalDate.ofYearDay(endYear, end);

    // Adjust initial season phase end to later insert the transitions
    // at the end of the season
    int diff = (int) ChronoUnit.DAYS.between(startDate, endDate);
    if (shouldComputeTransitions(diff)) {
      endDate = endDate.minusDays(configuration.fullTransitionLength(false));
      end = endDate.getDayOfYear();
    }

    return newTickRange(start, end, true);
  }

  @Override
  protected Map<SeasonType, Integer> seasonStartDays() {
    return configuration.seasonStartDates()
        .entrySet()
        .stream()
        .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), toYearDay(year, entry.getValue())))
        .sorted(Comparator.comparingInt(AbstractMap.SimpleEntry::getValue))
        .collect(Collectors.<Map.Entry<SeasonType, Integer>, SeasonType, Integer>toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  @Override
  protected boolean shouldComputeTransitions(long seasonLength) {
    return seasonLength > configuration.fullTransitionLength(false);
  }
}