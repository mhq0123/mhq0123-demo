package org.mhq0123.demo.fusionpay.cncb.wxpay.action;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.mhq0123.demo.fusionpay.cncb.wxpay.config.SwiftpassConfig;
import org.mhq0123.demo.fusionpay.cncb.wxpay.util.MD5;
import org.mhq0123.demo.fusionpay.cncb.wxpay.util.SignUtils;
import org.mhq0123.demo.fusionpay.cncb.wxpay.util.XmlUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;


/**
 * <一句话功能简述>
 * <功能详细描述>统一预下单
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TestPayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        SortedMap<String,String> map = XmlUtils.getParameterMap(req);

        map.put("mch_id", SwiftpassConfig.mch_id);

            map.put("notify_url", SwiftpassConfig.notify_url);
            map.put("nonce_str", String.valueOf(new Date().getTime()));

            Map<String,String> params = SignUtils.paraFilter(map);
            StringBuilder buf = new StringBuilder((params.size() +1) * 10);
            SignUtils.buildPayParams(buf,params,false);
            String preStr = buf.toString();
            String sign = MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
            map.put("sign", sign);

            String reqUrl = SwiftpassConfig.req_url;
            System.out.println("reqUrl：" + reqUrl);

            System.out.println("reqParams:" + XmlUtils.parseXML(map));
            CloseableHttpResponse response = null;
            CloseableHttpClient client = null;
            String res = null;
            try {
                HttpPost httpPost = new HttpPost(reqUrl);
                StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
                httpPost.setEntity(entityParams);
                //httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
                client = HttpClients.createDefault();
                response = client.execute(httpPost);
                if(response != null && response.getEntity() != null){
                    Map<String,String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                    res = XmlUtils.toXml(resultMap);
                    System.out.println("请求结果：" + res);

                    if(resultMap.containsKey("sign")){
                        if(!SignUtils.checkParam(resultMap, SwiftpassConfig.key)){
                            res = "验证签名不通过";
                        }else{
                            if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))){


                                String token_id = resultMap.get("token_id");
                                String services = resultMap.get("services");

                            }else{
                                req.setAttribute("result", res);
                            }
                        }
                    }
                }else{
                    res = "操作失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
                res = "系统异常";
            } finally {
                if(response != null){
                    response.close();
                }
                if(client != null){
                    client.close();
                }
            }
            if(res.startsWith("<")){
                resp.setHeader("Content-type", "text/xml;charset=UTF-8");
            }else{
                resp.setHeader("Content-type", "text/html;charset=UTF-8");
            }
            resp.getWriter().write(res);
        //}
    }
}
