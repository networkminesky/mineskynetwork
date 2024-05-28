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
        LiteralCommandNode<CommandSource> staffNode = BrigadierCommand.literalArgumentBuilder("ping")
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

                            proxy.getPlayer((String)context.getArgument("jogador", String.class)).ifPresentOrElse((p) -> {
                                ((CommandSource)context.getSource()).sendMessage(Component.text("O ping deste jogador é de " + getPing(p) + "ms ", NamedTextColor.GREEN));
                            }, () -> {
                                ((CommandSource)context.getSource()).sendMessage(Component.text("Jogador não encontrado.", NamedTextColor.RED));
                            });

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