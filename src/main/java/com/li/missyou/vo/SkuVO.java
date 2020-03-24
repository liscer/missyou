package com.li.missyou.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SkuVO {
    private Long id;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Boolean online = false;
    private String img;
    private String title;
    //@JsonBackReference
    private List<SpecVO> specList;
    private Long stock;
    private Long spuId;
    private String code;
}
