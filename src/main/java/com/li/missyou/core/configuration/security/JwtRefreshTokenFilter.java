package com.li.missyou.core.configuration.security;

import com.li.missyou.core.configuration.jwt.JwtTokenGenerator;
import com.li.missyou.core.configuration.jwt.JwtTokenPair;
import com.li.missyou.core.configuration.jwt.JwtTokenStorage;
import com.li.missyou.core.configuration.wxappletauth.WxAppletAuthenticationToken;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JwtRefreshTokenFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationEntryPoint restAuthenticationEntryPoint;
    private JwtTokenStorage jwtTokenStorage;
    private JwtTokenGenerator jwtTokenGenerator;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.refreshTokenHead}")
    private String refreshTokenHead;
    public JwtRefreshTokenFilter(JwtTokenGenerator jwtTokenGenerator, JwtTokenStorage jwtTokenStorage) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.jwtTokenStorage = jwtTokenStorage;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("processing refresh for [{}]", request.getRequestURI());
        String authHeader = request.getHeader(this.tokenHeader);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(this.refreshTokenHead)) {
            String authToken = authHeader.substring(this.refreshTokenHead.length());
            if (StringUtils.hasText(authToken)) {
                try {
                    authenticationTokenHandle(authToken);
                } catch (AuthenticationException e) {
                    restAuthenticationEntryPoint.commence(request,response,e);
                }
            } else {
                log.warn("couldn't find token string");
                restAuthenticationEntryPoint.commence(request,response,new AuthenticationCredentialsNotFoundException("token is not found"));
            }
        }
        filterChain.doFilter(request, response);
    }
    private void authenticationTokenHandle(String authToken) {
        String openid=jwtTokenGenerator.getAudFromToken(authToken);
        JwtTokenPair jwtTokenPair = jwtTokenStorage.get(openid);
        if (Objects.isNull(jwtTokenPair)) {
            if (log.isDebugEnabled()) {
                log.debug("token : {} is not in cache",authToken);
            }
            throw new CredentialsExpiredException("token is not in cache");
        }
        String refreshToken = jwtTokenPair.getRefreshToken();
        if (authToken.equals(refreshToken)) {
            log.debug("security context was null, so authorizing user");
            SecurityContextHolder.getContext().setAuthentication(
                    new WxAppletAuthenticationToken(openid, AuthorityUtils.NO_AUTHORITIES));
        } else {
            if (log.isDebugEnabled()) {
                log.debug("token : {} is not in matched",authToken);
            }
            throw new BadCredentialsException("token is not matched");
        }
    }
}
