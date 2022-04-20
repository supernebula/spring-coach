

springcloud （ Greenwich.SR4 ） 注册中心eureka

https://blog.csdn.net/qq_25385555/article/details/103780162

http://springcloud.fun/

https://www.cnblogs.com/binghe001/p/14466792.html

https://github.com/sunshinelyz/technology-binghe


## Eureka

spring cloud 搭建注册中心Eureka（单节点）
https://www.cnblogs.com/yhood/p/11558288.html

Spring Cloud Eureka集群配置及注意事项(Greenwich版本)

http://www.easysb.cn/2019/06/411.html

Spring Cloud Eureka整合使用和配置

https://www.imooc.com/article/293962


## nacos 注册发现 （替换eureka）

https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html

安装nacos访问地址
http://127.0.0.1:8848/nacos/#/login
默认账号密码：nacos

nacos文档
https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-config

https://nacos.io/zh-cn/docs/quick-start.html

Windows
启动命令(standalone代表着单机模式运行，非集群模式):
```shell
startup.cmd -m standalone
```

Mac 或 Linux 启动
```shell


```


## Gateway

https://developer.aliyun.com/article/766417

https://www.skypyb.com/2019/10/jishu/1120/

https://www.springcloud.cc/spring-cloud-greenwich.html

https://www.springcloud.cc/spring-cloud-greenwich.html#_spring_cloud_gateway

https://www.jianshu.com/p/6ff196940b67

自定义时长统计 filter

https://blog.51cto.com/15091660/2603999

自定义全局过滤器

https://www.cnblogs.com/ye-feng-yu/p/11111803.html

https://www.cnblogs.com/crazymakercircle/p/11704077.html


## 网关  soul

官方安装教程

https://dromara.org/zh/projects/soul/soul-set-up/

SpringCloud接入Soul网关

https://dromara.org/zh/projects/soul/spring-cloud-proxy/



#### 问题

Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean.

https://blog.devmgr.cn/2020/02/16/spring-gateway-%E9%81%87%E5%88%B0%E5%BC%82%E5%B8%B8unable-to-start-servletwebserverapplicationcontext-due-to-missing-servletwebserverfactory-bean-%E6%97%A0%E6%B3%95%E5%90%AF%E5%8A%A8/

出现这种状况，是由于项目中混入了 spring-webmvc 的包导致的，spring gateway 使用 webflux， 而webflux和webmvc不兼容，就会出现上面的错误。


### 限流 

https://blog.csdn.net/m0_37834471/article/details/82621353?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.control

## Feign

https://www.springcloud.cc/spring-cloud-greenwich.html#spring-cloud-feign


## 遇到的问题
Spring cloud eureka 客户端无法注册到注册中心
解决：
客户端maven依赖
spring-cloud-netflix-eureka-client
改为
spring-cloud-starter-netflix-eureka-client

## nacos
启动命令(standalone代表着单机模式运行，非集群模式):
```shell script
sh startup.sh -m standalone
```

https://www.cnblogs.com/nijunyang/p/14141769.html

本地安装访问地址：
http://127.0.0.1:8848/nacos/index.html

## swagger

https://github.com/xiaoymin/swagger-bootstrap-ui

https://blog.csdn.net/u010192145/article/details/79216037

## zipkin 

https://zipkin.io/pages/quickstart.html

Zipkin 与 Spring Cloud 的集成

https://www.cnblogs.com/jmcui/p/10940372.html



https://www.cnblogs.com/wucy/p/13642289.html

## 多数据源 / 动态数据源






alibaba/druid

阿里巴巴官方文档

https://juejin.cn/post/6844903763048677390

https://www.cnblogs.com/xiaomaomao/p/14063212.html

https://www.cnblogs.com/qlqwjy/p/13423021.html

https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter

https://github.com/alibaba/druid/wiki/%E9%A6%96%E9%A1%B5Q

https://www.cnblogs.com/softidea/p/7127874.html

https://juejin.cn/post/6844903566717485069

https://juejin.cn/post/6844903744967016456

https://learnku.com/articles/48516

http://blog.zollty.com/b/archive/solution-of-spring-multiple-datasource.html

https://blog.csdn.net/boling_cavalry/article/details/108179671

## docker 

https://www.runoob.com/docker/centos-docker-install.html

http://www.ityouknow.com/docker.html

https://deepmind.t-salon.cc/article/1849
FF
https://mp.weixin.qq.com/s/JUfxQtiTa21SFNYD7llEoA


https://www.cnblogs.com/binghe001/p/12885714.html

http://zouliming.cn/2019/04/03/docker-%E5%AF%BC%E5%85%A5%E9%95%9C%E5%83%8F%E6%97%B6%E6%8F%90%E7%A4%BAerror-processing-tar-fileexit-status-1-archivetar-invalid-tar-header/

