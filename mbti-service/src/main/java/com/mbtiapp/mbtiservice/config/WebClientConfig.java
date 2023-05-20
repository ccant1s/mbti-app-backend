package com.mbtiapp.mbtiservice.config;

import com.mbtiapp.mbticommon.Res.TestEmpModel;
import com.mbtiapp.mbtiservice.client.EmpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient employeeWebClient(){
        return WebClient.builder()
                .baseUrl("http://mbti-service-user")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public EmpClient empClient () {
        HttpServiceProxyFactory httpServiceProxyFactory =
        HttpServiceProxyFactory.builder(WebClientAdapter.forClient(employeeWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(EmpClient.class);
    }
}
