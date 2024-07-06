package niku.Event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TestEvent {

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String message = event.getMessage();
        if (message.equalsIgnoreCase("/hook 布吉岛")) {
            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;
            Minecraft.getInstance().gui.getChat().addMessage(new net.minecraft.network.chat.TextComponent(ChatFormatting.DARK_RED + "----- +[Hook]:布吉岛注入成功-----"));

            Minecraft.getInstance().gui.getChat().addMessage(new net.minecraft.network.chat.TextComponent(ChatFormatting.AQUA + "----- +[Hook]:布吉岛注入成功-----"));
        }
    }
}
