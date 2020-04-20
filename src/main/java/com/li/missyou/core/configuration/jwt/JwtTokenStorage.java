package com.li.missyou.core.configuration.jwt;

public interface JwtTokenStorage {
    JwtTokenPair put(JwtTokenPair jwtTokenPair,String userId);

    JwtTokenPair get(String userId);
}
