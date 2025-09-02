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

    implementation("org.springframework.ai:spring-ai-starter-model-deepseek:1.0.0")

    implementation("org.springframework:spring-webflux")
    implementation("org.springframework.boot:spring-boot-starter")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testImplementation("org.awaitility:awaitility:4.3.0")
    testImplementation("net.datafaker:datafaker:2.4.1")

    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

repositories {
    mavenLocal()
    maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
    maven { url = uri("https://repo.maven.apache.org/maven2") }
    maven { url = uri("https://repository.jboss.org/nexus/content/groups/public-jboss") }
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
    google()
    mavenCentral()
}
