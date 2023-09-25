package com.zbq.dingrobot.entity.webhook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zbq
 * @since 9/25/2023
 */
public class ImageFactory {

    private static final String URL_TEMPLATE = "https://images.pexels.com/photos/%s?auto=compress&cs=tinysrgb&w=320&h=160&dpr=1";

    private static final List<String> IMG = new ArrayList<>();

    static {
        IMG.add("461198/pexels-photo-461198.jpeg");
        IMG.add("1099680/pexels-photo-1099680.jpeg");
        IMG.add("920220/pexels-photo-920220.jpeg");
        IMG.add("299347/pexels-photo-299347.jpeg");
        IMG.add("2228559/pexels-photo-2228559.jpeg");
        IMG.add("3756498/pexels-photo-3756498.jpeg");
        IMG.add("2133989/pexels-photo-2133989.jpeg");
        IMG.add("5589043/pexels-photo-5589043.jpeg");
        IMG.add("708488/pexels-photo-708488.jpeg");
        IMG.add("793759/pexels-photo-793759.jpeg");
        IMG.add("691158/pexels-photo-691158.jpeg");
        IMG.add("2015191/pexels-photo-2015191.jpeg");
    }

    public static String getPic() {
        int day = LocalDate.now().getDayOfMonth();
        return String.format(URL_TEMPLATE, IMG.get(day % IMG.size()));
    }

}
