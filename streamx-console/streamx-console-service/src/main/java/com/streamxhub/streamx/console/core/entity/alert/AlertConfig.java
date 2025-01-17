/*
 * Copyright (c) 2019 The StreamX Project
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.streamxhub.streamx.console.core.entity.alert;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.streamxhub.streamx.console.base.util.JsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * t_alert_config
 *
 * @author weijinglun
 * @date 2022.01.14
 */
@Data
@TableName("t_alert_config")
@Slf4j
public class AlertConfig implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    /**
     * 报警组名称
     */
    private String alertName;

    /**
     * 报警类型
     */
    private Integer alertType;

    /**
     * 邮件报警配置信息
     */
    private String emailParams;

    /**
     * 钉钉报警配置信息
     */
    private String dingTalkParams;

    /**
     * 企微报警配置信息
     */
    private String weComParams;

    /**
     * 报警http回调配置信息
     */
    private String httpCallbackParams;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    public static AlertConfig of(AlertConfigWithParams params) {
        if (params == null) {
            return null;
        }
        AlertConfig alertConfig = new AlertConfig();
        BeanUtils.copyProperties(params, alertConfig, "emailParams", "dingTalkParams", "weComParams", "httpCallbackParams");
        try {
            if (params.getEmailParams() != null) {
                alertConfig.setEmailParams(JsonUtils.write(params.getEmailParams()));
            }
            if (params.getDingTalkParams() != null) {
                alertConfig.setDingTalkParams(JsonUtils.write(params.getDingTalkParams()));
            }
            if (params.getWeComParams() != null) {
                alertConfig.setWeComParams(JsonUtils.write(params.getWeComParams()));
            }
            if (params.getHttpCallbackParams() != null) {
                alertConfig.setHttpCallbackParams(JsonUtils.write(params.getHttpCallbackParams()));
            }
        } catch (JsonProcessingException e) {
            log.error("Json write failed", e);
        }
        return alertConfig;
    }

}
