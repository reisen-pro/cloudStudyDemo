<p align="center">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="80" height="80" alt="Spring" />
</p>

<h1 align="center">cloudStudyDemo</h1>

<p align="center">
  <b>Spring Cloud 微服务全家桶 — 从零搭建企业级微服务架构</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-2.3.3-6DB33F?logo=springboot&style=flat-square" />
  <img src="https://img.shields.io/badge/Spring_Cloud-Hoxton.SR8-6DB33F?logo=spring&style=flat-square" />
  <img src="https://img.shields.io/badge/Spring_Cloud_Alibaba-2.1.1-6DB33F?style=flat-square" />
  <img src="https://img.shields.io/badge/Java-1.8-ED8B00?logo=openjdk&style=flat-square" />
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&style=flat-square" />
  <img src="https://img.shields.io/badge/RabbitMQ-3.x-FF6600?logo=rabbitmq&style=flat-square" />
</p>

---

## 📖 项目简介

本项目是一个 **Spring Cloud 微服务架构** 系统学习仓库，通过 **22 个子模块** 模拟电商支付系统，循序渐进地演示了 Spring Cloud 生态中几乎所有核心组件的使用。

从服务注册发现开始，逐步深入到负载均衡、声明式调用、服务容错、API 网关、分布式配置、消息驱动等企业级微服务必备能力。

> 📅 学习时间：2020年6月-9月 | 🎯 适用人群：Java 后端开发者，Spring Boot → Spring Cloud 进阶

---

## 🏗️ 系统架构

```
                         ┌──────────────────────────────┐
                         │   Spring Cloud Gateway        │
                         │      (Gateway :9527)          │
                         │   路由断言 + 自定义全局过滤器     │
                         └──────────────┬───────────────┘
                                        │
        ┌───────────────────────────────┼───────────────────────────────┐
        │                               ▼                               │
        │               ┌──────────────────────────────┐                │
        │               │      Eureka 注册中心集群        │                │
        │               │   ┌────────┐   ┌────────┐    │                │
        │               │   │ :7001  │◄─►│ :7002  │    │                │
        │               │   └────────┘   └────────┘    │                │
        │               └──────┬───────────────┬───────┘                │
        │                      │               │                        │
        │             注册发现  │               │  注册发现               │
        │                      ▼               ▼                        │
        │  ┌──────────────────────────┐  ┌──────────────────────────┐  │
        │  │   支付服务集群 (Provider)   │  │   订单服务 (Consumer)      │  │
        │  │  ┌──────┐┌──────┐┌──────┐│  │  调用方式:                 │  │
        │  │  │:8001 ││:8002 ││:8004 ││  │  · Ribbon + RestTemplate  │  │
        │  │  └──────┘└──────┘└──────┘│  │  · OpenFeign 声明式调用    │  │
        │  │  MyBatis + MySQL 8.0     │  │  · Feign + Hystrix 降级   │  │
        │  └──────────────────────────┘  └──────────────────────────┘  │
        │                                                                │
        │   ┌────────────────────┐  ┌──────────────────────┐            │
        │   │  Spring Cloud Config│  │ Spring Cloud Stream  │            │
        │   │      :3344          │  │    + RabbitMQ         │            │
        │   │  Git 配置仓库 + Bus │  │  Provider :8801       │            │
        │   │  Client :3355/:3366 │  │  Consumer :8802/:8803 │            │
        │   └────────────────────┘  └──────────────────────┘            │
        │                                                                │
        │   ┌──────────────────────────────────────┐                    │
        │   │      Hystrix Dashboard :9001         │                    │
        │   │        断路器实时监控面板               │                    │
        │   └──────────────────────────────────────┘                    │
        │                                                                │
        │   支持三种注册中心: Eureka（主要）/ Zookeeper / Consul           │
        └────────────────────────────────────────────────────────────────┘
```

---

## 🗂️ 项目结构

