// Root Project中build.gradle
plugins {
    id("java")
}

// 确保 java 插件被正确应用
apply(plugin = "java")

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

// 只是用例测试，所有故意使用最新的版本(+)，在真实的开发中，请使用正确固定的大版本。
dependencies {

    // platform: 管理依赖的版本
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.5.7"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2025.0.0"))

    implementation(platform("com.fasterxml.jackson:jackson-bom:2.20.0"))
    implementation(platform("com.google.guava:guava-bom:33.5.0-jre"))
    implementation(platform("org.slf4j:slf4j-bom:2.1.0-alpha1"))

    implementation("io.vavr:vavr:1.0.0-alpha-4")
    implementation("com.google.guava:guava")
    implementation("org.apache.commons:commons-collections4:4.5.0")
    implementation("org.apache.commons:commons-lang3:3.19.0")
    implementation("commons-codec:commons-codec:1.19.0")
    implementation("commons-io:commons-io:2.20.0")

    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    testCompileOnly("org.projectlombok:lombok:1.18.42")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.42")

    testImplementation("org.junit.jupiter:junit-jupiter:6.0.0")
    testImplementation("org.awaitility:awaitility:4.3.0")
    testImplementation("net.datafaker:datafaker:2.5.3")

    // 添加JUnit 5引擎以运行测试
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
    maven { url = uri("https://repo.maven.apache.org/maven2") }
    maven { url = uri("https://repository.jboss.org/nexus/content/groups/public-jboss") }
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
    google()
}
