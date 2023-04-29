### 项目简介

食悦阁点餐系统是一个基于**SpringBoot**的**Java**单体项目，旨在为餐饮企业定制的一款软件产品，包括系统管理后台和移动端应用两部分，其中后台主要提供给内部员工使用，可以对餐厅的菜品，套餐，订单，员工信息等进行管理维护，移动端主要供消费者使用，可以在线浏览菜品，添加购物车，下单等。

通过该项目，你可以

1 可以快速了企业项目开发的基本流程，增长开发经验；

2 了解需求分析过程，提高分析和设计能力；

3 对所学技术灵活应用，提高编码能力；

4 解决各种异常情况，提高代码调试能力。

### 项目页面展示

1 这是项目管理端登录界面

![image-20230429195014197](.\images\image-20230429195014197.png)

2 这是项目移动点餐界面

![image-20230429195712202](.\images\image-20230429195712202.png)

3 这是后台管理菜品的页面![image-20230429195543342](.\images\image-20230429195543342.png)

### 具体技术栈

前端：`HTML＋CSS＋JavaScript + Vue + ElementUI + JavaWeb`

后端：`Spring+SpringMVC+SpringBoot+MybatisPlus+MySQL`

中间件及开发工具：`Druid+Redis+Maven+Git`

开发环境：`Win10+CentOS7+IDEA2022+Tomcat7+JDK11`

### 如何启动

1 安装项目所需组件，例如**MySQL,Redis**等；

2 导入该项目，作为**Maven**项目，注意**Maven**与**IDEA**版本问题，本人用的是**Maven3.6.1+IDEA2022.3**，高版本**Maven**与**IDEA2022**存在不兼容问题；

3 按照`src/main/resources/db_sql`文件，构建数据模型，注意数据库名称；

4 按照**pom.xml**导入相关依赖坐标；

5 修改**application.yml**中的**Redis**和**MySQL**等配置信息；

6 在搭建好所有项目依赖和资源后，启动项目，访问[http://localhost:8080/backend/page/login/login.html](http://localhost:8080/backend/page/login/login.html)登录界面，输入账户密码完成登录进入后台可完成对菜品员工信息的管理；

7 若要模拟移动端用户，则访问[http://localhost:8080/front/page/login.html](http://localhost:8080/front/page/login.html)进入登录页面，完成注册登录，这里登录和注册作为一个接口，若输入的账户不存在数据库中则自动给用户注册；

8 验证码会通过日志形式打印在控制台，若需要真实短信服务，请购买阿里云短信服务，输入验证码进入下单界面完成下单。

### 项目整体流程

![image-20230429213217080](.\images\image-20230429213217080.png)

### 优化空间

1 实现**MySQL**数据库的主从复制，完成读写分离；

2 同时部署多台**Tomcat**服务器，加入**Nginx**完成负载均衡；

3 订单业务可以引入**RabbitMQ**中间件，完成解耦，实现异步调用。