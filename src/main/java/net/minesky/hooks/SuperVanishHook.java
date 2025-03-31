package net.minesky.hooks;

import com.velocitypowered.api.proxy.Player;
import net.minesky.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SuperVanishHook {
    static List<UUID> PlayersIsVanished = new ArrayList<>();


    public static boolean isPlayerVanished(Player p) {
        return isPlayerVanished(p.getUniqueId());
    }

    public static boolean isPlayerVanished(String rawUsername) {
        return isPlayerVanished(Main.proxy.getPlayer(rawUsername).get());
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
