package net.advancedplugins.seasons.api.test;

import static org.junit.jupiter.api.Assertions.*;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.time.Month;
import java.time.MonthDay;
import java.util.Map;
import net.advancedplugins.seasons.api.calendar.configuration.CalendarConfiguration;
import net.advancedplugins.seasons.api.calendar.configuration.GregorianCalendarConfiguration;
import net.advancedplugins.seasons.api.calendar.day.DayLengthData;
import net.advancedplugins.seasons.api.calendar.year.YearExpects;
import net.advancedplugins.seasons.api.season.SeasonType;
import org.junit.jupiter.api.Test;

public class GregorianYearExpectsTest {

  private static final BiMap<SeasonType, MonthDay> DEFAULT_SEASON_START_DAYS = HashBiMap.create(
      Map.of(
          SeasonType.SPRING, MonthDay.of(Month.MARCH, 20),
          SeasonType.SUMMER, MonthDay.of(Month.JUNE, 20),
          SeasonType.FALL, MonthDay.of(Month.SEPTEMBER, 22),
          SeasonType.WINTER, MonthDay.of(Month.MARCH, 19) // WINTER will last 1 day
      )
  );

  private static final Map<SeasonType, DayLengthData> DEFAULT_DAY_CYCLES = Map.of(
      SeasonType.SPRING, new DayLengthData(12000, 12000),
      SeasonType.SUMMER, new DayLengthData(12000, 12000),
      SeasonType.FALL, new DayLengthData(12000, 12000),
      SeasonType.WINTER, new DayLengthData(12000, 12000)
  );

  private static final int DEFAULT_DAY_LENGTH = 24;
  private static final int DEFAULT_TRANSITION_DURATION_DAYS = 3;

  @Test
  public void testGregorianRangeTable() {
    CalendarConfiguration configuration = new GregorianCalendarConfiguration(
        "test",
        DEFAULT_DAY_LENGTH,
        DEFAULT_DAY_CYCLES,
        DEFAULT_TRANSITION_DURATION_DAYS,
        DEFAULT_SEASON_START_DAYS
    );

    int springStartDay = 80;
    int summerStartDay = 172;
    int fallStartDay = 266;
    int winterStartDay = 79;

    YearExpects yearExpects = configuration.newYearExpects(0);
    doAssertions(yearExpects, springStartDay, summerStartDay, fallStartDay, winterStartDay);
  }

  private void doAssertions(
      YearExpects yearExpects,
      int springDay,
      int summerDay,
      int fallDay,
      int winterDay
  ) {
    testSeasonDates(yearExpects, SeasonType.SPRING, springDay, summerDay);
    testSeasonDates(yearExpects, SeasonType.SUMMER, summerDay, fallDay);
    testSeasonDates(yearExpects, SeasonType.FALL, fallDay, winterDay);
    testSeasonDates(yearExpects, SeasonType.WINTER, winterDay, springDay);
  }

  private void testSeasonDates(YearExpects yearExpects, SeasonType seasonType, int seasonStartDay, int expectedEndDay) {
    assertEquals(seasonStartDay, yearExpects.startOfSeason(seasonType).toYearDay());
    assertEquals(expectedEndDay, yearExpects.endOfSeason(seasonType).toYearDay());

    if (seasonType == SeasonType.WINTER) { // WINTER lasts 1 day, so it doesn't have a transition
      assertNull(yearExpects.startOfPhase(seasonType, 1));
      assertNull(yearExpects.startOfPhase(seasonType, 2));
      assertNull(yearExpects.startOfPhase(seasonType, 3));
    } else {
      // On gregorian calendars, transition phases are calculated from the end of the season
      // to back.
      // So if a transition lasts 3 days and every phase lasts 1 day,
      // the first phase must start 3 days before the entire season ends
      assertEquals(expectedEndDay - 3, yearExpects.startOfPhase(seasonType, 1).toYearDay());
      assertEquals(expectedEndDay - 2, yearExpects.startOfPhase(seasonType, 2).toYearDay());
      assertEquals(expectedEndDay - 1, yearExpects.startOfPhase(seasonType, 3).toYearDay());
    }
  }
}