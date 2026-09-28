package com.skd.teleport_animation;

import java.util.Locale;

final class TeleportCommandMatcher {
    private TeleportCommandMatcher() {
    }

    static boolean isTeleportCommand(String command) {
        return getArgumentString(command) != null;
    }

    static String getArgumentString(String command) {
        int runIndex;
        String normalized = command.stripLeading();
        if (normalized.startsWith("/")) {
            normalized = normalized.substring(1).stripLeading();
        }
        String lowerCase = normalized.toLowerCase(Locale.ROOT);
        for (String name : new String[]{"minecraft:teleport", "minecraft:tp", "teleport", "tp"}) {
            if (lowerCase.equals(name)) {
                return "";
            }
            if (!lowerCase.startsWith(name + " ")) continue;
            return normalized.substring(name.length()).stripLeading();
        }
        if (lowerCase.startsWith("execute ") && (runIndex = lowerCase.indexOf(" run ")) >= 0) {
            return getArgumentString(normalized.substring(runIndex + 5).stripLeading());
        }
        return null;
    }
}
