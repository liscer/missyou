package com.li.missyou.api.v1;

import com.li.missyou.services.SpuService;
import com.li.missyou.vo.SpuDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private SpuService spuService;

    @RequestMapping("/id/{id}/detail")
    public SpuDetailVO getSpuDetail(@PathVariable @NonNull Long id){
        SpuDetailVO spuDetailVO = spuService.getSpuDetailByID(id);
        return spuDetailVO;
    }
}