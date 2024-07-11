package niku.mahiro.module;

import java.util.ArrayList;

public class ModuleManager {
    private final ArrayList<Module> modules;
    public static ModuleManager instance = new ModuleManager();

    public ModuleManager() {
        this.modules = new ArrayList<>();
        //this.modules.add(new Fly());
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public Module getModuleByName(String name) {
        for (Module m : this.modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Module> getModulesByCategory(Category c) {
        ArrayList<Module> mods = new ArrayList<>();
        for (Module m : this.modules) {
            if (m.getCategory() == c) {
                mods.add(m);
            }
        }
        return mods;
    }

    public ArrayList<Module> getActiveModules() {
        ArrayList<Module> active = new ArrayList<>();
        for (Module m : this.modules) {
            if (m.isToggled()) {
                active.add(m);
            }
        }
        return active;
    }
}
