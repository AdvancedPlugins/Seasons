package net.advancedplugins.seasons.api.calendar.year;

import com.google.common.collect.ImmutableBiMap;
import net.advancedplugins.seasons.api.calendar.configuration.CustomCalendarConfiguration;
import net.advancedplugins.seasons.api.season.SeasonType;
import net.advancedplugins.seasons.api.util.CircularIntRange;
import org.jetbrains.annotations.NotNull;

import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class CustomYearExpects extends AbstractYearExpects<CustomCalendarConfiguration> {

  private final @NotNull ImmutableBiMap<Month, CircularIntRange> monthRanges;

  public CustomYearExpects(@NotNull CustomCalendarConfiguration configuration, int year) {
    super(configuration, year);
    ImmutableBiMap.Builder<Month, CircularIntRange> monthRangesBuilder = new ImmutableBiMap.Builder<>();
    calculateMonthRanges(monthRangesBuilder::put);
    this.monthRanges = monthRangesBuilder.build();
  }

  @Override
  protected Map<SeasonType, Integer> seasonStartDays() {
    Map<SeasonType, Integer> starts = new LinkedHashMap<>();

    for (int i = 0; i < configuration.seasonSequence().size(); i++) {
      SeasonType seasonType = configuration.seasonSequence().get(i);
      starts.put(seasonType, configuration.fullSeasonDays() * i);
    }
    return starts;
  }

  @Override
  protected CircularIntRange calculateSeasonRange(int start, int end) {
    return newTickRange(start, end, true).reduceEnd(configuration.fullTransitionLength(true));
  }

  @Override
  protected boolean shouldComputeTransitions(long seasonLength) {
    return true;
  }

  public @NotNull ImmutableBiMap<Month, CircularIntRange> monthRanges() {
    return monthRanges;
  }

  private void calculateMonthRanges(BiConsumer<Month, CircularIntRange> consumer) {
    for (Month value : Month.values()) {
      int monthStart = configuration.monthStarts().get(value);

      int nextMonthNumber = value.getValue() + 1;

      if (nextMonthNumber > 12) {
        nextMonthNumber = 1;
      }

      Month nextMonth = Month.of(nextMonthNumber);

      int nextMonthStart = configuration.monthStarts().get(nextMonth);

      consumer.accept(value, new CircularIntRange(monthStart, nextMonthStart, 0, maxDays()));
    }
  }
}
