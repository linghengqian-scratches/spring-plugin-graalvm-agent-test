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
> Task :processTestAot
[native-image-plugin] Instrumenting task with the native-image-agent: processTestAot
[0.214s][warning][jni,resolve] Re-registering of platform native method: jdk.internal.misc.Unsafe.allocateInstance(Ljava/lang/Class;)Ljava/lang/Object; from code in a different classloader
20:42:10.489 [main] INFO org.springframework.test.context.aot.TestClassScanner -- Scanning for Spring test classes in all packages in classpath roots [/home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/classes/java/test, /home/linghengqian/TwinklingLiftWorks/git/public/spring-plugin-graalvm-agent-test/build/resources/test]
Exception in thread "main" java.lang.IllegalStateException: Cannot perform AOT processing during AOT run-time execution
        at org.springframework.util.Assert.state(Assert.java:76)
        at org.springframework.test.context.aot.TestContextAotGenerator.processAheadOfTime(TestContextAotGenerator.java:126)
        at org.springframework.test.context.aot.TestAotProcessor.performAotProcessing(TestAotProcessor.java:91)
        at org.springframework.test.context.aot.TestAotProcessor.doProcess(TestAotProcessor.java:72)
        at org.springframework.test.context.aot.TestAotProcessor.doProcess(TestAotProcessor.java:39)
        at org.springframework.context.aot.AbstractAotProcessor.process(AbstractAotProcessor.java:82)
        at org.springframework.boot.test.context.SpringBootTestAotProcessor.main(SpringBootTestAotProcessor.java:63)

> Task :processTestAot FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':processTestAot'.
> Process 'command '/home/linghengqian/.sdkman/candidates/java/22.3.1.r17-grl/bin/java'' finished with non-zero exit value 1

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 11s
5 actionable tasks: 4 executed, 1 up-to-date

```