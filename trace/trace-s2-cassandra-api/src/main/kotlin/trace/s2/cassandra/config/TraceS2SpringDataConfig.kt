package trace.s2.cassandra.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import s2.automate.core.persist.AutomatePersister
import s2.spring.automate.data.S2SpringDataConfigurerAdapter
import trace.s2.TraceS2AutomateExecutor
import trace.s2.automate.S2
import trace.s2.automate.TraceId
import trace.s2.automate.TraceState
import trace.s2.cassandra.TraceRepository
import trace.s2.entity.TraceEntity

@Configuration
class TraceS2SpringDataConfig(
	repository: TraceRepository,
	private val traceS2AutomateExecutor: TraceS2AutomateExecutor
): S2SpringDataConfigurerAdapter<TraceState, TraceId, TraceEntity, TraceS2AutomateExecutor>(repository) {

	override fun automate() = S2.traceAutomate


	override fun executor(): TraceS2AutomateExecutor = traceS2AutomateExecutor

	@Bean
	override fun aggregateRepository(): AutomatePersister<TraceState, TraceId, TraceEntity> {
		return super.aggregateRepository()
	}
}
