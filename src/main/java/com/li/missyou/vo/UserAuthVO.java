package com.li.missyou.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAuthVO {
    private String encryptedData;
    private String iv;
    private String signature;
    private String rawData;
    private String skey;
    private String code;
}
