# cloudStudyDemo
### 学习和熟悉spring cloud
```目录结构说明
cloud-api-commons下是公共组件

cloud-consumer-order 80端口订单模块
cloud-consumerconsul-order80  基于consul的8080端口订单模块  注册中心在consul cp
cloud-consumerzk-order 基于zookeeper的80端口订单模块 注册中心在zookeeper中 cp
cloud-consumer-feign-order80 集成了openfeign功能的order模块
cloud-consumer-feign-hystrix-order80 于openfeign的技术上，集成了hystrix服务降级、服务熔断功能

cloud-eureka-server7001 7001 erueka注册中心
cloud-eureka-server7002 7002 erueka注册中心
两个注册中心相互注册

cloud-privoder-payment8001 8001端口服务提供者
cloud-privoder-payment8002 8002端口服务提供者
cloud-privoder-payment8004 8004端口服务提供者
cloud-providerconsul-payment8006 8006 consul服务提供者

cloud-consumer-hystrix-dashboard9001提供hystrix后台监控
```

