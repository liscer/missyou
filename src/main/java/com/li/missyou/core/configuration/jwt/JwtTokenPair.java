package com.li.missyou.core.configuration.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class JwtTokenPair implements Serializable {
    private static final long serialVersionUID = -6003198187573818348L;
    private String accessToken;
    private String refreshToken;
}
