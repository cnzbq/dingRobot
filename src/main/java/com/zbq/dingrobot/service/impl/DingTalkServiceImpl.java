package com.zbq.dingrobot.service.impl;

import com.aliyun.dingtalkoauth2_1_0.Client;
import com.zbq.dingrobot.service.DingTalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zbq
 * @since 6/29/2022
 */
@Service
public class DingTalkServiceImpl implements DingTalkService {
    @Autowired
    private Client client;


    @Override
    public String getAccessToken() {

        return null;
    }
}
