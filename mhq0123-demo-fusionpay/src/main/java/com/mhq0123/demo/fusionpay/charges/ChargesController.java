package com.mhq0123.demo.fusionpay.charges;

import com.alibaba.fastjson.JSONObject;
import com.mhq0123.demo.fusionpay.charges.bean.Charges;
import org.mhq0123.demo.fusionpay.alipay.AlipayService;
import org.mhq0123.demo.fusionpay.cncb.CncbService;
import org.mhq0123.demo.fusionpay.sevenpay.SevenpayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * project: mhq0123-demo
 * author:  mhq0123
 * date:    2017/1/5.
 * desc:
 */
@Controller
@RequestMapping(ChargesPath.BASE)
public class ChargesController {

    private static final Logger logger = LoggerFactory.getLogger(ChargesController.class);

    @Autowired
    private AlipayService alipayService;
    @Autowired
    private CncbService cncbService;
    @Autowired
    private SevenpayService sevenpayService;

    /**
     * 进入pc
     * @return
     */
    @GetMapping(ChargesPath.CHARGES_PC)
    public String chargesPc(@ModelAttribute("charges")Charges charges) {

        charges.setOrderNo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        charges.setSubject("测试");
        charges.setBody("描述");
        charges.setAmount("0.01");
        charges.setShowUrl("http://goods");

        return ChargesPath.BASE + ChargesPath.CHARGES_PC;
    }

    /**
     * 进入渠道选择页面
     * @return
     */
    @PostMapping(ChargesPath.CHARGES_PC_CHANNEL)
    public String chargesPcChannel(@ModelAttribute("charges")Charges charges) {

        return ChargesPath.BASE + ChargesPath.CHARGES_PC_CHANNEL;
    }

    /**
     * 收费
     * @param charges
     * @param model
     * @return
     */
    @PostMapping(ChargesPath.CHARGES_PC_API)
    public String chargesPcApi(@ModelAttribute("charges")Charges charges, Model model) {

        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));

        String sHtmlText = null;
        String returnPath = ChargesPath.BASE + ChargesPath.CHARGES_PC_API;

        switch (charges.getChannel()) {
            case SEVENPAY_PC:
                sHtmlText = sevenpayService.chargesPc(charges);
                break;
            case ALIPAY_PC:
                // 支付宝即时到账支付，即支付宝 PC 网页支付
                sHtmlText = alipayService.chargesPc(charges);
                break;
            case CNCB_ALIPAY_PC:
                sHtmlText = cncbService.chargesAlipayScanOrPc(charges).getPayUrl();
                returnPath = ChargesPath.BASE + ChargesPath.CHARGES_PC_CNCB_ALIPAY;
                break;
            case CNCB_WXPAY_PC:
                sHtmlText = cncbService.chargesWxpayScanOrPc(charges);
                returnPath = ChargesPath.BASE + ChargesPath.CHARGES_PC_CNCB_ALIPAY;
                break;
            default:
                throw new IllegalArgumentException("暂不支持的操作：" + charges.getChannel());
        }

        model.addAttribute("sHtmlText", sHtmlText);

        return returnPath;
    }

}
