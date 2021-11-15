plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":trace:trace-s2"))
    api("city.smartb.f2:f2-dsl-function:${Versions.f2}")
    api("city.smartb.s2:s2-spring-boot-starter-automate-ssm:${Versions.s2}")

    implementation("city.smartb.ssm:ssm-data-spring-boot-starter:experimental-SNAPSHOT")
    implementation("city.smartb.ssm:ssm-tx-spring-boot-starter:experimental-SNAPSHOT")
}
