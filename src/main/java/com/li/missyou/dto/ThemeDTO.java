package com.li.missyou.dto;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "theme")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class ThemeDTO {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)private Long id;
    @NonNull private String title;
    @NonNull private String description;
    @NonNull private String name;
    @NonNull private String entranceImg;
    @NonNull private String extend;
    @NonNull private String internalTopImg;
    @NonNull private String titleImg;
    @NonNull private String tplName;
    @NonNull@ColumnDefault("1") private Boolean onLine = true;
}
