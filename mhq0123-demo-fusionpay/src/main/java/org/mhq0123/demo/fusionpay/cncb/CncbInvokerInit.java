package org.mhq0123.demo.fusionpay.cncb;

import com.sevenpay.gateway.bank.cncb.ICncb;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class CncbInvokerInit {
        
    @Value("${spring.cncb.invoker_url}")
    private String invokerUrl;
        
    @Bean
    public HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean(){
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        bean.setServiceUrl(invokerUrl);
        bean.setServiceInterface(ICncb.class);
        bean.setHttpInvokerRequestExecutor(new HttpComponentsHttpInvokerRequestExecutor());
        return bean;    
    }    
} 