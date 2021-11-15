object PluginVersions {
	const val kotlin = "1.6.0-RC2"
	const val springBoot = "2.5.3"
}

object Versions {
	const val springBoot = PluginVersions.springBoot
	const val coroutines = "1.5.2"

	const val junit = "5.8.1"
	const val assertj = "3.21.0"

	const val f2 = "experimental-SNAPSHOT"
	const val s2 = "experimental-lr-SNAPSHOT"
	const val ssm = "experimental-lr-SNAPSHOT"
}


object Dependencies {
	object jvm {
//		val coroutines = arrayOf(
//			"org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}",
//			"org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.coroutines}",
//			"org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${Versions.coroutines}",
//		)
		val junit = arrayOf(
			"org.junit.jupiter:junit-jupiter:${Versions.junit}",
			"org.junit.jupiter:junit-jupiter-api:${Versions.junit}",
			"org.assertj:assertj-core:${Versions.assertj}"
		)
	}
}