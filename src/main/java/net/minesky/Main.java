package net.minesky;

import com.google.inject.Inject;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import net.minesky.commands.*;
import net.minesky.listeners.PluginMessage;
import net.minesky.hooks.SuperVanishHook;
import net.minesky.listeners.ProxyPing;
import org.slf4j.Logger;

import java.util.List;

@Plugin(id = "mineskynetwork", name = "MineSky Network", version = "1.0.3-SNAPSHOT",
        description = "Plugin para funções gerais de Proxy", authors = {"Drawn", "BrunoC" },
        dependencies = { })
public class Main {

    public static ProxyServer proxy;
    public static Logger logger;
    public static final MinecraftChannelIdentifier IDENTIFIER = MinecraftChannelIdentifier.from("minesky:proxy");

    @Inject
    public Main(ProxyServer proxy, Logger logger) {
        Main.proxy = proxy;
        this.logger = logger;

        logger.info("Plugin feito exclusivamente para o MineSky.");
    }

    public static List<String> onlineWithoutVanished(ProxyServer proxy) {
        return proxy.getAllPlayers().stream()
                .filter(b -> !SuperVanishHook.isPlayerVanished(b))
                .map(Player::getUsername)
                .toList();
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        proxy.getChannelRegistrar().register(IDENTIFIER);

        proxy.getEventManager().register(this, new PluginMessage(proxy));
        proxy.getEventManager().register(this, new ProxyPing());
        CommandManager commandManager = proxy.getCommandManager();

        final BrigadierCommand staffChat = StaffChat.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(staffChat)
                .aliases("staffchat")
                .build(), staffChat);

        final BrigadierCommand tell = Tell.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(tell)
                .aliases("msg", "pm")
                .build(), tell);

        final BrigadierCommand evento = Evento.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(evento)
                .aliases("eventos")
                .build(), evento);

        final BrigadierCommand ping = Ping.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(ping)
                .build(), ping);

        final BrigadierCommand gList = GlobalList.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(gList)
                .aliases("globallist")
                .build(), gList);

        final BrigadierCommand anunciar = Anunciar.createBrigadierCommand(proxy);
        commandManager.register(commandManager.metaBuilder(anunciar)
                .build(), anunciar);

        //LiteBansHook.registerEvents();

    }

}

