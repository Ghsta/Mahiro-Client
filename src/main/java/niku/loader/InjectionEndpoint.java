package niku.loader;


import niku.Mahiro;

public class InjectionEndpoint {
    public static void Load() {
        (Mahiro.INSTANCE.instance = new Mahiro()).init();

    }
}
