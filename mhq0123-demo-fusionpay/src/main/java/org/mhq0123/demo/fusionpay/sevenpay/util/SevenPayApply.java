package org.mhq0123.demo.fusionpay.sevenpay.util;

import org.mhq0123.demo.fusionpay.sevenpay.config.SevenPayConfig;
import org.mhq0123.demo.fusionpay.sevenpay.sign.RSAUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @project STC_DEMO
 */
public class SevenPayApply {

	/**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param sevenPaySubmitUrl 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String sevenPaySubmitUrl) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();
        
        sbHtml.append("<form id=\"sevenpaysubmit\" name=\"sevenpaysubmit\" action=\"" + sevenPaySubmitUrl
                      + "\" method=\"" + strMethod + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"提交\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['sevenpaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
    
    
    /**
     * 生成要请求给七分钱的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = SevenPayHelper.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("signMsg", mysign);
        sPara.put("signType", SevenPayConfig.SIGN_TYPE);

        return sPara;
    }
    
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = SevenPayHelper.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if(SevenPayConfig.SIGN_TYPE.equals("RSA") ) {
        	try {
				mysign = RSAUtils.sign(prestr.getBytes("UTF-8"), null);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("签名过程出现异常");
			}
        }
        return mysign;
    }
	
}
