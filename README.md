# 《Redis 6 开发与实战》

![](https://images.cnblogs.com/cnblogs_com/wangshuo1/1199420/o_210812032523Redis6.jpg)

为什么要编写本书？
=========

Redis作为一个流行的key-value内存数据库 ，由于性能高，数据类型丰富，API功能强大，可用性高等特点，已经被越来越多的企业用于生产实践了。Redis可以讲所有数据都存放在内存中，所以它的读写性能非常惊人，Redis还可以讲内存中的数据利用快照和日志的形式保存到硬盘上，保证内存中的数据不会丢失。总之在合适的场景使用好Redis，它就会像一把瑞士军刀一样方便。

本书基于最新的Redis 6.*版本，为帮助大家的理解，书中使用了大量的实战案例，覆盖了Redis的几乎所有方面，从Redis基本数据类型，常用该命令，一直到Redis缓存持久化，集群环境部署和开发实战等高级主题。

学习任何技术都要理论联系实践，所以本书的作者将通过大量的案例向读者系统的讲解Redis的各个知识点，在读者的个人计算机上，只要遵循本书案例的操作步骤，都可以很容易的理解每个案例的知识点，缩短学习Redis的时间和降低学习编程的难度，书中使用的案例都是作者在企业开发和运维工作中经验的总结和感悟。
希望本书可能让读者更好的理解Redis，可以帮助读者在工作中正确使用Redis的新特性。

本书的结构
=========

使用的是最新版本的 Redis 6.*，详细讲解了Redis 6.* 的各个知识点，包含从入门到实战的所有例子。

第1章  初识Redis ，
本章详细介绍了Redis服务器的安装和基本操作，包括在Windows和Linux环境下启动和停止Redis，使用redis-cli连接到Redis服务器。在本章的最后还介绍了Redis集群实验的安装环境。

第2章  Redis常用数据类型及操作，
本章主要介绍了Redis的常用的数据类型和操作数据类型的常用命令，还介绍了Redis 6.* 的插件功能，在Redis服务器上加载布隆过滤器模块。

第3章  Redis常用命令，
本章主要介绍了Redis的常用命令，包括键值相关命令和服务器相关命令。

第4章  Redis高级主题，
本章主要介绍了Redis的高级主题，包括Redis服务器配置，事务，发布和订阅功能，Pipline批量发送请求，性能测试，客户端连接和Redis开机启动，介绍了Redis的高级功能。

第5章  Redis缓存持久化，
本章主要介绍了Redis中的两种持久化方式：RDB和AOF，讲解了在Redis中如何启动RDB和AOF来实现持久化，并揭示了持久化的工作原理。本章讨论了RDB和AOF之间的区别，以及如何将这两种方式结合起来使用。

第6章  Redis集群环境部署，
本章主要介绍了Redis的高可用相关结构。讲解了主从复制，哨兵模式 和Redis 6.* Cluster集群配置和命令。主要介绍了Redis 6.* 集群配置的详细实验步骤和工作原理进行了解释。

第7章  Redis开发实战，
本章主要讲解了如何使用Java开发Redis程序，本章讨论了Redis的数据类型和API的使用，讲解了使用Redis客户端jedis应用程序的实例，在本章中使用案例介绍了edis在生产环境的使用场景和技巧。

第8章  Spring Boot与Redis整合使用，
本章介绍了 Spring Boot2框架与Redis的集成，详细介绍了RedisTemplate API的使用，最后使用案例介绍了Spring Session中使用Redis场景和原理。

第9章  Redis监控，
本章介绍了Redis的性能监控和自定义开发应用程序来监控Redis的性能,使用的是最新的Websocket技术与后台进行消息推送和接收。

第10章  Redis的缓存设计与优化，
本章介绍了Redis作为缓存的收益和成本，以及在生产环境下出现缓存雪崩和缓存穿透的原因和解决方法。

第11章  扩展知识，
本书设计的技术知识内容比较多，所以把读者需要掌握的内容单独汇聚成一张，包括CentOS的配置，Maven基础知识，配置Intellij IDEA，配置SecureCRT,Chrome常用技巧和Python 3操作Redis 5集群。

本书使用软件和实验涉及的链接
=========
本书中涉及的软件下载链接如下所示：

- 1.1.1	Redis简介

Redis的官方网站是： http://Redis.io/ , 最新版本的Redis安装包是Redis 6.0.5(截止到2020年10月)

我们也可以访问Redis的中文官方网站： http://www.redis.cn ，这个页面有个方便的工具，访问互动教程链接(http://try.redis.io/) ,会弹出一个新的页面


- 1.2.1	在Windows下安装Redis

Redis的Windows版本下载链接：
https://github.com/MicrosoftArchive/redis

本节中使用的是基于Windows 64位版本下的Redis安装包,Redis安装包的下载地址:
https://github.com/MSOpenTech/Redis/releases

- 1.2.2	在Linux下安裝Redis

首先，从官网（https://redis.io/download）下载稳定版本的Redis源码包，目前(2019年9月)，本书使用的最新的稳定版本是5.0.5。

- 1.3	Redis可视化工具 

RedisDesktopManager官网地址如下：
https://redisdesktop.com/download

- 1.4.2	安装 Linux系统

CentOS的官网地址是 https://www.centos.org 。

- 2.7.1	加载布隆过滤器模块

在https://github.com/RedisBloom/RedisBloom下载最新发布的源码，点击页面的”Clone or download”按钮后选择”Download Zip”选项，下载RedisBloom-master.zip到本地硬盘。

- 6.4.2	开始Redis 6 集群搭建

下载Ruby依赖的redis.gem，可以去官网 https://rubygems.org/gems/redis/versions 下载。

- 7.1.1	在Windows环境下安装Java 8

JDK8的下载链接：
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

- 7.1.2	安装Tomcat 9

Tomcat官网地址：http://tomcat.apache.org/ 

- 7.2.1	连接Redis的两种方式

Jedis参考文档是http://jedis.mklab.cn/

- 7.2.7	Redis存储图片

访问网页 https://graph.baidu.com/thumb/691874602,911901597.jpg 可以看到一只可爱的小熊猫图片，我们要把这张图片保存在Redis中。

- 7.6.3	安装服务器 Tomcat和反向代理服务器Nginx

JDK的下载地址是 https://www.oracle.com/technetwork/java/javase/downloads 。

在Tomcat的官网 https://tomcat.apache.org/download-70.cgi ,下载Tomcat7的Linux版本压缩包apache-tomcat-7.0.96.tar.gz。

Nginx的官网地址是http://nginx.org 

- 7.6.5	配置Tomcat 使用Redis作为Session管理

下载最新的Jedis（一个Redis 的Java客户端），Tomcat Redis Session Manager 和 Apache Commons Pool， 把 jedis-3.1.0.jar ， tomcat-redis-session-manager1.2.jar ， commons-pool2-2.0.jar复制到 Tomcat7/lib目录下。各个组件的下载地址是：

JRedis :  https://github.com/xetorthio/jedis

Tomcat Redis Session Manager ：https://github.com/jcoleman/tomcat-redis-session-manager/downloads

Apache Commons Pool: http://commons.apache.org/proper/commons-pool/download/pool.cgi

- 8.1.1	Spring Boot 简介

Spring Boot官网上的文档质量很高，如果想快速理解SpringBoot的话，浏览官网网页是最快捷的途径，参考网址是：http://spring.io/guides/gs/rest-service/ 。


- 9.1.2	 使用redis-stat

redis-stat开源地址为：https://github.com/junegunn/redis-stat

安装 redis-stat时，redis-stat 的使用需要依赖Ruby环境，但JRuby为我们提供了使用Java的打开方式。从https://github.com/junegunn/redis-stat/releases  下载redis-stat-0.4.14.jar文件

- 9.2.1	前端页面

start bootstrap Admin2的官方地址和github地址如下所示。

官方地址：http://startbootstrap.com/template-overviews/sb-admin-2/ 

github地址： https://github.com/IronSummitMedia/startbootstrap-sb-admin-2

ECharts官网可以访问 http://echarts.baidu.com/

ECharts的官网例子,请参考以下页面。
http://echarts.baidu.com/echarts2/doc/example.html

如果需要定制复杂的图形，可以参考EChartsAPI文档。
http://echarts.baidu.com 

- 11.2.2	 Maven下载

Maven的下载地址是 http://maven.apache.org/download.cgi

- 11.2.6	 pom.xml文件中的groupId和artifactId到底该怎么定义？

可以从 http://mvnrepository.com/ 查找开源模块的依赖配置

- 11.7.1	 在Windows下安装Python

访问Python官方网站：https://www.python.org。

在下载页面（https://www.python.org/downloads/release/python-364/）中下载所需要的Python 3.6.4版本，读者可根据自己使用的平台选择相应的版本进行下载。

- 11.7.2	 使用Redis模块

Python标准模块中没有连接Redis的模块，但已经有开源的连接单机的Redis的Python模块: 
redis-py，更详细信息可以访问连接单机Redis的Python模块网页：

https://github.com/andymccurdy/Redis-py


Python连接集群的的Redis模块: redis-py-cluster，更多信息可以参考网页。

http://readthedocs.org/projects/redis-py-cluster/



购买通道
=========
《Redis 6 开发与实战 》已经在京东商城上线了，欢迎大家购买。

**亲爱的读者们，现在可以在京东商城购买《Redis 6 开发与实战 》了**。

店铺地址是：
https://item.jd.com/13381600.html


写书进度
=========
2021年1月完成本书的编写

- 2019年10月18日本书稿件第一版完成。

- 2020年2月10日本书稿件第二版修改完成。

- 2020年4月7日本书稿件第三版修改完成。

- 2020年11月29日本书稿件第四版修改完成。

- 2021年08月12 新书出版


联系作者
=========
如果读者有任何问题，请发送电子邮件联系2位作者(张云河，王硕)，邮箱为xpws2006@163.com，也可以直接加入QQ的**Redis6开发高级群(629588406)** 联系笔者，2位笔者白天都在群里。

路漫漫其修远兮，吾将上下而求索。在读者的不断求索中，能够与本书相遇也是一种缘分，如果读者能够从本书中获取自己想要的东西，那就是对笔者最大的欣慰。最后，提前预祝读者学习上轻松愉快、工作上更进一步、生活上幸福美满,学有所成。








