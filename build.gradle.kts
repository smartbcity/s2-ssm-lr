import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version PluginVersions.kotlin apply false
	kotlin("plugin.spring") version PluginVersions.kotlin apply false

	id("org.springframework.boot") version PluginVersions.springBoot apply false

}

allprojects {
	group = "city.smartb.lrtzc"
	version = System.getenv("VERSION") ?: "latest"
	repositories {
		mavenCentral()
		maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
	}
}

subprojects {
	plugins.withType(JavaPlugin::class.java).whenPluginAdded {
		tasks.withType<KotlinCompile>().configureEach {
			println("Configuring $name in project ${project.name}...")
			kotlinOptions {
				freeCompilerArgs = listOf("-Xjsr305=strict")
				jvmTarget = "11"
			}
		}
		tasks.withType<JavaCompile> {
			sourceCompatibility = JavaVersion.VERSION_11.toString()
			targetCompatibility = JavaVersion.VERSION_11.toString()
		}

		tasks.withType<Test> {
			useJUnitPlatform()
		}

		dependencies {
			val implementation by configurations
			val testImplementation by configurations

			implementation(kotlin("reflect"))
			Dependencies.jvm.junit.forEach { testImplementation(it) }
		}
	}
}
