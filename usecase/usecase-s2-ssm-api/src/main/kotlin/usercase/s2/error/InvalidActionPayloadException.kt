package usercase.s2.error

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class InvalidActionPayloadException(
	actionName: String,
	payload: String
): ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid payload[${payload}] for action: $actionName ")