package org.osc.oss.confoo.web.api

import org.osc.oss.confoo.core.conference.Conference
import org.osc.oss.confoo.core.conference.ConferenceManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant


@RestController
@RequestMapping("/api/conference")
class ConferenceController(private val conferenceManager: ConferenceManager) {

    @GetMapping("/list")
    fun listConferences(model: Model,
                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-yy HH:mm:ss") from: Instant,
                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-yy HH:mm:ss") to: Instant,
                        @RequestParam(required = false) page: Int?
    ): Page<Conference> {
        return  conferenceManager.getConferenceList(from, to, PageRequest.of(page ?: 0, 10))
    }
}