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
./gradlew clean test
./gradlew -Pagent clean test
./gradlew metadataCopy --task test
./gradlew clean nativeTest
```

- Error Log at `./gradlew clean test` step.
```shell
$ ./gradlew clean nativeTest

> Task :processTestAot
22:38:52.362 [main] INFO org.springframework.test.context.aot.TestClassScanner -- Scanning for Spring test classes in all packages in classpath roots [/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test, /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/resources/test]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.2)

2023-08-14T22:38:53.105+08:00  INFO 6129 --- [           main] com.lingh.AddRemoveDatasourceTest        : Starting AddRemoveDatasourceTest using Java 17.0.8 with PID 6129 (/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test started by linghengqian in /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test)
2023-08-14T22:38:53.107+08:00  INFO 6129 --- [           main] com.lingh.AddRemoveDatasourceTest        : No active profile set, falling back to 1 default profile: "default"

> Task :compileAotTestJava
注: /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/generated/aotTestSources/org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration__TestContext001_BeanDefinitions.java使用盖了已过时的 API。
注: 有关详细信息, 请使用 -Xlint:deprecation 重新编译。

> Task :test
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
2023-08-14T22:38:58.775+08:00  INFO 6165 --- [ionShutdownHook] c.b.d.d.DynamicRoutingDataSource         : dynamic-datasource start closing ....
2023-08-14T22:38:58.776+08:00  INFO 6165 --- [ionShutdownHook] c.b.d.d.DynamicRoutingDataSource         : dynamic-datasource all closed success,bye

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
Warning: Method boolean.<init>(Boolean) not found.
Warning: Method boolean.from(Boolean) not found.
Warning: Method boolean.of(Boolean) not found.
Warning: Method boolean.valueOf(Boolean) not found.
Warning: Could not resolve class cn.beecp.BeeDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: cn.beecp.BeeDataSource.
Warning: Could not resolve class co.elastic.clients.elasticsearch.ElasticsearchClient for reflection configuration. Reason: java.lang.ClassNotFoundException: co.elastic.clients.elasticsearch.ElasticsearchClient.
Warning: Could not resolve class co.elastic.clients.transport.ElasticsearchTransport for reflection configuration. Reason: java.lang.ClassNotFoundException: co.elastic.clients.transport.ElasticsearchTransport.
Warning: Could not resolve class com.alibaba.druid.pool.DruidDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: com.alibaba.druid.pool.DruidDataSource.
Warning: Could not resolve class com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure for reflection configuration. Reason: java.lang.ClassNotFoundException: com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure.
Warning: Could not resolve class com.atomikos.jdbc.AtomikosDataSourceBean for reflection configuration. Reason: java.lang.ClassNotFoundException: com.atomikos.jdbc.AtomikosDataSourceBean.
Warning: Method com.baomidou.dynamic.datasource.aop.DynamicDataSourceAnnotationAdvisor.close() not found.
Warning: Method com.baomidou.dynamic.datasource.aop.DynamicDataSourceAnnotationAdvisor.shutdown() not found.
Warning: Method com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator.close() not found.
Warning: Method com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator.shutdown() not found.
Warning: Method com.baomidou.dynamic.datasource.creator.basic.BasicDataSourceCreator.close() not found.
Warning: Method com.baomidou.dynamic.datasource.creator.basic.BasicDataSourceCreator.shutdown() not found.
Warning: Method com.baomidou.dynamic.datasource.creator.jndi.JndiDataSourceCreator.close() not found.
Warning: Method com.baomidou.dynamic.datasource.creator.jndi.JndiDataSourceCreator.shutdown() not found.
Warning: Method com.baomidou.dynamic.datasource.event.EncDataSourceInitEvent.close() not found.
Warning: Method com.baomidou.dynamic.datasource.event.EncDataSourceInitEvent.shutdown() not found.
Warning: Method com.baomidou.dynamic.datasource.processor.DsJakartaHeaderProcessor.close() not found.
Warning: Method com.baomidou.dynamic.datasource.processor.DsJakartaHeaderProcessor.shutdown() not found.
Warning: Method com.baomidou.dynamic.datasource.provider.YmlDynamicDataSourceProvider.close() not found.
Warning: Method com.baomidou.dynamic.datasource.provider.YmlDynamicDataSourceProvider.shutdown() not found.
Warning: Method com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAopConfiguration.setBeanFactory(BeanFactory) not found.
Warning: Method com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAssistConfiguration.setBeanFactory(BeanFactory) not found.
Warning: Method com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration.setBeanFactory(BeanFactory) not found.
Warning: Method com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceCreatorAutoConfiguration.setBeanFactory(BeanFactory) not found.
Warning: Could not resolve class com.baomidou.mybatisplus.core.override.MybatisMapperProxy for reflection configuration. Reason: java.lang.ClassNotFoundException: com.baomidou.mybatisplus.core.override.MybatisMapperProxy.
Warning: Could not resolve class com.baomidou.mybatisplus.core.override.PageMapperProxy for reflection configuration. Reason: java.lang.ClassNotFoundException: com.baomidou.mybatisplus.core.override.PageMapperProxy.
Warning: Could not resolve class com.couchbase.client.java.Bucket for reflection configuration. Reason: java.lang.ClassNotFoundException: com.couchbase.client.java.Bucket.
Warning: Could not resolve class com.couchbase.client.java.Cluster for reflection configuration. Reason: java.lang.ClassNotFoundException: com.couchbase.client.java.Cluster.
Warning: Could not resolve class com.datastax.oss.driver.api.core.CqlSession for reflection configuration. Reason: java.lang.ClassNotFoundException: com.datastax.oss.driver.api.core.CqlSession.
Warning: Method com.fasterxml.jackson.databind.ObjectMapper.close() not found.
Warning: Method com.fasterxml.jackson.databind.ObjectMapper.shutdown() not found.
Warning: Could not resolve class com.fasterxml.jackson.dataformat.cbor$CBORFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: com.fasterxml.jackson.dataformat.cbor$CBORFactory.
Warning: Could not resolve class com.fasterxml.jackson.dataformat.cbor.CBORFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: com.fasterxml.jackson.dataformat.cbor.CBORFactory.
Warning: Could not resolve class com.fasterxml.jackson.dataformat.smile$SmileFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: com.fasterxml.jackson.dataformat.smile$SmileFactory.
Warning: Could not resolve class com.fasterxml.jackson.dataformat.smile.SmileFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: com.fasterxml.jackson.dataformat.smile.SmileFactory.
Warning: Could not resolve class com.fasterxml.jackson.dataformat.xml$XmlMapper for reflection configuration. Reason: java.lang.ClassNotFoundException: com.fasterxml.jackson.dataformat.xml$XmlMapper.
Warning: Could not resolve class com.fasterxml.jackson.dataformat.xml.XmlMapper for reflection configuration. Reason: java.lang.ClassNotFoundException: com.fasterxml.jackson.dataformat.xml.XmlMapper.
Warning: Method com.fasterxml.jackson.module.paramnames.ParameterNamesModule.close() not found.
Warning: Method com.fasterxml.jackson.module.paramnames.ParameterNamesModule.shutdown() not found.
Warning: Could not resolve class com.github.benmanes.caffeine.cache.Caffeine for reflection configuration. Reason: java.lang.ClassNotFoundException: com.github.benmanes.caffeine.cache.Caffeine.
Warning: Could not resolve class com.google.gson$Gson for reflection configuration. Reason: java.lang.ClassNotFoundException: com.google.gson$Gson.
Warning: Could not resolve class com.google.gson.Gson for reflection configuration. Reason: java.lang.ClassNotFoundException: com.google.gson.Gson.
Warning: Could not resolve class com.hazelcast.core.HazelcastInstance for reflection configuration. Reason: java.lang.ClassNotFoundException: com.hazelcast.core.HazelcastInstance.
Warning: Method com.lingh.AddRemoveDatasourceApplication.setBeanFactory(BeanFactory) not found.
Warning: Could not resolve class com.mchange.v2.c3p0$ComboPooledDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: com.mchange.v2.c3p0$ComboPooledDataSource.
Warning: Could not resolve class com.mchange.v2.c3p0.ComboPooledDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: com.mchange.v2.c3p0.ComboPooledDataSource.
Warning: Could not resolve class com.mongodb.client.MongoClient for reflection configuration. Reason: java.lang.ClassNotFoundException: com.mongodb.client.MongoClient.
Warning: Could not resolve class com.mongodb.reactivestreams.client.MongoClient for reflection configuration. Reason: java.lang.ClassNotFoundException: com.mongodb.reactivestreams.client.MongoClient.
Warning: Could not resolve class com.rabbitmq.client.Channel for reflection configuration. Reason: java.lang.ClassNotFoundException: com.rabbitmq.client.Channel.
Warning: Could not resolve class com.rometools.rome.feed$WireFeed for reflection configuration. Reason: java.lang.ClassNotFoundException: com.rometools.rome.feed$WireFeed.
Warning: Could not resolve class com.rometools.rome.feed.WireFeed for reflection configuration. Reason: java.lang.ClassNotFoundException: com.rometools.rome.feed.WireFeed.
Warning: Could not resolve class com.samskivert.mustache$Template for reflection configuration. Reason: java.lang.ClassNotFoundException: com.samskivert.mustache$Template.
Warning: Could not resolve class com.samskivert.mustache.Mustache for reflection configuration. Reason: java.lang.ClassNotFoundException: com.samskivert.mustache.Mustache.
Warning: Could not resolve class com.samskivert.mustache.Template for reflection configuration. Reason: java.lang.ClassNotFoundException: com.samskivert.mustache.Template.
Warning: Could not resolve class com.sendgrid.SendGrid for reflection configuration. Reason: java.lang.ClassNotFoundException: com.sendgrid.SendGrid.
Warning: Could not resolve class com.unboundid.ldap.listener.InMemoryDirectoryServer for reflection configuration. Reason: java.lang.ClassNotFoundException: com.unboundid.ldap.listener.InMemoryDirectoryServer.
Warning: Could not resolve class com.zaxxer.hikari$HikariDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: com.zaxxer.hikari$HikariDataSource.
Warning: Could not resolve class com.zaxxer.hikari.HikariDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: com.zaxxer.hikari.HikariDataSource.
Warning: Could not resolve class freemarker.template$Configuration for reflection configuration. Reason: java.lang.ClassNotFoundException: freemarker.template$Configuration.
Warning: Could not resolve class freemarker.template.Configuration for reflection configuration. Reason: java.lang.ClassNotFoundException: freemarker.template.Configuration.
Warning: Could not resolve class graphql.GraphQL for reflection configuration. Reason: java.lang.ClassNotFoundException: graphql.GraphQL.
Warning: Could not resolve class groovy.lang$MetaClass for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.lang$MetaClass.
Warning: Could not resolve class groovy.lang.MetaClass for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.lang.MetaClass.
Warning: Could not resolve class groovy.text$TemplateEngine for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.text$TemplateEngine.
Warning: Could not resolve class groovy.text.TemplateEngine for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.text.TemplateEngine.
Warning: Could not resolve class groovy.text.markup.MarkupTemplateEngine for reflection configuration. Reason: java.lang.ClassNotFoundException: groovy.text.markup.MarkupTemplateEngine.
Warning: Could not resolve class io.micrometer.context$ContextSnapshot for reflection configuration. Reason: java.lang.ClassNotFoundException: io.micrometer.context$ContextSnapshot.
Warning: Could not resolve class io.micrometer.context.ContextSnapshot for reflection configuration. Reason: java.lang.ClassNotFoundException: io.micrometer.context.ContextSnapshot.
Warning: Could not resolve class io.micrometer.context.ThreadLocalAccessor for reflection configuration. Reason: java.lang.ClassNotFoundException: io.micrometer.context.ThreadLocalAccessor.
Warning: Could not resolve class io.micrometer.tracing$Tracer for reflection configuration. Reason: java.lang.ClassNotFoundException: io.micrometer.tracing$Tracer.
Warning: Could not resolve class io.micrometer.tracing.Tracer for reflection configuration. Reason: java.lang.ClassNotFoundException: io.micrometer.tracing.Tracer.
Warning: Could not resolve class io.netty.buffer.PooledByteBufAllocator for reflection configuration. Reason: java.lang.ClassNotFoundException: io.netty.buffer.PooledByteBufAllocator.
Warning: Could not resolve class io.netty.util.NettyRuntime for reflection configuration. Reason: java.lang.ClassNotFoundException: io.netty.util.NettyRuntime.
Warning: Could not resolve class io.r2dbc.spi.ConnectionFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: io.r2dbc.spi.ConnectionFactory.
Warning: Could not resolve class io.reactivex.rxjava3.core$Flowable for reflection configuration. Reason: java.lang.ClassNotFoundException: io.reactivex.rxjava3.core$Flowable.
Warning: Could not resolve class io.reactivex.rxjava3.core.Flowable for reflection configuration. Reason: java.lang.ClassNotFoundException: io.reactivex.rxjava3.core.Flowable.
Warning: Could not resolve class io.rsocket.RSocket for reflection configuration. Reason: java.lang.ClassNotFoundException: io.rsocket.RSocket.
Warning: Could not resolve class io.rsocket.core.RSocketServer for reflection configuration. Reason: java.lang.ClassNotFoundException: io.rsocket.core.RSocketServer.
Warning: Could not resolve class io.smallrye.mutiny$Multi for reflection configuration. Reason: java.lang.ClassNotFoundException: io.smallrye.mutiny$Multi.
Warning: Could not resolve class io.smallrye.mutiny.Multi for reflection configuration. Reason: java.lang.ClassNotFoundException: io.smallrye.mutiny.Multi.
Warning: Could not resolve class io.undertow.Undertow for reflection configuration. Reason: java.lang.ClassNotFoundException: io.undertow.Undertow.
Warning: Could not resolve class io.undertow.websockets.jsr.Bootstrap for reflection configuration. Reason: java.lang.ClassNotFoundException: io.undertow.websockets.jsr.Bootstrap.
Warning: Could not resolve class io.vavr.control$Try for reflection configuration. Reason: java.lang.ClassNotFoundException: io.vavr.control$Try.
Warning: Could not resolve class io.vavr.control.Try for reflection configuration. Reason: java.lang.ClassNotFoundException: io.vavr.control.Try.
Warning: Could not resolve class jakarta.ejb$EJB for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.ejb$EJB.
Warning: Could not resolve class jakarta.ejb$TransactionAttribute for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.ejb$TransactionAttribute.
Warning: Could not resolve class jakarta.ejb.EJB for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.ejb.EJB.
Warning: Could not resolve class jakarta.ejb.TransactionAttribute for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.ejb.TransactionAttribute.
Warning: Could not resolve class jakarta.faces.context$FacesContext for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.faces.context$FacesContext.
Warning: Could not resolve class jakarta.faces.context.FacesContext for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.faces.context.FacesContext.
Warning: Could not resolve class jakarta.inject$Inject for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.inject$Inject.
Warning: Could not resolve class jakarta.inject$Named for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.inject$Named.
Warning: Could not resolve class jakarta.inject$Provider for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.inject$Provider.
Warning: Could not resolve class jakarta.inject$Qualifier for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.inject$Qualifier.
Warning: Could not resolve class jakarta.inject.Inject for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.inject.Inject.
Warning: Could not resolve class jakarta.inject.Named for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.inject.Named.
Warning: Could not resolve class jakarta.inject.Provider for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.inject.Provider.
Warning: Could not resolve class jakarta.inject.Qualifier for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.inject.Qualifier.
Warning: Could not resolve class jakarta.jms.ConnectionFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.jms.ConnectionFactory.
Warning: Could not resolve class jakarta.jms.Message for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.jms.Message.
Warning: Could not resolve class jakarta.json.bind$Jsonb for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.json.bind$Jsonb.
Warning: Could not resolve class jakarta.json.bind.Jsonb for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.json.bind.Jsonb.
Warning: Could not resolve class jakarta.mail.internet.MimeMessage for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.mail.internet.MimeMessage.
Warning: Could not resolve class jakarta.persistence$EntityManagerFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.persistence$EntityManagerFactory.
Warning: Could not resolve class jakarta.persistence.EntityManager for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.persistence.EntityManager.
Warning: Could not resolve class jakarta.persistence.EntityManagerFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.persistence.EntityManagerFactory.
Warning: Could not resolve class jakarta.persistence.PersistenceContext for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.persistence.PersistenceContext.
Warning: Method jakarta.servlet.MultipartConfigElement.close() not found.
Warning: Method jakarta.servlet.MultipartConfigElement.shutdown() not found.
Warning: Could not resolve class jakarta.servlet.jsp.jstl.core$Config for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.servlet.jsp.jstl.core$Config.
Warning: Could not resolve class jakarta.servlet.jsp.jstl.core.Config for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.servlet.jsp.jstl.core.Config.
Warning: Could not resolve class jakarta.transaction$Transactional for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.transaction$Transactional.
Warning: Could not resolve class jakarta.transaction.Transaction for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.transaction.Transaction.
Warning: Could not resolve class jakarta.transaction.TransactionManager for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.transaction.TransactionManager.
Warning: Could not resolve class jakarta.transaction.Transactional for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.transaction.Transactional.
Warning: Could not resolve class jakarta.validation$Validator for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.validation$Validator.
Warning: Could not resolve class jakarta.validation.Validator for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.validation.Validator.
Warning: Could not resolve class jakarta.validation.executable.ExecutableValidator for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.validation.executable.ExecutableValidator.
Warning: Could not resolve class jakarta.xml.ws.WebServiceRef for reflection configuration. Reason: java.lang.ClassNotFoundException: jakarta.xml.ws.WebServiceRef.
Warning: Method java.lang.Boolean.toboolean() not found.
Warning: Could not resolve class java.lang.Thread$Builder for reflection configuration. Reason: java.lang.ClassNotFoundException: java.lang.Thread$Builder.
Warning: Could not resolve class java.lang.WrongThreadException for reflection configuration. Reason: java.lang.ClassNotFoundException: java.lang.WrongThreadException.
Warning: Could not resolve class javax.annotation$PostConstruct for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.annotation$PostConstruct.
Warning: Could not resolve class javax.annotation$PreDestroy for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.annotation$PreDestroy.
Warning: Could not resolve class javax.annotation$Resource for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.annotation$Resource.
Warning: Could not resolve class javax.annotation.PostConstruct for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.annotation.PostConstruct.
Warning: Could not resolve class javax.annotation.PreDestroy for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.annotation.PreDestroy.
Warning: Could not resolve class javax.annotation.Resource for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.annotation.Resource.
Warning: Could not resolve class javax.cache.Caching for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.cache.Caching.
Warning: Could not resolve class javax.inject$Inject for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.inject$Inject.
Warning: Could not resolve class javax.inject.Inject for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.inject.Inject.
Warning: Could not resolve class javax.money$MonetaryAmount for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.money$MonetaryAmount.
Warning: Could not resolve class javax.money.MonetaryAmount for reflection configuration. Reason: java.lang.ClassNotFoundException: javax.money.MonetaryAmount.
Warning: Method jdk.internal.loader.ClassLoaders$AppClassLoader.clearCache() not found.
Warning: Method jdk.internal.loader.ClassLoaders$PlatformClassLoader.clearCache() not found.
Warning: Could not resolve class kotlin$Metadata for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlin$Metadata.
Warning: Could not resolve class kotlin.Metadata for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlin.Metadata.
Warning: Could not resolve class kotlin.reflect.full$KClasses for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlin.reflect.full$KClasses.
Warning: Could not resolve class kotlin.reflect.full.KClasses for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlin.reflect.full.KClasses.
Warning: Could not resolve class kotlinx.coroutines.reactor$MonoKt for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlinx.coroutines.reactor$MonoKt.
Warning: Could not resolve class kotlinx.coroutines.reactor.MonoKt for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlinx.coroutines.reactor.MonoKt.
Warning: Could not resolve class kotlinx.serialization.cbor$Cbor for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlinx.serialization.cbor$Cbor.
Warning: Could not resolve class kotlinx.serialization.cbor.Cbor for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlinx.serialization.cbor.Cbor.
Warning: Could not resolve class kotlinx.serialization.json$Json for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlinx.serialization.json$Json.
Warning: Could not resolve class kotlinx.serialization.json.Json for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlinx.serialization.json.Json.
Warning: Could not resolve class kotlinx.serialization.protobuf$ProtoBuf for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlinx.serialization.protobuf$ProtoBuf.
Warning: Could not resolve class kotlinx.serialization.protobuf.ProtoBuf for reflection configuration. Reason: java.lang.ClassNotFoundException: kotlinx.serialization.protobuf.ProtoBuf.
Warning: Could not resolve class liquibase.change.DatabaseChange for reflection configuration. Reason: java.lang.ClassNotFoundException: liquibase.change.DatabaseChange.
Warning: Could not resolve class okhttp3$OkHttpClient for reflection configuration. Reason: java.lang.ClassNotFoundException: okhttp3$OkHttpClient.
Warning: Could not resolve class okhttp3.OkHttpClient for reflection configuration. Reason: java.lang.ClassNotFoundException: okhttp3.OkHttpClient.
Warning: Could not resolve class oracle.jdbc.OracleConnection for reflection configuration. Reason: java.lang.ClassNotFoundException: oracle.jdbc.OracleConnection.
Warning: Could not resolve class oracle.ucp.jdbc$PoolDataSourceImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: oracle.ucp.jdbc$PoolDataSourceImpl.
Warning: Could not resolve class oracle.ucp.jdbc.PoolDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: oracle.ucp.jdbc.PoolDataSource.
Warning: Could not resolve class oracle.ucp.jdbc.PoolDataSourceImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: oracle.ucp.jdbc.PoolDataSourceImpl.
Warning: Could not resolve class org.apache.commons.dbcp2$BasicDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.commons.dbcp2$BasicDataSource.
Warning: Could not resolve class org.apache.commons.dbcp2.BasicDataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.commons.dbcp2.BasicDataSource.
Warning: Could not resolve class org.apache.hc.client5.http.impl.classic$HttpClients for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.hc.client5.http.impl.classic$HttpClients.
Warning: Could not resolve class org.apache.hc.client5.http.impl.classic.HttpClients for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.hc.client5.http.impl.classic.HttpClients.
Warning: Could not resolve class org.apache.ibatis.binding.MapperProxy for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.ibatis.binding.MapperProxy.
Warning: Could not resolve class org.apache.ibatis.transaction.TransactionFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.ibatis.transaction.TransactionFactory.
Warning: Could not resolve class org.apache.jasper.compiler$JspConfig for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.jasper.compiler$JspConfig.
Warning: Could not resolve class org.apache.jasper.compiler.JspConfig for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.jasper.compiler.JspConfig.
Warning: Could not resolve class org.apache.jasper.servlet$JspServlet for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.jasper.servlet$JspServlet.
Warning: Could not resolve class org.apache.jasper.servlet.JspServlet for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.jasper.servlet.JspServlet.
Warning: Could not resolve class org.apache.logging.log4j.core.impl$Log4jContextFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.logging.log4j.core.impl$Log4jContextFactory.
Warning: Could not resolve class org.apache.logging.log4j.core.impl.Log4jContextFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.logging.log4j.core.impl.Log4jContextFactory.
Warning: Could not resolve class org.apache.tomcat.jdbc.pool$DataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.tomcat.jdbc.pool$DataSource.
Warning: Could not resolve class org.apache.tomcat.jdbc.pool.DataSource for reflection configuration. Reason: java.lang.ClassNotFoundException: org.apache.tomcat.jdbc.pool.DataSource.
Warning: Could not resolve class org.cache2k.Cache2kBuilder for reflection configuration. Reason: java.lang.ClassNotFoundException: org.cache2k.Cache2kBuilder.
Warning: Could not resolve class org.eclipse.core.runtime$FileLocator for reflection configuration. Reason: java.lang.ClassNotFoundException: org.eclipse.core.runtime$FileLocator.
Warning: Could not resolve class org.eclipse.core.runtime.FileLocator for reflection configuration. Reason: java.lang.ClassNotFoundException: org.eclipse.core.runtime.FileLocator.
Warning: Could not resolve class org.eclipse.jetty.server.Server for reflection configuration. Reason: java.lang.ClassNotFoundException: org.eclipse.jetty.server.Server.
Warning: Could not resolve class org.eclipse.jetty.util.Loader for reflection configuration. Reason: java.lang.ClassNotFoundException: org.eclipse.jetty.util.Loader.
Warning: Could not resolve class org.eclipse.jetty.webapp.WebAppContext for reflection configuration. Reason: java.lang.ClassNotFoundException: org.eclipse.jetty.webapp.WebAppContext.
Warning: Could not resolve class org.eclipse.jetty.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer for reflection configuration. Reason: java.lang.ClassNotFoundException: org.eclipse.jetty.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer.
Warning: Could not resolve class org.elasticsearch.client.RestClientBuilder for reflection configuration. Reason: java.lang.ClassNotFoundException: org.elasticsearch.client.RestClientBuilder.
Warning: Could not resolve class org.flywaydb.core.Flyway for reflection configuration. Reason: java.lang.ClassNotFoundException: org.flywaydb.core.Flyway.
Warning: Could not resolve class org.glassfish.jersey.server.spring.SpringComponentProvider for reflection configuration. Reason: java.lang.ClassNotFoundException: org.glassfish.jersey.server.spring.SpringComponentProvider.
Warning: Could not resolve class org.infinispan.spring.embedded.provider.SpringEmbeddedCacheManager for reflection configuration. Reason: java.lang.ClassNotFoundException: org.infinispan.spring.embedded.provider.SpringEmbeddedCacheManager.
Warning: Could not resolve class org.influxdb.InfluxDB for reflection configuration. Reason: java.lang.ClassNotFoundException: org.influxdb.InfluxDB.
Warning: Could not resolve class org.jboss.logging$Logger for reflection configuration. Reason: java.lang.ClassNotFoundException: org.jboss.logging$Logger.
Warning: Could not resolve class org.jboss.logging.Logger for reflection configuration. Reason: java.lang.ClassNotFoundException: org.jboss.logging.Logger.
Warning: Could not resolve class org.jooq.DSLContext for reflection configuration. Reason: java.lang.ClassNotFoundException: org.jooq.DSLContext.
Warning: Could not resolve class org.neo4j.driver.Driver for reflection configuration. Reason: java.lang.ClassNotFoundException: org.neo4j.driver.Driver.
Warning: Could not resolve class org.openqa.selenium$WebDriver for reflection configuration. Reason: java.lang.ClassNotFoundException: org.openqa.selenium$WebDriver.
Warning: Could not resolve class org.openqa.selenium.WebDriver for reflection configuration. Reason: java.lang.ClassNotFoundException: org.openqa.selenium.WebDriver.
Warning: Could not resolve class org.quartz.Scheduler for reflection configuration. Reason: java.lang.ClassNotFoundException: org.quartz.Scheduler.
Warning: Could not resolve class org.reactivestreams$Publisher for reflection configuration. Reason: java.lang.ClassNotFoundException: org.reactivestreams$Publisher.
Warning: Could not resolve class org.reactivestreams.Publisher for reflection configuration. Reason: java.lang.ClassNotFoundException: org.reactivestreams.Publisher.
Warning: Could not resolve class org.springframework.batch.core.launch.JobLauncher for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.batch.core.launch.JobLauncher.
Warning: Method org.springframework.boot.SpringApplication.<init>() not found.
Warning: Method org.springframework.boot.WebApplicationType.<init>() not found.
Warning: Method org.springframework.boot.ansi.AnsiOutput$Enabled.<init>() not found.
Warning: Method org.springframework.boot.autoconfigure.cache.CacheType.<init>() not found.
Warning: Method org.springframework.boot.autoconfigure.http.HttpMessageConverters.close() not found.
Warning: Method org.springframework.boot.autoconfigure.http.HttpMessageConverters.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration$StandardJackson2ObjectMapperBuilderCustomizer.close() not found.
Warning: Method org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration$Jackson2ObjectMapperBuilderCustomizerConfiguration$StandardJackson2ObjectMapperBuilderCustomizer.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer.close() not found.
Warning: Method org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.ssl.SslPropertiesBundleRegistrar.close() not found.
Warning: Method org.springframework.boot.autoconfigure.ssl.SslPropertiesBundleRegistrar.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers.close() not found.
Warning: Method org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.format.WebConversionService.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.format.WebConversionService.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration$LocaleCharsetMappingsCustomizer.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration$LocaleCharsetMappingsCustomizer.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.WelcomePageHandlerMapping.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.WelcomePageHandlerMapping.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.WelcomePageNotAcceptableHandlerMapping.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.WelcomePageNotAcceptableHandlerMapping.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$ErrorPageCustomizer.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$ErrorPageCustomizer.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$PreserveErrorControllerTargetClassPostProcessor.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$PreserveErrorControllerTargetClassPostProcessor.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$StaticView.close() not found.
Warning: Method org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration$StaticView.shutdown() not found.
Warning: Method org.springframework.boot.autoconfigure.websocket.servlet.TomcatWebSocketServletWebServerCustomizer.close() not found.
Warning: Method org.springframework.boot.autoconfigure.websocket.servlet.TomcatWebSocketServletWebServerCustomizer.shutdown() not found.
Warning: Method org.springframework.boot.availability.ApplicationAvailabilityBean.close() not found.
Warning: Method org.springframework.boot.availability.ApplicationAvailabilityBean.forPayload(Consumer) not found.
Warning: Method org.springframework.boot.availability.ApplicationAvailabilityBean.shutdown() not found.
Warning: Method org.springframework.boot.context.config.ConfigDataNotFoundAction.<init>() not found.
Warning: Method org.springframework.boot.jackson.JsonComponentModule.close() not found.
Warning: Method org.springframework.boot.jackson.JsonComponentModule.shutdown() not found.
Warning: Method org.springframework.boot.jackson.JsonMixinModule.close() not found.
Warning: Method org.springframework.boot.jackson.JsonMixinModule.shutdown() not found.
Warning: Method org.springframework.boot.jackson.JsonMixinModuleEntries.close() not found.
Warning: Method org.springframework.boot.jackson.JsonMixinModuleEntries.shutdown() not found.
Warning: Could not resolve class org.springframework.boot.logging.java.JavaLoggingSystem.Factory for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.boot.logging.java.JavaLoggingSystem.Factory.
Warning: Could not resolve class org.springframework.boot.logging.log4j2.Log4J2LoggingSystem.Factory for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.boot.logging.log4j2.Log4J2LoggingSystem.Factory.
Warning: Could not resolve class org.springframework.boot.logging.logback.LogbackLoggingSystem.Factory for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.boot.logging.logback.LogbackLoggingSystem.Factory.
Warning: Method org.springframework.boot.ssl.DefaultSslBundleRegistry.close() not found.
Warning: Method org.springframework.boot.ssl.DefaultSslBundleRegistry.shutdown() not found.
Warning: Method org.springframework.boot.task.TaskExecutorBuilder.close() not found.
Warning: Method org.springframework.boot.task.TaskExecutorBuilder.shutdown() not found.
Warning: Method org.springframework.boot.task.TaskSchedulerBuilder.close() not found.
Warning: Method org.springframework.boot.task.TaskSchedulerBuilder.shutdown() not found.
Warning: Method org.springframework.boot.type.classreading.ConcurrentReferenceCachingMetadataReaderFactory.toMetadataReaderFactory() not found.
Warning: Method org.springframework.boot.web.client.RestTemplateBuilder.close() not found.
Warning: Method org.springframework.boot.web.client.RestTemplateBuilder.shutdown() not found.
Warning: Method org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory.close() not found.
Warning: Method org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory.shutdown() not found.
Warning: Method org.springframework.boot.web.servlet.error.DefaultErrorAttributes.close() not found.
Warning: Method org.springframework.boot.web.servlet.error.DefaultErrorAttributes.shutdown() not found.
Warning: Method org.springframework.context.support.DefaultLifecycleProcessor.close() not found.
Warning: Method org.springframework.context.support.DefaultLifecycleProcessor.shutdown() not found.
Warning: Method org.springframework.context.support.PropertySourcesPlaceholderConfigurer.close() not found.
Warning: Method org.springframework.context.support.PropertySourcesPlaceholderConfigurer.shutdown() not found.
Warning: Method org.springframework.core.type.classreading.MetadataReaderFactory.<init>(ConcurrentReferenceCachingMetadataReaderFactory) not found.
Warning: Method org.springframework.core.type.classreading.MetadataReaderFactory.from(ConcurrentReferenceCachingMetadataReaderFactory) not found.
Warning: Method org.springframework.core.type.classreading.MetadataReaderFactory.of(ConcurrentReferenceCachingMetadataReaderFactory) not found.
Warning: Method org.springframework.core.type.classreading.MetadataReaderFactory.valueOf(ConcurrentReferenceCachingMetadataReaderFactory) not found.
Warning: Method org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor.close() not found.
Warning: Method org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor.shutdown() not found.
Warning: Could not resolve class org.springframework.data.cassandra.ReactiveSession for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.cassandra.ReactiveSession.
Warning: Could not resolve class org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate.
Warning: Could not resolve class org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient.
Warning: Could not resolve class org.springframework.data.elasticsearch.repository.ElasticsearchRepository for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.elasticsearch.repository.ElasticsearchRepository.
Warning: Could not resolve class org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration.
Warning: Could not resolve class org.springframework.data.jpa.repository.JpaRepository for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.jpa.repository.JpaRepository.
Warning: Could not resolve class org.springframework.data.ldap.repository.LdapRepository for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.ldap.repository.LdapRepository.
Warning: Could not resolve class org.springframework.data.r2dbc.core.R2dbcEntityTemplate for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.r2dbc.core.R2dbcEntityTemplate.
Warning: Could not resolve class org.springframework.data.redis.connection.RedisConnectionFactory for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.redis.connection.RedisConnectionFactory.
Warning: Could not resolve class org.springframework.data.redis.core.RedisOperations for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.redis.core.RedisOperations.
Warning: Could not resolve class org.springframework.data.redis.repository.configuration.EnableRedisRepositories for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.redis.repository.configuration.EnableRedisRepositories.
Warning: Could not resolve class org.springframework.data.rest.webmvc.alps.AlpsJsonHttpMessageConverter for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.rest.webmvc.alps.AlpsJsonHttpMessageConverter.
Warning: Could not resolve class org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration.
Warning: Could not resolve class org.springframework.data.web.PageableHandlerMethodArgumentResolver for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.data.web.PageableHandlerMethodArgumentResolver.
Warning: Could not resolve class org.springframework.graphql.test.tester$HttpGraphQlTester for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.graphql.test.tester$HttpGraphQlTester.
Warning: Could not resolve class org.springframework.graphql.test.tester.HttpGraphQlTester for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.graphql.test.tester.HttpGraphQlTester.
Warning: Could not resolve class org.springframework.hateoas.EntityModel for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.hateoas.EntityModel.
Warning: Could not resolve class org.springframework.hateoas.server.mvc.TypeConstrainedMappingJackson2HttpMessageConverter for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.hateoas.server.mvc.TypeConstrainedMappingJackson2HttpMessageConverter.
Warning: Method org.springframework.http.converter.StringHttpMessageConverter.close() not found.
Warning: Method org.springframework.http.converter.StringHttpMessageConverter.shutdown() not found.
Warning: Method org.springframework.http.converter.json.MappingJackson2HttpMessageConverter.close() not found.
Warning: Method org.springframework.http.converter.json.MappingJackson2HttpMessageConverter.shutdown() not found.
Warning: Could not resolve class org.springframework.integration.config.EnableIntegration for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.integration.config.EnableIntegration.
Warning: Method org.springframework.jdbc.core.JdbcTemplate.close() not found.
Warning: Method org.springframework.jdbc.core.JdbcTemplate.shutdown() not found.
Warning: Method org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate.close() not found.
Warning: Method org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate.shutdown() not found.
Warning: Method org.springframework.jdbc.support.JdbcTransactionManager.close() not found.
Warning: Method org.springframework.jdbc.support.JdbcTransactionManager.shutdown() not found.
Warning: Could not resolve class org.springframework.jms.core.JmsTemplate for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.jms.core.JmsTemplate.
Warning: Could not resolve class org.springframework.kafka.core.KafkaTemplate for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.kafka.core.KafkaTemplate.
Warning: Could not resolve class org.springframework.ldap.core.ContextSource for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.ldap.core.ContextSource.
Warning: Could not resolve class org.springframework.mail.javamail.JavaMailSenderImpl for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.mail.javamail.JavaMailSenderImpl.
Warning: Could not resolve class org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.
Warning: Could not resolve class org.springframework.oxm.Marshaller for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.oxm.Marshaller.
Warning: Could not resolve class org.springframework.r2dbc.connection.R2dbcTransactionManager for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.r2dbc.connection.R2dbcTransactionManager.
Warning: Could not resolve class org.springframework.r2dbc.connection.init.DatabasePopulator for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.r2dbc.connection.init.DatabasePopulator.
Warning: Could not resolve class org.springframework.restdocs$ManualRestDocumentation for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.restdocs$ManualRestDocumentation.
Warning: Could not resolve class org.springframework.restdocs.ManualRestDocumentation for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.restdocs.ManualRestDocumentation.
Warning: Could not resolve class org.springframework.security.authentication.AuthenticationManager for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.authentication.AuthenticationManager.
Warning: Could not resolve class org.springframework.security.authentication.DefaultAuthenticationEventPublisher for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.authentication.DefaultAuthenticationEventPublisher.
Warning: Could not resolve class org.springframework.security.authentication.ReactiveAuthenticationManager for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.authentication.ReactiveAuthenticationManager.
Warning: Could not resolve class org.springframework.security.config.annotation.web.configuration.EnableWebSecurity for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.config.annotation.web.configuration.EnableWebSecurity.
Warning: Could not resolve class org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity.
Warning: Could not resolve class org.springframework.security.config.http.SessionCreationPolicy for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.config.http.SessionCreationPolicy.
Warning: Could not resolve class org.springframework.security.oauth2.server.authorization.OAuth2Authorization for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.oauth2.server.authorization.OAuth2Authorization.
Warning: Could not resolve class org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken.
Warning: Could not resolve class org.springframework.security.rsocket.core.SecuritySocketAcceptorInterceptor for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.rsocket.core.SecuritySocketAcceptorInterceptor.
Warning: Could not resolve class org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository.
Warning: Could not resolve class org.springframework.session.Session for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.session.Session.
Warning: Method org.springframework.transaction.annotation.AnnotationTransactionAttributeSource.close() not found.
Warning: Method org.springframework.transaction.annotation.AnnotationTransactionAttributeSource.shutdown() not found.
Warning: Method org.springframework.transaction.event.TransactionalEventListenerFactory.close() not found.
Warning: Method org.springframework.transaction.event.TransactionalEventListenerFactory.shutdown() not found.
Warning: Method org.springframework.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor.close() not found.
Warning: Method org.springframework.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor.shutdown() not found.
Warning: Method org.springframework.transaction.interceptor.TransactionInterceptor.close() not found.
Warning: Method org.springframework.transaction.interceptor.TransactionInterceptor.shutdown() not found.
Warning: Method org.springframework.transaction.support.TransactionTemplate.close() not found.
Warning: Method org.springframework.transaction.support.TransactionTemplate.shutdown() not found.
Warning: Method org.springframework.transaction.support.TransactionTemplate.withDefaults() not found.
Warning: Method org.springframework.transaction.support.TransactionTemplate.withoutTransaction() not found.
Warning: Method org.springframework.util.AntPathMatcher.close() not found.
Warning: Method org.springframework.util.AntPathMatcher.shutdown() not found.
Warning: Method org.springframework.web.accept.ContentNegotiationManager.close() not found.
Warning: Method org.springframework.web.accept.ContentNegotiationManager.shutdown() not found.
Warning: Method org.springframework.web.method.support.CompositeUriComponentsContributor.close() not found.
Warning: Method org.springframework.web.method.support.CompositeUriComponentsContributor.shutdown() not found.
Warning: Method org.springframework.web.multipart.support.StandardServletMultipartResolver.close() not found.
Warning: Method org.springframework.web.multipart.support.StandardServletMultipartResolver.shutdown() not found.
Warning: Could not resolve class org.springframework.web.reactive$DispatcherHandler for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.web.reactive$DispatcherHandler.
Warning: Could not resolve class org.springframework.web.reactive.DispatcherHandler for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.web.reactive.DispatcherHandler.
Warning: Could not resolve class org.springframework.web.reactive.HandlerResult for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.web.reactive.HandlerResult.
Warning: Could not resolve class org.springframework.web.reactive.config.WebFluxConfigurer for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.web.reactive.config.WebFluxConfigurer.
Warning: Could not resolve class org.springframework.web.reactive.function.client$WebClient for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.web.reactive.function.client$WebClient.
Warning: Could not resolve class org.springframework.web.reactive.function.client.WebClient for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.web.reactive.function.client.WebClient.
Warning: Method org.springframework.web.servlet.DispatcherServlet.close() not found.
Warning: Method org.springframework.web.servlet.DispatcherServlet.shutdown() not found.
Warning: Method org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport$NoOpValidator.close() not found.
Warning: Method org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport$NoOpValidator.shutdown() not found.
Warning: Method org.springframework.web.servlet.function.support.HandlerFunctionAdapter.close() not found.
Warning: Method org.springframework.web.servlet.function.support.HandlerFunctionAdapter.shutdown() not found.
Warning: Method org.springframework.web.servlet.function.support.RouterFunctionMapping.close() not found.
Warning: Method org.springframework.web.servlet.function.support.RouterFunctionMapping.shutdown() not found.
Warning: Method org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping.close() not found.
Warning: Method org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping.shutdown() not found.
Warning: Method org.springframework.web.servlet.handler.HandlerExceptionResolverComposite.close() not found.
Warning: Method org.springframework.web.servlet.handler.HandlerExceptionResolverComposite.shutdown() not found.
Warning: Method org.springframework.web.servlet.handler.SimpleUrlHandlerMapping.close() not found.
Warning: Method org.springframework.web.servlet.handler.SimpleUrlHandlerMapping.shutdown() not found.
Warning: Method org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver.close() not found.
Warning: Method org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver.shutdown() not found.
Warning: Method org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter.close() not found.
Warning: Method org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter.shutdown() not found.
Warning: Method org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter.close() not found.
Warning: Method org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter.shutdown() not found.
Warning: Method org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping.initCorsConfiguration(Object, Method, Object) not found.
Warning: Method org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping.registerHandlerMethod(Object, Method, Object) not found.
Warning: Method org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping.registerMapping(Object, Object, Method) not found.
Warning: Method org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.close() not found.
Warning: Method org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.shutdown() not found.
Warning: Method org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping.close() not found.
Warning: Method org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping.shutdown() not found.
Warning: Method org.springframework.web.servlet.resource.ResourceUrlProvider.close() not found.
Warning: Method org.springframework.web.servlet.resource.ResourceUrlProvider.forPayload(Consumer) not found.
Warning: Method org.springframework.web.servlet.resource.ResourceUrlProvider.shutdown() not found.
Warning: Method org.springframework.web.servlet.support.SessionFlashMapManager.close() not found.
Warning: Method org.springframework.web.servlet.support.SessionFlashMapManager.shutdown() not found.
Warning: Method org.springframework.web.servlet.theme.FixedThemeResolver.close() not found.
Warning: Method org.springframework.web.servlet.theme.FixedThemeResolver.shutdown() not found.
Warning: Method org.springframework.web.servlet.view.BeanNameViewResolver.close() not found.
Warning: Method org.springframework.web.servlet.view.BeanNameViewResolver.shutdown() not found.
Warning: Method org.springframework.web.servlet.view.ContentNegotiatingViewResolver.close() not found.
Warning: Method org.springframework.web.servlet.view.ContentNegotiatingViewResolver.shutdown() not found.
Warning: Method org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator.close() not found.
Warning: Method org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator.shutdown() not found.
Warning: Method org.springframework.web.servlet.view.InternalResourceViewResolver.close() not found.
Warning: Method org.springframework.web.servlet.view.InternalResourceViewResolver.shutdown() not found.
Warning: Method org.springframework.web.servlet.view.ViewResolverComposite.close() not found.
Warning: Method org.springframework.web.servlet.view.ViewResolverComposite.shutdown() not found.
Warning: Could not resolve class org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer.
Warning: Method org.springframework.web.util.UrlPathHelper.close() not found.
Warning: Method org.springframework.web.util.UrlPathHelper.shutdown() not found.
Warning: Method org.springframework.web.util.pattern.PathPatternParser.close() not found.
Warning: Method org.springframework.web.util.pattern.PathPatternParser.shutdown() not found.
Warning: Could not resolve class org.springframework.ws.test.client$MockWebServiceServer for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.ws.test.client$MockWebServiceServer.
Warning: Could not resolve class org.springframework.ws.test.client.MockWebServiceServer for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.ws.test.client.MockWebServiceServer.
Warning: Could not resolve class org.springframework.ws.transport.http.MessageDispatcherServlet for reflection configuration. Reason: java.lang.ClassNotFoundException: org.springframework.ws.transport.http.MessageDispatcherServlet.
Warning: Could not resolve class org.thymeleaf.spring6$SpringTemplateEngine for reflection configuration. Reason: java.lang.ClassNotFoundException: org.thymeleaf.spring6$SpringTemplateEngine.
Warning: Could not resolve class org.thymeleaf.spring6.SpringTemplateEngine for reflection configuration. Reason: java.lang.ClassNotFoundException: org.thymeleaf.spring6.SpringTemplateEngine.
Warning: Could not resolve class org.webjars$WebJarAssetLocator for reflection configuration. Reason: java.lang.ClassNotFoundException: org.webjars$WebJarAssetLocator.
Warning: Could not resolve class org.webjars.WebJarAssetLocator for reflection configuration. Reason: java.lang.ClassNotFoundException: org.webjars.WebJarAssetLocator.
Warning: Could not resolve class org.xnio.SslClientAuthMode for reflection configuration. Reason: java.lang.ClassNotFoundException: org.xnio.SslClientAuthMode.
Warning: Could not resolve class reactor.core.publisher$Flux for reflection configuration. Reason: java.lang.ClassNotFoundException: reactor.core.publisher$Flux.
Warning: Could not resolve class reactor.core.publisher.Flux for reflection configuration. Reason: java.lang.ClassNotFoundException: reactor.core.publisher.Flux.
Warning: Could not resolve class reactor.core.publisher.Mono for reflection configuration. Reason: java.lang.ClassNotFoundException: reactor.core.publisher.Mono.
Warning: Could not resolve class reactor.netty.http.server.HttpServer for reflection configuration. Reason: java.lang.ClassNotFoundException: reactor.netty.http.server.HttpServer.
Warning: Could not resolve class reactor.tools.agent$ReactorDebugAgent for reflection configuration. Reason: java.lang.ClassNotFoundException: reactor.tools.agent$ReactorDebugAgent.
Warning: Could not resolve class reactor.tools.agent.ReactorDebugAgent for reflection configuration. Reason: java.lang.ClassNotFoundException: reactor.tools.agent.ReactorDebugAgent.
Warning: Method sun.security.provider.NativePRNG.<init>(SecureRandomParameters) not found.
[1/8] Initializing...                                                                                    (8.5s @ 0.27GB)
 Java version: 17.0.8+7, vendor version: GraalVM CE 17.0.8+7.1
 Graal compiler: optimization level: 2, target machine: x86-64-v3
 C compiler: gcc (linux, x86_64, 11.4.0)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
 2 user-specific feature(s)
 - org.graalvm.junit.platform.JUnitPlatformFeature
 - org.springframework.aot.nativex.feature.PreComputeFieldFeature
