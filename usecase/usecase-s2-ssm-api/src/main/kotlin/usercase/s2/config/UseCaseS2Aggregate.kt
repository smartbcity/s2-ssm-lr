package usercase.s2.config

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import s2.spring.automate.executor.S2AutomateExecutorSpring
import s2.spring.automate.ssm.S2SsmConfigurerAdapter
import ssm.chaincode.dsl.model.Agent
import ssm.sdk.sign.extention.loadFromFile
import usercase.s2.automate.S2UseCase
import usercase.s2.automate.UseCaseId
import usercase.s2.automate.UseCaseState
import usercase.s2.entity.UseCaseEntity

@Configuration
class UserCaseS2SpringDataConfigurerAdapter(
	private val useCaseS2Aggregate: UseCaseS2Aggregate
): S2SsmConfigurerAdapter<UseCaseState, UseCaseId, UseCaseEntity, UseCaseS2Aggregate>() {

	override fun automate() = S2UseCase
	override fun entityType() =  UseCaseEntity::class.java
	override fun executor() = useCaseS2Aggregate
	override fun chaincodeUri() = "chaincode:sandbox:ssm"
	override fun signerAgent() = loadFromFile("ssm-admin","user/ssm-admin")
}

@Service
class UseCaseS2Aggregate : S2AutomateExecutorSpring<UseCaseState, UseCaseId, UseCaseEntity>()
