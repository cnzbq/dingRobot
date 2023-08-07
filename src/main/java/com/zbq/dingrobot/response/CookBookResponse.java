package com.zbq.dingrobot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Dingwq
 * @since 7/13/2023
 */
@Data
public class CookBookResponse {

    private Integer code;

    private String msg;

    private String time;

    private CookBook data;

    @Data
    public static class CookBook {
        /**
         * 日期
         */
        @JsonProperty("today_time")
        @SerializedName("today_time")
        private List<String> todayTime;

        /**
         * 早餐
         */
        private List<String> breakfast;

        /**
         * 午餐
         */
        private List<String> lunch;

        /**
         * 小吃
         */
        @JsonProperty("lunch_snack")
        @SerializedName("lunch_snack")
        private List<String> lunchSnack;

        public String toMarkdown() {
            return build(this);
        }
    }

    private static String build(CookBookResponse.CookBook cookBook) {
        StringBuilder sb = new StringBuilder();
        build(sb, cookBook.getBreakfast(), "breakfast");
        wrap(sb);
        build(sb, cookBook.getLunch(), "lunch");
        wrap(sb);
        build(sb, cookBook.getLunchSnack(), "lunchSnack");
        return sb.toString();
    }

    private static void wrap(StringBuilder sb) {
        if (sb.length() != 0) {
            sb.append("\n");
        }
    }

    private static void build(StringBuilder sb, List<String> data, String type) {
        if (CollectionUtils.isEmpty(data) || !StringUtils.hasText(data.get(0))) {
            return;
        }

        String[] typeText = mapping.get(type);
        sb.append(String.format(head, typeText[0], typeText[1]));
        int length = data.size();
        for (int i = 0; i < length; i += 2) {
            String first = data.get(i);
            if (!StringUtils.hasText(first)) {
                return;
            }

            String second = (i + 1 < length) ? data.get(i + 1) : "";
            sb.append(String.format(body, first, second));
        }
    }

    private static final String head = """
            | **%s** | **%s** |
            | ------- | ------- |
            """;

    private static final String body = "| %s | %s |\n";

    private static final Map<String, String[]> mapping = Map.of("breakfast", new String[]{"早", "餐"},
            "lunch", new String[]{"午", "餐"}, "lunchSnack", new String[]{"小", "吃"});

}