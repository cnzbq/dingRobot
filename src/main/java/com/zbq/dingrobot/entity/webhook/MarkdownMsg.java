package com.zbq.dingrobot.entity.webhook;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarkdownMsg extends MsgBase {

    /**
     * markdown类型
     */
    private Markdown markdown;

    /**
     * @人列表
     */
    private At at;

    @Override
    public String getMsgType() {
        return "markdown";
    }

    @Data
    public static class Markdown {
        /**
         * 首屏会话透出的展示内容
         */
        private String title;

        /**
         * markdown格式的消息
         */
        private String text;
    }
}
