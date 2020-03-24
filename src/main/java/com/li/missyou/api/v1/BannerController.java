package com.li.missyou.api.v1;

import com.li.missyou.dto.PersionDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping("/banner")
@Validated
public class BannerController {

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

