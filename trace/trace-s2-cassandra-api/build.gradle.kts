plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
}

dependencies {
	implementation(project(":trace:trace-s2"))
	api("city.smartb.s2:s2-spring-boot-starter-automate-data:${Versions.s2}")
	api("org.springframework.boot:spring-boot-starter-data-cassandra:${Versions.springBoot}")
}