[junit-platform-native] Running in 'test listener' mode using files matching pattern [junit-platform-unique-ids*] found in folder [/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/test-results/test/testlist] and its subfolders.
Field org.springframework.cglib.core.AbstractClassGenerator#inNativeImage set to true at build time
Field org.apache.commons.logging.LogAdapter#log4jSpiPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#log4jSlf4jProviderPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#slf4jSpiPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#slf4jApiPresent set to true at build time
Field org.springframework.format.support.DefaultFormattingConversionService#jsr354Present set to false at build time
Field org.springframework.boot.autoconfigure.web.format.WebConversionService#JSR_354_PRESENT set to false at build time
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
Field org.springframework.core.NativeDetector#inNativeImage set to true at build time
Field org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener#MOCK_SERVER_PRESENT set to false at build time
Field org.springframework.aot.AotDetector#inNativeImage set to true at build time
Field org.springframework.context.annotation.CommonAnnotationBeanPostProcessor#jndiPresent set to true at build time
Field org.springframework.http.converter.json.Jackson2ObjectMapperBuilder#jackson2XmlPresent set to false at build time
Field org.springframework.transaction.annotation.AnnotationTransactionAttributeSource#jta12Present set to false at build time
Field org.springframework.transaction.annotation.AnnotationTransactionAttributeSource#ejb3Present set to false at build time
Field org.springframework.transaction.interceptor.TransactionAspectSupport#vavrPresent set to false at build time
Field org.springframework.transaction.interceptor.TransactionAspectSupport#reactiveStreamsPresent set to false at build time
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
Field org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener#MOCKITO_IS_PRESENT set to true at build time
Field org.springframework.boot.test.web.reactive.server.WebTestClientContextCustomizerFactory#webClientPresent set to false at build time
Field org.springframework.web.servlet.view.InternalResourceViewResolver#jstlPresent set to false at build time
Field org.springframework.core.KotlinDetector#kotlinPresent set to false at build time
Field org.springframework.core.KotlinDetector#kotlinReflectPresent set to false at build time
Field org.springframework.test.context.web.socket.MockServerContainerContextCustomizerFactory#webSocketPresent set to true at build time
Field org.springframework.web.context.request.RequestContextHolder#jsfPresent set to false at build time
Field org.springframework.web.context.support.StandardServletEnvironment#jndiPresent set to true at build time
Field org.springframework.boot.logging.java.JavaLoggingSystem$Factory#PRESENT set to true at build time
Field org.springframework.boot.logging.log4j2.Log4J2LoggingSystem$Factory#PRESENT set to false at build time
Field org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory#PRESENT set to true at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jaxb2Present set to true at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2Present set to true at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2XmlPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2SmilePresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#gsonPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jsonbPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationCborPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationProtobufPresent set to false at build time
Field org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener#REST_DOCS_PRESENT set to false at build time
Field org.springframework.web.context.support.WebApplicationContextUtils#jsfPresent set to false at build time
Field org.springframework.boot.web.client.ClientHttpRequestFactories#APACHE_HTTP_CLIENT_PRESENT set to false at build time
Field org.springframework.boot.web.client.ClientHttpRequestFactories#OKHTTP_CLIENT_PRESENT set to false at build time
Field org.springframework.web.servlet.support.RequestContext#jstlPresent set to false at build time
Field org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator#USER_PROVIDED_ERROR_CODES_FILE_PRESENT set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#reactorPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#rxjava3Present set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#kotlinCoroutinesPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#mutinyPresent set to false at build time
Field org.springframework.context.annotation.AnnotationConfigUtils#jakartaAnnotationsPresent set to true at build time
Field org.springframework.context.annotation.AnnotationConfigUtils#jsr250Present set to false at build time
Field org.springframework.context.annotation.AnnotationConfigUtils#jpaPresent set to false at build time
SLF4J: No SLF4J providers were found.
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See https://www.slf4j.org/codes.html#noProviders for further details.
Field org.springframework.web.servlet.mvc.method.annotation.ReactiveTypeHandler#isContextPropagationPresent set to false at build time
Field org.springframework.boot.logging.logback.LogbackLoggingSystemProperties#JBOSS_LOGGING_PRESENT set to false at build time
Field org.springframework.context.event.ApplicationListenerMethodAdapter#reactiveStreamsPresent set to false at build time
[2/8] Performing analysis...  [******]                                                                  (71.4s @ 3.10GB)
  21,441 (90.51%) of 23,688 types reachable
  36,237 (68.62%) of 52,808 fields reachable
 105,860 (63.37%) of 167,050 methods reachable
   6,674 types, 1,290 fields, and 8,535 methods registered for reflection
      66 types,    82 fields, and    55 methods registered for JNI access
       4 native libraries: dl, pthread, rt, z
