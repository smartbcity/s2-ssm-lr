package usercase.s2.automate

import java.time.LocalDateTime

typealias UserId = String
typealias LicenceId = String
typealias DataSetId = String

data class CreateCommand(
	val id: UseCaseId,
	val name: String,
	val creator: UserId
): UseCaseInitCommand

data class UseDatasetCommand(
	override val id: UseCaseId,
	val dataSetId: DataSetId
): UseCaseCommand

data class ApplyLicenceCommand(
	override val id: UseCaseId,
	val licenceId: LicenceId
): UseCaseCommand

data class PublishCommand(
	override val id: UseCaseId,
): UseCaseCommand

data class AddConsentCommand(
	override val id: UseCaseId,
	val consentOf: UserId
): UseCaseCommand

data class Event(
	val id: UseCaseId,
	val time: LocalDateTime
)