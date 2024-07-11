package niku.mahiro;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import niku.mahiro.manager.EventManager;
import niku.mahiro.module.Module;
import niku.mahiro.module.ModuleManager;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

@Mod("mahiro")
public class Mahiro {
	public static Minecraft mc = Minecraft.getInstance();
	public static Mahiro INSTANCE = new Mahiro();
	public Mahiro instance;
	public Mahiro()
	{
		EVENT_BUS.register(new ModuleManager());
		EVENT_BUS.register(new EventManager());
		EVENT_BUS.register(this);
	}
	public void init()
	{
		EVENT_BUS.register(new ModuleManager());
		EVENT_BUS.register(new EventManager());
		EVENT_BUS.register(this);
	}
}
