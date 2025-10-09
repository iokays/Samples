plugins {
    id("java")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

repositories {
    mavenCentral()
}
