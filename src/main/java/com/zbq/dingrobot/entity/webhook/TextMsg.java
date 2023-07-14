package com.zbq.dingrobot.entity.webhook;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TextMsg extends MsgBase {

    /**
     * text类型
     */
    private Text text;

    /**
     * @人列表
     */
    private At at;

    @Override
    public String getMsgType() {
        return "text";
    }

    public static class Text {
        /**
         * 消息内容
         */
        private String content;
    }
}
