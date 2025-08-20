// Root Project中build.gradle
plugins {
    id("java")
}

// 确保 java 插件被正确应用
apply(plugin = "java")

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview")
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("--enable-preview")
}

// Gradle 9.0 计划移除 自动加载测试框架依赖（如 JUnit、TestNG）的功能
tasks.named<Test>("test") {
    useJUnitPlatform()
}
tasks.withType<Test>().configureEach {
    jvmArgs("--enable-preview")
}

dependencies {
    implementation("io.vavr:vavr:+")
    implementation("com.google.guava:guava:+")
    implementation("org.apache.commons:commons-collections4:+")
    implementation("org.apache.commons:commons-lang3:+")
    implementation("commons-codec:commons-codec:+")
    implementation("commons-io:commons-io:+")
    compileOnly("org.projectlombok:lombok:+")
    annotationProcessor("org.projectlombok:lombok:+")

    // If using JUnit Jupiter
    // https://docs.gradle.org/8.12/userguide/upgrading_version_8.html#test_framework_implementation_dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:+")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testCompileOnly("org.projectlombok:lombok:+")
    testAnnotationProcessor("org.projectlombok:lombok:+")

    testImplementation("org.awaitility:awaitility:+")
    testImplementation("net.datafaker:datafaker:+")
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
