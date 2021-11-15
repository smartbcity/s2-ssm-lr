package trace.s2

import org.springframework.stereotype.Service
import s2.spring.automate.executor.S2AutomateExecutorSpring
import trace.s2.automate.TraceId
import trace.s2.automate.TraceState
import trace.s2.entity.TraceEntity

@Service
class TraceS2AutomateExecutor: S2AutomateExecutorSpring<TraceState, TraceId, TraceEntity>()