
# 迭代
## 迭代一 完成：迭代轮第一滚：框架、工具类技术点通、CURDQ、MQ、基本业务脚手架OK

阶段0： 数据库

阶段1：微服务、基本业务、日志、本地配置、部署

阶段2：ACM配置、redis、用户余额、MQ事务

阶段3： 抢购（付款定时过期mq延迟消息）、模拟回调、多数据源、分布式事务

阶段4: ELK、文件上传


## 迭代二 ：

1. 微信自动登录

    获取accessToken（OK）

    JWT 登录
  
2. Movie列表、详情、下单、30分钟未支付取消订单、支付、模拟回调、我的订单


3. C端界面项目调整、注册发现、网关、NatAPP域名

4. B端管理界面项目、注册发现、网关、NatAPP域名

5. 日志完整、ACM配置完整、日志完整


## 迭代三：

6. docker 、k8s

7. jenkins部署


阶段5：整理各种代码、spring admin 监控

抢购

阶段6：多线程

阶段7：Netty


#
商品管理（CURD）
商品展示（）
下单支付（）
抢购
退款


#
事务
分布式事务
MQ rabbitmq、rocketMQ



# basic component
ACM（Nacos）
logback
ehcache
多数据源
quraz 定时器


# data

redis
ELK
mongodb
influxdb
clickhouse
mysql


# optional
eventbus


## JWT（Json web token） 登录

https://segmentfault.com/a/1190000024448199

https://www.huaweicloud.com/zhishi/arc-9558590.html

https://www.jianshu.com/p/e88d3f8151db


使用git克隆github上的项目失败，报错error: RPC failed; curl 56 OpenSSL SSL_read: SSL_ERROR_SYSCALL, errno 10054

https://www.cnblogs.com/mmzs/p/12039888.html
