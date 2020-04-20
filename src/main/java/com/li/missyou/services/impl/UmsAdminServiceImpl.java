package com.li.missyou.services.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.li.missyou.common.utils.AESCBCUtil;
import com.li.missyou.core.configuration.jwt.JwtTokenGenerator;
import com.li.missyou.core.configuration.jwt.JwtTokenPair;
import com.li.missyou.dao.UmsUserRepository;
import com.li.missyou.dto.UmsUserDTO;
import com.li.missyou.exception.http.ForbiddenException;
import com.li.missyou.services.UmsAdminService;
import com.li.missyou.vo.UserAuthVO;
import com.li.missyou.vo.UserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Optional;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    private UmsUserRepository umsUserRepository;


    @Override
    public JwtTokenPair createNewUser(JSONObject bodyObject) {
        String openid = bodyObject.getStr("openid");
        JwtTokenPair jwtTokenPair = jwtTokenGenerator.jwtTokenPair(openid);
        Optional<UmsUserDTO> result = umsUserRepository.queryByOpenId(openid);
        if (result.isPresent()) {
            LOGGER.error(openid + ": 用户已存在");
            throw new ForbiddenException(20001);
        }
        umsUserRepository.save(new UmsUserDTO(openid, new Date(),
                bodyObject.get("session_key", String.class)));
        return jwtTokenPair;
    }

    @Override
    public String updateUser(String openid) {
        UmsUserDTO umsUser = getUserByOpenID(openid);
        umsUser.setLastVisitTime(new Date());
        umsUserRepository.save(umsUser);
        return "ok";
    }

    @Override
    public JwtTokenPair refreshToken(String openid) {
        return jwtTokenGenerator.jwtTokenPair(openid);
    }

    @Override
    public String getUnionIdByAuthInfo(UserAuthVO userAuth, String openid) {
        UmsUserDTO umsUser = getUserByOpenID(openid);
        return signAndUpdate(userAuth, umsUser);
    }

    @Override
    public String getUnionIdByAuthInfo(UserAuthVO userAuth, JSONObject bodyObject) {
        UmsUserDTO umsUser = getUserByOpenID(bodyObject.getStr("openid"));
        umsUser.setSessionKey(bodyObject.getStr("sessionkey"));
        return signAndUpdate(userAuth, umsUser);
    }

    private UmsUserDTO getUserByOpenID(String openid) {
        Optional<UmsUserDTO> result = umsUserRepository.queryByOpenId(openid);
        if (!result.isPresent()) {
            LOGGER.error(openid + ": 用户不存在");
            throw new ForbiddenException(20002);
        }
        return result.get();
    }

    private String signAndUpdate(UserAuthVO userAuth, UmsUserDTO umsUser) {
        boolean signResult = signatureVerification(userAuth, umsUser);
        String unionID = DecryptEncrypted(userAuth, umsUser);
        if (signResult) {
            UserInfoVO userInfo = JSONUtil.toBean(userAuth.getRawData(), UserInfoVO.class);
            umsUser.setNickName(userInfo.getNickName());
            umsUser.setGender(userInfo.getGender());
            umsUser.setCity(userInfo.getCity());
            umsUser.setProvince(userInfo.getProvince());
            umsUser.setCountry(userInfo.getCountry());
            umsUser.setAvatarUrl(userInfo.getAvatarUrl());
            if (unionID != null) {
                umsUser.setUnionId(unionID);
            }
            umsUserRepository.save(umsUser);
        }
        return "成功";
    }


    private String DecryptEncrypted(UserAuthVO userAuth, UmsUserDTO umsUser) {
        AESCBCUtil aescbcUtil = new AESCBCUtil();
        byte[] result = aescbcUtil.decrypt(userAuth.getEncryptedData(), umsUser.getSessionKey(), userAuth.getIv());
        JSONObject authObject = JSONUtil.parseObj(new String(result, Charset.forName("UTF-8")));
        LOGGER.debug("authObject" + authObject.toString());
        return authObject.get("unionId", String.class);
    }

    private boolean signatureVerification(UserAuthVO userAuth, UmsUserDTO umsUser) {
        String data = userAuth.getRawData() + umsUser.getSessionKey();
        String result = SecureUtil.sha1(data);
        LOGGER.info("result" + result);
        LOGGER.info("encry" + userAuth.getEncryptedData());
        return result.equals(userAuth.getSignature());
    }

}
