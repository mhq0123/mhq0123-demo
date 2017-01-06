package org.mhq0123.demo.fusionpay.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.mhq0123.demo.fusionpay.alipay.config.AlipayConfig;
import org.mhq0123.demo.fusionpay.alipay.util.AlipaySubmit;
import com.mhq0123.demo.fusionpay.charges.bean.Charges;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * project: mhq0123-demo
 * author:  mhq0123
 * date:    2017/1/5.
 * desc:
 */
@Service
public class AlipayService {

    private static final Logger logger = LoggerFactory.getLogger(AlipayService.class);

    @Autowired
    private AlipayClient alipayClient;

    /**
     * pc网页支付
     * @param charges
     * @return
     */
    public String chargesPc(Charges charges) {
        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("out_trade_no", charges.getOrderNo());
        sParaTemp.put("subject", charges.getSubject());
        sParaTemp.put("total_fee", charges.getAmount());
        sParaTemp.put("show_url", charges.getShowUrl());
        //sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
        sParaTemp.put("body", charges.getBody());
        //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
        //如sParaTemp.put("参数名","参数值");


        //建立请求
        logger.info(">>>>>>>>>>>>>请求参数sParaTemp:{}", JSONObject.toJSONString(sParaTemp, true));
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        logger.info(">>>>>>>>>>>>>返回sHtmlText:{}", sHtmlText);

        return sHtmlText;
    }

    /**
     * app支付
     * @param charges
     * @return
     */
    public String chargesApp(Charges charges) {
        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("out_trade_no", charges.getOrderNo());
        sParaTemp.put("subject", charges.getSubject());
        sParaTemp.put("total_fee", charges.getAmount());
        sParaTemp.put("show_url", charges.getShowUrl());
        sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
        sParaTemp.put("body", charges.getBody());
        //其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
        //如sParaTemp.put("参数名","参数值");


        //建立请求
        logger.info(">>>>>>>>>>>>>请求参数sParaTemp:{}", JSONObject.toJSONString(sParaTemp, true));
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        logger.info(">>>>>>>>>>>>>返回sHtmlText:{}", sHtmlText);

        return sHtmlText;
    }

    /**
     * h5支付
     * @param charges
     * @return
     */
    public String chargesH5(Charges charges) {
        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));
        String sHtmlText = null;
        try {
            AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);//在公共参数中设置回跳和通知地址
            alipayRequest.setBizContent("{" +
                    "    \"out_trade_no\":"+charges.getOrderNo()+"," +
                    "    \"total_amount\":"+charges.getAmount()+"," +
                    "    \"subject\":"+charges.getSubject()+"," +
                    "    \"seller_id\":"+AlipayConfig.seller_id+"," +
                    "    \"product_code\":\"QUICK_WAP_PAY\"" +
                    "  }");//填充业务参数

            logger.info(">>>>>>>>>>>>>alipayRequest:{}", JSONObject.toJSONString(alipayRequest, true));
            sHtmlText = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            logger.info(">>>>>>>>>>>>>sHtmlText:{}", sHtmlText);
        } catch (Exception e) {
            logger.error("调用支付宝H5支付出错"+e.getMessage(), e);
        }
        return sHtmlText;
    }
}
