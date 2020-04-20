package com.li.missyou;


import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.missyou.common.utils.AESCBCUtil;
import com.li.missyou.dao.*;
import com.li.missyou.dto.*;
import com.li.missyou.vo.SpecVO;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

//@SpringBootTest
public class MissYouTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(MissYouTest.class);
    @Autowired
    private ObjectMapper mapper;
    @Test
    public void test11() {
        String sessionKey = "oM3aTT8qoK1Jvg9iAtZ28g==";
        String rawData ="{\"nickName\":\"nimm\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"\",\"province\":\"Tianjin\",\"country\":\"China\",\"avatarUrl\":\"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLJBeibgyrTZEMgFRTfqZh3qCqOs0wicZWhLD2TS3b56hqbIicaicz4Pb84EcHGqxZknr6XUHIO1eyAAA/132\"}";
        String result = SecureUtil.sha1(rawData + sessionKey);
        System.out.println(result);
    }

    @Test
    public void aestest() throws UnsupportedEncodingException {
        String key = "oM3aTT8qoK1Jvg9iAtZ28g==";
        String iv = "WzXgkkAaHc+Ta4vFndXrug==";
        String encryptedData = "exzFjjgI5l3UJNiJf5x9f51o9w0ebWyFAhYx77WRXwULN0KZ59Jy+NULRBpvILN0AD6o9Z9AHdUBcAqvoYn6WKm97C7n6CnIEqPVDlGj4bHT+/X823F3B7I2xs+I1pGVW9Jbk0OVHogJrhtnVTbucPF7Vri0MWvo+drw20jC/HICds0YRJU0JRe4hdKcvWK6SxwsVd1UqgMH7vHwv28mMLZQ1eHWS/bgkG3UFHIVS8eNYrsMzWtVzc/w4/ZwUK6EkbohWvT7NsQrhqw7BP8f0/DS6HJJGO+E6izj7IJNNYjK/NJYXm884APR4CDno8fj32N4heJWoY+6rnPpTY0ZmGm0t5gsnFUjgylITo+Ly+qz5qMoPLaz7l2ABbuFfcT788JomgkDfNLs+rx6U11HmGiptuBMBO+4MFw3ru7VJm9Sk+BxhPKLjx8DLyINDgbJ8QZz7sWHr4R8/9vmUBZj0A==";
        AESCBCUtil d = new AESCBCUtil();
        byte[] decrypt = d.decrypt(encryptedData, key, iv);
        System.out.println(new String(decrypt,"UTF-8"));
    }
    @Test
    public void jsontest() throws UnsupportedEncodingException {
        LOGGER.debug("gggg");
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String sec = Encoders.BASE64.encode(secretKey.getEncoded());
        System.out.println(sec);
        //System.out.println(Arrays.toString(Decoders.BASE64.decode(sec)));
//        Date date = new Date();
//        System.out.println(date.getTime());
    }



}
