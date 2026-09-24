package com.skd.teleport_animation;

import net.minecraft.resources.ResourceKey;
import java.util.Locale;

final class DimensionIds {
    static final String OVERWORLD = "minecraft:overworld";
    static final String NETHER = "minecraft:the_nether";
    static final String END = "minecraft:the_end";

    private DimensionIds() {
    }

    static String fromResourceKey(ResourceKey<?> key) {
        if (key == null) {
            return null;
        }
        return normalize(key.identifier().toString());
    }

    static String normalize(String raw) {
        if (raw == null) {
            return null;
        }
        String value = raw.trim().toLowerCase(Locale.ROOT);
        if (value.isEmpty()) {
            return null;
        }
        if (value.contains(OVERWORLD)) {
            return OVERWORLD;
        }
        if (value.contains(NETHER)) {
            return NETHER;
        }
        if (value.contains(END)) {
            return END;
        }
        return switch (value) {
            case "overworld", "minecraft:overworld" -> OVERWORLD;
            case "nether", "the_nether", "minecraft:nether" -> NETHER;
            case "end", "the_end", "minecraft:end" -> END;
            default -> value;
        };
    }
}