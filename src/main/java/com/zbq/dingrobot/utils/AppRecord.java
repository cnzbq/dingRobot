package com.zbq.dingrobot.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author Dingwq
 * @since 7/31/2023
 */
@Slf4j
public class AppRecord {

    private final static String DATE_FILE_PATH = Home.getHomeDirectory().getAbsolutePath() + "date_file";

    public static String lastDate() throws IOException {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(DATE_FILE_PATH)));
            return br.readLine();
        } catch (FileNotFoundException e) {
            log.info("Could not read date file {} ", DATE_FILE_PATH);
            return null;
        }
    }

    public static void writeLastDate(String date) throws IOException {
        File file = new File(DATE_FILE_PATH);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(date);
        }
    }
}
