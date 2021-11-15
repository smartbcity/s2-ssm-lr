package trace.s2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import s2.automate.core.context.AutomateContext
import s2.automate.core.persist.AutomatePersister
import trace.s2.automate.S2
import trace.s2.automate.TraceId
import trace.s2.automate.TraceLogCommand
import trace.s2.automate.TraceState
import trace.s2.entity.TraceEntity

@RestController
@RequestMapping("traces")
class TraceEndpoint(
	private val persiter: AutomatePersister<TraceState, TraceId, TraceEntity>,
	private val aggregate: TraceAggregateService
) {

	@GetMapping("/{id}")
	suspend fun trace(@PathVariable id: TraceId): TraceEntity? {
		return load(id)
	}

	@PostMapping
	suspend fun log(@RequestBody cmd: TraceLogCommand){
		load(cmd.id)?.let {
			aggregate.log(cmd)
		} ?: aggregate.create(cmd)
	}

	private suspend fun load(traceId: TraceId): TraceEntity? {
		return try {
			persiter.load(AutomateContext(S2.traceAutomate, emptyList()), traceId)
		} catch (e: Exception) {
			e.printStackTrace()
			null
		}
	}

}