package com.zbq.dingrobot.entity.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Data
@NoArgsConstructor
public abstract class MsgBase {

    @JsonProperty("msgtype")
    private String msgType;

    public MsgBase(String msgType) {
        this.msgType = msgType;
    }
}