https://www.qikqiak.com/k8s-book/docs/5.%E7%A7%81%E6%9C%89%E9%95%9C%E5%83%8F%E4%BB%93%E5%BA%93.html

Dockerfile 及 Docker-compose使用详解

https://blog.nowcoder.net/n/2af1f1d690ec4d4abe62f71d16ce4f36

docker-compose 编排多服务应用入门与调试技巧
https://zhuanlan.zhihu.com/p/149519595

使用Docker Compose构建一键启动的运行环境
https://community.apinto.com/d/34058-docker-compose


## Dockerfile
Dockerfile复制上级目录文件
https://www.freeaihub.com/post/3094.html

## docker-compose

编排：构建docker镜像并启动docker
```shell script
docker-compose up
```

指定yml编排：构建docker镜像并启动docker
```shell script
docker-compose -f docker-composeWeb.yml up
```

停止所有docker-compose服务
```shell script
docker-compose down
```





## k8s

Kubernetes(k8s)中文教程
https://www.orchome.com/kubernetes/index

## ES、Mongo、redis、clickhouse、influxdb

### spring boot redis分布式锁

https://my.oschina.net/dengfuwei/blog/1600681

https://www.cnblogs.com/toutou/p/redis_lock.html

https://github.com/toutouge/javademosecond/tree/master/hellospringboot

https://blog.csdn.net/weixin_38399962/article/details/82753763

https://github.com/zjhzzhouzhou/SpringBootProject

## 分布式事务

https://cloud.tencent.com/developer/article/1536348


## logback

Spring Boot干货系列：（七）默认日志logback配置解析

推荐logback-spring.xml来配置

https://github.com/tengj/SpringBootDemo/tree/master

http://tengj.top/2017/04/05/springboot7/

## AOP

SpringBoot：切面AOP实现权限校验：实例演示与注解全解

https://www.mdeditor.tw/pl/pTuZ


@Aspect 注解使用详解

https://blog.csdn.net/fz13768884254/article/details/83538709

## Mybatis 

Mybatis Generator最完整配置详解

https://www.cnblogs.com/maanshancss/p/6027999.html

（JDBC 同一个数据链接生成主键并返回，避免再次查询）

集成github.papehelper
https://www.cnblogs.com/qlqwjy/p/8552078.html


pagehelper 分页不生效,总页数总是第一页解决方案
https://zyc88.blog.csdn.net/article/details/110002808

https://blog.csdn.net/wangpeng322/article/details/81330327

https://segmentfault.com/a/1190000021190378

classpath:和classpath*:  Invalid bound statement (not found)

https://www.cnblogs.com/thomas12112406/p/6545957.html

### 事务
https://blog.csdn.net/u010963948/article/details/79208328


## 自定义错误

https://www.hangge.com/blog/cache/detail_2503.html

##elasticsearch

https://www.cnblogs.com/wupeixuan/p/13450815.html

https://mp.weixin.qq.com/s/0h4qzSvhkKrLgLQh59dk7Q

https://www.tizi365.com/archives/947.html

https://es.quanke.name/aggregations/bucket-aggregations.html






## Springboot

https://github.com/wupeixuan/SpringBoot-Learn


JAVA 教程 - 线程和执行器

https://wizardforcel.gitbooks.io/modern-java/content/ch4.html

## 拦截器

Spring系列（8）- 过滤器+监听器+拦截器+AOP 比较

https://segmentfault.com/a/1190000021823564

## Spring Security

见 sop/spring-security.md

## MQ

### rabbitmq

1. rabbitmq 延迟消息

RabbitMQ安装延迟队列插件(docker)

RabbitMQ实战：界面管理和监控

https://www.jianshu.com/p/78354a3e35d0

https://juejin.cn/post/6844903591174471688

### rocketmq

使用 rocketmq-spring-boot-starter

使用指南
https://github.com/apache/rocketmq-spring/wiki

1. window启动rocketmq

powershell 添加环境变量
```shell
$Env:ROCKETMQ_HOME="D:\rocketmq"
$Env:NAMESRV_ADDR="localhost:9876"
```


```shell
.\bin\mqnamesrv.cmd
```

rocketmq控制面板
https://github.com/apache/rocketmq-dashboard

2. RocketMQ安装Linux/Mac/Window

https://zhuanlan.zhihu.com/p/228235404

下载rocketmq
https://rocketmq.apache.org/release_notes/release-notes-4.4.0/

rocketmq-all-4.4.0-bin-release.zip
```aidl
#unzip rocketmq-all-4.4.0-bin-release.zip
#cd rocketmq-all-4.4.0-bin-release
```
#启动RocketMQ的注册中心
```aidl
nohup sh mqnamesrv &
```
#启动broker
```aidl
nohup sh mqbroker -nlocalhost:9876 &
```
启动成功之后使用jps命令查看：
```aidl
evoldeMacBook-Pro:bin evol$ jps
976 
2753 nacos-server.jar
9859 NamesrvStartup
9923 Jps
2787 nacos-server.jar
9896 BrokerStartup
1609 App
1038 RemoteMavenServer36
1407 nacos-server.jar
```



