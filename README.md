# For https://github.com/oracle/graalvm-reachability-metadata/pull/154 and https://github.com/spring-projects/spring-boot/issues/34841

- Steps to reproduce on Ubuntu 22.04.2
```shell
sudo apt install unzip zip curl sed -y
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 22.3.1.r17-grl
sdk use java 22.3.1.r17-grl
gu install native-image
sudo apt-get install build-essential libz-dev zlib1g-dev -y

git clone git@github.com:linghengqian/spring-plugin-graalvm-agent-test.git
cd ./spring-plugin-graalvm-agent-test/
./gradlew -Pagent clean test
./gradlew metadataCopy --task test
./gradlew clean nativeTest
```

- Error Log at `./gradlew -Pagent clean test` step.
```shell
$ ./gradlew clean test

> Task :processTestAot
17:04:19.397 [main] INFO org.springframework.test.context.aot.TestClassScanner -- Scanning for Spring test classes in all packages in classpath roots [/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test, /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/resources/test]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.0)

2023-06-07T17:04:20.190+08:00  INFO 11027 --- [           main] com.lingh.AddRemoveDatasourceTest        : Starting AddRemoveDatasourceTest using Java 17.0.7 with PID 11027 (/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test started by linghengqian in /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test)
2023-06-07T17:04:20.192+08:00  INFO 11027 --- [           main] com.lingh.AddRemoveDatasourceTest        : No active profile set, falling back to 1 default profile: "default"

> Task :compileAotTestJava
注: /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/generated/aotTestSources/org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration__TestContext001_BeanDefinitions.jaa使用或覆盖了已过时的 API。
注: 有关详细信息, 请使用 -Xlint:deprecation 重新编译。

> Task :test
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
2023-06-07T17:04:27.385+08:00  INFO 11064 --- [ionShutdownHook] c.b.d.d.DynamicRoutingDataSource         : dynamic-datasource start closing ....
2023-06-07T17:04:27.385+08:00  INFO 11064 --- [ionShutdownHook] c.b.d.d.DynamicRoutingDataSource         : dynamic-datasource all closed success,bye

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

See https://docs.gradle.org/8.0/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 10s
7 actionable tasks: 7 executed
```

