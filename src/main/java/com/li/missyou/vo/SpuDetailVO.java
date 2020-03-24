package com.li.missyou.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    private Boolean onLine = false;
    private List<SkuVO> skuList;
    private List<SpuImgVO> spuImgList;
    private Long sketchSpecId;
    private Long defaultSkuId;


}
