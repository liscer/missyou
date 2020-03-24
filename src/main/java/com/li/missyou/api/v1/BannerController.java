package com.li.missyou.api.v1;

import com.li.missyou.dto.BannerDTO;
import com.li.missyou.dto.PersionDTO;
import com.li.missyou.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping("/banner")
@Validated
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/name/{name}")
    public BannerDTO getBannerByName(@PathVariable @NonNull String name){
        return bannerService.findBannerByName(name);
    }

//    @RequestMapping(value = "test")
//    public String test(@RequestBody @Validated PersionDTO p,
//                       @RequestParam @Max(value = 10) Integer s)  {
////        response.getWriter().write("hello,李宝嘉");
//        return "hello,李宝嘉";
//        //throw new RuntimeException("1111");
//        //throw new ForbiddenException(10001);
//
//    }
}

