package org.mhq0123.demo.fusionpay.cncb;

import com.alibaba.fastjson.JSONObject;
import com.mhq0123.demo.fusionpay.charges.bean.Charges;
import com.sevenpay.gateway.bank.cncb.ICncb;
import com.sevenpay.gateway.bank.cncb.RequestHead;
import com.sevenpay.gateway.bank.cncb.impl.A01.bean.TxnA01RequestBean;
import com.sevenpay.gateway.bank.cncb.impl.A01.bean.TxnA01RequestBody;
import com.sevenpay.gateway.bank.cncb.impl.A01.bean.TxnA01ResponseBean;
import com.sevenpay.gateway.bank.cncb.impl.A01.bean.TxnA01ResponseBody;
import com.sevenpay.gateway.bank.cncb.impl.W01.bean.TxnW01RequestBean;
import com.sevenpay.gateway.bank.cncb.impl.W01.bean.TxnW01RequestBody;
import com.sevenpay.gateway.bank.cncb.impl.W01.bean.TxnW01ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * project: mhq0123-demo
 * author:  mhq0123
 * date:    2017/1/5.
 * desc:
 */
@Service
public class CncbService {

    private static final Logger logger = LoggerFactory.getLogger(CncbService.class);

    @Autowired
    private ICncb cncbService;

    /**
     * app支付
     * @param charges
     * @return
     */
    public TxnA01ResponseBody chargesAlipayAppOrPc(Charges charges) {

        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));

        TxnA01RequestBean request = new TxnA01RequestBean();
        {
            RequestHead head = new RequestHead();
            {
                head.setMsgId(charges.getOrderNo());
                head.setSysId("S002");
            }
            TxnA01RequestBody body = new TxnA01RequestBody();
            {
                body.setOutTradeNo(charges.getOrderNo()); // 商户订单号
                body.setDeviceInfo(""); // 终端设备号
                body.setBody(charges.getBody()); // 商品描述
                body.setAttach("{\"merchentid\":\"0001\"}");// 附加信息
                body.setTotalFee(new BigDecimal(charges.getAmount()));// 总金额
                body.setMchCreateIp("192.168.1.100");// 订单生成的机器IP
                body.setTimeStart("");// 订单生成时间
                body.setTimeExpire("");// 订单超时时间;
                body.setGoodsTag("");// 商品标记, 微信平台配置的商品标记，用于优惠券或者满减使用
                body.setProductId("");// 商品标记, 微信平台配置的商品标记，用于优惠券或者满减使用
                body.setBuyerLogonId("tom@vip.qq.com");// 随机字符串，不长于32位
                body.setBuyerId("2088702759399674");// 随机字符串，不长于32位
            }

            request.setHead(head);
            request.setBody(body);
        }

        logger.info(">>>>>>>>>>>>>request:{}", JSONObject.toJSONString(request, true));
        TxnA01ResponseBean response = cncbService.txnA01(request);
        logger.info(">>>>>>>>>>>>>response:{}", JSONObject.toJSONString(response, true));

        return response.getBody();
    }

    /**
     * app支付
     * @param charges
     * @return
     */
    public String chargesWxpayAppOrPc(Charges charges) {

        logger.info(">>>>>>>>>>>>>charges:{}", JSONObject.toJSONString(charges, true));

        TxnW01RequestBean request = new TxnW01RequestBean();
        {
            RequestHead head = new RequestHead();
            {
                head.setMsgId(charges.getOrderNo());
                head.setSysId("S002");
            }
            TxnW01RequestBody body = new TxnW01RequestBody();
            {
                body.setOutTradeNo(charges.getOrderNo()); //商户订单号
                body.setIsRaw("1"); // 1
                body.setDeviceInfo("wesafasxsdfsdf"); // 终端设备号
                body.setBody(charges.getBody()); // 商品描述
                body.setSubOpenId("openId"); // 微信接口获取openId
                body.setAttach("{\"merchentid\":\"0001\"}");// 附加信息， 可填写此订单归属的商户 merchentId=M0000001
                body.setTotalFee(new BigDecimal(charges.getAmount()));// 总金额
                body.setMchCreateIp("192.168.1.100");//订单生成的机器IP
                body.setCallbackUrl("https://www.qifenqian.com/index.jsp");// 前端交易完成的跳转URL
                body.setTimeStart("");// 订单生成时间
                body.setTimeExpire("");// 订单超时时间;
                body.setGoodsTag("");// 商品标记, 微信平台配置的商品标记，用于优惠券或者满减使用
                body.setLimitCreditPay("0"); // 是否限制信用卡 0
                body.setOrderUrl(charges.getShowUrl());
            }

            request.setHead(head);
            request.setBody(body);
        }

        logger.info(">>>>>>>>>>>>>request:{}", JSONObject.toJSONString(request, true));
        TxnW01ResponseBean response = cncbService.txnW01(request);
        logger.info(">>>>>>>>>>>>>response:{}", JSONObject.toJSONString(response, true));

        return "https://pay.swiftpass.cn/pay/jspay?token_id=" + response.getBody().getTokenId() + "&showwxtitle=1";
    }
}
