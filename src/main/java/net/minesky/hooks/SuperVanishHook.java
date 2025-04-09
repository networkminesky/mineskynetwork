package net.minesky.hooks;

import com.velocitypowered.api.proxy.Player;
import net.minesky.Main;

import java.util.*;

public class SuperVanishHook {
    public static Set<UUID> PlayersIsVanished = new HashSet<>();


    public static boolean isPlayerVanished(Player p) {
        return isPlayerVanished(p.getUniqueId());
    }

    public static boolean isPlayerVanished(String rawUsername) {
        return Main.proxy.getPlayer(rawUsername)
                .map(SuperVanishHook::isPlayerVanished)
                .orElse(false);
    }

    public static boolean isPlayerVanished(UUID rawUUID) {
        return PlayersIsVanished.contains(rawUUID);
    }

    public static void setPlayersIsVanished(UUID uuid) {
        PlayersIsVanished.add(uuid);
    }

    public static void removePlayersIsVanished(UUID uuid) {
        PlayersIsVanished.remove(uuid);
    }
}
