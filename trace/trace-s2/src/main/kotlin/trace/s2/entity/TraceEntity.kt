package trace.s2.entity

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.Frozen
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import s2.dsl.automate.model.WithS2Id
import s2.dsl.automate.model.WithS2State
import trace.s2.automate.TraceId
import trace.s2.automate.TraceState
import java.io.Serializable
import java.time.LocalDateTime

@Table
data class TraceEntity(
	@PrimaryKey
	val id: TraceId,
	@Frozen
	val versions: MutableMap<LocalDateTime, String>
) : WithS2State<TraceState>, WithS2Id<TraceId>, Serializable {
	override fun s2State() = TraceState.Exist
	override fun s2Id(): TraceId = id
}