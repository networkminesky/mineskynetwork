package net.minesky.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minesky.Main;
import net.minesky.hooks.SuperVanishHook;

public class StaffChat {

    public static BrigadierCommand createBrigadierCommand(final ProxyServer proxy) {
        LiteralCommandNode<CommandSource> staffNode = BrigadierCommand.literalArgumentBuilder("s")
                .requires(source -> source.hasPermission("mineskynetwork.staffchat"))
                .executes(context -> {
                    CommandSource source = context.getSource();

                    Component message = Component.text("Você deve indicar uma mensagem ao comando.", NamedTextColor.RED);
                    source.sendMessage(message);

                    return Command.SINGLE_SUCCESS;
                })
                .then(BrigadierCommand.requiredArgumentBuilder("mensagem", StringArgumentType.greedyString())
                        .executes(context -> {
                            String name = (context.getSource() instanceof Player d) ? d.getUsername() : "CONSOLE";
                            String sv = (context.getSource() instanceof Player d) ? d.getCurrentServer().get().getServerInfo().getName() : "Nenhum (Console)";

                            sendStaffMessage(proxy, name, context.getArgument("mensagem", String.class), sv);

                            return Command.SINGLE_SUCCESS;
                        })
                )
                .build();

        return new BrigadierCommand(staffNode);
    }

    static void sendStaffMessage(ProxyServer proxy, String origin, String msg, String originServer) {
        String isVanished = (SuperVanishHook.isPlayerVanished(origin)) ? "§asim" : "§cnão";
        Component p = Component.text("§4[s] §c§n"+origin+"§8: §f"+msg)
                .hoverEvent(HoverEvent.showText(Component.text("§7Servidor: §6"+originServer
                        +"\n§7Vanish: "+ (SuperVanishHook.isPlayerVanished(origin) ? "§asim" : "§cnão")
                )));

        Main.logger.info(translateColorsToANSI(LegacyComponentSerializer.legacySection().serialize(p)));
        proxy.getAllPlayers().stream()
                .filter(bs -> bs.hasPermission("mineskynetwork.staffchat"))
                .forEach(bs -> bs.sendMessage(p));

    }

    private static String translateColorsToANSI(String message) {
        return message
                .replace("§0", "\u001B[30m") // Preto
                .replace("§1", "\u001B[34m") // Azul
                .replace("§2", "\u001B[32m") // Verde
                .replace("§3", "\u001B[36m") // Aqua
                .replace("§4", "\u001B[31m") // Vermelho
                .replace("§5", "\u001B[35m") // Roxo
                .replace("§6", "\u001B[33m") // Dourado
                .replace("§7", "\u001B[37m") // Cinza Claro
                .replace("§8", "\u001B[90m") // Cinza Escuro
                .replace("§9", "\u001B[94m") // Azul Claro
                .replace("§a", "\u001B[92m") // Verde Claro
                .replace("§b", "\u001B[96m") // Ciano
                .replace("§c", "\u001B[91m") // Vermelho Claro
                .replace("§d", "\u001B[95m") // Magenta
                .replace("§e", "\u001B[93m") // Amarelo
                .replace("§f", "\u001B[97m") // Branco
                .replace("§r", "\u001B[0m")  // Resetar
                .replace("§n", "\u001B[4m");  // Linha
    }
}