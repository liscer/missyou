package com.li.missyou.dto;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "spu_img")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"spu"})
public class SpuImgDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String img;
    @ManyToOne
    @JoinColumn(name = "spu_id",referencedColumnName = "id")
    private SpuDTO spu;

}