### seata

Seata整合SpringBoot和Mybatis
https://segmentfault.com/a/1190000040730791


Spring Cloud实战 | 第十篇 ：Spring Cloud + Nacos整合Seata 1.4.1最新版本实现微服务架构中的分布式事务，进阶之路必须要迈过的槛
https://www.cnblogs.com/haoxianrui/p/14280184.html



https://seata.io/zh-cn/docs/user/quickstart.html

最详细SpringCloud+nacos整合Seata1.4.2 实现分布式事务
https://blog.csdn.net/weixin_44839328/article/details/122616969

seata file模式引入springboot项目步骤(不使用注册中心)
https://www.cnblogs.com/PanYuDi/p/15102531.html


seata 常见问题

https://seata.io/zh-cn/docs/overview/faq.html

问题解决：can not get cluster name in registry config ‘service.vgroupMapping.xx‘, please make sure registry - Cache One

https://cache.one/read/10581911


## Thymeleaf3语法详解和实战

https://www.cnblogs.com/itdragon/archive/2018/04/13/8724291.html

https://www.cnblogs.com/diehuacanmeng/p/13651134.html

## JWT（Json web token） 登录

https://segmentfault.com/a/1190000024448199

https://www.huaweicloud.com/zhishi/arc-9558590.html

https://www.jianshu.com/p/e88d3f8151db


使用git克隆github上的项目失败，报错error: RPC failed; curl 56 OpenSSL SSL_read: SSL_ERROR_SYSCALL, errno 10054

https://www.cnblogs.com/mmzs/p/12039888.html


Spring Reactor 入门与实践

https://www.jianshu.com/p/7ee89f70dfe5

## maven


问题：
mvn多模块项目报错：Could not resolve dependencies for project
https://blog.csdn.net/qq_35279788/article/details/109196050

IDEA中Maven项目父子工程依赖（项目多root解决），以及各子模块之间依赖关系（各模块打包方式以及依赖导入）

https://blog.csdn.net/weixin_42437164/article/details/100577715

全部项目打包成功
出现的问题：
Could not resolve dependencies  coach-common-data for project， 搜索原因:要先打包coach-common-data bing install ,依然无效；
后发现idea maven窗口coach-common-data(root)被认定为根项目，但按项目结构它应该是第三层项目。
排查coach-common发现，<moudles>节点未包含<moudle>coach-common-data</moudle>,添加后解决。所有项目可以打包成功。
原因：多项目moudle，每层的moudles不能有遗留


maven 打包自动上传服务器

https://blog.csdn.net/weixin_42195292/article/details/104271831

## 上传centos，并docker-compose部署

部署步骤：
1. maven打包到每个moudle的docker目录
2. docker-out.sh脚本
2.1 复制每个moudle的docker目录到dockerout目录
2.2 复制docker-compose-server.yml 到dockerout/docker-compse.yml    (context目录结构调整为centos部署结构)
2.3 scp整个dockerout目录到服务器的/home/admin/coach-service
3 登录服务器执行docker-compose启动整个微服务项目，启动完成

### mac 安装 ftp（ftp不用） 
mac 卡住 Updating Homebrew
https://learnku.com/articles/18908

https://osxdaily.com/2018/08/07/get-install-ftp-mac-os/

通过编译 inetutils 获取 ftp
最后，如果您更喜欢这种方法，另一种选择是从源代码编译 inetutils，您可以从 gnu.org 获得。您需要安装 Mac OS 命令行工具，然后解压缩 tarball，运行 configure、make 和 make install：

http://ftp.gnu.org/gnu/inetutils/

```aidl
tar xvzf inetutils-1.9.4.tar.gz
cd inetutils-1.9.4
./configure
make
sudo make install
```

### mac 使用sftp上传文件

https://www.linuxprobe.com/cli-scp.html

### 问题
1. centos,docker-compose up 报错  Traceback () File "**.py"
原因 docker未启动，解决办法：启动docker
https://github.com/prisma/prisma1/issues/5120

上传和下载文件
```aidl
scp username@servername:/path/filename /tmp/local_destination
scp /path/local_filename username@servername:/path
```

上传和下载文件夹
```aidl
scp -r username@servername:remote_dir/ /tmp/local_dir
scp -r /tmp/local_dir username@servername:remote_dir
```

或者
```aidl
systemctl status docker
systemctl start docker
```


## redis
MAC下Redis开启局域网IP可访问
https://blog.csdn.net/With_Her/article/details/106291416



