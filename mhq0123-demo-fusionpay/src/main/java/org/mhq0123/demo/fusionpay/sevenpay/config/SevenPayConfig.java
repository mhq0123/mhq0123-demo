package org.mhq0123.demo.fusionpay.sevenpay.config;

/**
 * @project STC_DEMO
 * 七分钱配置参数
 */
public class SevenPayConfig {
	
	/**
	 * 签名方式
	 */
	public static final String SIGN_TYPE = "RSA";
	
	/**
	 * 字符编码格式 UTF-8
	 */
	public static final String INPUT_CHARSET = "UTF-8";
	
	/**
	 * 商户号
	 * 商户号查询：https://www.qifenqian.com/enterprise/login.do
	 */
	public static final String MERCHANT_CODE = "M9144030035873982X0";
	
	/**
	 * 手机H5网页订单提交地址
	 * 正式环境：https://www.qifenqian.com/standardwap/partner/login.do
	 * 测试环境：https://uat.qifenqian.com:7443/standardwap/partner/login.do
	 */
	public static final String SEVENPAY_SUBMIT_H5 = "https://uat.qifenqian.com:7443/standardwap/partner/login.do";
	
	/**
	 * PC 网页订单提交地址
	 * 正式环境地址：https://www.qifenqian.com/standard/partner/login.do 
	 * 测试环境地址：https://uat.qifenqian.com:7443/standard/partner/login.do
	 */
	public static final String SEVENPAY_SUBMIT_PC = "https://uat.qifenqian.com:7443/standard/partner/login.do";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://192.168.1.100:1234/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://192.168.1.100:1234/return_url";

	//商户号
	public static String custId = "M9144030035873982X0";

	public static String version = "v1.0";// 固定值v1.0,不用更改
	public static String pageLanguage = "1";// 固定值1，不用更改
	public static String payeeAcctType = "SEVN_CUS_0"; // 固定值SEVN_CUS_0，不用更改
	public static String payType = "1";// 支付方式。固定值1，不用更改
	public static String orderTimeOut = "3600";// 单位为秒。 此处表示60分钟。60 * 60 = 3600秒
}
