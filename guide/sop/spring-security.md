# Spring security

## 官方文档  
https://docs.spring.io/spring-security/site/docs/5.1.13.RELEASE/reference/html5/#sample-apps

认证方式
1、UsernamePasswordAuthenticationFilter 
Spring Security 默认认证过滤器，处于拦截器链当中

## Spring security 集成 jwt流程

https://10wjfang.github.io/blog/2019/02/14/springsecurity-jwt/

https://juejin.cn/post/6846687598442708999

概念：
## spring security 和权限、角色
计理念
从设计上来说，这是两个不同的东西。同时提供 role 和 authority 就是为了方便开发者从两个不同的维度去设计权限，所以并不冲突。

authority 描述的的是一个具体的权限，例如针对某一项数据的查询或者删除权限，它是一个 permission，例如 read_employee、delete_employee、update_employee 之类的，这些都是具体的权限，相信大家都能理解。

role 则是一个 permission 的集合，它的命名约定就是以 ROLE_ 开始，例如我们定义的 ROLE 是 ROLE_ADMIN、ROLE_USER 等等。我们在 Spring Security 中的很多地方都能看到对 Role 的特殊处理，例如上篇文章我们所讲的投票器和决策器中，RoleVoter 在处理 Role 时会自动添加 ROLE_ 前缀。

在项目中，我们可以将用户和角色关联，角色和权限关联，权限和资源关联。

## 原理

登录、登录验证、鉴权验证、url权限验证、注解权限验证


## Spring Security OAuth2

Spring Security OAuth2 Demo（阿里云）
https://developer.aliyun.com/article/315216

官方Oauth2表格
https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql


《深入浅出SpringCloud》Spring Security Oauth2
https://blog.csdn.net/ysj_2021/article/details/124394933
https://blog.csdn.net/m0_37892044/article/details/116599077

https://www.cnblogs.com/zsg88/p/11382054.html


Spring Security Oauth2 数据库
https://blog.csdn.net/Victor_An/article/details/81510874


SpringBoot + Spring Security OAuth2基本使用
https://blog.csdn.net/u013435893/article/details/79735097

Springboot 2.11版本集成security+oauth2
https://blog.csdn.net/xiyuan_mc/article/details/85015868
https://github.com/NicolaLuoqi/zhaospace.git

spring boot 2.1.6.RELEASE配oauth2-2.0版本
https://blog.csdn.net/qq_34530405/article/details/103037213

项目链接地址
https://github.com/whyalwaysmea/Spring-Security

Spring Boot Security 整合 OAuth2 设计安全API接口服务

https://developer.aliyun.com/article/703185


## 登录回调

前后端分离登录
https://mp.weixin.qq.com/s/kHJRKwH-WUx-JEeaQMa7jw


## 整合OAuth2

SpringBoot整合spring-security-oauth2完整实现例子
https://github.com/EalenXie/spring-oauth2-authenticator



## 参考 文章

SpringBoot集成Spring Security，用JWT令牌实现登录和鉴权 「这篇文章很棒」

https://zhuanlan.zhihu.com/p/142090453

SpringBoot+SpringSecurity+jwt整合及初体验 

https://www.cnblogs.com/pjjlt/p/10960690.html

Spring Security 系列第 30 篇：Spring Security 中的四种权限控制方式
https://blog.csdn.net/u012702547/article/details/106800446

spring security中Authority、Role的区别
https://blog.csdn.net/cngkqy/article/details/102919820

SpringBoot+SpringSecurity+JWT实现认证和授权
https://blog.csdn.net/u014553029/article/details/112759382


Spring Security官方文档 ：权限控制：
https://docs.spring.io/spring-security/reference/servlet/authorization/index.html


Spring Security 中的 hasRole 和 hasAuthority 有区别吗？
https://cloud.tencent.com/developer/article/1703187

https://blog.csdn.net/qq_33797688/article/details/104366838

```text
2.设计理念
从设计上来说，这是两个不同的东西。同时提供 role 和 authority 就是为了方便开发者从两个不同的维度去设计权限，所以并不冲突。

authority 描述的的是一个具体的权限，例如针对某一项数据的查询或者删除权限，它是一个 permission，例如 read_employee、delete_employee、update_employee 之类的，这些都是具体的权限，相信大家都能理解。

role 则是一个 permission 的集合，它的命名约定就是以 ROLE_ 开始，例如我们定义的 ROLE 是 ROLE_ADMIN、ROLE_USER 等等。我们在 Spring Security 中的很多地方都能看到对 Role 的特殊处理，例如上篇文章我们所讲的投票器和决策器中，RoleVoter 在处理 Role 时会自动添加 ROLE_ 前缀。

在项目中，我们可以将用户和角色关联，角色和权限关联，权限和资源关联。
```

Spring Security最简单全面教程（带Demo）
https://blog.csdn.net/qq_37771475/article/details/86153799


Spring Security系列之一 简单介绍和实战
https://juejin.cn/post/6934503341380599822

SpringSecurity+JWT认证流程解析 | 掘金新人第一弹
https://juejin.cn/post/6846687598442708999

Spring Security中文文档(官方)

https://www.springcloud.cc/spring-security.html#preface
第二部分。Servlet应用程序
https://www.springcloud.cc/spring-security.html#servlet-applications


SpringBoot 集成 Security
https://juejin.cn/post/7037014454067789860


手把手带你入门 Spring Security！ 
https://www.cnblogs.com/lenve/p/11242055.html

权限框架： Sa-Token v1.29.0
https://sa-token.dev33.cn/doc/index.html#/

Sa-token 案例
https://gitee.com/wtsoftware/jthink