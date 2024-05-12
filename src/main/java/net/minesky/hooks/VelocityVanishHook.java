package net.minesky.hooks;

import com.velocitypowered.api.proxy.Player;
import ir.syrent.velocityvanish.velocity.VelocityVanish;

public class VelocityVanishHook {

    public static boolean isPlayerVanished(Player p) {
        return isPlayerVanished(p.getUsername());
    }

    public static boolean isPlayerVanished(String rawUsername) {
        return VelocityVanish.instance.vanishedPlayersOnline().contains(rawUsername);
    }

}
