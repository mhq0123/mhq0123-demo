package com.mhq0123.demo.fusionpay.test;

import com.mhq0123.demo.fusionpay.charges.ChargesPath;
import com.mhq0123.demo.fusionpay.charges.bean.Charges;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    /**
     * 进入渠道选择页面
     * @return
     */
    @GetMapping(ChargesPath.BASE + ChargesPath.CHARGES_PC_CNCB_ALIPAY)
    public String chargesPcCncbAlipay(@ModelAttribute("charges")Charges charges) {

        return ChargesPath.BASE + ChargesPath.CHARGES_PC_CNCB_ALIPAY;
    }
}
