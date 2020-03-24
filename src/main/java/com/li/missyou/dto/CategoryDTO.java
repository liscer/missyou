package com.li.missyou.dto;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class CategoryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String name;
    @ColumnDefault("0")
    private Boolean isRoot=false;
    @NonNull private String img;
    private Long parentId;
    @NonNull private Long index1;
    //@OneToMany(mappedBy = "rootCategoryId") private List<SpuDTO> spuList;
}
