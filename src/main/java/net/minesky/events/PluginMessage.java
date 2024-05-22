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

import java.io.EOFException;

public class PluginMessage {
    private final ProxyServer proxy;

    public PluginMessage(ProxyServer proxy) {
        this.proxy = proxy;
    }

    @Subscribe
    public void onPluginMessageReceived(PluginMessageEvent event) {
        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String subChannel = ""; // meio bosta mas pra resolver problemas
        try {
            subChannel = in.readUTF();
        } catch(Exception ex) {
            return;
        }

        if (subChannel.equals("MessageEvent")) {
            String mensagem = in.readUTF();
            Component message = Component.text("NENHUM EVENTO");
            Component hoverTextSP = Component.text()
                    .append(Component.text("Spleef").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("  O evento se baseia em um lugar onde onde os players").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" estarão em uma pequena arena onde tentam derrubar um")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" ao outro quebrando a neve abaixo de onde os outros players estão.").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
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
            Component hoverTextTT = Component.text()
                    .append(Component.text("TNT-TAG").color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" Semelhante ao uma batata quente só que com tnt").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" Caso tenha a tnt na sua cabeça pegue alguém entre")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" 15 a 30 segundos! Antes que exploda em você!")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            Component hoverTextCDP = Component.text()
                    .append(Component.text("Corrida de Parapente").color(NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" O Objetivo é que os players passe em todos os").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" arcos utilizando o Parapente (ITEM CUSTOMIZADO DO SERVER)")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            Component hoverTextMS = Component.text()
                    .append(Component.text("Mini-Wars").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" O evento Mini-Wars é baseado em 4 times se").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" combatendo em uma pequena arena, defendendo a sua cor combatendo contra")
                            .color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" outros jogadores utilizando bestas e espadas").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            Component hoverTextCP = Component.text()
                    .append(Component.text("CopaPVP").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" Campeonato de x1 com ganhadores e posições finais.").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            Component hoverTextE = Component.text()
                    .append(Component.text("Esconde-Esconde").color(NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" Evento onde vários jogadores se escondem em uma").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" arena enquanto os staffers (ou outros jogadores) procuram todos os outros jogadores.").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();
            Component hoverTextR = Component.text()
                    .append(Component.text("Ruínas").color(NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.BOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("Descrição").color(NamedTextColor.GRAY))
                    .append(Component.text(":").color(NamedTextColor.GOLD))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text(" Evento onde vários jogadores tem que proteger os golems").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.text("| ").color(NamedTextColor.GOLD))
                    .append(Component.text("de ferro dos mobs que terá em cada wave.").color(NamedTextColor.GRAY))
                    .append(Component.newline())
                    .append(Component.newline())
                    .append(Component.text("CLIQUE AQUI PARA ENTRAR").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                    .build();

            switch (mensagem) {
                case "Spleef":
                    message = (Component) Component.text("§b§lSpleef §8| §aPara participar do evneto §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextSP);
                    break;
                case "TijolãoWars":
                    message = (Component) Component.text("§6§lTijolãoWars §8| §aPara participar do evneto §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextTW);
                    break;
                case "Corrida":
                    message = (Component) Component.text("§e§lCorrida §8| §aPara participar do evento §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextC);
                    break;
                case "TNTRun":
                    message = (Component) Component.text("§c§lTNTRUN §8| §aPara participar do evneto §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextTR);
                    break;
                case "CorridaBoat":
                    message = (Component) Component.text("§9§lCorrida de barco §8| §aPara participar do evneto §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextCB);
                    break;
                case "Sumo":
                    message = (Component) Component.text("§4§lSumo §8| §aPara participar do evento §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextS);
                    break;
                case "TNTTag":
                    message = (Component) Component.text("§c§lTNT-TAG §8| §aPara participar do evento §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextTT);
                    break;
                case "Parapente":
                    message = (Component) Component.text("§3§lCorrida de Parapente §8| §aPara participar do evento §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextCDP);
                    break;
                case "Mini-Wars":
                    message = (Component) Component.text("§6§lMINI-WARS §8| §aPara participar do evento §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextMS);
                    break;
                case "CopaPVP":
                    message = (Component) Component.text("§6§lCopaPVP §8| §aPara participar do evento §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextCP);
                    break;
                case "Esconde-esconde":
                    message = (Component) Component.text("§d§lEsconde-Esconde §8| §aPara participar do evento §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextE);
                    break;
                case "Ruínas":
                    message = (Component) Component.text("§d§lRuínas §8| §aPara participar do evento §lCLIQUE AQUI!")
                            .clickEvent(ClickEvent.runCommand("/joinqueue eventos"))
                            .hoverEvent(hoverTextR);
                    break;
            }

            for (Player player : proxy.getAllPlayers()) {
                player.sendMessage(message);
                player.playSound(Sound.sound(Key.key("entity.experience_orb.pickup"),
                        Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self());
            }
        }

        if (subChannel.equals("TextEvents")) {
            String mensagem = in.readUTF();
            Component mensagemFormatada = Component.text(mensagem);

            for (Player player : proxy.getAllPlayers()) {
                player.sendMessage(mensagemFormatada);
                player.playSound(Sound.sound(Key.key("entity.ender_dragon.ambient"), Sound.Source.AMBIENT, 1.0f, 1.0f), Sound.Emitter.self());
            }
        }

        if (subChannel.equals("ConectionEvents")) {
            String mensagem = in.readUTF();

            proxy.getPlayer(mensagem).ifPresent(a -> a.createConnectionRequest(proxy.getServer("lobby").orElse(null)).fireAndForget());
        }

        if (subChannel.equals("PlayerMessage")) {
            String p = in.readUTF();
            String mensagem = in.readUTF();
            Component mensagemF = Component.text(mensagem);

            proxy.getPlayer(p).ifPresent(d -> d.sendMessage(mensagemF)); // método mais seguro
        }
    }
}