[3/8] Building universe...                                                                               (9.3s @ 3.11GB)
[4/8] Parsing methods...      [***]                                                                      (5.4s @ 2.25GB)
[5/8] Inlining methods...     [***]                                                                      (2.8s @ 3.34GB)
[6/8] Compiling methods...    [********]                                                                (61.8s @ 1.76GB)
[7/8] Layouting methods...    [***]                                                                      (8.8s @ 1.88GB)
[8/8] Creating image...       [***]                                                                      (8.5s @ 2.86GB)
  52.77MB (52.35%) for code area:    70,897 compilation units
  44.07MB (43.72%) for image heap:  466,348 objects and 364 resources
   3.96MB ( 3.93%) for other data
 100.79MB in total
------------------------------------------------------------------------------------------------------------------------
Top 10 origins of code area:                                Top 10 object types in image heap:
  13.95MB java.base                                           11.70MB byte[] for code metadata
   5.62MB h2-2.1.214.jar                                       5.20MB java.lang.Class
   4.53MB tomcat-embed-core-10.1.11.jar                        4.63MB java.lang.String
   3.81MB java.xml                                             4.19MB byte[] for java.lang.String
   2.09MB jackson-databind-2.15.2.jar                          3.64MB byte[] for general heap data
   1.92MB aspectjweaver-1.9.19.jar                             2.57MB byte[] for embedded resources
   1.78MB spring-core-6.0.11.jar                               1.80MB com.oracle.svm.core.hub.DynamicHubCompanion
   1.48MB svm.jar (Native Image)                               1.42MB byte[] for reflection metadata
   1.44MB spring-boot-3.1.2.jar                              957.09kB java.lang.String[]
   1.06MB spring-beans-6.0.11.jar                            771.60kB c.o.svm.core.hub.DynamicHub$ReflectionMetadata
  14.60MB for 108 more packages                                6.60MB for 4241 more object types
