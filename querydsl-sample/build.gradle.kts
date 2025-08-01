// Root Project中build.gradle
plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.20" apply (false)
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20" apply (false)
    id("org.springframework.boot") version "3.4.4" apply (false)
    id("io.spring.dependency-management") version "1.1.7" apply (false)
}

// 确保 java 插件被正确应用
apply(plugin = "java")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

java {
    sourceCompatibility = JavaVersion.VERSION_23
    targetCompatibility = JavaVersion.VERSION_23
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview")
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("--enable-preview")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
}

dependencies {

    implementation("io.vavr:vavr:0.10.4")
    implementation("com.google.guava:guava:33.3.1-jre")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("org.apache.commons:commons-lang3:3.18.0")
    implementation("commons-codec:commons-codec:1.16.0")
    implementation("commons-io:commons-io:2.19.0")

    implementation("org.postgresql:postgresql:42.7.5")
    implementation("com.querydsl:querydsl-sql-spring:5.1.0")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    // JDBC的QueryDSL的查询类生成 [该部分的业务逻辑是抛开领域模型，面向表结构的查询，主要用于后端的简单报表，相对复杂的列表，详情的查询]
    // 生成没有任何依赖，建议放在单独的模块中.
    testImplementation("com.querydsl:querydsl-sql-codegen:5.1.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testImplementation("org.awaitility:awaitility:4.3.0")
    testImplementation("net.datafaker:datafaker:2.4.1")

    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
