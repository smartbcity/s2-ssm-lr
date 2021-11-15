package usercase.s2.entity

import s2.dsl.automate.model.WithS2Id
import s2.dsl.automate.model.WithS2State
import usercase.s2.automate.AddConsentCommand
import usercase.s2.automate.ApplyLicenceCommand
import usercase.s2.automate.CreateCommand
import usercase.s2.automate.PublishCommand
import usercase.s2.automate.UseCaseId
import usercase.s2.automate.UseCaseState
import usercase.s2.automate.UseDatasetCommand

data class UseCaseEntity(
	val id: UseCaseId,
	var created: CreateCommand,
	var withDataSet: MutableList<UseDatasetCommand> = mutableListOf(),
	var withLicence: ApplyLicenceCommand? = null,
	var published: PublishCommand? = null,
	var withConsent: MutableList<AddConsentCommand> = mutableListOf(),
) : WithS2State<UseCaseState>, WithS2Id<UseCaseId> {

	override fun s2State(): UseCaseState {
		return if(published == null) {
			UseCaseState.Draft
		} else {
			UseCaseState.Public
		}
	}

	override fun s2Id(): UseCaseId = id
}