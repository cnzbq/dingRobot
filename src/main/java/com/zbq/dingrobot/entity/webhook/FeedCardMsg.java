package com.zbq.dingrobot.entity.webhook;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FeedCardMsg extends MsgBase {

    public FeedCardMsg() {
        super("feedCard");
    }

    private FeedCard feedCard;

    @Data
    public static class FeedCard {
        private List<Link> links;
    }

    @Data
    public static class Link {
        /**
         * 单条信息文本
         */
        private String title;
        /**
         * 点击单条信息到跳转链接
         */
        private String messageURL;
        /**
         * 单条信息后面图片的URL
         */
        private String picURL;
    }
}
