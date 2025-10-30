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
    implementation(platform("org.springframework.boot:spring-boot-dependencies:+"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:+"))

    implementation("io.vavr:vavr:+") //0.10.7
    implementation("com.google.guava:guava:+")
    implementation("org.apache.commons:commons-collections4:+")
    implementation("org.apache.commons:commons-lang3:+")
    implementation("commons-codec:commons-codec:+")
    implementation("commons-io:commons-io:+")

    compileOnly("org.projectlombok:lombok:+")
    annotationProcessor("org.projectlombok:lombok:+")

    testCompileOnly("org.projectlombok:lombok:+")
    testAnnotationProcessor("org.projectlombok:lombok:+")

    testImplementation("org.junit.jupiter:junit-jupiter:+")
    testImplementation("org.awaitility:awaitility:+")
    testImplementation("net.datafaker:datafaker:+")
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