```
cloudStudyDemo/
├── pom.xml                                    # 父工程（聚合 22 个模块，统一依赖管理）
│
├── cloud-api-commons/                         # 公共模块（Payment 实体 + CommonResult 返回类）
│
├── ─── 注册中心 ─────────────────────────────────────
├── cloud-eureka-server7001/                   # Eureka 注册中心 1（端口 7001）
├── cloud-eureka-server7002/                   # Eureka 注册中心 2（端口 7002，互注册 HA）
│
├── ─── 服务提供者 ───────────────────────────────────
├── cloud-privoder-payment8001/                # 支付服务 1（端口 8001，MyBatis + Druid + MySQL）
├── cloud-privoder-payment8002/                # 支付服务 2（端口 8002，集群节点）
├── cloud-privoder-payment8004/                # 支付服务 3（端口 8004，集群节点）
├── cloud-providerconsul-payment8006/          # Consul 支付服务（端口 8006）
│
├── ─── 服务消费者 ───────────────────────────────────
├── cloud-consumer-order80/                    # Ribbon + RestTemplate 调用（端口 80）
├── cloud-consumerzk-order80/                  # Zookeeper 消费者（端口 80）
├── cloud-consumerconsul-order80/             # Consul 消费者（端口 80）
├── cloud-consumer-feign-order80/              # OpenFeign 声明式调用（端口 80）
├── cloud-consumer-feign-hystrix-order80/      # Feign + Hystrix 降级/熔断（端口 80）
│
├── ─── 服务容错与监控 ───────────────────────────────
├── cloud-provider-hystrix-payment8001/        # Hystrix 服务降级/熔断/限流（端口 8001）
├── cloud-consumer-hystrix-dashboard9001/      # Hystrix Dashboard 监控面板（端口 9001）
│
├── ─── 服务网关 ─────────────────────────────────────
├── cloud-gateway-gateway9527/                 # Spring Cloud Gateway（端口 9527）
│
├── ─── 分布式配置 ───────────────────────────────────
├── cloud-config-center3344/                   # Config Server + Bus（端口 3344）
├── cloud-config-client3355/                   # Config Client 1（端口 3355，@RefreshScope）
├── cloud-config-client3366/                   # Config Client 2（端口 3366）
│
├── ─── 消息驱动 ─────────────────────────────────────
├── cloud-stream-rabbitmq-provider8801/        # Stream 消息生产者（端口 8801）
├── cloud-stream-rabbitmq-consumer8802/        # Stream 消息消费者 1（端口 8802，分组消费）
├── cloud-stream-rabbitmq-consumer8803/        # Stream 消息消费者 2（端口 8803，竞争消费）
│
├── 笔记/
│   ├── 笔记.md                                 # 详细技术笔记
│   └── monitor.ftlh                           # Hystrix Dashboard 监控模板
│
└── README.md
```

---

## 🛠️ 技术栈

| 类别 | 技术 | 版本 | 说明 |
|------|------|------|------|
| 基础框架 | Spring Boot | 2.3.3.RELEASE | 微服务基础 |
| 微服务框架 | Spring Cloud | Hoxton.SR8 | 微服务全家桶 |
| 阿里巴巴 | Spring Cloud Alibaba | 2.1.1.RELEASE | 阿里巴巴微服务组件 |
| 服务注册 | Eureka / Zookeeper / Consul | - | 三种注册中心对比 |
| 负载均衡 | Ribbon | 内置 | 客户端负载均衡 + 自定义算法 |
| 服务调用 | OpenFeign | 内置 | 声明式 HTTP 客户端 |
| 服务容错 | Hystrix | 内置 | 降级 / 熔断 / 限流 |
| API 网关 | Spring Cloud Gateway | 内置 | 基于 WebFlux 的网关 |
| 配置中心 | Spring Cloud Config + Bus | 内置 | 分布式配置 + RabbitMQ 广播刷新 |
| 消息驱动 | Spring Cloud Stream + RabbitMQ | 内置 | 消息中间件抽象 |
| ORM | MyBatis + Druid | 1.3.2 / 1.1.16 | 数据访问层 |
| 数据库 | MySQL | 8.0.21 | 关系型数据库 |
| 工具 | Lombok / Hutool | 1.16.18 / 5.1.0 | 简化开发 |

---

## 🎯 学习路径

按以下顺序启动各模块，可以循序渐进地理解微服务架构演进：

```
阶段 1 ──► 服务注册与发现
             ├── cloud-eureka-server7001 + 7002（Eureka 集群，相互注册 HA）
             └── cloud-privoder-payment8001（注册到 Eureka）
             核心概念：服务治理、CAP 理论、自我保护机制

阶段 2 ──► 服务调用与负载均衡
             ├── cloud-consumer-order80（RestTemplate + @LoadBalanced）
             └── cloud-privoder-payment8002 + 8004（支付服务集群）
             核心概念：Ribbon 负载均衡策略、自定义轮询算法、IRule

阶段 3 ──► 声明式服务调用
             └── cloud-consumer-feign-order80（@FeignClient 注解）
             核心概念：声明式 HTTP 客户端、Feign 日志配置、超时控制

阶段 4 ──► 服务容错与降级
             ├── cloud-provider-hystrix-payment8001（服务提供者端熔断）
             ├── cloud-consumer-feign-hystrix-order80（消费者端降级）
             └── cloud-consumer-hystrix-dashboard9001（可视化监控）
             核心概念：服务降级、服务熔断、服务限流、断路器状态机、Fallback

阶段 5 ──► API 网关
             └── cloud-gateway-gateway9527
             核心概念：Route/Predicate/Filter 三大核心、自定义全局过滤器

阶段 6 ──► 分布式配置中心
             ├── cloud-config-center3344（Config Server，从 Git 拉取配置）
             ├── cloud-config-client3355 + 3366（Config Client）
             └── Spring Cloud Bus + RabbitMQ（配置变更广播刷新）
             核心概念：统一配置管理、@RefreshScope 热刷新、Bus 广播通知

阶段 7 ──► 消息驱动
             ├── cloud-stream-rabbitmq-provider8801（消息生产者）
             └── cloud-stream-rabbitmq-consumer8802 + 8803（消费者）
             核心概念：Binder/Source/Sink 抽象、分组消费 vs 竞争消费、消息持久化
```

