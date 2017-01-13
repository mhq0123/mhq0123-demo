package com.mhq0123.demo.fusionpay.charges;

import com.alibaba.fastjson.JSONObject;
import com.mhq0123.demo.fusionpay.charges.bean.Charges;
import org.mhq0123.demo.fusionpay.cncb.CncbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * project: mhq0123-demo
 * author:  mhq0123
 * date:    2017/1/5.
 * desc:
 */
@RestController
@RequestMapping(ChargesPath.API_BASE)
public class ChargesRestfull {

    private static final Logger logger = LoggerFactory.getLogger(ChargesRestfull.class);

    @Autowired
    private CncbService cncbService;

    /**
     * 收费
     * @param charges
     * @return
     */
    @PostMapping(ChargesPath.CHARGES_APP_API)
    public String chargesAppApi(@ModelAttribute("charges") Charges charges) {

        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));

        switch (charges.getChannel()) {
            case CNCB_WXPAY_APP:
                break;
            default:
                throw new IllegalArgumentException("暂不支持的操作：" + charges.getChannel());
        }

        return ChargesPath.BASE + ChargesPath.CHARGES_APP_API;
    }

    /**
     * h5支付
     * @param charges
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping(ChargesPath.CHARGES_H5_API)
    public String chargesH5Api(@ModelAttribute("charges")Charges charges, Model model) throws Exception {

        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));

        String sHtmlText = null;

        throw new IllegalArgumentException("暂不支持的操作：" + charges.getChannel());

//        model.addAttribute("sHtmlText", sHtmlText);
//
//        return ChargesPath.BASE + ChargesPath.CHARGES_H5_API;
    }
}
