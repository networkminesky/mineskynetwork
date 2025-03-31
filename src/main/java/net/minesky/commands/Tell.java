package net.minesky.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minesky.Main;
import net.minesky.hooks.SuperVanishHook;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tell {

    static final TextColor rosaClaro = TextColor.fromHexString("#f77293");
    static final TextColor rosaEscuro = TextColor.fromHexString("#db4275");

    public static BrigadierCommand createBrigadierCommand(final ProxyServer proxy) {
        LiteralCommandNode<CommandSource> staffNode = BrigadierCommand.literalArgumentBuilder("tell")
                .executes(context -> {
                    CommandSource source = context.getSource();

                    Component message = Component.text("Uso correto: /tell <jogador> <mensagem>", NamedTextColor.RED);
                    source.sendMessage(message);

                    return Command.SINGLE_SUCCESS;
                })
                .then(BrigadierCommand.requiredArgumentBuilder("jogador", StringArgumentType.word())
                        .suggests((ctx, builder) -> {
                            Main.onlineWithoutVanished(proxy).forEach(builder::suggest);

                            builder.suggest("all");
                            return builder.buildFuture();
                        })
                        .executes(context -> {
                            CommandSource source = context.getSource();

                            Component message = Component.text("Uso correto: /tell <jogador> <mensagem>", NamedTextColor.RED);
                            source.sendMessage(message);

                            return Command.SINGLE_SUCCESS;
                        })
                        .then(BrigadierCommand.requiredArgumentBuilder("mensagem", StringArgumentType.greedyString())
                                .executes(context -> {

                                    Player sender = (Player)context.getSource();

                                    final String msg = context.getArgument("mensagem", String.class);
                                    final String player = context.getArgument("jogador", String.class);

                                    proxy.getPlayer(player).ifPresentOrElse(target -> {
                                        sendTell(sender, target, msg);
                                    }, () -> {
                                        sender.sendMessage(Component.text("Esse jogador não existe ou não está online.", NamedTextColor.RED));
                                    });

                                    return Command.SINGLE_SUCCESS;
                                })
                        )
                )
                .build();

        // BrigadierCommand implements Command
        return new BrigadierCommand(staffNode);
    }

    static void sendTell(Player source, Player who, String msg) {

        if(who == null || (SuperVanishHook.isPlayerVanished(who) && !source.hasPermission("mineskynetwork.tell.sendtovanished"))) {
            source.sendMessage(Component.text("Esse jogador não existe ou não está online.", NamedTextColor.RED));
            return;
        }

        if(source.equals(who)) {
            source.sendMessage(Component.text("Você não pode enviar mensagens para você mesmo.", NamedTextColor.RED));
            return;
        }

        // unsafe
        final String outrosv = who.getCurrentServer().get().equals(source.getCurrentServer().get()) ?
                "" : "\n\n§6* Jogador em outro servidor";

        Component paraQuemTaRecebendo = Component.text("[", rosaClaro)
                .append(Component.text("⏿", rosaClaro, TextDecoration.BOLD))
                .append(Component.text("] ", rosaClaro))
                .append(Component.text(source.getUsername(), Style.style(rosaClaro, TextDecoration.ITALIC, TextDecoration.BOLD)))
                .append(Component.text(" -> ", NamedTextColor.GRAY))
                .append(Component.text("Você", Style.style(rosaEscuro, TextDecoration.ITALIC)))
                .append(Component.text(":", NamedTextColor.DARK_GRAY))
                .append(Component.text(" "+msg, rosaClaro, TextDecoration.ITALIC))
                .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, Component.text("§eClique para responder rapidamente."+outrosv)))
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell "+source.getUsername()+" "));

        Component paraQuemEnviou = Component.text("[", rosaClaro)
                .append(Component.text("⏿", rosaClaro, TextDecoration.BOLD))
                .append(Component.text("] ", rosaClaro))
                .append(Component.text("Você", Style.style(rosaEscuro, TextDecoration.ITALIC)))
                .append(Component.text(" -> ", NamedTextColor.GRAY))
                .append(Component.text(who.getUsername(), Style.style(rosaClaro, TextDecoration.ITALIC, TextDecoration.BOLD)))
                .append(Component.text(":", NamedTextColor.DARK_GRAY))
                .append(Component.text(" "+msg, rosaClaro, TextDecoration.ITALIC));

        who.playSound(Sound.sound(Key.key("block.note_block.bell"),
                Sound.Source.MASTER, 0.5f, 1.2f), Sound.Emitter.self());

        source.sendMessage(paraQuemEnviou);
        who.sendMessage(paraQuemTaRecebendo);

    }
}
