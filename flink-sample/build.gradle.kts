// Root Project中build.gradle
plugins {
    id("java")
    id("application")
}

// 确保 java 插件被正确应用
apply(plugin = "java")
apply(plugin = "application")

application {
    mainClass.set("com.iokays.sample.WordCount") // 替换为你的主类全限定名
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

// 配置 JAR 打包任务
tasks.jar {
// 设置 JAR 文件名
    archiveBaseName.set("my-app")
    archiveVersion.set("")
    archiveClassifier.set("")

// 设置 Main-Class
    manifest {
        attributes(
            "Main-Class" to "com.iokays.sample.WordCount", // 替换为你的主类
            "Class-Path" to "." // 可选，如果依赖外置
        )
    }

// 将所有依赖项打包进 JAR（fat JAR）
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

// 避免重复的 META-INF 文件（如 LICENSE、NOTICE）
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {

    implementation("io.vavr:vavr:0.10.4")
    implementation("com.google.guava:guava:33.3.1-jre")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("org.apache.commons:commons-lang3:3.18.0")
    implementation("commons-codec:commons-codec:1.16.0")
    implementation("commons-io:commons-io:2.19.0")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.apache.flink:flink-java:1.20.2")
    implementation("org.apache.flink:flink-streaming-java:2.1.0")
    implementation("org.apache.flink:flink-clients:2.1.0")
    implementation("org.apache.flink:flink-runtime-web:2.1.0")
    implementation("org.apache.flink:flink-connector-files:2.1.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testImplementation("org.awaitility:awaitility:4.3.0")
    testImplementation("net.datafaker:datafaker:2.4.1")

    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("org.jsoup:jsoup:1.17.2")
}

repositories {
    maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
    maven { url = uri("https://repo.maven.apache.org/maven2") }
    maven { url = uri("https://repository.jboss.org/nexus/content/groups/public-jboss") }
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
    google()
    mavenCentral()
}
