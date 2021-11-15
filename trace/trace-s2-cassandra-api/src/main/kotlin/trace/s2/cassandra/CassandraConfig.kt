package trace.s2.cassandra

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

@EnableCassandraRepositories(basePackages = ["trace.s2"])
@Configuration
class CassandraConfig : AbstractCassandraConfiguration() {

	@Value("\${cassandra.contact-points}")
	private lateinit var contactPoint: String

	override fun getContactPoints(): String {
		return contactPoint
	}

	override fun getKeyspaceName(): String {
		return "kspace"
	}

	override fun getSchemaAction(): SchemaAction {
		return SchemaAction.RECREATE_DROP_UNUSED
	}

	override fun getKeyspaceCreations(): List<CreateKeyspaceSpecification> {
		val specification: CreateKeyspaceSpecification = CreateKeyspaceSpecification.createKeyspace(getKeyspaceName())
			.ifNotExists()
			.with(KeyspaceOption.DURABLE_WRITES, true)
			.withSimpleReplication()
		return listOf(specification)
	}


//	@Bean
//	@Throws(Exception::class)
//	fun cassandraOperations(): CassandraOperations {
//		return CassandraTemplate(session().getObject())
//	}

	override fun getEntityBasePackages(): Array<String> {
		return arrayOf("trace.s2")
	}

}