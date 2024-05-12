package net.minesky.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class StaffChat {

    public static BrigadierCommand createBrigadierCommand(final ProxyServer proxy) {
        LiteralCommandNode<CommandSource> staffNode = BrigadierCommand.literalArgumentBuilder("s")
                // Here you can filter the subjects that can execute the command.
                // This is the ideal place to do "hasPermission" checks
                .requires(source -> source.hasPermission("mineskynetwork.staffchat"))
                // Here you can add the logic that will be used in
                // the execution of the "/test" command without any argument
                .executes(context -> {
                    // Here you get the subject that executed the command
                    CommandSource source = context.getSource();

                    Component message = Component.text("Você deve indicar uma mensagem ao comando.", NamedTextColor.RED);
                    source.sendMessage(message);

                    // Returning Command.SINGLE_SUCCESS means that the execution was successful
                    // Returning BrigadierCommand.FORWARD will send the command to the server
                    return Command.SINGLE_SUCCESS;
                })
                // Using the "then" method, you can add sub-arguments to the command.
                // For example, this subcommand will be executed when using the command "/test <some argument>"
                // A RequiredArgumentBuilder is a type of argument in which you can enter some undefined data
                // of some kind. For example, this example uses a StringArgumentType.word() that requires
                // a single word to be entered, but you can also use different ArgumentTypes provided by Brigadier
                // that return data of type Boolean, Integer, Float, other String types, etc
                .then(BrigadierCommand.requiredArgumentBuilder("mensagem", StringArgumentType.greedyString())
                        .executes(context -> {
                            String name = (context.getSource() instanceof Player d) ? d.getUsername() : "CONSOLE";
                            String sv = (context.getSource() instanceof Player d) ? d.getCurrentServer().get().getServerInfo().getName() : "Nenhum (Console)";

                            // Here you get the argument that the CommandSource has entered.
                            // You must enter exactly the name as you have named the argument
                            // and you must provide the class of the argument you expect, in this case... a String
                            String argumentProvided = context.getArgument("mensagem", String.class);

                            sendStaffMessage(proxy, name, argumentProvided, sv);

                            // Returning Command.SINGLE_SUCCESS means that the execution was successful
                            // Returning BrigadierCommand.FORWARD will send the command to the server
                            return Command.SINGLE_SUCCESS;
                        })
                )
                .build();

        // BrigadierCommand implements Command
        return new BrigadierCommand(staffNode);
    }

    static void sendStaffMessage(ProxyServer proxy, String origin, String msg, String originServer) {
        Component p = Component.text("§4[s] §c§n"+origin+"§8: §f"+msg)
                .hoverEvent(HoverEvent.showText(Component.text("§7Servidor: §6"+originServer)));

        for(Player bs : proxy.getAllPlayers()) {
            if(bs.hasPermission("mineskynetwork.staffchat"))
                bs.sendMessage(p);
        }
    }
}
