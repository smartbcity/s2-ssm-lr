package usercase.s2.error

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import usercase.s2.automate.UseCaseState

class InvalidTransitionException(
	msg: String?,
): ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid transition $msg")
