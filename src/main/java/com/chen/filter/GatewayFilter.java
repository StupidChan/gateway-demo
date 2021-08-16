package com.chen.filter;

import cn.hutool.json.JSONUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Set;

@Component
@Configuration
public class GatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        System.out.println("request: " + JSONUtil.toJsonStr(request));

        //--------   uri  ----------
        URI uri = request.getURI();
        System.out.println("uri: " + JSONUtil.toJsonStr(uri));
        String path = uri.getPath();
        String scheme = uri.getScheme();
        int port = uri.getPort();
        String authority = uri.getAuthority();
        String query = uri.getQuery();
        String host = uri.getHost();
        String schemeSpecificPart = uri.getSchemeSpecificPart();

        // -----------   body  ------------
        Flux<DataBuffer> body = request.getBody();


        // -----------  query  ------------
        System.out.println();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        Set<String> keys = queryParams.keySet();
        for (String key : keys) {
            System.out.print("  key：" + key + " value:" + queryParams.getFirst(key));
        }
        System.out.println();
        HttpHeaders headers = request.getHeaders();

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
