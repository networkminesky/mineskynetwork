package net.minesky.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import net.minesky.Main;
import net.minesky.hooks.SuperVanishHook;

public class ProxyPing {
    @Subscribe
    public void onProxyPing(ProxyPingEvent event) {
        ServerPing org = event.getPing();

        ServerPing.Players players = new ServerPing.Players(
                Main.proxy.getAllPlayers().size() - SuperVanishHook.PlayersIsVanished.size(),
                org.getPlayers().get().getMax(),
                org.getPlayers().get().getSample()
        );

        event.setPing(new ServerPing(
                org.getVersion(),
                players,
                org.getDescriptionComponent(),
                org.getFavicon().orElse(null)
        ));
    }

}
