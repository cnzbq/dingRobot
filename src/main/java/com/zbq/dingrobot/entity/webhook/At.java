package com.zbq.dingrobot.entity.webhook;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author zbq
 * @since 7/14/2023
 */
@Data
public class At {
    /**
     * 被@人的手机号
     */
    @Nullable
    private List<String> atMobiles;

    /**
     * 被@人的用户userid。
     */
    @Nullable
    private List<String> atUserIds;

    /**
     * 是否@所有人
     */
    @Nullable
    private Boolean isAtAll;
}
