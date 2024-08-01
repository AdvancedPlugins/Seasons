package net.advancedplugins.seasons.api.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record CircularIntRange(int start, int end, int smallestValue, int higherValue) {

  /**
   * Creates a new IntRange.
   *
   * @param start The start of the range (inclusive)
   * @param end The end of the range (exclusive)
   * @param smallestValue The smallest value of the range
   * @param higherValue The higher value of the range
   */
  public CircularIntRange(int start, int end, int smallestValue, int higherValue) {
    this.start = start;
    this.end = end;
    this.higherValue = higherValue;
    this.smallestValue = smallestValue;

    if (end > higherValue) {
      throw new IllegalArgumentException(end + " > " + higherValue);
    }

    if (start < smallestValue) {
      throw new IllegalArgumentException(start + " < " + smallestValue);
    }
  }

  public boolean isBetween(int a) {
    if (start > end) {
      return (a >= start && a <= higherValue) || (a >= smallestValue && a < end);
    }

    return a >= start && a < end;
  }

  @Contract("_ -> new")
  public @NotNull CircularIntRange reduceEnd(int value) {
    int newEnd = end - value;

    if (newEnd < smallestValue) {
      newEnd = higherValue - value;
    }

    return new CircularIntRange(start, newEnd, smallestValue, higherValue);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CircularIntRange circularIntRange = (CircularIntRange) o;
    return start == circularIntRange.start
        && end == circularIntRange.end
        && higherValue == circularIntRange.higherValue
        && smallestValue == circularIntRange.smallestValue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end, smallestValue, higherValue);
  }

  @Override
  public String toString() {
    return "IntRange{"
        + "start="
        + start
        + ", end="
        + end
        + ", smallestValue="
        + smallestValue
        + ", higherValue="
        + higherValue
        + '}';
  }
}
