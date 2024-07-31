package net.advancedplugins.seasons.api.calendar.year;

import com.google.common.collect.ImmutableBiMap;
import net.advancedplugins.seasons.api.calendar.CalendarType;
import net.advancedplugins.seasons.api.calendar.configuration.CalendarConfiguration;
import net.advancedplugins.seasons.api.calendar.configuration.CustomCalendarConfiguration;
import net.advancedplugins.seasons.api.season.SeasonSnapshot;
import net.advancedplugins.seasons.api.season.SeasonType;
import net.advancedplugins.seasons.api.util.CircularIntRange;
import net.advancedplugins.seasons.api.util.YearTick;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Year;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractYearExpects<T extends CalendarConfiguration> implements YearExpects {

  protected final int year;
  private final @NotNull ImmutableBiMap<SeasonSnapshot, CircularIntRange> phaseRanges;
  private final @NotNull Map<SeasonSnapshot, CircularIntRange> orderedPhaseRanges = new LinkedHashMap<>();
  protected final @NotNull T configuration;

  public AbstractYearExpects(@NotNull T configuration, int year) {
    this.configuration = configuration;
    this.year = year;
    computeSeasonValues();
    this.phaseRanges = ImmutableBiMap.copyOf(orderedPhaseRanges);
  }

  @Override
  public int year() {
    return year;
  }

  @Override
  public int durationOfSeason(@NotNull SeasonType seasonType, boolean withTransitions, boolean inTicks) {
    YearTick start = startOfSeason(seasonType);
    YearTick end = endOfSeason(seasonType);

    int diff = Math.abs(start.tick() - end.tick());
    if (!withTransitions) {
      diff -= configuration.fullTransitionLength(true);
    }

    if (!inTicks) {
      diff /= configuration.dayLength();
    }

    return diff;
  }

  @Override
  public @NotNull Optional<SeasonSnapshot> getSeasonAt(int yearTick) {
    for (Map.Entry<CircularIntRange, SeasonSnapshot> entry : phaseRanges.inverse().entrySet()) {
      CircularIntRange range = entry.getKey();

      if (range.isBetween(yearTick)) {
        return Optional.of(entry.getValue());
      }
    }

    return Optional.empty();
  }

  @Override
  public @NotNull Optional<SeasonSnapshot> getSeasonAtDay(int yearDay) {
    return getSeasonAt(yearDay * configuration.dayLength());
  }

  @Override
  public @NotNull YearTick startOfSeason(@NotNull SeasonType seasonType) {
    return Objects.requireNonNull(startOfPhase(seasonType, 0), "Cannot get start of season");
  }

  @Override
  public @NotNull YearTick endOfSeason(@NotNull SeasonType seasonType) {
    return Objects.requireNonNull(startOfPhase(configuration.nextTo(seasonType), 0), "Cannot get end of season");
  }

  @Override
  public @Nullable YearTick startOfPhase(@NotNull SeasonType seasonType, int transition) {
    SeasonSnapshot snapshot = new SeasonSnapshot(seasonType, transition);
    CircularIntRange range = phaseRanges.get(snapshot);

    if (range == null) return null;

    return new YearTick(range.start(), configuration.dayLength());
  }

  @Override
  public @Nullable YearTick endOfPhase(@NotNull SeasonType seasonType, int transition) {
    SeasonSnapshot snapshot = new SeasonSnapshot(seasonType, transition);
    CircularIntRange range = phaseRanges.get(snapshot);

    if (range == null) return null;

    return new YearTick(range.end(), configuration.dayLength());
  }

  @Override
  public int maxDays() {
    if (configuration.type() == CalendarType.CUSTOM) {
      return ((CustomCalendarConfiguration) configuration).fullSeasonDays() * 4;
    }

    return Year.of(year).isLeap() ? 366 : 365;
  }

  @Override
  public int maxTicks() {
    return maxDays() * configuration.dayLength();
  }

  @Override
  public @NotNull ImmutableBiMap<SeasonSnapshot, CircularIntRange> phaseRanges() {
    return phaseRanges;
  }

  private void computeSeasonValues() {
    Map<SeasonType, Integer> seasonStartDays = seasonStartDays();

    seasonStartDays.forEach((season, startDay) -> {
      SeasonType next = configuration.nextTo(season);

      int nextSeasonStartDay = seasonStartDays.get(next);
      int nextSeasonStartTick = nextSeasonStartDay * configuration.dayLength();

      long seasonDays = Math.abs(startDay - nextSeasonStartDay);

      // By the moment we use this to adjust the end of the whole season
      // because the seasonStartDays() method returns the start day of every
      // season taking in care the length of the transition phases, but
      // the code below will process the transition phases
      // assuming that the end of this range
      // is the end of the initial season phase (equivalent to SeasonSnapshot(SeasonType, phase = 0)) and not the end of the whole season
      CircularIntRange range = calculateSeasonRange(startDay, nextSeasonStartDay);
      orderedPhaseRanges.put(new SeasonSnapshot(season, 0), range);

      // If the
      // transition duration is major than the total season duration,
      // it means that there's no space for the transition phases.
      // Just for gregorian calendars only.
      if (shouldComputeTransitions(seasonDays)) {
        for (int transitionIndex = 0; transitionIndex < 3; transitionIndex++) {
          int transitionStart = range.end() + configuration.singleTransitionLengthInTicks() * transitionIndex;
          int transitionEnd = transitionStart + configuration.singleTransitionLengthInTicks();

          if (transitionIndex == 2) {
            transitionEnd = nextSeasonStartTick;
          }

          orderedPhaseRanges.put(new SeasonSnapshot(season, transitionIndex + 1), newTickRange(transitionStart, transitionEnd, false));
        }
      }
    });
  }

  protected abstract CircularIntRange calculateSeasonRange(int start, int end);

  protected abstract Map<SeasonType, Integer> seasonStartDays();

  protected abstract boolean shouldComputeTransitions(long seasonLength);

  protected CircularIntRange newTickRange(int from, int to, boolean toTicks) {
    int dayLength = configuration.dayLength();
    if (toTicks) {
      from *= dayLength;
      to *= dayLength;
    }

    return new CircularIntRange(from, to, 0, maxTicks());
  }

  protected CircularIntRange newRange(int from, int to) {
    return new CircularIntRange(from, to, 0, maxDays());
  }
}