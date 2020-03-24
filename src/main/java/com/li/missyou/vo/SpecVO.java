package com.li.missyou.vo;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class SpecVO {
    private Long keyId;
    private String key;
    private Long valueId;
    private String value;
}
