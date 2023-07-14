package com.zbq.dingrobot.entity.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Data
public abstract class MsgBase {

    @JsonProperty("msgtype")
    private String msgType;

    public abstract String getMsgType();
}
