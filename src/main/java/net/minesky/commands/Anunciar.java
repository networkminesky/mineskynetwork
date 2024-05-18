package net.minesky.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Anunciar {

    public static BrigadierCommand createBrigadierCommand(final ProxyServer proxy) {
        LiteralCommandNode<CommandSource> staffNode = BrigadierCommand.literalArgumentBuilder("anunciar")
                .requires(a -> a.hasPermission("mineskynetwork.anunciar"))
                .executes(context -> {
                    CommandSource source = context.getSource();

                    Component message = Component.text("Você deve indicar uma mensagem ao comando.", NamedTextColor.RED);
                    source.sendMessage(message);

                    return Command.SINGLE_SUCCESS;
                })
                .then(BrigadierCommand.requiredArgumentBuilder("mensagem", StringArgumentType.greedyString())
                        .executes(context -> {

                            final String s = context.getArgument("mensagem", String.class).replace('&', '§');
                            final Component parsedLegacyText = LegacyComponentSerializer.legacySection().deserialize(s);

                            Component t = Component.text(" \n§6§lMine§f§lSky §8: §6")
                                    .append(parsedLegacyText.color(NamedTextColor.GOLD))
                                    .append(Component.text("\n "))
                                    .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, Component.text("" +
                                            "§a✔ Mensagem oficial do servidor!")));

                            for(Player b : proxy.getAllPlayers()) {
                                b.sendMessage(t);

                                // velocity ainda nao permite tocar sons, arrumar isso dps
                                Sound sound = Sound.sound(Key.key("entity.player.levelup"), Sound.Source.MASTER, 1f, 1.4f);
                                Audience audience = (Audience)b;

                                audience.playSound(sound, Sound.Emitter.self());
                            }

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .build();

        // BrigadierCommand implements Command
        return new BrigadierCommand(staffNode);
    }

}
