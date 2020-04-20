package com.li.missyou.core.configuration.jwt;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class JwtTokenCacheStorage implements JwtTokenStorage{
    public static final String TOKEN_CACHE = "usrTkn";

    @CachePut(value = TOKEN_CACHE,key = "#userId")
    @Override
    public JwtTokenPair put(JwtTokenPair jwtTokenPair, String userId) {
        return jwtTokenPair;
    }

    @Cacheable(value = TOKEN_CACHE,key = "#userId")
    @Override
    public JwtTokenPair get(String userId) {
        return null;
    }
}
