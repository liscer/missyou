package com.li.missyou.api.v1;

import com.li.missyou.dto.ThemeDTO;
import com.li.missyou.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/theme")
public class ThemeController {
    @Autowired
    private ThemeService themeService;
    @RequestMapping("/by/names")
    public List<ThemeDTO> getThemesByName(@RequestParam @NonNull String names){
        return themeService.getThemesByName(names);
    }
}
