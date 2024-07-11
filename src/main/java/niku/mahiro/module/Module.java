package niku.mahiro.module;

import net.minecraft.client.Minecraft;

public class Module {
    private final String name;
    private final String description;
    private int key;
    private final Category category;
    private boolean toggled;

    protected Minecraft mc = Minecraft.getInstance();

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.key = 0;
        this.toggled = false;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        if (this.toggled)
            this.onEnable();
        else
            this.onDisable();
    }

    public void toggle() {
        this.toggled = !this.toggled;

        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onTick() {

    }
}
