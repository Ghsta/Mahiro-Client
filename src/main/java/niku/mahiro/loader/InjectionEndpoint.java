package niku.mahiro.loader;


import niku.mahiro.Mahiro;

public class InjectionEndpoint {
    public static void Load() {
        (Mahiro.INSTANCE.instance = new Mahiro()).init();

    }
}
