spring-boot-starter-actuator 是什么

一句话，actuator是监控系统健康情况的工具。
- 怎么用?
1. 添加 POM依赖

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

2. 启动的时候就会有下面这些提示.

Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.uti
Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.ser
Mapped URL path [/webjars/**] onto handler of type [class org.springframework.we
Mapped URL path [/**] onto handler of type [class org.springframework.web.servle
Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframewor
Mapped "{[/metrics/{name:.*}],methods=[GET],produces=[application/json]}" onto p
Mapped "{[/metrics || /metrics.json],methods=[GET],produces=[application/json]}"
Mapped "{[/trace || /trace.json],methods=[GET],produces=[application/json]}" ont
Mapped "{[/configprops || /configprops.json],methods=[GET],produces=[application
Mapped "{[/autoconfig || /autoconfig.json],methods=[GET],produces=[application/j
Mapped "{[/info || /info.json],methods=[GET],produces=[application/json]}" onto 
Mapped "{[/dump || /dump.json],methods=[GET],produces=[application/json]}" onto 
Mapped "{[/mappings || /mappings.json],methods=[GET],produces=[application/json]
Mapped "{[/health || /health.json],produces=[application/json]}" onto public jav
Mapped "{[/beans || /beans.json],methods=[GET],produces=[application/json]}" ont
Mapped "{[/env/{name:.*}],methods=[GET],produces=[application/json]}" onto publi
Mapped "{[/env || /env.json],methods=[GET],produces=[application/json]}" onto pu

- actuator 的端点分为3类

    应用配置类

    /configprops /autoconfig /beans /env /info /mappings

    度量指标类

    /dump /health

    操作控制类

    下面找几个来解释
        /autoconfig

自动化配置报告,可以看出一些自动化配置为什么没有生效

image.png

    /beans

可以看到定义的所有的bean

image.png

    /configprops

可以看到application.properties里面的信息

image.png

    /env

image.png

    /mappings

image.png

    /info

返回application.properties文件中info开头的配置信息,如:

# /info端点信息配置
info.app.name=spring-boot-hello
info.app.version=v1.0.0

image.png

下面是度量指标类

    /metrics

image.png

我们也可以自定实现 CounterService 接口来实现count指标.
image.png

也可以用 [/metrics/{name:.*}] 如: /metrics/mem.free 来获取单个指标信息
image.png

    /health

可以通过实现 HealthIndicator 接口来实现健康检查,返回值的状态信息在org.springframework.boot.actuate.health.Status内
image.png

image.png

    /dump

调用 java.lang.management.ThreadMXBean的
public ThreadInfo[] dumpAllThreads(boolean lockedMonitors, boolean lockedSynchronizers); 方法来返回活动线程的信息
image.png

image.png

    操作控制类

如:/shutdown ,通过在application.properties文件中添加
endpoints.shutdown.enabled=true
来开启
image.png

貌似上面命令直接把应用关了......

作者：巨子联盟
链接：https://www.jianshu.com/p/481134c3fab7
來源：简书
简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。