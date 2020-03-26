package com.li.missyou.dto;

import com.li.missyou.dao.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SpuDTOTest {
    @Autowired
    private SpuRepository spuRepository;
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SpuImgRepositroy spuImgRepositroy;
    @Autowired
    private SpecsRepository specsRepository;

    @Test
    public void firstInitData() {
        CategoryDTO rootCategory = new CategoryDTO("居家", "img", 1L);
        rootCategory.setIsRoot(true);
        CategoryDTO subCategory = new CategoryDTO("沙发", "img", 2L);

        SpecDTO key = new SpecDTO("颜色");
        SpecDTO key1 = new SpecDTO("图案");
        SpecDTO key2 = new SpecDTO("尺码");
        SpecDTO value = new SpecDTO("金属灰");
        value.setIsKey(false);
        SpecDTO value1 = new SpecDTO("七龙珠");
        value1.setIsKey(false);
        SpecDTO value2 = new SpecDTO("小号 S");
        value2.setIsKey(false);

        SpuDTO spu = new SpuDTO("林间有风自营针织衫", "瓜瓜设计三件包邮", new BigDecimal("77.0"),
                "img", "themeimg", new BigDecimal("67.0"), "tags",
                new ArrayList<>(), new ArrayList<>(), rootCategory, subCategory, 1L, 2L);
        SkuDTO sku = new SkuDTO(new BigDecimal("76.0"), new BigDecimal("44.0"), "img",
                "金属灰,七龙珠", 6L, "code");
        List<SpecDTO> specs = new ArrayList<>();
        specs.add(key);
        specs.add(key1);
        specs.add(key2);
        specs.add(value);
        specs.add(value1);
        specs.add(value2);
        sku.setSpecs(specs);
        SpuImgDTO spuImg = new SpuImgDTO("img");
        SpuImgDTO spuImg1 = new SpuImgDTO("img");
        SpuImgDTO spuImg2 = new SpuImgDTO("img");
        spu.getSpuImgList().add(spuImg);
        spu.getSpuImgList().add(spuImg1);
        spu.getSpuImgList().add(spuImg2);
        spuImg.setSpu(spu);
        spuImg1.setSpu(spu);
        spuImg2.setSpu(spu);
        spu.getSkuList().add(sku);
        sku.setSpu(spu);
        Long rootId = categoryRepository.save(rootCategory).getId();
        subCategory.setParentId(rootId);
        categoryRepository.save(subCategory);
        Long keyId = specsRepository.save(key).getId();
        Long keyId1 = specsRepository.save(key1).getId();
        Long keyId2 = specsRepository.save(key2).getId();
        value.setParentId(keyId);
        specsRepository.save(value);
        value1.setParentId(keyId1);
        specsRepository.save(value1);
        value2.setParentId(keyId2);
        specsRepository.save(value2);
        spuRepository.save(spu);
        skuRepository.save(sku);

        spuImgRepositroy.save(spuImg);
        spuImgRepositroy.save(spuImg1);
        spuImgRepositroy.save(spuImg2);
    }

    @Test
    public void insertSecondSkuData() {
        SpecDTO key = specsRepository.findByValue("颜色").get();
        SpecDTO key1 = specsRepository.findByValue("图案").get();
        SpecDTO key2 = specsRepository.findByValue("尺码").get();
        SpecDTO value = new SpecDTO("青芒色");
        value.setIsKey(false);
        SpecDTO value1 = new SpecDTO("灌篮高手");
        value1.setIsKey(false);
        SpecDTO value2 = new SpecDTO("中号 M");
        value2.setIsKey(false);

        SpuDTO spu = spuRepository.findById(1L).get();
        SkuDTO sku = new SkuDTO(new BigDecimal("66.0"), new BigDecimal("59.0"), "img",
                "青芒色,灌篮高手", 8L, "code");
        List<SpecDTO> specs = new ArrayList<>();
        specs.add(key);
        specs.add(key1);
        specs.add(key2);
        specs.add(value);
        specs.add(value1);
        specs.add(value2);
        sku.setSpecs(specs);
        spu.getSkuList().add(sku);
        sku.setSpu(spu);
        value.setParentId(key.getId());
        specsRepository.save(value);
        value1.setParentId(key1.getId());
        specsRepository.save(value1);
        value2.setParentId(key2.getId());
        specsRepository.save(value2);
        skuRepository.save(sku);

    }

    @Test
    public void insertManySpu() {
        for (int i = 0; i <= 10; i++) {
            insertSeoundSpu();
        }

    }

    private void insertSeoundSpu(){
        CategoryDTO rootCategory = categoryRepository.findById(1L).get();
        CategoryDTO subCategory = categoryRepository.findById(2L).get();

        SpecDTO key = specsRepository.findByValue("颜色").get();
        SpecDTO key1 = specsRepository.findByValue("图案").get();
        SpecDTO key2 = specsRepository.findByValue("尺码").get();
        SpecDTO value = specsRepository.findByValue("青芒色").get();
        SpecDTO value1 = specsRepository.findByValue("灌篮高手").get();
        SpecDTO value2 = specsRepository.findByValue("中号 M").get();;
        SpuDTO spu = new SpuDTO("林间有风自营针织衫", "瓜瓜设计三件包邮", new BigDecimal("77.0"),
                "img", "themeimg", new BigDecimal("67.0"), "tags",
                new ArrayList<>(), new ArrayList<>(), rootCategory, subCategory, 1L, 2L);
        SkuDTO sku = new SkuDTO(new BigDecimal("76.0"), new BigDecimal("44.0"), "img",
                "金属灰,七龙珠", 6L, "code");

        List<SpecDTO> specs = new ArrayList<>();
        specs.add(key);
        specs.add(key1);
        specs.add(key2);
        specs.add(value);
        specs.add(value1);
        specs.add(value2);
        sku.setSpecs(specs);
        spu.getSkuList().add(sku);
        sku.setSpu(spu);
        SpuImgDTO spuImg = new SpuImgDTO("img");
        spu.getSpuImgList().add(spuImg);
        spuImg.setSpu(spu);

        spuRepository.save(spu);
        skuRepository.save(sku);
        spuImgRepositroy.save(spuImg);
    }


    @Test
    public void findSpu() {
        Optional<SpuDTO> result = spuRepository.findById(1L);
        System.out.println(result.get().toString());
    }
}
