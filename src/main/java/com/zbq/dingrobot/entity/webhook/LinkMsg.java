package com.zbq.dingrobot.entity.webhook;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LinkMsg extends MsgBase {

    /**
     * link类型
     */
    private Link link;

    @Override
    public String getMsgType() {
        return "link";
    }

    @Data
    public static class Link {
        /**
         * 消息内容。如果太长只会部分展示
         */
        private String text;
        /**
         * 消息标题
         */
        private String title;
        /**
         * 图片URL
         */
        private String picUrl;
        /**
         * 点击消息跳转的URL
         *
         * @link <a href="https://open.dingtalk.com/document/orgapp/custom-robot-access#title-72m-8ag-pqw">...</a>
         */
        private String messageUrl;
    }
}
