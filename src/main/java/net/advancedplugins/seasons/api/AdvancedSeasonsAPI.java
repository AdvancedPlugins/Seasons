package net.advancedplugins.seasons.api;

import org.bukkit.World;
import org.bukkit.entity.Player;

@Deprecated
public class AdvancedSeasonsAPI {

  /**
   * @param world
   * @return The season of the specified world, does not include transition in the name, so it only would look like e.g. WINTER
   */
  public String getSeason(World world) {
    throw new UnsupportedOperationException("Unimplemented");
  }

  /**
   * @param world
   * @return The season of the specified world, includes transition in the name e.g. WINTER_TRANSITION_3,
   * there are 3 transitions and main season, e.g.  WINTER, WINTER_TRANSITION_1...3
   */
  public String getSeasonWithTransitions(World world) {
    throw new UnsupportedOperationException("Unimplemented");
  }

  /**
   * @param world
   * @param season Sets the season of the specified world.
   */
  public void setSeason(String season, World world) {
    throw new UnsupportedOperationException("Unimplemented");
  }

  /**
   * @param player
   * @return The player's temperature in degrees.
   */
  public int getTemperature(Player player) {
    throw new UnsupportedOperationException("Unimplemented");
  }

  /**
   * @param player
   * @param temperature Sets the player's temperature to the specified amount of degrees.
   */
  public void setTemperature(Player player, int temperature) {
    throw new UnsupportedOperationException("Unimplemented");
  }

  /**
   * @param player
   * @param temperature Boosts the player's temperature by the specified amount of degrees.
   */
  public void boostTemperature(Player player, int temperature) {
    throw new UnsupportedOperationException("Unimplemented");
  }
}