package org.mhq0123.demo.fusionpay.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088702759399674";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDya1TX8RCCUtkndmR071D4TAlylFXZaSxw5a5BKXXrikUPysvEAUgxGs6LUWBYn92plZX6uMChGIN2LHVDCZt7Yc+ld/tbaalOI4XDqiekF9F5vdUi8RjVmU6mAf+1n1nPFCRrW46V7tR3to4qrFQI8YnUWkQZLzhFUMC51TGIJi5Uusu5tXLuufGB+wBXE9fhiMQC0m89x6dvSix3jQ08tEvEsl+WhcsssYBrA0XdaW2HDZ6zu5m+F1XnWXC+HMmGohWFoZHauaXibGHQuy04ftGbujX5oct6MU0cVYcQcIKHNb9XeeKxOdybQbaq9QQPzquQrTvDXmuMvl30gwTAgMBAAECggEASFTu3q3XR+9WA/lnKtSuMo1sIbGzh2ZwSGjzVsUoSQJ75XLuPrxqNBvaRmoGlgb7sqGFDmgLBJyoPLVMlYQ/q2INMtqLtwLRzikGQ7116uGOiQ1Ffo/f0C3hDZHYAZSz6299QReRQlBYrYzE9QkqnLOl1HqoUakYyekjiHpzWvJLb//h6vGktp/aWbnp7ocVHVLR9K1dIo8x7092Xlm5FozaD3HVambM1c3atUL0XDMD5OAxrx7jRSzQbQYWLfeeyBafIFg2rkBC77sqe7PmXxHNzPpq33OckRl52Eij6KhsBsEultb5zk5y8/laPTxeavSn0HdeLXDvwNV32hBG0QKBgQC8VWOZFXsWA654coXeOTSFV+g2EU8P95anJYwpLv1cQaiOJyPV6PN6zOIhLNa8OMzRoMXp9IBnrUe5g7QfbauhMM1GGJrCC1nupkGqdtLyBQXoR+RrOePjiTRJcurNNQR3XrdxcXEMbm1Yk5pCXXe7YPxvSNbVPPdBXPOx9jvdnwKBgQCzI01+dgftjmzK7O9x+PFRYPb3D+gKHad9weKy3K4z4ug2j1CFIBxFisTBOG56MoRU42rkQ80QgsY4vvR7jdbbYhyDOeMOVX7yZCn2s/v8M/5QsYh0zqixpQg3P7/7b4rrZHWDqm6Uq9zLXLxgNK0wMJzVd0bYrChRNuQl2I5VDQKBgCdOcwEKOKw5jHpa/JGKwulplW9jYWrls62i6oPV6M8ObQ1uIc6NVfO1OyTNZOvXtvexZF0VyI7a3XNch4VfsEhuFQmiLPp+wABM9ruLHS+h2L/H8WZnqKZehmkl8NdvT0VSoVqt4HhFu9CWzcU4GLsk3MqlGyFaHgioS7xCte/lAoGBAIUA+iGh2wMLU0Vbo/C4R9wHJXaMrdkM6XXaxcnNRd4qZAVWZQ9ipvsPLETV9FjwNwacD5pJqPDQWAJ77SPrTRJ4Bvl7ulTDL80ih3GvcrFvnSwmlE09v0yeXro+r/kseTWK0iGfIhlqfTXUtdysX8Y+4cMwmz52nDyoh759yjflAoGAWceWB2iDsTb/tsEqU3CtK7G3JL6MaUOiX6qfXAou0MftDpjbRmZA0QfmpnOt6PZGX26YUrJPAP7GcFAaq8wdY1oFO8ilE4Mr99va94evoBVx4jQYbIqPms8JICexn0YHy1fXNlEOY++HCxz7n7KFZIjI7yuPvgZVguraU79/Yng=";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";


	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://192.168.1.100:1234/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://192.168.1.100:1234/return_url";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "D:\\mahq\\workspace_java\\mhq0123-demo\\mhq0123-demo-alipay\\log";
		
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

