package com.li.missyou.dto;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "spu")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class SpuDTO {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String title;
    @NonNull private String subtitle;
    @NonNull@Column(columnDefinition = "DECIMAL(10,2)") private BigDecimal price;
    @NonNull private String img;
    @NonNull private String forThemeImg;
    @NonNull@Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal discountPrice;
    @NonNull private String tags;
    @ColumnDefault("1") private Boolean isTest=true;
    @ColumnDefault("1") private Boolean online=true;
    @NonNull@OneToMany(mappedBy = "spu") private List<SkuDTO> skuList;
    @NonNull@OneToMany(mappedBy = "spu") private List<SpuImgDTO> spuImgList;
    @NonNull@ManyToOne@JoinColumn(referencedColumnName = "id",name = "root_category_id")
    private CategoryDTO rootCategory;
    @NonNull@ManyToOne@JoinColumn(referencedColumnName = "id",name = "category_id")
    private CategoryDTO category;
    @NonNull private Long sketchSpecId;
    @NonNull private Long defaultSkuId;


}
