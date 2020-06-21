package com.lv.cloud.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger lg = LoggerFactory.getLogger(GatewayGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        lg.info("***********进入拦截器****************");
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        URI uri = request.getURI();
        System.out.println("path: "+path);
        System.out.println("uri: "+uri);
        if(path.equals("authroien")){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //直接返回
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    //越小，filter在filter链中的优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
