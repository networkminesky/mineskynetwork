package net.minesky.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.minesky.Main;

public class MessageUtils {

    public static boolean sendDataToBackgroundServers(final ByteArrayDataOutput data) {
        final byte[] dataAr = data.toByteArray();

        boolean right = true;

        // enviando a todos os servidores
        for(RegisteredServer server : Main.proxy.getAllServers()) {
            right = server.sendPluginMessage(Main.IDENTIFIER, dataAr);
        }

        return right;
    }



}
