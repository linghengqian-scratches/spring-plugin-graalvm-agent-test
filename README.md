# Fixes in https://github.com/baomidou/dynamic-datasource/pull/562.
# For https://github.com/oracle/graalvm-reachability-metadata/pull/154 and https://github.com/spring-projects/spring-boot/issues/34841

- Steps to reproduce on Ubuntu 22.04.3.
```shell
sudo apt install unzip zip curl sed -y
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 17.0.8-graalce
sdk use java 17.0.8-graalce
sudo apt-get install build-essential libz-dev zlib1g-dev -y

git clone git@github.com:linghengqian/spring-plugin-graalvm-agent-test.git
cd ./spring-plugin-graalvm-agent-test/
./gradlew -Pagent clean test
./gradlew metadataCopy --task test
./gradlew clean nativeTest
```

- Error Log at `./gradlew clean test` step.
```shell
$ ./gradlew clean nativeTest

> Task :processTestAot
22:56:35.861 [main] INFO org.springframework.test.context.aot.TestClassScanner -- Scanning for Spring test classes in all packages in classpath roots [/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test, /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/resources/test]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.2)

2023-08-14T22:56:36.606+08:00  INFO 7442 --- [           main] com.lingh.AddRemoveDatasourceTest        : Starting AddRemoveDatasourceTest using Java 17.0.8 with PID 7442 (/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test started by linghengqian in /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test)
2023-08-14T22:56:36.608+08:00  INFO 7442 --- [           main] com.lingh.AddRemoveDatasourceTest        : No active profile set, falling back to 1 default profile: "default"

> Task :compileAotTestJava
注: /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/generated/aotTestSources/org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration__TestContext001_BeanDefinitions.java使用盖了已过时的 API。
注: 有关详细信息, 请使用 -Xlint:deprecation 重新编译。

> Task :test
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
2023-08-14T22:56:42.224+08:00  INFO 7480 --- [ionShutdownHook] c.b.d.d.DynamicRoutingDataSource         : dynamic-datasource start closing ....
2023-08-14T22:56:42.224+08:00  INFO 7480 --- [ionShutdownHook] c.b.d.d.DynamicRoutingDataSource         : dynamic-datasource all closed success,bye

> Task :generateTestResourcesConfigFile
[native-image-plugin] Resources configuration written into /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/native/generated/generateTestResourcesConfigFile/resource-config.json

> Task :nativeTestCompile
[native-image-plugin] GraalVM Toolchain detection is disabled
[native-image-plugin] GraalVM location read from environment variable: JAVA_HOME
[native-image-plugin] Native Image executable path: /home/linghengqian/.sdkman/candidates/java/17.0.8-graalce/lib/svm/bin/native-image
========================================================================================================================
GraalVM Native Image: Generating 'spring-plugin-graalvm-agent-test-tests' (executable)...
========================================================================================================================
For detailed information and explanations on the build output, visit:
https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md
------------------------------------------------------------------------------------------------------------------------
Warning: Could not resolve class kotlin.jvm.JvmInline for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlin.jvm.JvmInline.
Warning: Could not resolve class org.mockito.configuration.MockitoConfiguration for reflection configuration. Reason: java.lang.ClassNotFoundException: org.mockito.configuration.MockitoConfiguration.
Warning: Method com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAssistConfiguration.setBeanFactory(BeanFactory) not found.
Warning: Method com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration.setBeanFactory(BeanFactory) not found.
Warning: Method com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceCreatorAutoConfiguration.setBeanFactory(BeanFactory) not found.
Warning: Could not resolve class com.baomidou.mybatisplus.core.override.MybatisMapperProxy for reflection configuration. Reason: java.lang.ClassNotFoundException: com.baomidou.mybatisplus.core.override.MybatisMapperProxy.
Warning: Could not resolve class com.baomidou.mybatisplus.core.override.PageMapperProxy for reflection configuration. Reason: java.lang.ClassNotFoundException: com.baomidou.mybatisplus.core.override.PageMapperProxy.
Warning: Method com.lingh.AddRemoveDatasourceApplication.setBeanFactory(BeanFactory) not found.
Warning: Could not resolve class com.mchange.v2.c3p0$ComboPooledDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: com.mchange.v2.c3p0$ComboPooledDataSource.
Warning: Could not resolve class com.mchange.v2.c3p0.ComboPooledDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: com.mchange.v2.c3p0.ComboPooledDataSource.
Warning: Could not resolve class com.zaxxer.hikari$HikariDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: com.zaxxer.hikari$HikariDataSource.
Warning: Could not resolve class com.zaxxer.hikari.HikariDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: com.zaxxer.hikari.HikariDataSource.
Warning: Could not resolve class oracle.ucp.jdbc$PoolDataSourceImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: oracle.ucp.jdbc$PoolDataSourceImpl.
Warning: Could not resolve class oracle.ucp.jdbc.PoolDataSourceImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: oracle.ucp.jdbc.PoolDataSourceImpl.
Warning: Could not resolve class org.apache.commons.dbcp2$BasicDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.commons.dbcp2$BasicDataSource.
Warning: Could not resolve class org.apache.commons.dbcp2.BasicDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.commons.dbcp2.BasicDataSource.
Warning: Could not resolve class org.apache.ibatis.binding.MapperProxy for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.ibatis.binding.MapperProxy.
Warning: Could not resolve class org.apache.tomcat.jdbc.pool$DataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.tomcat.jdbc.pool$DataSource.
Warning: Could not resolve class org.apache.tomcat.jdbc.pool.DataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.tomcat.jdbc.pool.DataSource.
Warning: Method org.springframework.boot.availability.ApplicationAvailabilityBean.forPayload(Consumer) not found.
Warning: Method org.springframework.transaction.support.TransactionTemplate.withDefaults() not found.
Warning: Method org.springframework.transaction.support.TransactionTemplate.withoutTransaction() not found.
Warning: Method org.springframework.web.servlet.resource.ResourceUrlProvider.forPayload(Consumer) not found.
[1/8] Initializing...                                                                                    (8.3s @ 0.27GB)
 Java version: 17.0.8+7, vendor version: GraalVM CE 17.0.8+7.1
 Graal compiler: optimization level: 2, target machine: x86-64-v3
 C compiler: gcc (linux, x86_64, 11.4.0)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
 2 user-specific feature(s)
 - org.graalvm.junit.platform.JUnitPlatformFeature
 - org.springframework.aot.nativex.feature.PreComputeFieldFeature
[junit-platform-native] Running in 'test listener' mode using files matching pattern [junit-platform-unique-ids*] found in folder [/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/test-results/test/testlist] and its subfolders.
Field org.apache.commons.logging.LogAdapter#log4jSpiPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#log4jSlf4jProviderPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#slf4jSpiPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#slf4jApiPresent set to true at build time
Field org.springframework.boot.logging.log4j2.Log4J2LoggingSystem$Factory#PRESENT set to false at build time
Field org.springframework.http.converter.json.Jackson2ObjectMapperBuilder#jackson2XmlPresent set to false at build time
Field org.springframework.cglib.core.AbstractClassGenerator#inNativeImage set to true at build time
Field org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener#REST_DOCS_PRESENT set to false at build time
Field org.springframework.transaction.interceptor.TransactionAspectSupport#vavrPresent set to false at build time
Field org.springframework.transaction.interceptor.TransactionAspectSupport#reactiveStreamsPresent set to false at build time
Field org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener#MOCKITO_IS_PRESENT set to true at build time
Field org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener#MOCK_SERVER_PRESENT set to false at build time
Field org.springframework.web.servlet.view.InternalResourceViewResolver#jstlPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#romePresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jaxb2Present set to true at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2Present set to true at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2XmlPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2SmilePresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2CborPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#gsonPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jsonbPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#kotlinSerializationCborPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#kotlinSerializationProtobufPresent set to false at build time
Field org.springframework.aot.AotDetector#inNativeImage set to true at build time
Field org.springframework.test.context.web.socket.MockServerContainerContextCustomizerFactory#webSocketPresent set to true at build time
Field org.springframework.web.client.RestTemplate#romePresent set to false at build time
Field org.springframework.web.client.RestTemplate#jaxb2Present set to true at build time
Field org.springframework.web.client.RestTemplate#jackson2Present set to true at build time
Field org.springframework.web.client.RestTemplate#jackson2XmlPresent set to false at build time
Field org.springframework.web.client.RestTemplate#jackson2SmilePresent set to false at build time
Field org.springframework.web.client.RestTemplate#jackson2CborPresent set to false at build time
Field org.springframework.web.client.RestTemplate#gsonPresent set to false at build time
Field org.springframework.web.client.RestTemplate#jsonbPresent set to false at build time
Field org.springframework.web.client.RestTemplate#kotlinSerializationCborPresent set to false at build time
Field org.springframework.web.client.RestTemplate#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.web.client.RestTemplate#kotlinSerializationProtobufPresent set to false at build time
Field org.springframework.boot.test.web.reactive.server.WebTestClientContextCustomizerFactory#webClientPresent set to false at build time
Field org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory#PRESENT set to true at build time
Field org.springframework.core.KotlinDetector#kotlinPresent set to false at build time
Field org.springframework.core.KotlinDetector#kotlinReflectPresent set to false at build time
Field org.springframework.format.support.DefaultFormattingConversionService#jsr354Present set to false at build time
Field org.springframework.core.NativeDetector#inNativeImage set to true at build time
Field org.springframework.boot.autoconfigure.web.format.WebConversionService#JSR_354_PRESENT set to false at build time
Field org.springframework.web.context.request.RequestContextHolder#jsfPresent set to false at build time
Field org.springframework.transaction.annotation.AnnotationTransactionAttributeSource#jta12Present set to false at build time
Field org.springframework.transaction.annotation.AnnotationTransactionAttributeSource#ejb3Present set to false at build time
Field org.springframework.boot.logging.java.JavaLoggingSystem$Factory#PRESENT set to true at build time
Field org.springframework.web.context.support.StandardServletEnvironment#jndiPresent set to true at build time
Field org.springframework.web.context.support.WebApplicationContextUtils#jsfPresent set to false at build time
Field org.springframework.boot.logging.logback.LogbackLoggingSystemProperties#JBOSS_LOGGING_PRESENT set to false at build time
Field org.springframework.boot.web.client.ClientHttpRequestFactories#APACHE_HTTP_CLIENT_PRESENT set to false at build time
Field org.springframework.boot.web.client.ClientHttpRequestFactories#OKHTTP_CLIENT_PRESENT set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jaxb2Present set to true at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2Present set to true at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2XmlPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2SmilePresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#gsonPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jsonbPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationCborPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationProtobufPresent set to false at build time
Field org.springframework.context.event.ApplicationListenerMethodAdapter#reactiveStreamsPresent set to false at build time
Field org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator#USER_PROVIDED_ERROR_CODES_FILE_PRESENT set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#reactorPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#rxjava3Present set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#kotlinCoroutinesPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#mutinyPresent set to false at build time
SLF4J: No SLF4J providers were found.
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See https://www.slf4j.org/codes.html#noProviders for further details.
Field org.springframework.web.servlet.mvc.method.annotation.ReactiveTypeHandler#isContextPropagationPresent set to false at build time
Field org.springframework.web.servlet.support.RequestContext#jstlPresent set to false at build time
[2/8] Performing analysis...  [******]                                                                  (66.0s @ 2.11GB)
  20,919 (90.65%) of 23,077 types reachable
  35,299 (68.47%) of 51,556 fields reachable
 103,082 (63.56%) of 162,170 methods reachable
   6,394 types,   370 fields, and 6,982 methods registered for reflection
      65 types,    74 fields, and    55 methods registered for JNI access
       4 native libraries: dl, pthread, rt, z
[3/8] Building universe...                                                                               (7.7s @ 2.29GB)
[4/8] Parsing methods...      [***]                                                                      (5.2s @ 2.15GB)
[5/8] Inlining methods...     [***]                                                                      (3.9s @ 1.09GB)
[6/8] Compiling methods...    [*******]                                                                 (51.2s @ 2.11GB)
[7/8] Layouting methods...    [***]                                                                      (9.1s @ 1.82GB)
[8/8] Creating image...       [***]                                                                      (7.8s @ 2.79GB)
  51.28MB (52.49%) for code area:    68,672 compilation units
  42.56MB (43.57%) for image heap:  454,643 objects and 188 resources
   3.85MB ( 3.94%) for other data
  97.69MB in total
------------------------------------------------------------------------------------------------------------------------
Top 10 origins of code area:                                Top 10 object types in image heap:
  13.80MB java.base                                           11.37MB byte[] for code metadata
   5.62MB h2-2.1.214.jar                                       5.07MB java.lang.Class
   4.51MB tomcat-embed-core-10.1.11.jar                        4.51MB java.lang.String
   3.81MB java.xml                                             4.04MB byte[] for java.lang.String
   2.08MB jackson-databind-2.15.2.jar                          3.57MB byte[] for general heap data
   1.74MB spring-core-6.0.11.jar                               1.99MB byte[] for embedded resources
   1.43MB aspectjweaver-1.9.19.jar                             1.76MB com.oracle.svm.core.hub.DynamicHubCompanion
   1.43MB svm.jar (Native Image)                               1.27MB byte[] for reflection metadata
   1.41MB spring-boot-3.1.2.jar                              933.91kB java.lang.String[]
1022.95kB spring-beans-6.0.11.jar                            752.77kB c.o.svm.core.hub.DynamicHub$ReflectionMetadata
  13.96MB for 105 more packages                                6.47MB for 4163 more object types
------------------------------------------------------------------------------------------------------------------------
Recommendations:
 HEAP: Set max heap for improved and more predictable memory usage.
 CPU:  Enable more CPU features with '-march=native' for improved performance.
------------------------------------------------------------------------------------------------------------------------
                        14.1s (8.7% of total time) in 91 GCs | Peak RSS: 4.71GB | CPU load: 4.84
------------------------------------------------------------------------------------------------------------------------
Produced artifacts:
 /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/native/nativeTestCompile/spring-plugin-graalvm-agent-test-tests (executable)
========================================================================================================================
Finished generating 'spring-plugin-graalvm-agent-test-tests' in 2m 40s.
[native-image-plugin] Native Image written to: /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/native/nativeTestCompile

> Task :nativeTest FAILED
JUnit Platform on Native Image - report
----------------------------------------


  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.2)

2023-08-14T22:59:24.236+08:00  INFO 7707 --- [           main] com.lingh.AddRemoveDatasourceTest        : Starting AOT-processed AddRemoveDatasourceTest using Java 17.0.8 with PID 7707 (started by linghengqian in /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test)
2023-08-14T22:59:24.236+08:00  INFO 7707 --- [           main] com.lingh.AddRemoveDatasourceTest        : No active profile set, falling back to 1 default profile: "default"
2023-08-14T22:59:24.276+08:00  INFO 7707 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 0 (http)
2023-08-14T22:59:24.278+08:00  INFO 7707 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-08-14T22:59:24.278+08:00  INFO 7707 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.11]
2023-08-14T22:59:24.292+08:00  INFO 7707 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-08-14T22:59:24.292+08:00  INFO 7707 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 56 ms
2023-08-14T22:59:24.372+08:00  WARN 7707 --- [           main] w.s.c.ServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource': null
2023-08-14T22:59:24.372+08:00  INFO 7707 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2023-08-14T22:59:24.373+08:00 ERROR 7707 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource': null
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1770) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:598) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:734) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:436) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:137) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1406) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:545) ~[na:na]
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:137) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContextForAotRuntime(SpringBootContextLoader.java:119) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInAotMode(DefaultCacheAwareContextLoaderDelegate.java:217) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:116) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127) ~[na:na]
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependenciesInAotMode(DependencyInjectionTestExecutionListener.java:148) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:94) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:241) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:138) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$10(ClassBasedTestDescriptor.java:377) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:382) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$11(ClassBasedTestDescriptor.java:377) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at java.base@17.0.8/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197) ~[na:na]
        at java.base@17.0.8/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179) ~[na:na]
        at java.base@17.0.8/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625) ~[na:na]
        at java.base@17.0.8/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509) ~[spring-plugin-graalvm-agent-test-tests:na]
        at java.base@17.0.8/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499) ~[spring-plugin-graalvm-agent-test-tests:na]
        at java.base@17.0.8/java.util.stream.StreamSpliterators$WrappingSpliterator.forEachRemaining(StreamSpliterators.java:310) ~[na:na]
        at java.base@17.0.8/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:735) ~[spring-plugin-graalvm-agent-test-tests:na]
        at java.base@17.0.8/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734) ~[spring-plugin-graalvm-agent-test-tests:na]
        at java.base@17.0.8/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:762) ~[na:na]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:376) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]


        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$6(ClassBasedTestDescriptor.java:289) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:288) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]

============================
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$4(ClassBasedTestDescriptor.java:278) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at java.base@17.0.8/java.util.Optional.orElseGet(Optional.java:364) ~[spring-plugin-graalvm-agent-test-tests:na]
CONDITIONS EVALUATION REPORT
============================


Positive matches:
-----------------

    None


Negative matches:
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$5(ClassBasedTestDescriptor.java:277) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
-----------------

    None


Exclusions:
-----------

    None


Unconditional classes:
----------------------

    None

        at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:31) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:105) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]


        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:104) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:68) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:123) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:123) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:90) ~[na:na]
        at java.base@17.0.8/java.util.ArrayList.forEach(ArrayList.java:1511) ~[spring-plugin-graalvm-agent-test-tests:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95) ~[na:na]
        at java.base@17.0.8/java.util.ArrayList.forEach(ArrayList.java:1511) ~[spring-plugin-graalvm-agent-test-tests:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:147) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:127) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:90) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:55) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:102) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:54) ~[na:na]
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:114) ~[na:na]
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:95) ~[na:na]
        at org.junit.platform.launcher.core.DefaultLauncherSession$DelegatingLauncher.execute(DefaultLauncherSession.java:91) ~[na:na]
        at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:60) ~[na:na]
        at org.graalvm.junit.platform.NativeImageJUnitLauncher.execute(NativeImageJUnitLauncher.java:74) ~[na:na]
        at org.graalvm.junit.platform.NativeImageJUnitLauncher.main(NativeImageJUnitLauncher.java:129) ~[na:na]
Caused by: java.lang.NullPointerException: null
        at com.baomidou.dynamic.datasource.DynamicRoutingDataSource.afterPropertiesSet(DynamicRoutingDataSource.java:224) ~[spring-plugin-graalvm-agent-test-tests:na]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1817) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1766) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        ... 90 common frames omitted

2023-08-14T22:59:24.374+08:00 ERROR 7707 --- [           main] o.s.test.context.TestContextManager      : Caught exception while allowing TestExecutionListener [org.springframework.test.context.support.DependencyInjectionTestExecutionListener] to prepare test instance [com.lingh.AddRemoveDatasourceTest@2a42d359]

java.lang.IllegalStateException: Failed to load ApplicationContext for [AotMergedContextConfiguration@41dad2bb testClass = com.lingh.AddRemoveDatasourceTest, contextInitializerClass = com.lingh.AddRemoveDatasourceTest__TestContext001_ApplicationContextInitializer, original = [WebMergedContextConfiguration@3d56551 testClass = com.lingh.AddRemoveDatasourceTest, locations = [], classes = [com.lingh.AddRemoveDatasourceApplication], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = ["org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true", "server.port=0"], contextCustomizers = [org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@192374, org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@7cf97288, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@5e6ffcff, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@5177b62b, org.springframework.boot.test.context.SpringBootTestAnnotation@256a8d6f], resourceBasePath = "src/main/webapp", contextLoader = org.springframework.boot.test.context.SpringBootContextLoader, parent = null]]
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127) ~[na:na]
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependenciesInAotMode(DependencyInjectionTestExecutionListener.java:148) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:94) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:241) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:138) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$10(ClassBasedTestDescriptor.java:377) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:382) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$11(ClassBasedTestDescriptor.java:377) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at java.base@17.0.8/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197) ~[na:na]
        at java.base@17.0.8/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179) ~[na:na]
        at java.base@17.0.8/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625) ~[na:na]
        at java.base@17.0.8/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509) ~[spring-plugin-graalvm-agent-test-tests:na]
        at java.base@17.0.8/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499) ~[spring-plugin-graalvm-agent-test-tests:na]
        at java.base@17.0.8/java.util.stream.StreamSpliterators$WrappingSpliterator.forEachRemaining(StreamSpliterators.java:310) ~[na:na]
        at java.base@17.0.8/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:735) ~[spring-plugin-graalvm-agent-test-tests:na]
        at java.base@17.0.8/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734) ~[spring-plugin-graalvm-agent-test-tests:na]
        at java.base@17.0.8/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:762) ~[na:na]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:376) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$6(ClassBasedTestDescriptor.java:289) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:288) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$4(ClassBasedTestDescriptor.java:278) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at java.base@17.0.8/java.util.Optional.orElseGet(Optional.java:364) ~[spring-plugin-graalvm-agent-test-tests:na]
        at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$5(ClassBasedTestDescriptor.java:277) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:31) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:105) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:104) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:68) ~[spring-plugin-graalvm-agent-test-tests:5.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:123) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:123) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:90) ~[na:na]
        at java.base@17.0.8/java.util.ArrayList.forEach(ArrayList.java:1511) ~[spring-plugin-graalvm-agent-test-tests:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95) ~[na:na]
        at java.base@17.0.8/java.util.ArrayList.forEach(ArrayList.java:1511) ~[spring-plugin-graalvm-agent-test-tests:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57) ~[na:na]
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54) ~[spring-plugin-graalvm-agent-test-tests:1.9.3]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:147) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:127) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:90) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:55) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:102) ~[na:na]
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:54) ~[na:na]
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:114) ~[na:na]
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:95) ~[na:na]
        at org.junit.platform.launcher.core.DefaultLauncherSession$DelegatingLauncher.execute(DefaultLauncherSession.java:91) ~[na:na]
        at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:60) ~[na:na]
        at org.graalvm.junit.platform.NativeImageJUnitLauncher.execute(NativeImageJUnitLauncher.java:74) ~[na:na]
        at org.graalvm.junit.platform.NativeImageJUnitLauncher.main(NativeImageJUnitLauncher.java:129) ~[na:na]
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource': null
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1770) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:598) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:734) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:436) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:137) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1406) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:545) ~[na:na]
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:137) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContextForAotRuntime(SpringBootContextLoader.java:119) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInAotMode(DefaultCacheAwareContextLoaderDelegate.java:217) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:116) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        ... 68 common frames omitted
Caused by: java.lang.NullPointerException: null
        at com.baomidou.dynamic.datasource.DynamicRoutingDataSource.afterPropertiesSet(DynamicRoutingDataSource.java:224) ~[spring-plugin-graalvm-agent-test-tests:na]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1817) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1766) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        ... 90 common frames omitted

com.lingh.AddRemoveDatasourceTest > testAddAndRemoveDataSource() FAILED


Failures (1):
  JUnit Jupiter:AddRemoveDatasourceTest:testAddAndRemoveDataSource()
    MethodSource [className = 'com.lingh.AddRemoveDatasourceTest', methodName = 'testAddAndRemoveDataSource', methodParameterTypes = '']
    => java.lang.IllegalStateException: Failed to load ApplicationContext for [AotMergedContextConfiguration@41dad2bb testClass = com.lingh.AddRemoveDatasourceTest, contextInitializerClass = com.lingh.AddRemoveDatasourceTest__TestContext001_ApplicationContextInitializer, original = [WebMergedContextConfiguration@3d56551 testClass = com.lingh.AddRemoveDatasourceTest, locations = [], classes = [com.lingh.AddRemoveDatasourceApplication], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = ["org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true", "server.port=0"], contextCustomizers = [org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@192374, org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@7cf97288, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@5e6ffcff, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@5177b62b, org.springframework.boot.test.context.SpringBootTestAnnotation@256a8d6f], resourceBasePath = "src/main/webapp", contextLoader = org.springframework.boot.test.context.SpringBootContextLoader, parent = null]]
       org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143)
       org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
       org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependenciesInAotMode(DependencyInjectionTestExecutionListener.java:148)
       org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:94)
       org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:241)
       [...]
     Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource': null
       org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1770)
       org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:598)
       org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520)
       org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326)
       org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
       [...]
     Caused by: java.lang.NullPointerException
       com.baomidou.dynamic.datasource.DynamicRoutingDataSource.afterPropertiesSet(DynamicRoutingDataSource.java:224)
       org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1817)
       org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1766)
       [...]

Test run finished after 156 ms
[         2 containers found      ]
[         0 containers skipped    ]
[         2 containers started    ]
[         0 containers aborted    ]
[         2 containers successful ]
[         0 containers failed     ]
[         1 tests found           ]
[         0 tests skipped         ]
[         1 tests started         ]
[         0 tests aborted         ]
[         0 tests successful      ]
[         1 tests failed          ]


FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':nativeTest'.
> Process 'command '/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/native/nativeTestCompile/spring-plugin-graalvm-agent-test-tests'' finished with non-zero exit value 1

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.2.1/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD FAILED in 2m 49s
11 actionable tasks: 11 executed

```
