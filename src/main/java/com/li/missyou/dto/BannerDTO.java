package com.li.missyou.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "banner")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class BannerDTO {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private String title;
    @NonNull private String img;
    @OneToMany(mappedBy = "banner")@JsonProperty("items")
    private List<BannerItemDTO> bannerItemDTOList;
}
