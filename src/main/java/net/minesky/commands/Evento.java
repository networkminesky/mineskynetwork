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

public class Evento {

    public static BrigadierCommand createBrigadierCommand(final ProxyServer proxy) {
        LiteralCommandNode<CommandSource> staffNode = BrigadierCommand.literalArgumentBuilder("evento")
                .requires(a -> a.hasPermission("mineskynetwork.evento"))
                .executes(context -> {
                    CommandSource source = context.getSource();
                    Player player = (Player) source;

                    player.createConnectionRequest(
                            proxy.getServer("eventos").orElse(null)
                    ).fireAndForget();

                    Component message = Component.text("Você deve indicar uma mensagem ao comando.", NamedTextColor.RED);
                    source.sendMessage(message);

                    return Command.SINGLE_SUCCESS;
                })
                .build();

        // BrigadierCommand implements Command
        return new BrigadierCommand(staffNode);
    }

}
