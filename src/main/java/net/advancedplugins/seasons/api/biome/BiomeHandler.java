package net.advancedplugins.seasons.api.biome;

import java.util.List;
import java.util.Optional;
import net.advancedplugins.seasons.api.AdvancedSeasonsAssembly;
import net.advancedplugins.seasons.api.annotation.NotCache;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

@NotCache
public interface BiomeHandler {

  static @NotNull BiomeHandler instance() {
    return AdvancedSeasonsAssembly.biomeHandler();
  }

  @NotNull
  @UnmodifiableView
  List<ArtificialBiome> loadedBiomes();

  default @NotNull Optional<ArtificialBiome> getBiome(@NotNull String name) {
    return loadedBiomes().stream()
        .filter(artificialBiome -> artificialBiome.name().equals(name))
        .findFirst();
  }

  @NotNull
  Optional<ArtificialBiome> getBiomeAt(@NotNull Location location);
}