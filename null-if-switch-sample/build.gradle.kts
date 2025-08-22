// Root Project中build.gradle
plugins {
    id("java")
}

// 确保 java 插件被正确应用
apply(plugin = "java")

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
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testImplementation("io.vavr:vavr:0.10.4")
    testImplementation("com.google.guava:guava:33.3.1-jre")
    testImplementation("org.apache.commons:commons-collections4:4.4")
    testImplementation("org.apache.commons:commons-lang3:3.18.0")
    testImplementation("commons-codec:commons-codec:1.16.0")
    testImplementation("commons-io:commons-io:2.19.0")


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