------------------------------------------------------------------------------------------------------------------------
Recommendations:
 HEAP: Set max heap for improved and more predictable memory usage.
 CPU:  Enable more CPU features with '-march=native' for improved performance.
------------------------------------------------------------------------------------------------------------------------
                        17.0s (9.5% of total time) in 98 GCs | Peak RSS: 5.03GB | CPU load: 4.76
------------------------------------------------------------------------------------------------------------------------
Produced artifacts:
 /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/native/nativeTestCompile/spring-plugin-graalvm-agent-test-tests (executable)
========================================================================================================================
Finished generating 'spring-plugin-graalvm-agent-test-tests' in 2m 57s.
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

2023-08-14T22:41:57.828+08:00  INFO 6497 --- [           main] com.lingh.AddRemoveDatasourceTest        : Starting AOT-processed AddRemoveDatasourceTest using Java 17.0.8 with PID 6497 (started by linghengqian in /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test)
2023-08-14T22:41:57.828+08:00  INFO 6497 --- [           main] com.lingh.AddRemoveDatasourceTest        : No active profile set, falling back to 1 default profile: "default"
2023-08-14T22:41:57.862+08:00  INFO 6497 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 0 (http)
2023-08-14T22:41:57.863+08:00  INFO 6497 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-08-14T22:41:57.863+08:00  INFO 6497 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.11]
2023-08-14T22:41:57.880+08:00  INFO 6497 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-08-14T22:41:57.880+08:00  INFO 6497 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 52 ms
2023-08-14T22:41:57.983+08:00  WARN 6497 --- [           main] w.s.c.ServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource': null
2023-08-14T22:41:57.984+08:00  INFO 6497 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2023-08-14T22:41:57.985+08:00 ERROR 6497 --- [           main] o.s.boot.SpringApplication               : Application run failed

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



