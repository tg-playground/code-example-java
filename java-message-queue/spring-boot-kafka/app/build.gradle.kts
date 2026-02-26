plugins {
    java
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.taogen"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-kafka")
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    testCompileOnly("org.projectlombok:lombok:1.18.42")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.42")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
