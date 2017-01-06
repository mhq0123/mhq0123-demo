package org.mhq0123.demo.fusionpay.sevenpay.util;

import org.mhq0123.demo.fusionpay.sevenpay.sign.RSAUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台通知处理。商户在此处处理七分钱异步后台通知。
 */
public class BgUrlDealParams {
	
	/**
	 * 回调处理
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public void callBack(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// 获取所有回调参数
		Map<String, String[]> paramMap = new HashMap<String, String[]>();
		paramMap.putAll(request.getParameterMap());
		
		// 移除signType和signMsg，这两个参数不参与签名
		String[] signType = paramMap.remove("signType");
		String[] signData = paramMap.remove("signMsg");
		System.out.println("七分钱签名："+signData[0]);

		// 校验签名方法和签名是否为空
		if(!checkSignData(signType, signData)){
			// 签名为空，直接返回七分钱“failure”
			response.getWriter().write("failure");
			return;
		}

		// 对移除signType和signMsg后的剩余参数进行排序、组装，获得待签名字符串。
		String signMsg = SevenPayHelper.dealSignParam(paramMap);
		System.out.println("签名字符串："+signMsg);
		
		byte[] data = signMsg.getBytes("UTF-8");

		// 七分钱使用RSA方式进行签名
		if ("RSA".equals(signType[0])) {

			// 获取七分钱公钥sysPublicKey
			
			// 校验签名
			boolean result = RSAUtils.verify(data, RSAUtils.getSevenPayPubKey(), signData[0]);

			if (!result) {
				System.out.println("签名验证不通过");
				response.getWriter().write("failure");
				return;
			}
			else{
				System.out.println("签名验证通过");
				
				// 校验通过后，处理订单支付结果返回信息
				dealOrderRst(paramMap);
				
				// 商户后台通知处理完成之后必须返回给七分钱“success”
				response.getWriter().write("success");
				return;
			}
		} else {
			System.out.println("签名方式错误，当前版本只支持RSA签名");
			response.getWriter().write("failure");
			return;
		}
		
	}
	
	/**
	 * 商户处理七分钱返回的订单交易结果
	 * @param paramMap
	 */
	public void dealOrderRst(Map<String, String[]> paramMap){
		
		/**
		 *  获取返回信息，以下是商户可能会用到的常用的数据，其他数据详见
		 *  《七分钱人民币网关支付接口文档-V1.0》2.3.2节“七分钱返回到商户”
		 */
		String orderId = paramMap.get("outOrderId")[0]; // 商户订单号
		String tradeStatus = paramMap.get("tradeStatus")[0]; // 订单交易状态
		String gmtPayment = paramMap.get("gmtPayment")[0]; // 订单支付时间 
		String orderAmount = paramMap.get("orderAmount")[0]; //订单金额
		String inOrderId = paramMap.get("inOrderId")[0]; // 七分钱内部订单号
		
		
		if(tradeStatus.equals(TradeStatus.TRADE_SUCCESS.name())){
			// 交易成功 TODO 商户根据自身需要，处理返回数据；如更新订单状态、发货、通知用户等
			System.out.println("商户处理订单支付结果...");
			System.out.println("商户订单号:["+orderId+"]");
			System.out.println("订单交易状态:["+tradeStatus+"]");
			System.out.println("订单支付时间:["+gmtPayment+"]");
			System.out.println("订单金额:["+orderAmount+"]");
			System.out.println("七分钱内部订单号:["+inOrderId+"]");
		}else{
			// TODO 
		}
		
	}
	
	/** 
	 * 校验签名参数合法性
	 * @param signType
	 * @param signData
	 * @throws Exception 
	 */
	public boolean checkSignData(String[] signType, String[] signData) throws Exception {
		if(null == signType || signType.length <= 0)
		{
			System.out.println("签名类型不能为空");
			return false;
		}
		
		if(null == signData || signData.length <= 0)
		{
			System.out.println("签名内容不能为空");
			return false;
		}
		
		return true;
	}

	
}
