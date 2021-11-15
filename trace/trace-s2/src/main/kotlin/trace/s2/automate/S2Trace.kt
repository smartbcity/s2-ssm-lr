package trace.s2.automate

import s2.dsl.automate.*
import s2.dsl.automate.builder.s2
import java.time.LocalDateTime

typealias TraceId = String

object S2 {
	val traceAutomate = s2<TraceId, TraceState> {
		name = "S2Trace"
		init<TraceLogCommand> {
			to = TraceState.Exist
			role = TraceRole.Tracer
			cmd = TraceLogCommand::class
		}
		transaction<TraceLogCommand> {
			from = TraceState.Exist
			to = TraceState.Exist
			role = TraceRole.Tracer
			cmd = TraceLogCommand::class
		}
	}
}

open class TraceRole(open val name: String): S2Role {
	object Tracer: TraceRole("Tracer")
	override fun toString(): String = name
}

open class TraceState(override var position: Int): S2State {
	object Exist: TraceState(0)
}

data class TraceLogCommand(
	override val id: TraceId,
	val payload: Map<String, String>
): S2InitCommand, S2Command<TraceId>

data class LoggedEvent(
	val id: TraceId,
	val time: LocalDateTime
)