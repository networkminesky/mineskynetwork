package net.minesky.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import java.util.List;
import java.util.Objects;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minesky.Main;

public class Ping {
    public static BrigadierCommand createBrigadierCommand(final ProxyServer proxy) {
        LiteralCommandNode<CommandSource> staffNode = BrigadierCommand.literalArgumentBuilder("anunciar")
                .requires(a -> a.hasPermission("mineskynetwork.ping"))
                .executes(context -> {

                    ((CommandSource)context.getSource()).sendMessage(Component.text("O seu ping é de " + getPing((Player)context.getSource()) + "ms ", NamedTextColor.GREEN));

                    return Command.SINGLE_SUCCESS;
                })
                .then(BrigadierCommand.requiredArgumentBuilder("jogador", StringArgumentType.word())
                        .requires(a -> a.hasPermission("mineskynetwork.ping.others"))
                        .suggests((ctx, builder) -> {
                            Main.onlineWithoutVanished(proxy).forEach(builder::suggest);

                            builder.suggest("all");
                            return builder.buildFuture();
                        })
                        .executes(context -> {

                            final String s = context.getArgument("mensagem", String.class).replace('&', '§');
                            final Component parsedLegacyText = LegacyComponentSerializer.legacySection().deserialize(s);

                            Component t = Component.text(" \n§b§lM§f§lS§b§lN §8: §f")
                                    .append(parsedLegacyText)
                                    .append(Component.text("\n ")); // appendNewLine?

                            for(Player b : proxy.getAllPlayers()) {
                                b.sendMessage(t);
                                b.playSound(Sound.sound(Key.key("minecraft", "entity.experience_orb.pickup"),
                                        Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self());
                            }

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .build();

        // BrigadierCommand implements Command
        return new BrigadierCommand(staffNode);
    }

    private static int getPing(Player p) {
        return (int)p.getPing();
    }
}