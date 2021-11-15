package usercase.s2

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import f2.dsl.fnc.invoke
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import s2.automate.core.error.AutomateException
import ssm.chaincode.dsl.query.SsmGetSessionLogsQuery
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryFunction
import ssm.chaincode.dsl.query.SsmGetSessionLogsQueryResult
import usercase.s2.automate.AddConsentCommand
import usercase.s2.automate.ApplyLicenceCommand
import usercase.s2.automate.CreateCommand
import usercase.s2.automate.Event
import usercase.s2.automate.PublishCommand
import usercase.s2.automate.UseCaseId
import usercase.s2.automate.UseDatasetCommand
import usercase.s2.error.InvalidActionPayloadException
import usercase.s2.error.InvalidTransitionException

@RestController
@RequestMapping("usecases")
class UseCaseEndpoint(
	private val ssmGetSessionLogsQueryFunction: SsmGetSessionLogsQueryFunction,
	private val objectMapper: ObjectMapper,
	private val useCaseAggregateService: UseCaseAggregateService
) {

	@GetMapping("/{id}")
	suspend fun trace(@PathVariable id: UseCaseId): SsmGetSessionLogsQueryResult {
		return ssmGetSessionLogsQueryFunction.invoke(
			SsmGetSessionLogsQuery(
				sessionName = id,
			)
		)
	}

	@PostMapping("/create")
	suspend fun create(@RequestBody body: CreateCommand): Event = handleError {
		useCaseAggregateService.create(body)
	}

	@PostMapping("/applylicence")
	suspend fun applyLicence(@RequestBody body: ApplyLicenceCommand): Event = handleError {
		useCaseAggregateService.applyLicence(body)
	}
	@PostMapping("/usedataset")
	suspend fun create(@RequestBody body: UseDatasetCommand): Event = handleError {
		useCaseAggregateService.useDataset(body)
	}
	@PostMapping("/publish")
	suspend fun create(@RequestBody body: PublishCommand): Event = handleError {
		useCaseAggregateService.publish(body)
	}
	@PostMapping("/addconsent")
	suspend fun create(@RequestBody body: AddConsentCommand): Event = handleError {
		useCaseAggregateService.addConsent(body)
	}

	suspend fun handleError(exec: suspend () -> Event): Event {
		try {
			return exec()
		} catch (e: AutomateException) {
			throw InvalidTransitionException(e.message)
		}
	}
}