============================
CONDITIONS EVALUATION REPORT
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:436) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:137) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1406) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
        at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:545) ~[na:na]
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:137) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]
============================


Positive matches:
-----------------

    None


Negative matches:
-----------------
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContextForAotRuntime(SpringBootContextLoader.java:119) ~[spring-plugin-graalvm-agent-test-tests:3.1.2]

    None
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInAotMode(DefaultCacheAwareContextLoaderDelegate.java:217) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:116) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]


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
Exclusions:
-----------

    None


Unconditional classes:
----------------------

    None



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

2023-08-14T22:41:57.987+08:00 ERROR 6497 --- [           main] o.s.test.context.TestContextManager      : Caught exception while allowing TestExecutionListener [org.springframework.test.context.support.DependencyInjectionTestExecutionListener] to prepare test instance [com.lingh.AddRemoveDatasourceTest@1180f615]

java.lang.IllegalStateException: Failed to load ApplicationContext for [AotMergedContextConfiguration@6dbced testClass = com.lingh.AddRemoveDatasourceTest, contextInitializerClass = com.lingh.AddRemoveDatasourceTest__TestContext001_ApplicationContextInitializer, original = [WebMergedContextConfiguration@77dcf2df testClass = com.lingh.AddRemoveDatasourceTest, locations = [], classes = [com.lingh.AddRemoveDatasourceApplication], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = ["org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true", "server.port=0"], contextCustomizers = [org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@7cf7e6f8, org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@1019330, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@258b18a6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@5e8a2a43, org.springframework.boot.test.context.SpringBootTestAnnotation@2de570b8], resourceBasePath = "src/main/webapp", contextLoader = org.springframework.boot.test.context.SpringBootContextLoader, parent = null]]
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
        at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127) ~[spring-plugin-graalvm-agent-test-tests:6.0.11]
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
    => java.lang.IllegalStateException: Failed to load ApplicationContext for [AotMergedContextConfiguration@6dbced testClass = com.lingh.AddRemoveDatasourceTest, contextInitializerClass = com.lingh.AddRemoveDatasourceTest__TestContext001_ApplicationContextInitializer, original = [WebMergedContextConfiguration@77dcf2df testClass = com.lingh.AddRemoveDatasourceTest, locations = [], classes = [com.lingh.AddRemoveDatasourceApplication], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = ["org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true", "server.port=0"], contextCustomizers = [org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@7cf7e6f8, org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@1019330, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@258b18a6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@5e8a2a43, org.springframework.boot.test.context.SpringBootTestAnnotation@2de570b8], resourceBasePath = "src/main/webapp", contextLoader = org.springframework.boot.test.context.SpringBootContextLoader, parent = null]]
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

Test run finished after 175 ms
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

BUILD FAILED in 3m 7s
11 actionable tasks: 11 executed
```