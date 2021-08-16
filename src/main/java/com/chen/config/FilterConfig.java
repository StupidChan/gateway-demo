package com.chen.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // 可以对比application.yml中关于路由转发的配置 192.168.1.6:9000/spring-demo
        return builder.routes()
                .route(r -> r.path("/spring-demo/**")
                        .uri("http://192.168.1.6:8099")
                        .order(0)
                        .id("spring-demo")
                )
                .build();

    }

}