```shell
$ ./gradlew -Pagent clean test

> Task :processTestAot
[native-image-plugin] Instrumenting task with the native-image-agent: processTestAot
[0.106s][warning][jni,resolve] Re-registering of platform native method: jdk.internal.misc.Unsafe.allocateInstance(Ljava/lang/Class;)Ljava/lang/Object; from code in a different classloader
17:05:04.089 [main] INFO org.springframework.test.context.aot.TestClassScanner -- Scanning for Spring test classes in all packages in classpath roots [/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test, /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/resources/test]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.0)

2023-06-07T17:05:06.823+08:00 ERROR 11458 --- [           main] o.s.boot.SpringApplication               : Application run failed

java.lang.IllegalArgumentException: Could not find class [com.lingh.AddRemoveDatasourceTest__ApplicationContextInitializer]
        at org.springframework.util.ClassUtils.resolveClassName(ClassUtils.java:334) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.context.aot.AotApplicationContextInitializer.instantiateInitializer(AotApplicationContextInitializer.java:80) ~[spring-context-6.0.9.jar:6.0.9]
        at org.springframework.context.aot.AotApplicationContextInitializer.initialize(AotApplicationContextInitializer.java:71) ~[spring-context-6.0.9.jar:6.0.9]
        at org.springframework.context.aot.AotApplicationContextInitializer.lambda$forInitializerClasses$0(AotApplicationContextInitializer.java:61) ~[spring-context-6.0.9.jar:6.0.9]
        at org.springframework.boot.SpringApplication.applyInitializers(SpringApplication.java:606) ~[spring-boot-3.1.0.jar:3.1.0]
        at org.springframework.boot.SpringApplication.prepareContext(SpringApplication.java:386) ~[spring-boot-3.1.0.jar:3.1.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:310) ~[spring-boot-3.1.0.jar:3.1.0]
        at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:137) ~[spring-boot-test-3.1.0.jar:3.1.0]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1405) ~[spring-boot-3.1.0.jar:3.1.0]
        at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:545) ~[spring-boot-test-3.1.0.jar:3.1.0]
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:137) ~[spring-boot-test-3.1.0.jar:3.1.0]
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContextForAotProcessing(SpringBootContextLoader.java:113) ~[spring-boot-test-3.1.0.jar:3.1.0]
        at org.springframework.test.context.aot.TestContextAotGenerator.loadContextForAotProcessing(TestContextAotGenerator.java:263) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestContextAotGenerator.processAheadOfTime(TestContextAotGenerator.java:232) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestContextAotGenerator.lambda$processAheadOfTime$4(TestContextAotGenerator.java:204) ~[spring-test-6.0.9.jar:6.0.9]
        at java.base/java.util.LinkedHashMap.forEach(LinkedHashMap.java:721) ~[na:na]
        at org.springframework.util.MultiValueMapAdapter.forEach(MultiValueMapAdapter.java:179) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestContextAotGenerator.processAheadOfTime(TestContextAotGenerator.java:196) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestContextAotGenerator.processAheadOfTime(TestContextAotGenerator.java:158) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestAotProcessor.performAotProcessing(TestAotProcessor.java:91) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestAotProcessor.doProcess(TestAotProcessor.java:72) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestAotProcessor.doProcess(TestAotProcessor.java:39) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.context.aot.AbstractAotProcessor.process(AbstractAotProcessor.java:82) ~[spring-context-6.0.9.jar:6.0.9]
        at org.springframework.boot.test.context.SpringBootTestAotProcessor.main(SpringBootTestAotProcessor.java:63) ~[spring-boot-test-3.1.0.jar:3.1.0]
Caused by: java.lang.ClassNotFoundException: com.lingh.AddRemoveDatasourceTest__ApplicationContextInitializer
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641) ~[na:na]
        at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188) ~[na:na]
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:520) ~[na:na]
        at java.base/java.lang.Class.forName0(Native Method) ~[na:na]
        at java.base/java.lang.Class.forName(Class.java:467) ~[na:na]
        at org.springframework.util.ClassUtils.forName(ClassUtils.java:284) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.util.ClassUtils.resolveClassName(ClassUtils.java:324) ~[spring-core-6.0.9.jar:6.0.9]
        ... 25 common frames omitted

2023-06-07T17:05:06.842+08:00  WARN 11458 --- [           main] o.s.t.c.aot.TestContextAotGenerator      : Failed to generate AOT artifacts for test classes [com.lingh.AddRemoveDatasourceTest]

org.springframework.test.context.aot.TestContextAotException: Failed to load ApplicationContext for AOT processing for test class [com.lingh.AddRemoveDatasourceTest]
        at org.springframework.test.context.aot.TestContextAotGenerator.loadContextForAotProcessing(TestContextAotGenerator.java:272) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestContextAotGenerator.processAheadOfTime(TestContextAotGenerator.java:232) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestContextAotGenerator.lambda$processAheadOfTime$4(TestContextAotGenerator.java:204) ~[spring-test-6.0.9.jar:6.0.9]
        at java.base/java.util.LinkedHashMap.forEach(LinkedHashMap.java:721) ~[na:na]
        at org.springframework.util.MultiValueMapAdapter.forEach(MultiValueMapAdapter.java:179) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestContextAotGenerator.processAheadOfTime(TestContextAotGenerator.java:196) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestContextAotGenerator.processAheadOfTime(TestContextAotGenerator.java:158) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestAotProcessor.performAotProcessing(TestAotProcessor.java:91) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestAotProcessor.doProcess(TestAotProcessor.java:72) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.test.context.aot.TestAotProcessor.doProcess(TestAotProcessor.java:39) ~[spring-test-6.0.9.jar:6.0.9]
        at org.springframework.context.aot.AbstractAotProcessor.process(AbstractAotProcessor.java:82) ~[spring-context-6.0.9.jar:6.0.9]
        at org.springframework.boot.test.context.SpringBootTestAotProcessor.main(SpringBootTestAotProcessor.java:63) ~[spring-boot-test-3.1.0.jar:3.1.0]
Caused by: java.lang.IllegalArgumentException: Could not find class [com.lingh.AddRemoveDatasourceTest__ApplicationContextInitializer]
        at org.springframework.util.ClassUtils.resolveClassName(ClassUtils.java:334) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.context.aot.AotApplicationContextInitializer.instantiateInitializer(AotApplicationContextInitializer.java:80) ~[spring-context-6.0.9.jar:6.0.9]
        at org.springframework.context.aot.AotApplicationContextInitializer.initialize(AotApplicationContextInitializer.java:71) ~[spring-context-6.0.9.jar:6.0.9]
        at org.springframework.context.aot.AotApplicationContextInitializer.lambda$forInitializerClasses$0(AotApplicationContextInitializer.java:61) ~[spring-context-6.0.9.jar:6.0.9]
        at org.springframework.boot.SpringApplication.applyInitializers(SpringApplication.java:606) ~[spring-boot-3.1.0.jar:3.1.0]
        at org.springframework.boot.SpringApplication.prepareContext(SpringApplication.java:386) ~[spring-boot-3.1.0.jar:3.1.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:310) ~[spring-boot-3.1.0.jar:3.1.0]
        at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:137) ~[spring-boot-test-3.1.0.jar:3.1.0]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1405) ~[spring-boot-3.1.0.jar:3.1.0]
        at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:545) ~[spring-boot-test-3.1.0.jar:3.1.0]
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:137) ~[spring-boot-test-3.1.0.jar:3.1.0]
        at org.springframework.boot.test.context.SpringBootContextLoader.loadContextForAotProcessing(SpringBootContextLoader.java:113) ~[spring-boot-test-3.1.0.jar:3.1.0]
        at org.springframework.test.context.aot.TestContextAotGenerator.loadContextForAotProcessing(TestContextAotGenerator.java:263) ~[spring-test-6.0.9.jar:6.0.9]
        ... 11 common frames omitted
Caused by: java.lang.ClassNotFoundException: com.lingh.AddRemoveDatasourceTest__ApplicationContextInitializer
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641) ~[na:na]
        at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188) ~[na:na]
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:520) ~[na:na]
        at java.base/java.lang.Class.forName0(Native Method) ~[na:na]
        at java.base/java.lang.Class.forName(Class.java:467) ~[na:na]
        at org.springframework.util.ClassUtils.forName(ClassUtils.java:284) ~[spring-core-6.0.9.jar:6.0.9]
        at org.springframework.util.ClassUtils.resolveClassName(ClassUtils.java:324) ~[spring-core-6.0.9.jar:6.0.9]
        ... 25 common frames omitted


> Task :test
[native-image-plugin] Instrumenting task with the native-image-agent: test
[0.098s][warning][jni,resolve] Re-registering of platform native method: jdk.internal.misc.Unsafe.allocateInstance(Ljava/lang/Class;)Ljava/lang/Object; from code in a different classloader

AddRemoveDatasourceTest > testAddAndRemoveDataSource() FAILED
    java.lang.IllegalStateException at DefaultCacheAwareContextLoaderDelegate.java:142
        Caused by: java.lang.IllegalArgumentException at ClassUtils.java:334
            Caused by: java.lang.ClassNotFoundException at BuiltinClassLoader.java:641

1 test completed, 1 failed

> Task :test FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':test'.
> There were failing tests. See the report at: file:///home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/reports/tests/test/index.html

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Get more help at https://help.gradle.org

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

See https://docs.gradle.org/8.0/userguide/command_line_interface.html#sec:command_line_warnings

BUILD FAILED in 11s
7 actionable tasks: 7 executed
```