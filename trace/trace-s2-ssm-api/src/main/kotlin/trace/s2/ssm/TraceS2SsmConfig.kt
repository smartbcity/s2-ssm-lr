package trace.s2.ssm

import trace.s2.entity.TraceEntity
import org.springframework.context.annotation.Configuration
import s2.spring.automate.executor.S2AutomateExecutorSpring
import trace.s2.automate.TraceId
import trace.s2.automate.TraceState
import s2.spring.automate.ssm.S2SsmConfigurerAdapter
import ssm.chaincode.dsl.model.Agent
import ssm.sdk.dsl.SignerName
import ssm.sdk.sign.extention.loadFromFile
import trace.s2.TraceS2AutomateExecutor
import trace.s2.automate.S2

@Configuration
class TraceS2SsmConfig(
	private val traceS2AutomateExecutor: TraceS2AutomateExecutor
): S2SsmConfigurerAdapter<TraceState, TraceId, TraceEntity, TraceS2AutomateExecutor>() {

	override fun automate() = S2.traceAutomate
	override fun entityType() = TraceEntity::class.java
	override fun executor() = traceS2AutomateExecutor
	override fun chaincodeUri() = "chaincode:sandbox:ssm"
	override fun signerAgent() = loadFromFile("ssm-admin","user/ssm-admin")
}

