package com.li.missyou.api.v1;

import cn.hutool.json.JSONObject;
import com.li.missyou.common.utils.CommonResult;
import com.li.missyou.core.UnifyResponse;
import com.li.missyou.core.configuration.jwt.JwtTokenGenerator;
import com.li.missyou.core.configuration.jwt.JwtTokenPair;
import com.li.missyou.services.UmsAdminService;
import com.li.missyou.services.WxAppletService;
import com.li.missyou.vo.CodeVO;
import com.li.missyou.vo.UserAuthVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UmsUserController {

    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private WxAppletService wxAppletService;
    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> userRegister(@RequestBody CodeVO code) {
        JSONObject jsonObject = wxAppletService.jsCode2SessionInfo(code.getCode());
        JwtTokenPair jwtTokenPair = umsAdminService.createNewUser(jsonObject);
        return CommonResult.success(jwtTokenPair);
    }

    @RequestMapping(value = "/online", method = RequestMethod.POST)
    public ResponseEntity userOnline() {
        String openid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String ok = umsAdminService.updateUser(openid);
        UnifyResponse message = new UnifyResponse(0, "OK", ok);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<UnifyResponse> r = new ResponseEntity<>(message, headers, HttpStatus.OK);
        return r;
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.POST)
    public ResponseEntity getUserInfo(@RequestBody UserAuthVO userAuth) {
        //session_id有效
        if (userAuth.getCode() == null) {
            String openid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String result = umsAdminService.getUnionIdByAuthInfo(userAuth, openid);
            UnifyResponse message = new UnifyResponse(0, "OK", result);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<UnifyResponse> r = new ResponseEntity<>(message, headers, HttpStatus.OK);
            return r;
        }
        JSONObject bodyObject = wxAppletService.jsCode2SessionInfo(userAuth.getCode());
        String result = umsAdminService.getUnionIdByAuthInfo(userAuth, bodyObject);
        UnifyResponse message = new UnifyResponse(0, "OK", result);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<UnifyResponse> r = new ResponseEntity<>(message, headers, HttpStatus.OK);
        return r;
    }

    @RequestMapping(value = "/refresh",method = RequestMethod.GET)
    public ResponseEntity<?> refreshToken() {
        String openid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("openid : "+openid);
        JwtTokenPair jwtTokenPair =umsAdminService.refreshToken(openid);
        return CommonResult.success(jwtTokenPair);
    }

    @RequestMapping("/test")
    public ResponseEntity<?> test(HttpServletRequest request) {
        return CommonResult.success("jiji");
    }
}
