plugins {
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-webflux:${Versions.springBoot}")
    api("city.smartb.s2:s2-spring-boot-starter-automate-ssm:${Versions.s2}")
    api("city.smartb.ssm:ssm-chaincode-spring-boot-starter:${Versions.ssm}")
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootBuildImage> {
    imageName = "smartbcity/lrtzc-dataset:${this.project.version}"
}