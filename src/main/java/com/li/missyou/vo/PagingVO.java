package com.li.missyou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingVO {
    private Long total;
    private Integer count;
    private Integer page;
    private Integer totalPage;
    private List<SpuPageVO> items;
}
