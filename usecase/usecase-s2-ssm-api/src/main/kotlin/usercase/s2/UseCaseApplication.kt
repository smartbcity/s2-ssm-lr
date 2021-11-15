package usercase.s2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration
import org.springframework.boot.runApplication
import ssm.sdk.sign.extention.loadFromFile

@SpringBootApplication(scanBasePackages = ["usercase.s2"])
class UseCaseApplication

fun main(args: Array<String>) {
	runApplication<UseCaseApplication>(*args)
}
