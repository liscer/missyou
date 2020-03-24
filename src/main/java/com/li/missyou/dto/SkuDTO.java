package com.li.missyou.dto;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sku")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"spu"})
public class SkuDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull@Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price;
    @NonNull@Column(name = "discount_price",columnDefinition = "DECIMAL(10,2)")
    private BigDecimal discountPrice;
    @NonNull@ColumnDefault("1") private Boolean online=true;
    @NonNull private String img;
    @NonNull private String title;
    @ManyToOne@JoinColumn(name = "spu_id",referencedColumnName = "id")
    private SpuDTO spu;
    @ManyToMany()
    private List<SpecDTO> specs;
    @NonNull private Long stock;
    @NonNull private String code;

}
