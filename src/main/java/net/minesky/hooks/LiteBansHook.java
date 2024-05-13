package net.minesky.hooks;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.proxy.Player;
import ir.syrent.velocityvanish.dependencies.cloud.commandframework.bukkit.parsers.OfflinePlayerArgument;
import litebans.api.Entry;
import litebans.api.Events;
import net.minesky.Main;
import net.minesky.utils.MessageUtils;

import javax.security.auth.callback.Callback;
import java.util.UUID;

public class LiteBansHook {

    public static void writeEvent(String punishType, String playerInfo, String staffer, String reason, String duration,
                                  String server, boolean silent) {
        ByteArrayDataOutput byteData = ByteStreams.newDataOutput();
        byteData.writeUTF("litebansevent");
        byteData.writeUTF(punishType+"|"+playerInfo+"|"+staffer+"|"+reason+"|"+duration+"|"+server+"|"+silent);

        MessageUtils.sendDataToBackgroundServers(byteData);
    }

    public static void registerEvents() {
        Events.get().register(new Events.Listener() {
            // evento quando alguem é mutado/banido/warnado etc
            @Override
            public void entryAdded(Entry entry) {
                Main.logger.info("Nova punição registrada, enviando messaging.");

                Player punishedPlayer = Main.proxy.getPlayer(UUID.fromString(entry.getUuid())).orElse(null);
                String nickname = punishedPlayer == null ? "Desconhecido [Bugado]" : punishedPlayer.getUsername() + " ["+entry.getUuid()+"]";

                    writeEvent(entry.getType().toUpperCase(),
                        nickname, entry.getExecutorName(), entry.getReason(), entry.getDurationString(),
                            entry.getServerOrigin()+", afetado: "+entry.getServerScope(), entry.isSilent());

            }
            // evento quando alguém é desbanido/desmutado/deswarnado etc
            @Override
            public void entryRemoved(Entry entry) {
                Main.logger.info("Nova punição removida, enviando messaging.");

                Player punishedPlayer = Main.proxy.getPlayer(UUID.fromString(entry.getUuid())).orElse(null);
                String nickname = punishedPlayer == null ? "Desconhecido [Bugado]" : punishedPlayer.getUsername() + " ["+entry.getUuid()+"]";

                writeEvent("UN"+entry.getType().toUpperCase(),
                        nickname, entry.getExecutorName(), entry.getReason(), entry.getDurationString(),
                        entry.getServerOrigin()+", afetado: "+entry.getServerScope(), entry.isSilent());

            }
        });
    }

}
