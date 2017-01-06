package com.mhq0123.demo.fusionpay.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * project: mhq0123-demo
 * author:  mhq0123
 * date:    2017/1/6.
 * desc:
 */
@Controller
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "/test/test";
    }
}
