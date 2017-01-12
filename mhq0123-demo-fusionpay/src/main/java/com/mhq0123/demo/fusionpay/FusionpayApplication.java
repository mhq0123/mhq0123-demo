package com.mhq0123.demo.fusionpay;

import org.mhq0123.demo.common.adapter.CorsConfigurerAdapter;
import org.mhq0123.demo.common.filter.GenernalFilter;
import org.mhq0123.demo.fusionpay.cncb.CncbInvokerInit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.servlet.Filter;

/**
 * project: mhq0123-demo
 * author:  mhq0123
 * date:    2017/1/5.
 * desc:
 */
@SpringBootApplication
@ComponentScan({"com.sevenpay", "org.mhq0123", "com.mhq0123"})
@Import(CncbInvokerInit.class)
public class FusionpayApplication {

    @Bean
    public Filter filter() {
        return new GenernalFilter();
    }

    @Bean // TODO 全局ajax跨越
    public CorsConfigurerAdapter corsConfigurerAdapter() {
        return new CorsConfigurerAdapter();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(FusionpayApplication.class).web(true).run(args);
    }
}
