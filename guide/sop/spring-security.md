Spring security 集成 jwt流程

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