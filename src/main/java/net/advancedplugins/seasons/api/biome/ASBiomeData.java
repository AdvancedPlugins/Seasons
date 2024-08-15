package net.advancedplugins.seasons.api.biome;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;

public record ASBiomeData(
    @NotNull NamespacedKey key,
    @NotNull @UnmodifiableView List<NamespacedKey> subBiomes,
    int temperature,
    @NotNull @UnmodifiableView Map<String, SeasonData> seasonDataMap
) {
  public record SeasonData(boolean snowy, Map<BiomeVisualKey, String> visualValues) {}
}