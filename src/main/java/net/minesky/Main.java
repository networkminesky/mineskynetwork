package net.minesky;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.inject.Inject;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import ir.syrent.velocityvanish.velocity.VelocityVanish;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minesky.commands.Anunciar;
import net.minesky.commands.GlobalList;
import net.minesky.commands.StaffChat;
import net.minesky.commands.Tell;
import net.minesky.events.PluginMessage;
import net.minesky.hooks.LiteBansHook;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Plugin(id = "mineskynetwork", name = "MineSky Network", version = "1.0.3-SNAPSHOT",
        description = "Plugin para funções gerais de Proxy", authors = {"Drawn", "BrunoC" }, dependencies = { @Dependency(id = "velocityvanish"), @Dependency(id = "litebans") })
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
        List<String> nova = new ArrayList<>();

        for(Player b : proxy.getAllPlayers()) {
            if(!VelocityVanish.instance.vanishedPlayersOnline().contains(b.getUsername()))
                nova.add(b.getUsername());
        }
        return nova;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        proxy.getChannelRegistrar().register(IDENTIFIER);

        proxy.getEventManager().register(this, new PluginMessage(proxy));
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

        LiteBansHook.registerEvents();

    }

}

