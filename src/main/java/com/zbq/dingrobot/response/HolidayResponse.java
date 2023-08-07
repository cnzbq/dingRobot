package com.zbq.dingrobot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Data
public class HolidayResponse {

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("msg")
    private String msg;

    @Data
    public static class DataDTO {
        @JsonProperty("date")
        private String date;
        @JsonProperty("work")
        private Boolean work;
    }
}
