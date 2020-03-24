package com.li.missyou.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.li.missyou.dto.SpuImgDTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpuDetailVO {
    private Long id;
    private String title;
    private String subtitle;
    private Long rootCategoryId;
    private Long categoryId;
    private BigDecimal price;
    private String img;
    private String forThemeImg;
    private BigDecimal discountPrice;
    private String tags;
    private Boolean isTest = true;
    private Boolean online = false;
    //@JsonBackReference
    private List<SkuVO> skuList;
    @JsonBackReference
    private List<SpuImgDTO> spuImgList;
    private Long sketchSpecId;
    private Long defaultSkuId;


}
