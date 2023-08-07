package com.zbq.dingrobot.utils;

import java.io.File;

/**
 * @author zbq
 * @since 7/31/2023
 */
public class Home {
    private static File home;

    public Home() {
    }

    public static File getHomeDirectory() {
        if (home == null)
            setHomeDirectory();

        return home;
    }

    private synchronized static void setHomeDirectory() {
        String dir = System.getProperty("push.home", ".");
        home = new File(dir);

        if (!home.isDirectory()) {
            home = new File(".");
        }
    }
}
