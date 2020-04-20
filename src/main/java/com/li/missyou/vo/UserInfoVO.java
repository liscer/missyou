package com.li.missyou.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoVO {
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String nickName;
    private Integer gender;
    private String language;
}
