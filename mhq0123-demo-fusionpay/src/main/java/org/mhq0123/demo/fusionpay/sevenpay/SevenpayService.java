package org.mhq0123.demo.fusionpay.sevenpay;

import com.alibaba.fastjson.JSONObject;
import com.mhq0123.demo.fusionpay.charges.bean.Charges;
import org.mhq0123.demo.fusionpay.sevenpay.config.SevenPayConfig;
import org.mhq0123.demo.fusionpay.sevenpay.util.SevenPayApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * project: mhq0123-demo
 * author:  mhq0123
 * date:    2017/1/6.
 * desc:
 */
@Service
public class SevenpayService {

    private static final Logger logger = LoggerFactory.getLogger(SevenpayService.class);

    /**
     * 充值
     * @param charges
     * @return
     */
    public String chargesPc(Charges charges) {
        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));

        // 用户在商户网站下单后，商户后台生成商户本地订单，然后按照七分钱《七分钱人民币网关支付接口文档》中“商户提交到七分钱”接口，以POST方式提交数据，可参考如下代码
        // 以下所列为向七分钱提交订单数据时常用的字段，其他字段(非必填)可参考接口文档

        HashMap<String,String> postParams = new HashMap<String,String>();
        postParams.put("outOrderId", charges.getOrderNo());
        postParams.put("custId", SevenPayConfig.custId);
        postParams.put("version",SevenPayConfig.version);
        postParams.put("inputCharset",SevenPayConfig.INPUT_CHARSET);
        postParams.put("pageLanguage",SevenPayConfig.pageLanguage);
        postParams.put("pgUrl",SevenPayConfig.return_url);
        postParams.put("bgUrl",SevenPayConfig.notify_url);
        postParams.put("payeeAcctType",SevenPayConfig.payeeAcctType);
        postParams.put("orderAmount",charges.getAmount());
        postParams.put("orderTimestamp",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        postParams.put("prodName",charges.getSubject());
        postParams.put("prodDesc",charges.getBody());
        postParams.put("orderTimeOut",SevenPayConfig.orderTimeOut);
        postParams.put("payType", SevenPayConfig.payType);

        // 获取待签名字符串，形式为：
        // 参数  1={ 参数  1}& 参数  2={参数  2}& …… & 参数 n={参数 n}
//        String params = SevenPayHelper.genSignParam(postParams);

        // 创建七分钱订单请求
        logger.info(">>>>>>>>>>>>>postParams:{}", JSONObject.toJSONString(postParams, true));
        String sHtmlText = SevenPayApply.buildRequest(postParams, "post", SevenPayConfig.SEVENPAY_SUBMIT_PC);
        logger.info(">>>>>>>>>>>>>sHtmlText:{}", JSONObject.toJSONString(sHtmlText, true));

        return sHtmlText;
    }
}