---

## 🚀 快速开始

### 前置环境

- JDK 1.8+
- Maven 3.6+
- MySQL 8.0
- RabbitMQ 3.x
- Consul / Zookeeper（可选，默认使用 Eureka）

### 环境准备

```bash
# 1. 配置 hosts（Eureka 集群需要）
# 在 C:\Windows\System32\drivers\etc\hosts 中添加：
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com

# 2. 创建 MySQL 数据库
CREATE DATABASE clouddemo;
USE clouddemo;
CREATE TABLE payment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    serial VARCHAR(200) DEFAULT ''
);
```

### 启动顺序

```bash
# 1. 编译整个父工程
mvn clean install -DskipTests

# 2. 依次启动各模块（顺序很重要！）

# 第1步：启动 Eureka 注册中心集群
cd cloud-eureka-server7001 && mvn spring-boot:run &
cd cloud-eureka-server7002 && mvn spring-boot:run &

# 第2步：启动支付服务集群
cd cloud-privoder-payment8001 && mvn spring-boot:run &
cd cloud-privoder-payment8002 && mvn spring-boot:run &
cd cloud-privoder-payment8004 && mvn spring-boot:run &

# 第3步：启动消费者（按需选择）
cd cloud-consumer-order80 && mvn spring-boot:run &

# 第4步：启动其他组件
# Gateway :9527 / Config :3344 / Stream :8801~8803 ...
```

### 测试接口

```bash
# 测试支付服务
curl http://localhost:8001/payment/get/1

# 通过订单服务调用支付服务（Ribbon 负载均衡）
curl http://localhost/consumer/payment/get/1

# 通过 Feign 调用
curl http://localhost/consumer/feign/payment/get/1

# 测试 Hystrix 熔断
curl http://localhost:8001/payment/circuit/-1

# 测试 Gateway 路由
curl http://localhost:9527/payment/get/1

# 测试 Config 刷新
curl -X POST http://localhost:3344/actuator/bus-refresh

# Hystrix Dashboard
# 浏览器访问 http://localhost:9001/hystrix
```

---

## ✨ 项目亮点

1. **全面覆盖** — 一套项目覆盖 Spring Cloud 生态 **8 大核心组件**，一揽子解决微服务基础设施
2. **模块化设计** — 22 个子模块职责清晰，可独立运行、组合测试，符合微服务分而治之的理念
3. **架构演进** — 从单注册中心到集群 HA，从直接调用到 Feign + Hystrix，完整体现架构演进过程
4. **多方案对比** — 同时演示 Eureka / Zookeeper / Consul 三种注册中心，Ribbon / Feign 两种调用方式
5. **自定义算法** — 手写 Ribbon 负载均衡轮询算法，深入理解客户端负载均衡原理
6. **熔断实战** — Hystrix Dashboard 可视化监控 + 断路器状态机完整演示
7. **配置热刷新** — Config + Bus + RabbitMQ 实现配置变更全自动广播刷新
8. **配套笔记** — 详细中文笔记，包含原理说明、操作步骤、踩坑记录、组件选型分析
9. **生产最佳实践** — Hosts 配置 / 集群部署 / 分组消费 / 全局过滤器 / 日志级别控制

---

## 📝 组件选型建议（笔记摘录）

| 组件 | 当时使用 | 推荐替代 | 说明 |
|------|---------|---------|------|
| 注册中心 | Eureka | **Nacos** | Eureka 2.0 已停更，Nacos 同时支持 AP/CP |
| 负载均衡 | Ribbon | **Spring Cloud LoadBalancer** | Ribbon 停止维护 |
| 服务调用 | Feign | **OpenFeign** | ✅ 已使用 OpenFeign |
| 服务容错 | Hystrix | **Sentinel**（国内）/ Resilience4j | Hystrix 进入维护模式 |
| API 网关 | Gateway | **Spring Cloud Gateway** | ✅ 已使用 Gateway，替代 Zuul |
| 配置中心 | Config | **Nacos** | Config 可配合 Bus 使用，Nacos 更便捷 |

---

## 📚 相关资源

- [Spring Cloud 官方文档](https://spring.io/projects/spring-cloud)
- [Spring Cloud Alibaba 文档](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/zh-cn/)
- [Nacos 官方文档](https://nacos.io/zh-cn/)
- [Sentinel 官方文档](https://sentinelguard.io/zh-cn/)

---

## 📝 License

MIT — 仅供学习参考，欢迎 Star & Fork ⭐

