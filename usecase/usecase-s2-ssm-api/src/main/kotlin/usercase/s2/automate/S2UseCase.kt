package usercase.s2.automate

import kotlinx.serialization.Serializable
import s2.dsl.automate.*
import s2.dsl.automate.builder.s2

typealias UseCaseId = String

val S2UseCase = s2<UseCaseId, UseCaseState> {
	name = "S2UseCase"
	init<CreateCommand> {
		to = UseCaseState.Draft
		role = UseCaseRole.Tracer
		cmd = CreateCommand::class
	}
	transaction<UseDatasetCommand> {
		from = UseCaseState.Draft
		to = UseCaseState.Draft
		role = UseCaseRole.Tracer
		cmd = UseDatasetCommand::class
	}
	transaction<ApplyLicenceCommand> {
		from = UseCaseState.Draft
		to = UseCaseState.Draft
		role = UseCaseRole.Tracer
		cmd = ApplyLicenceCommand::class
	}
	transaction<PublishCommand> {
		from = UseCaseState.Draft
		to = UseCaseState.Public
		role = UseCaseRole.Tracer
		cmd = PublishCommand::class
	}
	transaction<AddConsentCommand> {
		from = UseCaseState.Public
		to = UseCaseState.Public
		role = UseCaseRole.Tracer
		cmd = AddConsentCommand::class
	}
}

open class UseCaseRole(open val name: String): S2Role {
	object Tracer: UseCaseRole("Tracer")
	override fun toString(): String = name
}

@Serializable
open class UseCaseState(override var position: Int): S2State {
	object Draft: UseCaseState(0)
	object Public: UseCaseState(1)
}

interface UseCaseInitCommand: S2InitCommand
interface UseCaseCommand: S2Command<UseCaseId>