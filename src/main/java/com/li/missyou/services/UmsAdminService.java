package com.li.missyou.services;

import cn.hutool.json.JSONObject;
import com.li.missyou.core.configuration.jwt.JwtTokenPair;
import com.li.missyou.vo.UserAuthVO;


public interface UmsAdminService {

    JwtTokenPair createNewUser(JSONObject bodyObject);

    String updateUser(String openid);

    String getUnionIdByAuthInfo(UserAuthVO userAuth, String openid);

    String getUnionIdByAuthInfo(UserAuthVO userAuth, JSONObject bodyObject);

    JwtTokenPair refreshToken(String openid);
}
