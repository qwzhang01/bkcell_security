**项目说明** 
- 采用SpringBoot、MyBatis、Shiro、KISSO，开发的一套单点登录权限系统，极低门槛，拿来即用。

<br>

**具有如下特点** 
- 灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 完善的XSS防范及脚本过滤，彻底杜绝XSS攻击
- 支持分布式部署，session存储在redis中

<br>

**权限设计思想** 
- 管理员管理、角色管理、部门管理，可操作本部门及子部门数据

<br> 

**项目结构** 
```
bkcell_security
├─bkcell_security-common     公共基础方法
│
├─bkcell_security-generator  代码生成器
│    ├─mapper   MyBatis 自动生成mapper文件
│    └─pojo     MyBatis自动生成数据库实体 
│
├─bkcell_security_shiro 权限公共模块
│    ├─shiro   Shiro session等重新实现模块
│    └─kisso   KiSSO中间件配置模块
│
├─bkcell_security_web      管理后台
│    ├─bkcell_security.sql  数据库SQL脚本
│    ├─src       具体后台逻辑实现模块
│    └─resources 配置文件及静态资源
│       ├─statics  静态资源
│       ├─template 系统页面
│       └─application.yml   全局配置文件
│
├─bkcell_security_dict    数据字典模块（单点登录demo）
 
```

<br>

 **技术选型：** 
- 核心框架：Spring Boot 1.5
- 安全框架：Apache Shiro 1.3
- 持久层框架：MyBatis 3.3
- 模板引擎：Beetl 1.1.33
- 数据库连接池：Druid 1.1
- 单点登录中间件：KISSO 3.6.10
- 缓存：redis
<br>

 **软件需求** 
- JDK1.8
- MySQL5.5+
- Tomcat8.0+
- Maven3.0+
- Redis

<br>

 **本地部署**
- 通过git下载源码
- 创建数据库bkcell_security，数据库编码为UTF-8
- 执行bkcell_security.sql文件，初始化数据
- 修改application.properties文件，更新MySQL账号和密码
- 修改application.properties文件，更新Redis IP和密码
- 配置本地的host文件，自定义一个访问127.0.0.1的域名
- 修改sso.properties文件中的sso.cookie.domain、sso.login.url，即上一步的域名
- 在bkcell_security目录下，执行mvn clean install
<br>

- Eclipse、IDEA运行AdminApplicationBkCellSecurityStarter.java，则可启动项目【bkcell_security_web】
- bkcell_security访问路径：http://localhost:8088
- 账号密码：admin/000000

**相关博文**
- [使用Redis缓存Shiro授权认证信息，搭建集群权限系统](https://my.oschina.net/qwzhang01/blog/1620339)
- [Shiro集成kisso，搭建单点登录系统](https://my.oschina.net/qwzhang01/blog/1620564)

**备注** 
- 项目是自己学习SpringBoot、Shiro缓存使用Redis以及单点登录的时候开发的。功能中应该有很多代码是抄的。下面简单做备注，不记得的应该在代码里面做了备注了。
- 防止XSS攻击功能，摘抄的是[renren-security](https://gitee.com/renrenio/renren-security)中的功能
