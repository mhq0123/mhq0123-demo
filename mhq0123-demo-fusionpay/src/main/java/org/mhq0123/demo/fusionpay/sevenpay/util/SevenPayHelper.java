package org.mhq0123.demo.fusionpay.sevenpay.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 七分钱参数帮助类
 */
public class SevenPayHelper {

	/** 
	 * 对接收的参数进行排序，获取待签名字符串
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	public static String dealSignParam(Map<String, String[]> paramMap) throws Exception {
		if(null == paramMap || paramMap.isEmpty())
		{
			System.out.println("访问参数值不能为空");
		}
		
		StringBuffer result = new StringBuffer();
		List<String> keyList = new ArrayList<String>();
		keyList.addAll(paramMap.keySet());
		
		Collections.sort(keyList);
		
		for(int i=0; i<keyList.size(); i++)
		{
			String key = keyList.get(i);
			String[] value = paramMap.get(key);
			if(null != value && value.length > 0)
			{
				// new String(value[0].getBytes("ISO-8859-1"),"UTF-8"); 如果出现乱码情况，请使用这种方式获取参数值
				// result.append("&").append(key).append("=").append(URLDecoder.decode(value[0],"UTF-8"));
				result.append("&").append(key).append("=").append(URLDecoder.decode(new String(value[0].getBytes("ISO-8859-1"),"UTF-8"),"UTF-8"));
			}
		}
		
		return result.deleteCharAt(0).toString();
	}
	
	
	/** 
	 * 对待提交的参数进行排序，获取待签名字符串
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	public static String genSignParam(Map<String, String> paramMap) throws Exception {
		if(null == paramMap || paramMap.isEmpty())
		{
			System.out.println("提交不能为空");
		}
		
		StringBuffer result = new StringBuffer();
		List<String> keyList = new ArrayList<String>();
		keyList.addAll(paramMap.keySet());
		
		Collections.sort(keyList);
		
		for(int i=0; i<keyList.size(); i++)
		{
			String key = keyList.get(i);
			String value = paramMap.get(key);
			if(null != value && !"".equals(value) )
			{
				result.append("&").append(key).append("=").append(URLDecoder.decode(value,"UTF-8"));
			}
		}
		
		return result.deleteCharAt(0).toString();
	}
	
	
	/** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("signMsg")
                || key.equalsIgnoreCase("signType")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

}
