plugins {
    id("java")
    id("org.springframework.boot") version "3.2.12"
    // Use the Spring Dependency Management plugin to manage Spring Cloud Alibaba dependencies.
    id("io.spring.dependency-management") version "1.1.7"
}

extra["springCloudAlibabaVersion"] = "2023.0.3.3"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${property("springCloudAlibabaVersion")}")
    }
}

tasks.test {
    useJUnitPlatform()
}
