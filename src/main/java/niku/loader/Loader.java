package niku.loader;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;


/**
 * loader.h 的class数据
 */

public class Loader extends Thread {

    private final byte[][] classes;


    public Loader(final byte[][] classes){
        this.classes = classes;
    }

    /**
     * 调用建构元
     *
     * @return 状态码 目前为0
     */

    public static int a(final byte[][] array) {
        try {
            new Loader(array).start();
        } catch (Exception ignored) {
        }
        return 0;
    }


    public static byte[][] a(final int n) {
        return new byte[n][];
    }

    @Override
    public void run() {
        try {
            String className = "niku.loader.InjectionEndpoint";
            ClassLoader contextClassLoader = null;
            for (final Thread thread : Thread.getAllStackTraces().keySet()) {
                if (thread.getName().equalsIgnoreCase("Render thread")) {contextClassLoader = thread.getContextClassLoader(); // 客户端线程的CL
                    System.out.println("发现线程！！！！！！！！！！！！！");
                    System.out.println("注入ing.............");
                }
                else System.out.println("线程未找到！！！！！！！！！！！！！");
            }
            if (contextClassLoader == null) {
                return;
            }

            Class unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            Module baseModule = Object.class.getModule();
            Class currentClass = Loader.class;
            long addr = unsafe.objectFieldOffset(Class.class.getDeclaredField("module"));
            unsafe.getAndSetObject(currentClass, addr, baseModule);

            this.setContextClassLoader(contextClassLoader);
            final Method declaredMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
            declaredMethod.setAccessible(true);

            Class clazz = null;
            // 逐个加载class
            for (final byte[] array : this.classes) {
                final Class clazz2 = (Class) declaredMethod.invoke(contextClassLoader, null, array, 0, array.length, contextClassLoader.getClass().getProtectionDomain());
                if (clazz2 != null && clazz2.getName().contains(className)) {
                    clazz = clazz2;
                }
            }
            // 调用初始化方法
            if (clazz != null) {
                clazz.getDeclaredMethod("Load").invoke(null);
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
