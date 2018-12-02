package org.osc.oss.confoo.web.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.osc.oss.confoo.core.conference.Conference
import org.osc.oss.confoo.core.conference.ConferenceManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@Api
@RestController
class ConferenceController(private val conferenceManager: ConferenceManager) {


    @ApiOperation(value = "List all conferences starting between given dates. Results are paginated.")
    @GetMapping("/api/conference/list")
    fun listConferences(model: Model,
                        @ApiParam(value = "Date, format: YYYY-MM-DD ", required = true) @RequestParam from: LocalDate,
                        @ApiParam(value = "Date, format: YYYY-MM-DD ", required = true) @RequestParam to: LocalDate,
                        @ApiParam(value = "Page  number (optional)", required = false) @RequestParam(required = false) page: Int?
    ): Page<Conference> {
        return  conferenceManager.getConferenceList(from, to, PageRequest.of(page ?: 0, 10))
    }


}