package com.li.missyou.vo;

import com.li.missyou.dto.CategoryDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoriesVO {
    private List<CategoryDTO> roots;
    private List<CategoryDTO> subs;
}
