package trace.s2.cassandra

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories


@EntityScan("trace.s2")
@EnableReactiveCassandraRepositories("trace.s2")
@SpringBootApplication(scanBasePackages = ["trace.s2"])
class TraceS2CassandraApplication

fun main(args: Array<String>) {
	runApplication<TraceS2CassandraApplication>(*args)
}
