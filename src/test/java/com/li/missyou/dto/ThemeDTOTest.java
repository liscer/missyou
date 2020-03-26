package com.li.missyou.dto;

import com.li.missyou.dao.ThemeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ThemeDTOTest {
    @Autowired
    private ThemeRepository themeRepository;
    @Test
    public void insertTheme(){
        ThemeDTO themeDTO = new ThemeDTO("清凉一夏,折扣季","秋天是金色的","t-1",
                "entranceImg","extend","internalTopImg","titleIMg",
                "janna");
        ThemeDTO themeDTO1 = new ThemeDTO("每周上新","秋天是金色的","t-2",
                "entranceImg","extend","internalTopImg","titleIMg",
                "janna");
        themeRepository.save(themeDTO);
        themeRepository.save(themeDTO1);
    }
}