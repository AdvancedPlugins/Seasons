package net.advancedplugins.seasons.api.calendar.year;

import net.advancedplugins.seasons.api.calendar.configuration.CustomCalendarConfiguration;
import net.advancedplugins.seasons.api.season.SeasonType;
import net.advancedplugins.seasons.api.util.CircularIntRange;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomYearExpects extends AbstractYearExpects<CustomCalendarConfiguration> {
  public CustomYearExpects(@NotNull CustomCalendarConfiguration configuration, int year) {
    super(configuration, year);
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
}
