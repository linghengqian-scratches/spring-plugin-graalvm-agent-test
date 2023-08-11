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

- Error Log at `./gradlew -Pagent clean test` step.
```shell
$ ./gradlew clean test

> Task :processTestAot
18:14:18.701 [main] INFO org.springframework.test.context.aot.TestClassScanner -- Scanning for Spring test classes in all packages in classpath roots [/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test, /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/resources/test]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.2)

2023-08-11T18:14:19.500+08:00  INFO 4301 --- [           main] com.lingh.AddRemoveDatasourceTest        : Starting AddRemoveDatasourceTest using Java 17.0.8 with PID 4301 (/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test started by linghengqian in /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test)
2023-08-11T18:14:19.503+08:00  INFO 4301 --- [           main] com.lingh.AddRemoveDatasourceTest        : No active profile set, falling back to 1 default profile: "default"

> Task :compileAotTestJava
注: /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/generated/aotTestSources/org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration__TestContext001_BeanDefinitions.java使用盖了已过时的 API。
注: 有关详细信息, 请使用 -Xlint:deprecation 重新编译。

> Task :test
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

AddRemoveDatasourceTest > testAddAndRemoveDataSource() FAILED
    com.baomidou.dynamic.datasource.exception.ErrorCreateDataSourceException at AddRemoveDatasourceTest.java:37
2023-08-11T18:14:25.944+08:00  INFO 4353 --- [ionShutdownHook] c.b.d.d.DynamicRoutingDataSource         : dynamic-datasource start closing ....
2023-08-11T18:14:25.944+08:00  INFO 4353 --- [ionShutdownHook] c.b.d.d.DynamicRoutingDataSource         : dynamic-datasource all closed success,bye

1 test completed, 1 failed

> Task :test FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':test'.
> There were failing tests. See the report at: file:///home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/reports/tests/test/index.html

* Try:
> Run with --scan to get full insights.

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.2.1/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD FAILED in 8s
7 actionable tasks: 7 executed

```