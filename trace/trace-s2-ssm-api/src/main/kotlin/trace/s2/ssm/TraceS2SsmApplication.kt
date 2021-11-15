package trace.s2.ssm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
	scanBasePackages = ["trace.s2"],
	exclude = [CassandraAutoConfiguration::class]
)
class TraceS2SsmApplication

fun main(args: Array<String>) {
	runApplication<TraceS2SsmApplication>(*args)
}
