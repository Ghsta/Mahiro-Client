package niku;

import net.minecraftforge.fml.common.Mod;
import niku.Event.TestEvent;

import javax.swing.*;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod("mahiro")
public class Mahiro {
    public static Mahiro INSTANCE = new Mahiro();
    public Mahiro instance;
    public Mahiro() {
        EVENT_BUS.register(new TestEvent());
        EVENT_BUS.register(this);
    }
    public void init()
    {   EVENT_BUS.register(new TestEvent());
        JOptionPane.showMessageDialog(null,"加载完毕");
        EVENT_BUS.register(this);
    }
}
