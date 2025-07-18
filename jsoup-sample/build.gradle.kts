// Root Project中build.gradle
plugins {
	id("java")
	id("org.jetbrains.kotlin.jvm") version "2.1.20" apply (false)
	id("org.jetbrains.kotlin.plugin.spring") version "2.1.20" apply (false)
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

	implementation("io.vavr:vavr:0.10.4")
	implementation("com.google.guava:guava:33.3.1-jre")
	implementation("org.apache.commons:commons-collections4:4.4")
	implementation("org.apache.commons:commons-lang3:3.18.0")
	implementation("commons-codec:commons-codec:1.16.0")
	implementation("commons-io:commons-io:2.19.0")

	implementation("org.jsoup:jsoup:1.21.1")

	implementation("org.slf4j:slf4j-api:+")
	implementation("ch.qos.logback:logback-core:+")
	implementation("ch.qos.logback:logback-classic:+")

	testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
	testImplementation("org.awaitility:awaitility:4.3.0")
	testImplementation("net.datafaker:datafaker:2.4.1")
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
