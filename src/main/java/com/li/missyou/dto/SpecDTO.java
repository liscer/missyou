package com.li.missyou.dto;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spec")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class SpecDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String value;
    @NonNull@ColumnDefault("1") private Boolean isKey=true;
    private Long parentId;
    //@ManyToMany(mappedBy = "specs")
    //private List<SkuDTO> skuList=new ArrayList<>();

}
