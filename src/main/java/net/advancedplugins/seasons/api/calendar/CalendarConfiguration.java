package net.advancedplugins.seasons.api.calendar;

import net.advancedplugins.seasons.api.calendar.day.DayDuration;
import net.advancedplugins.seasons.api.season.SeasonType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class CalendarConfiguration {

  private final @UnmodifiableView List<SeasonType> seasonSequence;
  private final @UnmodifiableView Map<SeasonType, DayDuration> dayDurationBySeason;
  private final int dayDurationInTicks;
  private final int transitionDurationInDays;

  public CalendarConfiguration(@UnmodifiableView List<SeasonType> seasonSequence, @NotNull Map<SeasonType, DayDuration> dayDurationBySeason, int dayDurationInTicks, int transitionDurationInDays) {
    this.seasonSequence = Collections.unmodifiableList(seasonSequence);
    this.dayDurationBySeason = Collections.unmodifiableMap(dayDurationBySeason);
    this.transitionDurationInDays = transitionDurationInDays;
    this.dayDurationInTicks = dayDurationInTicks;

    // TODO validate dayDurationInTicks with the total duration of the seasons' days
    // TODO check no repeated seasons on seasonSequence
    // TODO check that all season types are in seasonSequence (or maybe not, idk if evict this check to let the user remove not wanted seasons)
  }

  public @UnmodifiableView Map<SeasonType, DayDuration> dayDurationBySeason() {
    return dayDurationBySeason;
  }

  public @UnmodifiableView List<SeasonType> seasonSequence() {
    return seasonSequence;
  }

  public int transitionDurationInDays() {
    return transitionDurationInDays;
  }

  public int dayDurationInTicks() {
    return dayDurationInTicks;
  }

  public @NotNull SeasonType nextTo(@NotNull SeasonType type) {
    int index = type.ordinal();
    int next = index + 1;
    next = next >= seasonSequence.size() ? 0 : next;

    return SeasonType.values()[next];
  }

  public @NotNull SeasonType previousTo(@NotNull SeasonType type) {
    int index = type.ordinal();
    int previous = index - 1;
    previous = previous > 0 ? seasonSequence.size() - 1 : previous;

    return SeasonType.values()[previous];
  }

  public abstract @NotNull CalendarType type();

  public abstract int seasonDuration(@NotNull SeasonType seasonType);

  public static class Gregorian extends CalendarConfiguration {
    private final @UnmodifiableView Map<SeasonType, MonthDay> seasonStartDates;

    public Gregorian(@NotNull List<SeasonType> seasonSequence, @NotNull Map<SeasonType, DayDuration> dayDurationBySeason, int dayDurationInTicks, int transitionDurationInDays, @NotNull Map<SeasonType, MonthDay> seasonStartDates) {
      super(seasonSequence, dayDurationBySeason, dayDurationInTicks, transitionDurationInDays);
      this.seasonStartDates = Collections.unmodifiableMap(seasonStartDates);
      // TODO check seasonStartDates matches with seasonSequence
    }

    public @UnmodifiableView Map<SeasonType, MonthDay> seasonStartDates() {
      return seasonStartDates;
    }

    @Override
    public final @NotNull CalendarType type() {
      return CalendarType.GREGORIAN;
    }

    @Override
    public int seasonDuration(@NotNull SeasonType seasonType) {
      Instant instant = Instant.now();
      int startYear = instant.get(ChronoField.YEAR);
      int nextSeasonYear = startYear;

      MonthDay currentSeasonStart = seasonStartDates.get(seasonType);
      MonthDay nextSeasonStart = seasonStartDates.get(nextTo(seasonType));

      int startDayValue = currentSeasonStart.getDayOfMonth();
      int endDayValue = nextSeasonStart.getDayOfMonth();

      int startMonthIndex = currentSeasonStart.getMonthValue();
      int endMonthIndex = nextSeasonStart.getMonthValue();

      if (startMonthIndex < endMonthIndex) {
        // Year change
        nextSeasonYear++;
      }

      LocalDate start = LocalDate.of(startYear, startMonthIndex, startDayValue);
      LocalDate end = LocalDate.of(nextSeasonYear, endMonthIndex, endDayValue);

      return (int) ChronoUnit.DAYS.between(start, end);
    }
  }

  public static class Custom extends CalendarConfiguration {
    private final int seasonDurationInDays;

    public Custom(@NotNull List<SeasonType> seasonSequence, @NotNull Map<SeasonType, DayDuration> dayDurationBySeason, int dayDurationInTicks, int transitionDurationInDays, int seasonDurationInDays) {
      super(seasonSequence, dayDurationBySeason, dayDurationInTicks, transitionDurationInDays);
      this.seasonDurationInDays = seasonDurationInDays;
    }

    public int seasonDurationInDays() {
      return seasonDurationInDays;
    }

    @Override
    public @NotNull CalendarType type() {
      return CalendarType.CUSTOM;
    }

    @Override
    public int seasonDuration(@NotNull SeasonType seasonType) {
      return seasonDurationInDays;
    }
  }
}