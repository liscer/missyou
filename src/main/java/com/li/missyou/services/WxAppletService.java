package com.li.missyou.services;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.li.missyou.core.configuration.Code2SessionConfiguration;
import com.li.missyou.exception.http.ForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WxAppletService {
    public static final Logger LOGGER = LoggerFactory.getLogger(WxAppletService.class);
    @Autowired
    private Code2SessionConfiguration code2SessionConfiguration;
    public JSONObject jsCode2SessionInfo(String code) {
        Map<String, String> codes = code2SessionConfiguration.getCode();
        codes.put("js_code",code);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code",
                String.class,
                codes);
        String body = forEntity.getBody();
        if (body.contains("errcode")) {
            LOGGER.error(code+"出错了: "+body);
            throw new ForbiddenException(20004);
        }
        LOGGER.debug("body: "+body);
        return JSONUtil.parseObj(body);
    }
}
