package com.li.missyou.services;

import com.li.missyou.dao.ThemeRepository;
import com.li.missyou.dto.ThemeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public List<ThemeDTO> getThemesByName(String names) {
        List<ThemeDTO> themeDTOList = new ArrayList<>();
        String[] splitName = names.split(",");
        if (splitName.length == 1) {
            Optional<ThemeDTO> result = themeRepository.findByName(splitName[0]);
            themeDTOList.add(result.orElse(null));
        }else {
            for (String n :
                    splitName) {
                Optional<ThemeDTO> result = themeRepository.findByName(n);
                themeDTOList.add(result.orElse(null));
            }
        }
        return themeDTOList;
    }
}
