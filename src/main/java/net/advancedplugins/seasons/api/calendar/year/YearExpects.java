package net.advancedplugins.seasons.api.calendar.year;

import com.google.common.collect.ImmutableBiMap;
import net.advancedplugins.seasons.api.season.SeasonSnapshot;
import net.advancedplugins.seasons.api.season.SeasonType;
import net.advancedplugins.seasons.api.util.CircularIntRange;
import org.jetbrains.annotations.NotNull;

public interface YearExpects extends SeasonExpects {

  int year();

  int maxDays();

  int maxTicks();

  int durationOfSeason(@NotNull SeasonType seasonType, boolean withTransitions, boolean inTicks);

  @NotNull ImmutableBiMap<SeasonSnapshot, CircularIntRange> phaseRanges();

}