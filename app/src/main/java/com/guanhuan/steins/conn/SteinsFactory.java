
package com.guanhuan.steins.conn;

/**
 * SteinsRetrofit需要改善
 * @Auther: guanhuan_li
 * @Date: 16:39 2017/11/15
 */
public class SteinsFactory {

    protected static final Object monitor = new Object();
    private static UserApi userIOSingleton = null;
    private static boolean isDebug = true;


    public static UserApi getUserIOSingleton() {
        synchronized (monitor) {
            if (userIOSingleton == null) {
                userIOSingleton = new SteinsRetrofit().getUserService();
            }
            return userIOSingleton;
        }
    }

    public static boolean isDebug(){
        return isDebug;
    }

}
