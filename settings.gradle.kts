pluginManagement {
	repositories {
		gradlePluginPortal()
	}
}

rootProject.name = "lrtzc-poc"

include (
	"usecase:usecase-s2-ssm-api"
)
include (
	"trace:trace-s2-cassandra-api",
	"trace:trace-s2-ssm-api",
	"trace:trace-s2"
)
