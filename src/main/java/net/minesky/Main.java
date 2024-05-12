package net.minesky;

import com.google.inject.Inject;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import ir.syrent.velocityvanish.velocity.VelocityVanish;
import net.minesky.commands.Anunciar;
import net.minesky.commands.GlobalList;
import net.minesky.commands.StaffChat;
import net.minesky.commands.Tell;
import org.slf4j.Logger;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Plugin(id = "mineskynetwork", name = "MineSky Network", version = "1.0.0-SNAPSHOT",
        description = "Plugin para funções gerais de Proxy", authors = {"Drawn"}, dependencies = { @Dependency(id = "velocityvanish") })
public class Main {

    private final ProxyServer proxy;
    private final Logger logger;

    @Inject
    public Main(ProxyServer proxy, Logger logger) {
        this.proxy = proxy;
        this.logger = logger;

        logger.info("Plugin feito exclusivamente para o MineSky.");
    }

    public static List<String> onlineWithoutVanished(ProxyServer proxy) {
        List<String> nova = new ArrayList<>();

        for(Player b : proxy.getAllPlayers()) {
            if(!VelocityVanish.instance.vanishedPlayersOnline().contains(b.getUsername()))
                nova.add(b.getUsername());
        }
        return nova;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        CommandManager commandManager = proxy.getCommandManager();

        final BrigadierCommand staffChat = StaffChat.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(staffChat)
                .aliases("staffchat")
                .build(), staffChat);

        final BrigadierCommand tell = Tell.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(tell)
                .aliases("msg", "pm")
                .build(), tell);

        final BrigadierCommand gList = GlobalList.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(gList)
                .aliases("globallist")
                .build(), gList);

        final BrigadierCommand anunciar = Anunciar.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(anunciar)
                .build(), anunciar);
    }

}

