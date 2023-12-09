/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
@Configuration
public class NacosConfig {

    @Value("${nacos.addr:localhost}")
    private String addr;

    @Value("${nacos.port:8848}")
    private String port;

    @Value("${nacos.username:nacos}")
    private String username;

    @Value("${nacos.password:nacos}")
    private String password;

    /**
     * nacos配置中心
     */
    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR,addr + ":" + port);
        properties.put(PropertyKeyConst.USERNAME,username);
        properties.put(PropertyKeyConst.PASSWORD,password);
        return ConfigFactory.createConfigService(properties);
    }

    /**
     * 流控规则flowRule编码器
     */
    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return list -> JSON.toJSONString(list, SerializerFeature.PrettyFormat);
    }

    /**
     * 流控规则flowRule解码器
     */
    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    /**
     * 熔断规则degradeRule编码器
     */
    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return list -> JSON.toJSONString(list, SerializerFeature.PrettyFormat);
    }

    /**
     * 熔断规则degradeRule解码器
     */
    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }

    /**
     * 授权规则authority编码器
     */
    @Bean
    public Converter<List<AuthorityRuleEntity>, String> authorityRuleRuleEntityEncoder() {
        return list -> JSON.toJSONString(list, SerializerFeature.PrettyFormat);
    }

    /**
     * 授权规则authority解码器
     */
    @Bean
    public Converter<String, List<AuthorityRuleEntity>> authorityRuleEntityEntityDecoder() {
        return s -> JSON.parseArray(s, AuthorityRuleEntity.class);
    }

    /**
     * 热点参数规则paramFlow编码器
     */
    @Bean
    public Converter<List<ParamFlowRuleEntity>, String> paramFlowRuleRuleEntityEncoder() {
        return list -> JSON.toJSONString(list, SerializerFeature.PrettyFormat);
    }

    /**
     * 热点参数规则paramFlow解码器
     */
    @Bean
    public Converter<String, List<ParamFlowRuleEntity>> paramFlowRuleEntityEntityDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRuleEntity.class);
    }

    /**
     * 系统规则system编码器
     */
    @Bean
    public Converter<List<SystemRuleEntity>, String> systemRuleRuleEntityEncoder() {
        return list -> JSON.toJSONString(list, SerializerFeature.PrettyFormat);
    }

    /**
     * 系统规则system解码器
     */
    @Bean
    public Converter<String, List<SystemRuleEntity>> systemRuleEntityEntityDecoder() {
        return s -> JSON.parseArray(s, SystemRuleEntity.class);
    }

}
