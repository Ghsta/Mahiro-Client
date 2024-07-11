package niku.mahiro.manager;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventManager {

    public Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String message = event.getMessage();
        if (message.equalsIgnoreCase("/hook 布吉岛")) {
            Minecraft mc = Minecraft.getInstance();
            mc.gui.getChat().addMessage(new net.minecraft.network.chat.TextComponent(ChatFormatting.DARK_RED + "----- +[Hook]:布吉岛注入成功-----"));

            mc.gui.getChat().addMessage(new net.minecraft.network.chat.TextComponent(ChatFormatting.AQUA + "----- +[Hook]:布吉岛注入成功-----"));
        }
    }

    @SubscribeEvent
    public void onRenderLiving(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player && entity.isInvisible()) {
            // 取消隐身效果
            entity.setInvisible(false);
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (player.isInvisible()) {
                player.setInvisible(false);
            } else {
                player.setInvisible(true);
            }
        }
    }
}
