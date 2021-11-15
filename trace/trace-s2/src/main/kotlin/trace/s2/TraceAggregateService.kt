package trace.s2

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import trace.s2.automate.LoggedEvent
import trace.s2.automate.TraceLogCommand
import trace.s2.entity.TraceEntity
import java.time.LocalDateTime

@Service
class TraceAggregateService(
	private val aggregate: TraceS2AutomateExecutor,
	private val objectMapper: ObjectMapper
) {

	suspend fun create(cmd: TraceLogCommand) = aggregate.createWithEvent(cmd) {
		val time = LocalDateTime.now()
		TraceEntity(
			id = cmd.id,
			versions = mutableMapOf(
				time to objectMapper.writeValueAsString(cmd.payload)
			)
		) to LoggedEvent(
			id = cmd.id,
			time = time
		)
	}

	suspend fun log(cmd: TraceLogCommand) = aggregate.doTransition(cmd) {
		val time = LocalDateTime.now()
		this.versions[time] = objectMapper.writeValueAsString(cmd.payload)
		this to LoggedEvent(
			id = cmd.id,
			time = time
		)
	}

}

