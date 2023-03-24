## 资源服务器对接文档



#### 1> Dependencies

见pom.xml	

#### 2> 新增类

配置文件：conf包中的两个类`BeanConf` `ResourceServerConf`

令牌解析器 `TokenParse`

#### 3> 在主启动类上加注解

```java
@EnableResourceServer // 开启资源服务器
@SpringBootApplication
```



#### 4> 启动

先在认证服务器获取令牌

然后在请求头中携带令牌访问资源服务器



> 为了避免依赖冲突，特定选择了与认证服务器不同的spring-boot版本