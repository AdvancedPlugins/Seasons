package net.advancedplugins.seasons.api.biome;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Objects;

public record ArtificialBiome(
    @NotNull List<NamespacedKey> subBiomes,
    @NotNull String name,
    boolean isSnowy,
    int temperature
) {
  @Override
  public @UnmodifiableView @NotNull List<NamespacedKey> subBiomes() {
    return subBiomes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ArtificialBiome that = (ArtificialBiome) o;
    return isSnowy == that.isSnowy && temperature == that.temperature && Objects.equals(name, that.name) && Objects.equals(subBiomes, that.subBiomes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subBiomes, name, isSnowy, temperature);
  }
}