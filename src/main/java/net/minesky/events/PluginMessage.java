package net.minesky.events;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class PluginMessage {
    private final ProxyServer proxy;

    public PluginMessage(ProxyServer proxy) {
        this.proxy = proxy;
    }
    @Subscribe
    public void onPluginMessageReceived(PluginMessageEvent event) {
        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String subChannel = in.readUTF();

        if (subChannel.equals("MessageEvent")) {
            String mensagem = in.readUTF();
            Component message = Component.text("NENHUM EVENT");
            Component hoverTextTW = Component.text()
                    .append(Component.text("TijolãoWars").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" Um evento semelhante ao Mini-Wars, onde os jogadores").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" se combatem em uma arena arremessando tijolos em seus")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" adversários.").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            Component hoverTextC = Component.text()
                    .append(Component.text("Corrida").color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" O player que chegar primeiro, segundo e terceiro").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" ganhará o prêmio em dinheiro")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            Component hoverTextCB = Component.text()
                    .append(Component.text("Corrida de barco").color(NamedTextColor.BLUE).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" O objetivo do evento se encontra em os players").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" entrarem em barcos e fazerem uma corrida, qual")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" será no gelo e com barcos.")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            Component hoverTextTR = Component.text()
                    .append(Component.text("TNTRUN").color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" Os três últimos a sobreviver ganha os prêmios").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" (O prêmio vária de acordo com a sua colocação.)")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            Component hoverTextS = Component.text()
                    .append(Component.text("Sumo").color(NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" O Objetivo é os players empurrarem uns aos").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" outros da plataforma de gelo até o último sobrevivente")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            switch (mensagem) {
                case "TijolãoWars":
                    message = (Component) Component.text("§6§lTijolãoWars §8| §aPara participar do evneto §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/server eventos"))
                            .hoverEvent(hoverTextTW);
                    break;
                case "Corrida":
                    message = (Component) Component.text("§e§lCorrida §8| §aPara participar do evento §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/server eventos"))
                            .hoverEvent(hoverTextC);
                    break;
                case "TNTRun":
                    message = (Component) Component.text("§c§lTNTRUN §8| §aPara participar do evneto §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/server eventos"))
                            .hoverEvent(hoverTextTR);
                    break;
                case "CorridaBoat":
                    message = (Component) Component.text("§9§lCorrida de barco §8| §aPara participar do evneto §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/server eventos"))
                            .hoverEvent(hoverTextCB);
                    break;
                case "Sumo":
                    message = (Component) Component.text("§4§lSumo §8| §aPara participar do evneto §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/server eventos"))
                            .hoverEvent(hoverTextCB);
                    break;
            }
            for (Player player : proxy.getAllPlayers()) {
                player.sendMessage(message);
                player.playSound(Sound.sound(Key.key("minecraft", "entity.experience_orb.pickup"),
                        Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self());
            }
        }

        if (subChannel.equals("TextEvents")) {
            String mensagem = in.readUTF();
            Component mensagemFormatada = Component.text(mensagem);
            for (Player player : proxy.getAllPlayers()) {
                player.sendMessage(mensagemFormatada);
                player.playSound(Sound.sound(Key.key("entity.ender_dragon.ambient"), Sound.Source.AMBIENT, 1.0f, 1.0f));
            }
        }

        if (subChannel.equals("ConectionEvents")) {
            String mensagem = in.readUTF();
            Player player = proxy.getPlayer(mensagem).get();
            player.createConnectionRequest(proxy.getServer("lobby").orElse(null)).fireAndForget();
        }

        if (subChannel.equals("PlayerMessage")) {
            String p = in.readUTF();
            String mensagem = in.readUTF();
            Component mensagemF = Component.text(mensagem);
            Player player = proxy.getPlayer(p).get();
            player.sendMessage(mensagemF);
        }
    }
}
