package usercase.s2

import org.springframework.stereotype.Service
import usercase.s2.automate.AddConsentCommand
import usercase.s2.automate.ApplyLicenceCommand
import usercase.s2.automate.CreateCommand
import usercase.s2.automate.Event
import usercase.s2.automate.PublishCommand
import usercase.s2.automate.UseCaseState
import usercase.s2.automate.UseDatasetCommand
import usercase.s2.config.UseCaseS2Aggregate
import usercase.s2.entity.UseCaseEntity
import java.time.LocalDateTime
import java.util.UUID

@Service
class UseCaseAggregateService(
	private val aggregate: UseCaseS2Aggregate,
) {

	suspend fun create(cmd: CreateCommand) = aggregate.createWithEvent(cmd) {
		val time = LocalDateTime.now()
		UseCaseEntity(
			id = cmd.id,
			created = cmd,
		) to Event(cmd.id, time)
	}

	suspend fun useDataset(cmd: UseDatasetCommand) = aggregate.doTransition(cmd) {
		val time = LocalDateTime.now()
		withDataSet.add(cmd)
		this to Event(cmd.id, time)
	}

	suspend fun applyLicence(cmd: ApplyLicenceCommand) = aggregate.doTransition(cmd) {
		val time = LocalDateTime.now()
		withLicence = cmd
		this to Event(cmd.id, time)
	}

	suspend fun publish(cmd: PublishCommand) = aggregate.doTransition(cmd) {
		val time = LocalDateTime.now()
		this.published = cmd
		this to Event(cmd.id, time)
	}

	suspend fun addConsent(cmd: AddConsentCommand) = aggregate.doTransition(cmd) {
		val time = LocalDateTime.now()
		withConsent.add(cmd)
		this to Event(cmd.id, time)
	}
}
