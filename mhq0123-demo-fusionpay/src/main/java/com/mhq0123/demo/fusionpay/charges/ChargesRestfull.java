package com.mhq0123.demo.fusionpay.charges;

import com.alibaba.fastjson.JSONObject;
import com.mhq0123.demo.fusionpay.charges.bean.Charges;
import com.sevenpay.gateway.bank.cncb.impl.A01.bean.TxnA01ResponseBody;
import org.mhq0123.demo.fusionpay.cncb.CncbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping(ChargesPath.API_CHARGES_CNCB_APP_API)
    public TxnA01ResponseBody chargesCncbAppApi(Charges charges) {

        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));

        return cncbService.chargesAlipayAppOrPc(charges);
    }
}
