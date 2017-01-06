package org.mhq0123.demo.fusionpay.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.mhq0123.demo.fusionpay.alipay.config.AlipayConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * project: mhq0123-demo
 * author:  mhq0123
 * date:    2017/1/5.
 * desc:
 */
@Component
public class AlipayClientInit {

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                "2017010404836445",
                AlipayConfig.private_key,
                "json",
                AlipayConfig.input_charset,
                AlipayConfig.alipay_public_key
        );
    }
}
