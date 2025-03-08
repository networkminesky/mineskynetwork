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
        Component p = Component.text("§4[s] §c§n"+origin+"§8: §f"+msg)
                .hoverEvent(HoverEvent.showText(Component.text("§7Servidor: §6"+originServer
                        +"\n§7Vanish: "+ /*(VelocityVanishHook.isPlayerVanished(origin) ? "§asim" : "§cnão")*/ "Talvez"
                )));

        for(Player bs : proxy.getAllPlayers()) {
            if(bs.hasPermission("mineskynetwork.staffchat"))
                bs.sendMessage(p);
        }
    }
}
