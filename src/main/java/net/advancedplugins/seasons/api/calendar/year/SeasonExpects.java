package net.advancedplugins.seasons.api.calendar.year;

import java.util.Optional;
import net.advancedplugins.seasons.api.season.SeasonSnapshot;
import net.advancedplugins.seasons.api.season.SeasonType;
import net.advancedplugins.seasons.api.util.YearTick;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SeasonExpects {

  @NotNull Optional<SeasonSnapshot> getSeasonAt(int yearTick);

  @NotNull Optional<SeasonSnapshot> getSeasonAtDay(int yearDay);

  @NotNull YearTick startOfSeason(@NotNull SeasonType seasonType);

  @NotNull YearTick endOfSeason(@NotNull SeasonType seasonType);

  @Nullable YearTick startOfPhase(@NotNull SeasonType seasonType, int transition);

  @Nullable YearTick endOfPhase(@NotNull SeasonType seasonType, int transition);

}