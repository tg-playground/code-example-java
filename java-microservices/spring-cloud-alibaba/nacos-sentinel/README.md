# Nacos Sentinel

只有主动调用了 Sentinel 保护的资源（接口、方法），才会触发 Sentinel 控制台的监控。

每次启动项目后，通过 Sentinel 控制台手动添加限流和熔断规则。或者添加 spring.cloud.sentinel.datasource 的 Nacos 配置，自动读取
Nacos 中的配置作为限流和熔断规则。
