package org.mhq0123.demo.fusionpay.cncb;

import com.sevenpay.gateway.bank.cncb.ICncb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class CncbInvokerInit {
        
    @Value("${spring.cncb.invoker_url}")
    private String invokerUrl;
        
    @Autowired
    private HttpComponentsHttpInvokerRequestExecutor httpInvokerRequestExecutor;
        
    @Bean
    public HttpInvokerProxyFactoryBean cncbService(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl(invokerUrl);
        bean.setServiceInterface(ICncb.class);
        bean.setHttpInvokerRequestExecutor(httpInvokerRequestExecutor);    
        return bean;    
    }    
} 