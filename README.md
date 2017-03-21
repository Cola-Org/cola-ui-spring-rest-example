# Cola-UI Spring Rest Example



## 技术选型

1、前端

* UI框架：Cola-UI + jQuery 2.1.3。

2、后端

* 核心框架：Spring Framework 4.2.0.RELEASE
* 视图框架：Spring MVC 4.2.0.RELEASE
* 服务框架：Spring REST 4.2.0.RELEASE
* 持久层框架：Hibernate 4.3.6.Final
* 工具类：Apache Commons、Jackson 2.5.3

3、数据库

* MySQL


## 如何启动

1、修改 src/main/resources/configure.properties

```
jdbc.username = username
jdbc.password = password
jdbc.url = jdbc:mysql://localhost:3306/dbname?useUnicode=true&characterEncoding=UTF-8
jdbc.driver = com.mysql.jdbc.Driver
```

2、启动服务

3、初始化数据

* `此文件为初始化数据，表结构将服务启动时自动创建`
* 初始化文件在/src/main/resources/init-data.sql

4、浏览器访问

http://localhost:port/context
	
		
## Cola 开放数据
##### Cola-UI 测试与演示平台使用
   
提供的接口列表如下
* /service/company

* /service/company/find/:from/:limit

* /service/company/find?pageSize=5&pageNo=1

* /service/company/:id/depts

* /service/company/:id/employees

* /service/company/:id/find/depts?pageSize=5&pageNo=1

* /service/company/:id/find/employees?pageSize=5&pageNo=1

* /service/dept

* /service/dept/find/:from/:limit

* /service/dept/find?pageSize=5&pageNo=1

* /service/dept/:id/employees

* /service/dept/:id/find/employees?pageSize=5&pageNo=1

* /service/employee

* /service/employee/find/:from/:limit

* /service/employee/find?pageSize=5&pageNo=1

* /service/district/

* /service/district/provinces

* /service/district/children?parentId=2

* /service/district/:id/children

* /service/employee/exists?email=alex.tong007@gmail.com

* /service/dictionary?codes=10011,10012,10013,10014



