package com.li.missyou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpuPageVO {
    private Long id;
    private String title;
    private String subtitle;
    private BigDecimal price;
    private String img;
    private String forThemeImg;
    private BigDecimal discountPrice;
    private String tags;
    private Long sketchSpecId;
    private Long maxPurchaseQuantity;
    private Long minurchaseQuantity;
}
