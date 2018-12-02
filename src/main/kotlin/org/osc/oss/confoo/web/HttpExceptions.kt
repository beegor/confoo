package org.osc.oss.confoo.web

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN, reason = "You are not authorized to do this")
class ForbiddenException : RuntimeException()


@ResponseStatus(HttpStatus.NOT_FOUND, reason = "Resource not found")
class NoSuchResourceException() : RuntimeException()