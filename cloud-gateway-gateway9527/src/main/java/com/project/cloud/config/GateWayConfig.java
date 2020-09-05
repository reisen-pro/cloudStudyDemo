package com.project.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Reisen
 * @title: GateWayConfig
 * @projectName cloud
 * @description: TODO
 * @date 2020/9/5 23:37
 */
@Configuration
public class GateWayConfig {

    /**
     * 配置了一个id为route-name的路由规则
     * 当访问地址http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     *
     * @param routeLocatorBuilder RouteLocatorBuilder
     * @return RouteLocator
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_guonei",
                r -> r.path("/gounei")
                        .uri("https://news.163.com/domestic")).build();
        routes.route("path_route_sports",
                r -> r.path("/sports")
                        .uri("https://news.baidu.com/sports")).build();
        routes.route("path_route_bilibili",
                r -> r.path("/bilibili")
                        .uri("https://www.bilibili.com")).build();
        return routes.build();
    }

}
