package com.mhq0123.demo.fusionpay.charges.bean;

import com.mhq0123.demo.fusionpay.FusionpayDictionary;

import java.io.Serializable;

/**
 * project: mhq0123-demo
 * author:  mhq0123
 * date:    2017/1/5.
 * desc:
 */
public class Charges implements Serializable{

    /** 支付渠道*/
    private FusionpayDictionary.Channel channel;
    /** 订单编号*/
    private String orderNo;
    /** 商品名称*/
    private String subject;
    /** 收费金额*/
    private String amount;
    /** 商品描述*/
    private String body;
    /** 商品展示地址*/
    private String showUrl;

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public FusionpayDictionary.Channel getChannel() {
        return channel;
    }

    public void setChannel(FusionpayDictionary.Channel channel) {
        this.channel = channel;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
