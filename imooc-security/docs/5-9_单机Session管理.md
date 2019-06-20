# 5-9 单机Session管理

* Session超时处理

    - session超时时间，单位秒
    - server.session.timeout = 600
    - 在SpringBoot中Session最短时间为1分钟
    - TomcatEmbeddedServletContainerFactory.configureSession

* Session并发控制

* 集群Session管理