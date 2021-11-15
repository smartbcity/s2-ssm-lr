plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
}

dependencies {
	api("org.springframework.boot:spring-boot-starter-webflux:${Versions.springBoot}")
	api("org.springframework.data:spring-data-cassandra:3.3.0")
	api("city.smartb.s2:s2-spring-boot-starter-automate:${Versions.s2}")
}