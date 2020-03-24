package com.li.missyou.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "banner_item")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"banner"})
public class BannerItemDTO {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String img;
    @NonNull private Long keyword;
    @NonNull private Long type;
    @NonNull private String name;
    @ManyToOne@JoinColumn(name = "banner_id")@JsonProperty("banner_id")
    private BannerDTO banner;
}
