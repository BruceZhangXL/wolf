使用maven构建的完整项目，下载后以maven项目导入开发环境，运行Application.java即可启动。

<h2>脚手架</h2>
构建：maven<br>
基础：spring-boot<br>
ORM：mybatis<br>
数据库：MySQL<br>
中间件：redis,rabbitMQ<br>
<h2>项目结构</h2>
<img src="http://p02s7nfnp.bkt.clouddn.com/sb-ss.png">
<h2>启动说明</h2>
找到wolf-web下的Application类，右击：run as:java application，当看到输出日志“---------WOLF IS READY---------”，即启动成功。<br>
访问路径：http://localhost:8080/index  默认是8080端口,如何改默认端口，以及日志配置，数据库的配置，往下看。
<h2>wolf项目的说明</h2>
<h3>profile配置</h3>
spring boot项目的配置默认都在application.properties文件里，也即是wolf-web模块下的src/main/resources目录下的application.properties
但是，这些配置是和环境相关的，dev，test，prep，prod的配置是不同的，所以为了区分，spring boot提供了与maven的profile配合使用，我们放了
4个配置文件，分别对应这几个环境。同时你会发现application.properties依然存在，但是它里面只放了一些默认的配置。当你使用
mvn package -Dmaven.test.skip=true -Pdev打包的时候，这个默认的配置文件和application-dev.properties，这两个配置文件都会代入变量。
<h3>内置tomcat配置</h3>
都在application.properties文件中
端口server.port=8080<br>
server.tomcat.accept-count= <br>
server.tomcat.max-connections= <br>
server.tomcat.max-http-post-size=0 <br>
server.tomcat.max-threads=0 <br>
server.tomcat.min-spare-threads=0<br>
当然还有其他配置，可以查文档
<h3>数据源配置</h3>
以前的是需要单独的dataSource配置文件来配置数据源，现在只要在application-{profiles.active}.properties中配置数据源就好了
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/goodscenter?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull&useSSL=false<br>
spring.datasource.username=root<br>
spring.datasource.password=root<br>
spring.datasource.initialSize=10<br>
spring.datasource.maxActive=30<br>
spring.datasource.maxIdle=10<br>
spring.datasource.maxWait=10000<br>

必须要以spring开头，此时你一定会有疑问，如何指定特定的数据源比如c3p0，durid，当有多个数据源时怎么办，不用担心，这些spring boot都是支持的，只要稍微改动下
application.properties即可。
<h3>日志</h3>
使用logback，配置在src/main/resources目录下的logback-spring.xml 为啥叫logback-spring.xml,为啥不直接叫logback.xml，官方推荐是要携带spring的，直接叫
logback.xml也可以，但是spring boot可能无法将一些自有特性加上去。
此外，日志的级别，日志文件路径在application-{profiles.active}.properties中配置
## log configuration
logging.path=/Users/zhangxiaolong/Documents/logs<br>
loggingLevel=DEBUG<br>
<h3>与dubbo的融合</h3>
以前项目中会有一个dubbo.properties文件，里面内容如下
dubbo.spring.config=classpath*:/spring/spring-*.xml,classpath*:/spring_service/spring-*.xml
这样在触发dubbo的Main方法启动的时候，就能读到项目中的dubbo的consumer和provider的配置。
在spring boot项目中，延续使用了要通过xml来reference提供者，可怎么初始化这些配置文件呢？
配置文件放在了vms-rpc模块的src/main/resources目录下的dubbo文件夹中。
映射关系通过wolf-common模块下的DubboConfig类来映射。
<h3>与jsp的结合</h3>
在application.properties文件中定义了，如下一些配置<br>
spring.mvc.view.prefix=/WEB-INF/jsp/<br>
spring.mvc.view.suffix=.jsp<br>
<h3>service层单元测试</h3>
可参考wolf-service模块下的src/java/test目录下的WolfIndexServiceTest类
注意：这种方式仍会启动spring容器，并不是纯粹的单元测试，如何mock，有待补充
为什么没有web层单元测试？
感觉没有必要，web层启动都这么简单了!
<h2>过滤器，拦截器，AOP</h2>
<h3>过滤器</h3>
待补充
<h3>拦截器</h3>
通过WolfWebMvcConfigurer类来扩展
<h3>AOP</h3>
在application.properties文件中增加如下配置<br>
spring.aop.auto=true # 开启AOP 相当于注释 @EnableAspectJAutoProxy<br>
spring.aop.proxy-target-class=true # 如果设置为true，意思是使用cglib的字节码方式生成代理类，为false则是使用java的动态代理来
如果使用cglib的方式，pom要增加依赖
<dependency> 
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-aop</artifactId> 
</dependency>
<h2>事务控制</h2>
<h3>单库事务</h3>
VMS项目中，service模块单库事务控制已经开启，如需对某个方法加控制，方法上增加@Transactional 注解即可。
<h3>分布式事务</h3>
VMS项目使用默认的事务管理器DataSourceTransactionManager，所以暂不支持分布式事务。
但是我们可以通过手动配置，显示指定事务管理器为JtaTransactionManager来支持分布式事务。
<h2>Spring Security实现登录</h2>
cn.htd.vms.web.config.VmsWebSecurityConfig类实现了所有的登录相关的配置，资源访问限制，登录策略，登录成功后处理逻辑，登录失败后的逻辑，以及
登出策略等。
<h2>CSRF防范</h2>
CSRF攻击，如果你不了解，请自行百度。
spring security自带了对CSRF攻击的防范，使用_csrf的token机制。
 
当我们向后端发起POST请求，spring security会有拦截器识别本次请求中的token是否与后台session中的token一致，如果token不合法或者没有传递，则会响应403异常。
一般向后端提交POST请求有两种方式：<br>
1 表单形式提交<br>
需要在form表单元素中增加隐藏表单域<input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>，这两个值直接引用即可<br>
2 ajax提交<br>
vms项目中，已经在公共js中增加了在ajax请求头中传递csrf需要的token，在使用ajax方式请求时，按照ajax常规方式使用就可以了。<br>
<h2>swagger</h2>
vms项目中集成了接口文档工具swagger，启动后，直接访问http://localhost:8080/swagger-ui.html#/就可以看到
<h2>将vms项目部署到外部tomcat</h2>
排除对内置tomcat依赖，修改wolf-web/pom.xml,排除spring-boot-starter-tomcat<br>
为了保证，使用内置tomcat还是可以启动，手动依赖tomcat，并且周期范围是provider<br>
 <dependency><br>
            <groupId>org.springframework.boot</groupId><br>
            <artifactId>spring-boot-starter-tomcat</artifactId><br>
            <scope>provided</scope><br>
      </dependency><br>
 
修改启动main方法，继承SpringBootServletInitializer类，并覆写方法configure<br>

<h2>技术支持</h2>
qq:597810804
