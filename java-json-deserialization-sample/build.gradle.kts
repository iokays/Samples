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
    implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.0-RC1"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2025.1.0-M4"))

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

    // Junit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.3")
    testImplementation("org.junit.platform:junit-platform-launcher")

    testImplementation("org.awaitility:awaitility:4.3.0")
    testImplementation("net.datafaker:datafaker:2.5.3")

    // JSON Deserialization Libraries
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.2")
    implementation("com.google.code.gson:gson:2.12.1")
    implementation("org.json:json:20250107")
    implementation("jakarta.json:jakarta.json-api:2.1.3")
    implementation("org.eclipse.parsson:parsson:1.1.5")
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

tasks.withType<Test> {
    useJUnitPlatform()
}
