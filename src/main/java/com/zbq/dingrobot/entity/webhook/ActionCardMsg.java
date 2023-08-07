package com.zbq.dingrobot.entity.webhook;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ActionCardMsg extends MsgBase {

    public ActionCardMsg() {
        super("actionCard");
    }

    /**
     * actionCard类型
     */
    private ActionCard actionCard;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SingleActionCard extends ActionCard {
        /**
         * 单个按钮的方案。(设置此项和singleURL后btns无效。)
         */
        private String singleTitle;
        /**
         * 点击singleTitle按钮触发的URL
         */
        private String singleURL;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BtnsActionCard extends ActionCard {
        /**
         * 按钮的信息：title-按钮方案，actionURL-点击按钮触发的URL
         */
        private Btn[] btns;
    }

    @Data
    public static class Btn {
        /**
         * 按钮标题。
         */
        private String title;
        /**
         * 点击按钮触发的URL
         *
         * @link <a href="https://open.dingtalk.com/document/orgapp/custom-robot-access#title-72m-8ag-pqw">...</a>
         */
        private String actionURL;
    }

    @Data
    public static abstract class ActionCard {
        /**
         * 首屏会话透出的展示内容
         */
        private String title;

        /**
         * markdown格式的消息
         */
        private String text;

        /**
         * 0-按钮竖直排列，1-按钮横向排列
         */
        private String btnOrientation;
    }
}
