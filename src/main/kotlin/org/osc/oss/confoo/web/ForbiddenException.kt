package org.osc.oss.confoo.web

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN, reason = "You are not authorized to do this")
class ForbiddenException : RuntimeException()