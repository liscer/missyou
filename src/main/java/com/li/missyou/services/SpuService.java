package com.li.missyou.services;

import com.li.missyou.dao.SpecsRepository;
import com.li.missyou.dao.SpuRepository;
import com.li.missyou.dto.SkuDTO;
import com.li.missyou.dto.SpecDTO;
import com.li.missyou.dto.SpuDTO;
import com.li.missyou.dto.SpuImgDTO;
import com.li.missyou.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpuService {

    @Autowired
    private SpuRepository spuRepository;
    @Autowired
    private SpecsRepository specsRepository;

    public SpuDetailVO getSpuDetailByID(Long id) {
        Optional<SpuDTO> result = spuRepository.findById(id);
        if (!result.isPresent()) {
            return null;
        }
        SpuDTO spu = result.get();
        List<SkuVO> skuVOList = handleSku(spu.getSkuList());
        List<SpuImgVO> spuImgVOList = handleSpuImg(spu.getSpuImgList());
        SpuDetailVO spuDetailVO = new SpuDetailVO(spu.getId(), spu.getTitle(), spu.getSubtitle(),
                spu.getRootCategory().getId(), spu.getCategory().getId(), spu.getPrice(), spu.getImg(), spu.getForThemeImg(),
                spu.getDiscountPrice(), spu.getTags(), spu.getIsTest(), spu.getOnline(), skuVOList, spuImgVOList,
                spu.getSketchSpecId(), spu.getDefaultSkuId());
        return spuDetailVO;
    }

    public PagingVO getSpuLatest(Integer start, Integer count) {
        PageRequest pageRequest = PageRequest.of(start, count, Sort.unsorted());
        Page<SpuDTO> pages = spuRepository.findAll(pageRequest);
        return handlePaging(pages,start);
    }

    private PagingVO handlePaging(Page<SpuDTO> pages,Integer start) {
        List<SpuPageVO> spuPageVOList = new ArrayList<>();
        for (SpuDTO s :
                pages.getContent()) {
            SpuPageVO spuPageVO = new SpuPageVO(s.getId(),s.getTitle(),s.getSubtitle(),s.getPrice(),
                    s.getImg(),s.getForThemeImg(),s.getDiscountPrice(),s.getTags(),s.getSketchSpecId(),
                    null,null);
            spuPageVOList.add(spuPageVO);
        }
        return new PagingVO(pages.getTotalElements(), pages.getSize(), start,
                pages.getTotalPages(), spuPageVOList);
    }

    private List<SpuImgVO> handleSpuImg(List<SpuImgDTO> spuImgList) {
        List<SpuImgVO> spuImgVOList = new ArrayList<>();
        for (SpuImgDTO s :
                spuImgList) {
            SpuImgVO spuImgVO = new SpuImgVO(s.getId(), s.getImg(), s.getSpu().getId());
            spuImgVOList.add(spuImgVO);
        }
        return spuImgVOList;
    }

    private List<SkuVO> handleSku(List<SkuDTO> skuList) {
        List<SkuVO> skuVOList = new ArrayList<>();
        for (SkuDTO sku :
                skuList) {
            List<SpecVO> specsVOList = handleSpecs(sku.getSpecs());
            String code = handleCode(sku.getSpu().getId(),specsVOList);
            SkuVO skuVO = new SkuVO(sku.getId(),sku.getPrice(),sku.getDiscountPrice(),sku.getOnline(),sku.getImg(),
                    sku.getTitle(),specsVOList,sku.getStock(),sku.getSpu().getId(),code);
            skuVOList.add(skuVO);
        }
        return skuVOList;
    }

    private String handleCode(Long spuId, List<SpecVO> specsVOList) {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(spuId).append('$');
        for (SpecVO s :
                specsVOList) {
            sBuilder.append(s.getKeyId()).append('-').append(s.getValueId()).append('#');
        }
        return sBuilder.toString().substring(0,sBuilder.length()-1);
    }

    private List<SpecVO> handleSpecs(List<SpecDTO> specs) {
        List<SpecVO> specsVOList = new ArrayList<>();
        for (SpecDTO s :
                specs) {
            if (s.getIsKey()) {
                SpecDTO value = findValueByParentID(specs,s.getId());
                SpecVO spec = SpecVO.builder().keyId(s.getId())
                        .key(s.getValue())
                        .valueId(value.getId())
                        .value(value.getValue()).build();
                specsVOList.add(spec);
            }
        }
        return specsVOList;
    }

    private SpecDTO findValueByParentID(List<SpecDTO> specs,Long keyId) {
        for (SpecDTO s :
                specs) {
            if (s.getParentId() != null && s.getParentId().equals(keyId)) {
                return s;
            }
        }
        return null;
    }


}
