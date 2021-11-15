package trace.s2.cassandra

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import trace.s2.automate.TraceId
import trace.s2.entity.TraceEntity

@Repository
interface TraceRepository: ReactiveCrudRepository<TraceEntity, TraceId>