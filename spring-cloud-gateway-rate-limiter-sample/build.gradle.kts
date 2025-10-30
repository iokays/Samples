// Root Project中build.gradle
plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.20" apply (false)
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20" apply (false)
    id("org.springframework.boot") version "3.5.6" apply (false)
    id("io.spring.dependency-management") version "1.1.7" apply (false)
}

apply(plugin = "java")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

group = "com.example"
version = "0.0.1-SNAPSHOT"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.3.2")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    compileOnly("org.projectlombok:lombok:+") // 1.18.42
    annotationProcessor("org.projectlombok:lombok:+") // 1.18.42
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test:3.8.0-RC1")
}


repositories {
    mavenLocal()
    maven { url = uri("https://repo.maven.apache.org/maven2") }
    maven { url = uri("https://repository.jboss.org/nexus/content/groups/public-jboss") }
    maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
    google()
    mavenCentral()
}
